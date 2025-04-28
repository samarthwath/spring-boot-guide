package com.learn.gateway.filter;

import com.learn.gateway.Config;
import com.learn.gateway.RouteValidator;
import com.learn.gateway.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GatewayAuthenticationFilter extends AbstractGatewayFilterFactory<GatewayAuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    public GatewayAuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //Header contains Authorization or not.
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing Authorization Header.");
                }
                String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeaders != null && authHeaders.startsWith("Bearer")) {
                    authHeaders = authHeaders.split(" ")[1];
                }
                try {
                    //REST Call to auth-service.
                    //Below is again not a best practice as we are adding one more network call.
                    //Also can lead to security issues. Therefore commenting below piece of code. (REST Call.)
                    //String url = "http://auth-service:8080/validate-token?token=" + authHeaders;
                    //Boolean isTokenValid = restTemplate.getForObject(url, Boolean.class);
                    boolean isTokenValid = jwtUtil.validateToken(authHeaders);
                    

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config {

    }
}
