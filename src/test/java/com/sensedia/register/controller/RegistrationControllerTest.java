package com.sensedia.register.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import com.sensedia.register.exception.MessageError;
import com.sensedia.register.exception.Messages;
import com.sensedia.register.response.RegistrationResponse;

public class RegistrationControllerTest extends AbstractControllerTest {
	
	@InjectMocks
	private RegistrationController controller;
	
	private MockHttpServletResponse response;
	private String document;
	
	@Override
	protected Object controllerClass() {
		return controller;
	}
	
	@Test
    public void controllerShouldFailWithNotInformedDocument() throws Exception {
		givenANullDocument();
		whenCallGetRegistrations();
		thenExpectPreconditionFailedStatus();
		thenExpectRequiredFieldErrorCode();
    }
	
	@Test
    public void controllerShouldFindScoreByDocumentSuccessfully() throws Exception {
		givenAValidDocument();
		whenCallGetRegistrations();
		thenExpectOkStatus();
		thenExpectValidScoreOnBody();
    }
	
	/*
	 * Given methods
	 */
	
	private void givenANullDocument() {
		this.document = null;
	}
	
	private void givenAValidDocument() {
		this.document = "12345678901";
	}
	
	/*
	 * When methods
	 */
	
	private void whenCallGetRegistrations() throws Exception {
		this.response = mockMvc.perform(get("/registrations")
                .param("document", document))
                .andReturn()
                .getResponse();
	}
	
	/*
	 * Then methods
	 */
	
	private void thenExpectOkStatus() {
		assertThat(this.response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	private void thenExpectPreconditionFailedStatus() {
		assertThat(this.response.getStatus()).isEqualTo(HttpStatus.PRECONDITION_FAILED.value());
	}
	
	private void thenExpectValidScoreOnBody() throws UnsupportedEncodingException {
		String json = this.response.getContentAsString();
        RegistrationResponse registrationResponse = stringJsonToObject(json, RegistrationResponse.class);
        assertThat(registrationResponse).isNotNull();
        assertThat(registrationResponse.getScore()).isGreaterThanOrEqualTo(0);
        assertThat(registrationResponse.getScore()).isLessThanOrEqualTo(1000);
	}
	
	private void thenExpectRequiredFieldErrorCode() throws UnsupportedEncodingException {
		String json = this.response.getContentAsString();
		MessageError error = stringJsonToObject(json, MessageError.class);
		assertThat(error.getCode()).isEqualTo(Messages.REQUIRED_FIELD);
	}

}
