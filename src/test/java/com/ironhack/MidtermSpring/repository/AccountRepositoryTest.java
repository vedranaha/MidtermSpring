package com.ironhack.MidtermSpring.repository;

import com.ironhack.MidtermSpring.model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_JPAMethods() {
        // Test findAll
        List<Account> accountList = accountRepository.findAll(); // size = 2
        assertEquals(2, accountList.size());

        // Test findById
        // Optional: Clase especial de java que se usa cuando podemos recibir un valor null
        // Siempre va a instanciado, osea que nunca va a ser igual a null
        //Optional<Song> optionalSong = songRepository.findById(song1.getId());
       // assertTrue(optionalSong.isPresent()); // Validando que song no sea null
       // assertEquals(song1, optionalSong.get()); // El método get me trae la instancia que estaba buscando
    }

    private void assertEquals(int i, int size) {
    }


}