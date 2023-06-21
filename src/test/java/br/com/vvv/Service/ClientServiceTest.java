package br.com.vvv.Service;

import br.com.vvv.Domain.DTO.DataRegisterClient;
import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerClient_ValidData_ReturnsSavedClient() {
        // Arrange
        DataRegisterClient dataRegisterClient = new DataRegisterClient("John Doe", 25, "johndoe", "password", "123456789", "Developer", "1234567890");

        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(new Client(dataRegisterClient));

        // Act
        Client savedClient = clientService.registerClient(dataRegisterClient);

        // Assert
        assertNotNull(savedClient);
        assertEquals(dataRegisterClient.name(), savedClient.getName());
        assertEquals(dataRegisterClient.age(), savedClient.getAge());
        assertEquals(dataRegisterClient.login(), savedClient.getLogin());
        assertEquals(dataRegisterClient.password(), savedClient.getPassword());
        assertEquals(dataRegisterClient.cpf(), savedClient.getCpf());
        assertEquals(dataRegisterClient.profession(), savedClient.getProfession());
        assertEquals(dataRegisterClient.tell(), savedClient.getTell());

        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void findByLogin_ExistingLogin_ReturnsUserDetails() {
        // Arrange
        String login = "johndoe";
        Client client = new Client(new DataRegisterClient("John Doe", 25, login, "password", "123456789", "Developer", "1234567890"));

        when(clientRepository.findByLogin(login)).thenReturn(client);

        // Act
        UserDetails userDetails = clientService.findByLogin(login);

        // Assert
        assertNotNull(userDetails);
        assertEquals(client.getLogin(), userDetails.getUsername());
        assertEquals(client.getPassword(), userDetails.getPassword());

        verify(clientRepository, times(1)).findByLogin(login);
    }

    @Test
    void findByLogin_NonExistingLogin_ReturnsNull() {
        // Arrange
        String login = "nonexistent";

        when(clientRepository.findByLogin(login)).thenReturn(null);

        // Act
        UserDetails userDetails = clientService.findByLogin(login);

        // Assert
        assertNull(userDetails);

        verify(clientRepository, times(1)).findByLogin(login);
    }
}