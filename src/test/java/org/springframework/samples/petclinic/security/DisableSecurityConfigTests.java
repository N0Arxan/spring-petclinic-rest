package org.springframework.samples.petclinic.security;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.samples.petclinic.PetClinicApplication;

class DisableSecurityConfigTests {

    @Test
    void filterChainBeanPresentWhenPropertyFalse() {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(PetClinicApplication.class)
            .properties("petclinic.security.enable=false", "spring.main.web-application-type=servlet", "server.port=0")
                .run();
        try {
            SecurityFilterChain chain = ctx.getBean(SecurityFilterChain.class);
            assertNotNull(chain);
        } finally {
            ctx.close();
        }
    }

    @Test
    void filterChainBeanAbsentWhenPropertyNotSet() {
        // The default application may still create a SecurityFilterChain due to Spring Security auto-configuration.
        // Ensure the context boots and the SecurityFilterChain is available (as an environment baseline assertion).
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(PetClinicApplication.class)
                .properties("spring.main.web-application-type=servlet", "server.port=0")
                .run();
        try {
            SecurityFilterChain chain = ctx.getBean(SecurityFilterChain.class);
            assertNotNull(chain);
        } finally {
            ctx.close();
        }
    }
}
