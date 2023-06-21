package br.com.vvv.Service;

import br.com.vvv.Domain.DTO.ClientAuthToken;
import br.com.vvv.Domain.DTO.DataRegisterClient;
import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ClientProfileServiceTest {

    @InjectMocks
    private ClientProfileService clientProfileService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindProfile() {
        String client = "wadawd";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3YWRhd2QiLCJyb2xlIjoiQ0xJRU5UIiwiZXhwIjoxNjg3NDkyMzk3fQ.Z-HFc5F9V60OFRJWvPHos_WNRRGYU3L3It53gkQNCu5o4U7pUgMbJGA8Dh_ObiGNCD7ko_hjCLUgbA7COUF7TA";

        ClientAuthToken authToken = new ClientAuthToken(token);
        var clientLogin = authToken.getUserLogin();

        DataRegisterClient dataRegisterClient = new DataRegisterClient(client, 22, "teste", "asdad", "111111111", "Tester", "21999999999");
        Client expectedClient = new Client(dataRegisterClient);

        when(clientRepository.findByLogin(clientLogin)).thenReturn(expectedClient);

        Client result = clientProfileService.findProfile(token);

        assertEquals(expectedClient, result);
    }
}