package com.sensedia.register.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensedia.register.component.Dictionary;
import com.sensedia.register.controller.ControllerAdvice;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractControllerTest {

    protected MockMvc mockMvc;

    private ObjectMapper mapper;

    @Before
    public void setup() {
    	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:internationalization/messages_en");
        messageSource.setDefaultEncoding("UTF-8");
        ReflectionTestUtils.setField(Dictionary.class, "messageSource", messageSource);
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controllerClass())
        		.setControllerAdvice(new ControllerAdvice())
        		.build();
    }

    protected <T> T stringJsonToObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    /**
     * @return the controller to be tested (with @InjectMocks annotation)
     */
    protected abstract Object controllerClass();

}
