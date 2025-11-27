package org.springframework.samples.petclinic.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class RootRestControllerTests {

    @Test
    void redirectToSwaggerUsesContextPath() throws Exception {
        RootRestController controller = new RootRestController();
        ReflectionTestUtils.setField(controller, "servletContextPath", "");

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/swagger-ui/index.html"));
    }

    @Test
    void redirectToSwaggerWithCustomContextPath() throws Exception {
        RootRestController controller = new RootRestController();
        ReflectionTestUtils.setField(controller, "servletContextPath", "/petclinic");

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/petclinic/swagger-ui/index.html"));
    }
}
