package br.com.vvv.Controller;

import br.com.vvv.Domain.DTO.DataProfileClient;
import br.com.vvv.Domain.DTO.DataRegisterClient;
import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Service.ClientProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ClientProfileControllerTest {

    @InjectMocks
    private ClientProfileController clientProfileController;

    @Mock
    private ClientProfileService clientProfileService;

    DataRegisterClient dataRegisterClient = new DataRegisterClient("José Aldo", 22, "Josesinho", "awdawda", "11111111111", "Tester","21999999999");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(clientProfileService.findProfile(Mockito.anyString())).thenReturn(new Client(dataRegisterClient));
    }

    @Test
    public void testFindClientProfile() throws Exception {
        String authenticationToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3YWRhd2QiLCJyb2xlIjoiQ0xJRU5UIiwiZXhwIjoxNjg3NDkyMzk3fQ.Z-HFc5F9V60OFRJWvPHos_WNRRGYU3L3It53gkQNCu5o4U7pUgMbJGA8Dh_ObiGNCD7ko_hjCLUgbA7COUF7TA";

        DataProfileClient dataProfileClient = new DataProfileClient("José Aldo", 22, "Josesinho", "11111111111", "Tester", "21999999999");

        ResponseEntity result = clientProfileController.findClientProfile(authenticationToken);
        assertNotEquals(ResponseEntity.ok(dataProfileClient), result);
    }
}