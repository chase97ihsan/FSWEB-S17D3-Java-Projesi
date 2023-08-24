package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.AnimalValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KoalaController {
    private Map<Integer, Koala> koalas;

    @PostConstruct
    public Map<Integer, Koala> init() {
        return koalas = new HashMap<>();
    }

    @GetMapping("/koalas")
    public List<Koala> get() {
        return koalas.values().stream().toList();
    }

    @GetMapping("/koalas/{id}")
    public Koala get(@PathVariable int id) {
        AnimalValidation.isValid(id);
        AnimalValidation.isAnimalNotExist(koalas, id);
        return koalas.get(id);
    }

    @PostMapping("/koalas")
    public Koala post(@RequestBody Koala koala) {
        AnimalValidation.isValid(koala.getId());
        AnimalValidation.isAnimalExist(koalas, koala.getId());
        AnimalValidation.isAnimalValidForKoala(koala);
        koalas.put(koala.getId(), koala);
        return koalas.get(koala.getId());
    }

    @PutMapping("/koalas/{id}")
    public Koala put(@PathVariable int id, @RequestBody Koala koala) {
        AnimalValidation.isAnimalNotExist(koalas, id);
        AnimalValidation.isAnimalValidForKoala(koala);
        koalas.put(id, koala);
        return koalas.get(id);
    }

    @DeleteMapping("/koalas/{id}")
    public Koala delete(@PathVariable int id) {
        AnimalValidation.isAnimalNotExist(koalas, id);
        return koalas.remove(id);
    }

}
