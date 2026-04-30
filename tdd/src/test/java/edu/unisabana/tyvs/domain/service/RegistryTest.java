package edu.unisabana.tyvs.domain.service;

import edu.unisabana.tyvs.domain.model.*;
import org.junit.Assert;
import org.junit.Test;

public class RegistryTest { //primera prueba (red) 

    @Test
    public void shouldRegisterValidPerson() {
        // Arrange: preparar los datos y el objeto a probar
        Registry registry = new Registry();
        Person person = new Person("Ana", 1, 30, Gender.FEMALE, true);

        // Act: ejecutar la acción que queremos probar
        RegisterResult result = registry.registerVoter(person);

        // Assert: verificar el resultado esperado
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    // segunda prueba -> persona muetra
    @Test
    public void shouldRejectDeadPerson() {
        // Arrange: preparar los datos y el objeto a probar
        Registry registry = new Registry();
        Person dead = new Person("Carlos", 2, 40, Gender.MALE, false);

        // Act: ejecutar la acción que queremos probar
        RegisterResult result = registry.registerVoter(dead);

        // Assert: verificar el resultado esperado
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    // tercera prueba -> equivalencia
    @Test
    public void shouldReturnInvalidWhenPersonIsNull() {
        Registry registry = new Registry();
       // no creamos persona por que debemos probar cuando este vacio

        RegisterResult result = registry.registerVoter(null);
        Assert.assertEquals(RegisterResult.INVALID, result);
    }

    @Test
    public void shouldRejectNegativeAge() {
        Registry registry = new Registry();
        Person negPerson = new Person("David", 3, -24, Gender.MALE, true);

        RegisterResult result = registry.registerVoter(negPerson);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldRejectUnderAgeAt17 () {
        Registry registry = new Registry();
        Person child = new Person("Maria", 4, 16, Gender.FEMALE, true);

        RegisterResult result = registry.registerVoter(child);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void shouldAcceptAdultAt18 () {
        Registry registry = new Registry();
        Person adult = new Person("Sofia", 5, 18, Gender.FEMALE, true);

        RegisterResult result = registry.registerVoter(adult);
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldAcceptMaxAge120 () {
        Registry registry = new Registry();
        Person oldPerson = new Person("Jose", 6, 120, Gender.MALE, true);

        RegisterResult result = registry.registerVoter(oldPerson);
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldRejectInvalidAgeOver120 () {
        Registry registry = new Registry();
        Person oldPerson = new Person("Pedro", 7, 150, Gender.MALE, true);

        RegisterResult result = registry.registerVoter(oldPerson);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldRejectDuplicatedVoter () {
        Registry registry = new Registry();
        Person person1 = new Person("Alejandra", 8, 25, Gender.FEMALE, true);
        Person person2 = new Person("Fernanda", 8, 35, Gender.FEMALE, true);

        registry.registerVoter(person1);
        RegisterResult result = registry.registerVoter(person2);
        Assert.assertEquals(RegisterResult.DUPLICATED, result);
    }

    @Test
    public void shouldRejectWhenIdIsZeroOrNegative () {
        Registry registry = new Registry();
        Person invalidIdPerson = new Person("Camila", 0, 25, Gender.FEMALE, true);

        RegisterResult result = registry.registerVoter(invalidIdPerson);
        Assert.assertEquals(RegisterResult.INVALID, result);
    }
}

