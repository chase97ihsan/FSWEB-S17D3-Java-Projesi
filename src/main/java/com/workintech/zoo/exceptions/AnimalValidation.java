package com.workintech.zoo.exceptions;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class AnimalValidation {

    public static void isValid(int id) {
        if (id <= 0) {
            throw new AnimalException("Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void isAnimalNotExist(Map animals, int id) {
        if (!animals.containsKey(id)) {
            throw new AnimalException("Animal with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public static void isAnimalExist(Map animals, int id) {
        if (animals.containsKey(id)) {
            throw new AnimalException("Animal with given id is exist: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void isAnimalValidForKangaroo(Kangaroo kangaroo) {
        if (kangaroo.getName() == null || kangaroo.getName().isEmpty() ||
                kangaroo.getGender() == null || kangaroo.getGender().isEmpty()) {
            throw new AnimalException("Animal credentials are not valid. ", HttpStatus.BAD_REQUEST);
        }
    }

    public static void isAnimalValidForKoala(Koala koala) {
        if (koala.getName() == null || koala.getName().isEmpty() ||
                koala.getGender() == null || koala.getGender().isEmpty()) {
            throw new AnimalException("Animal credentials are not valid. ", HttpStatus.BAD_REQUEST);
        }
    }
}
