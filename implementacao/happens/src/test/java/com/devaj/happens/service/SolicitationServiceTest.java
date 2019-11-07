package com.devaj.happens.service;

import com.devaj.happens.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class SolicitationServiceTest {

    @Autowired
    private SolicitationService solicitationService;


//    @Test
    public void saveTest(){

        Branch branch = new Branch();
        branch.setId(1L);

        UserSystem user = new UserSystem();
        user.setId(1L);

        Client client = new Client();
        client.setId(1L);

        Solicitation newSolicitation = new Solicitation();
        newSolicitation.setBranch(branch);
        newSolicitation.setUser(user);
        newSolicitation.setClient(client);
        newSolicitation.setNote("Teste Solicitação");

        Solicitation createdSolicitation = solicitationService.save(newSolicitation);

        assertThat(createdSolicitation.getId()).isEqualTo(2L);
    }

//    @Test
    void listAllTest() {
        List<Solicitation> solicitations = solicitationService.listAll();

        assertThat(solicitations.size()).isEqualTo(2);
    }
}