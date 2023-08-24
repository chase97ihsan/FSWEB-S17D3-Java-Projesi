package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.AnimalValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KangarooController {

    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public Map<Integer, Kangaroo> init() {
        return kangaroos = new HashMap<>();
    }

    @GetMapping("/kangaroos")
    public List<Kangaroo> get() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/kangaroos/{id}")
    public Kangaroo get(@PathVariable int id) {
        AnimalValidation.isValid(id);
        AnimalValidation.isAnimalNotExist(kangaroos, id);
        return kangaroos.get(id);
    }

    @PostMapping("/kangaroos")
    public Kangaroo post(@RequestBody Kangaroo kangaroo) {
        AnimalValidation.isValid(kangaroo.getId());
        AnimalValidation.isAnimalExist(kangaroos, kangaroo.getId());
        AnimalValidation.isAnimalValidForKangaroo(kangaroo);
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/kangaroos/{id}")
    public Kangaroo put(@PathVariable int id, @RequestBody Kangaroo kangaroo) {
        AnimalValidation.isAnimalNotExist(kangaroos, id);
        AnimalValidation.isAnimalValidForKangaroo(kangaroo);
        kangaroos.put(id, kangaroo);
        return kangaroos.get(id);
    }

    @DeleteMapping("/kangaroos/{id}")
    public Kangaroo delete(@PathVariable int id) {
        AnimalValidation.isAnimalNotExist(kangaroos, id);
        return kangaroos.remove(id);
    }

}
