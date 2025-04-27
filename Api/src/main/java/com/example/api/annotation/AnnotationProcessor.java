package com.example.api.annotation;

import com.example.api.kafka.DynamicTopicPublisher;
import com.example.domain.Purchase;
import com.example.tranformers.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect
public class AnnotationProcessor {
    private final TaskExecutor simpleTaskExecutor = new SimpleAsyncTaskExecutor();

    private final List<BaseTransformer<?, ?>> transformers;
    private final DynamicTopicPublisher dynamicTopicPublisher;

    public AnnotationProcessor(List<BaseTransformer<?, ?>> transformers, DynamicTopicPublisher dynamicTopicPublisher) {
        this.transformers = transformers;
        this.dynamicTopicPublisher = dynamicTopicPublisher;
    }


    @Around("@annotation(com.example.api.annotation.PublishOnReturn)")
    public Object captureResult(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Method " + methodName + " called with args: " + Arrays.toString(args) + " returned: " + result);
        if(result instanceof Purchase purchase) {
            simpleTaskExecutor.execute(()->publish(purchase));
        } else  {
            System.out.println("Not a supported publisher type");
        }
        return result;
    }

    public void publish(Purchase purchase) {
        for (BaseTransformer<?, ?> transformer : transformers) {
            TransformerType transformerType = transformer.getTransformerType();
            if (transformer instanceof OnPurchaseTransform<?> onPurchaseTransform) {
                Object avroObject = onPurchaseTransform.apply(purchase);
                if (TransformerType.KAKFA.equals(transformerType)) {
                    try {
                        dynamicTopicPublisher.publish(((KafkaTransform) transformer).getTopicName(), avroObject);
                    } catch (Exception e) {
                        //Could produce to dlq if wanted
                        System.out.println(e);
                    }
                }
            }
        }
    }
}
