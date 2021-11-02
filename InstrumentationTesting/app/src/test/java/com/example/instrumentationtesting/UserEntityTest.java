package com.example.instrumentationtesting;

import com.example.instrumentationtesting.db.UserEntity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserEntityTest {

    //// compare users with same parameters
    @Test
    public void isUserEqual_identicalProperties_returnTrue() throws Exception {

        UserEntity entity1 = new UserEntity("Satabdi","Bhadrak, Odisha");
        entity1.setUnique_id(1);
        UserEntity entity2 = new UserEntity("Satabdi","Bhadrak, Odisha");
        entity2.setUnique_id(1);

        assertEquals(entity1, entity2);
        System.out.println("The users are equal!");
    }

    // compare users with different id
    @Test
    void isUserEqual_differentIds_returnFalse() throws Exception {

        UserEntity entity1 = new UserEntity("Satabdi","Bhadrak, Odisha");
        entity1.setUnique_id(1);
        UserEntity entity2 = new UserEntity("Satabdi","Bhadrak, Odisha");
        entity2.setUnique_id(2);

        assertNotEquals(entity1, entity2);
        System.out.println("The users are not equal!");
    }

    // compare users with different name
    @Test
    void isUserEqual_differentName_returnFalse() throws Exception {

        UserEntity entity1 = new UserEntity("Satabdi","Bhadrak, Odisha");
        entity1.setUnique_id(1);

        UserEntity entity2 = new UserEntity("Satu","Bhadrak, Odisha");
        entity2.setUnique_id(1);

        assertNotEquals(entity1, entity2);
        System.out.println("The users are not equal!");
    }

    // compare users with different address
    @Test
    void isUserEqual_differentAddress_returnFalse() throws Exception {

        UserEntity entity1 = new UserEntity("Satabdi","Bhadrak, Odisha");
        entity1.setUnique_id(1);
        UserEntity entity2 = new UserEntity("Satabdi","Manjuriroad, Odisha");
        entity2.setUnique_id(1);

        assertNotEquals(entity1, entity2);
        System.out.println("The users are not equal!");
    }
}
