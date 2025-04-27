package com.example.tranformers;

import com.nimbusds.oauth2.sdk.TokenIntrospectionRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

public interface RestTransform {
    String getPath();
    void setOAuth2ClientHttpRequestInterceptor(OAuth2ClientHttpRequestInterceptor interceptor);
    RestClient getRestClient();
    OAuth2ClientHttpRequestInterceptor getOAuth2ClientHttpRequestInterceptor();
}