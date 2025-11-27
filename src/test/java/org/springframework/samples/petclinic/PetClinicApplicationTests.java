package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;

class PetClinicApplicationTests {

    @Test
    void mainInvokesSpringApplicationRun() {
        try (MockedStatic<SpringApplication> springApp = Mockito.mockStatic(SpringApplication.class)) {
            // Return null to avoid mocking ConfigurableApplicationContext (Byte Buddy / Java 25 compatibility)
            springApp.when(() -> SpringApplication.run(Mockito.eq(PetClinicApplication.class), Mockito.any(String[].class))).thenReturn(null);
            PetClinicApplication.main(new String[] {"--spring.main.web-application-type=none"});

            springApp.verify(() -> SpringApplication.run(Mockito.eq(PetClinicApplication.class), Mockito.any(String[].class)));
        }
    }

    @Test
    void applicationClassIsSpringBootApplication() throws Exception {
        assertNotNull(PetClinicApplication.class.getAnnotation(org.springframework.boot.autoconfigure.SpringBootApplication.class));
    }
}
