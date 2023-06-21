package br.com.vvv.Controller;

import br.com.vvv.Domain.DTO.DataBadRequestMessage;
import br.com.vvv.Domain.DTO.DataRegisterClient;
import br.com.vvv.Service.ClientService;
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
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerClient_ValidData_ReturnsOkResponse() {
        DataRegisterClient dataRegisterClient = new DataRegisterClient("Fds", 21, "fds", "fds123", "11111111111", "fds", "21111111111");

        ResponseEntity<?> response = clientController.registerClient(dataRegisterClient);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(clientService, times(1)).registerClient(dataRegisterClient);
    }

    @Test
    void registerClient_InvalidData_ReturnsBadRequestResponse() {
        DataRegisterClient dataRegisterClient = new DataRegisterClient("Fds", 21, null, "fds123", "11111111111", "fds", "21111111111");

        doAnswer(invocation -> {
            throw new Exception("Invalid data");
        }).when(clientService).registerClient(dataRegisterClient);

        ResponseEntity<?> response = clientController.registerClient(dataRegisterClient);

        assertEquals(ResponseEntity.badRequest().body(new DataBadRequestMessage("Invalid data")), response);
        verify(clientService, times(1)).registerClient(dataRegisterClient);
    }
}