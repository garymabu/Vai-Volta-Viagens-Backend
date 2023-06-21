package br.com.vvv.Controller;

import br.com.vvv.Domain.DTO.DadosTokenJWT;
import br.com.vvv.Domain.DTO.DataBadRequestMessage;
import br.com.vvv.Domain.DTO.DataToLogin;
import br.com.vvv.Service.ClientAuthService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ClientAuthControllerTest {

    @InjectMocks
    private ClientAuthController clientAuthController;

    @Mock
    private ClientAuthService clientAuthService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ValidData_ReturnsOkResponseWithToken() {
        DataToLogin dataToLogin = new DataToLogin("johndoe", "password");
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        when(clientAuthService.login(dataToLogin)).thenReturn(token);

        ResponseEntity<?> response = clientAuthController.login(dataToLogin);

        assertEquals(ResponseEntity.ok().body(new DadosTokenJWT(token)), response);

        verify(clientAuthService, times(1)).login(dataToLogin);
    }

    @Test
    void testLogin_InvalidCredentials() {
        // Arrange
        DataToLogin dataToLogin = new DataToLogin("username", "password");
        when(clientAuthService.login(dataToLogin)).thenThrow(new ValidationException("Invalid credentials"));

        // Act
        ResponseEntity<?> response = clientAuthController.login(dataToLogin);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Username or Password is Invalid", ((DataBadRequestMessage) response.getBody()).message());
    }

}