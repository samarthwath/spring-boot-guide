package com.learn.security.config.others;

import com.learn.security.config.service.PaymentService;
import com.learn.security.config.service.PaypallService;
import com.learn.security.config.service.StripeService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    @Bean
    @ConditionalOnProperty(
            prefix = "payment",
            name = "stripe",
            havingValue = "true"
    )
    public PaymentService stripeService() {
        return new StripeService();
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "payment",
            name = "paypall",
            havingValue = "true"
    )
    public PaymentService paypallService() {
        return new PaypallService();
    }

}
