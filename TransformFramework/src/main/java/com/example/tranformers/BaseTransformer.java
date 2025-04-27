package com.example.tranformers;

import java.util.function.Function;

/**
 * BaseTransformer interface that extends Function interface.
 * It is used to define a transformer that can transform an object of type T to an object of type R.
 * Any dependencies required for the transformation should be injected through the constructor. If it is not a base repository,
 * it will not be injected.
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
public interface BaseTransformer<T,R> extends Function<T,R> {
    TransformerType getTransformerType();
}
