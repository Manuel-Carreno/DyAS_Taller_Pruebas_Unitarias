package edu.unisabana.tyvs.domain.service;

import java.util.ArrayList;
import java.util.List;

import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;

public class Registry {

    private static int min_age= 18;
    private static int max_age = 120;
    private List<Integer> registraduria = new ArrayList<>();

    public RegisterResult registerVoter(Person p) {
        if (p == null) {
            return RegisterResult.INVALID; 
        }
        if (!p.isAlive()) {
            return RegisterResult.DEAD;
        }
        if (p.getId()<= 0) {
            return RegisterResult.INVALID;
        }
        if (p.getAge() < 0 || p.getAge() > max_age) {
            return RegisterResult.INVALID_AGE;
        }
        if (p.getAge() < min_age) {
            return RegisterResult.UNDERAGE;
        }
        if (registraduria.contains(p.getId())) {
            return RegisterResult.DUPLICATED;
        }
        registraduria.add(p.getId()); //si no pasa nada de los anteriores casos solo agrega el id y listo
        return RegisterResult.VALID;
    }
}