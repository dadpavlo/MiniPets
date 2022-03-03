package com.example.pets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    private ShelterRepository repository;

    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return repository.findAll();
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable(value = "id") Long id) {

        Pet pet = repository.findById(id)
                .orElseThrow(() -> new PetNotFoundExeption(id));

        return ResponseEntity.ok().body(pet);
    }

    @PostMapping("/pets")
    public Pet createPet(@Valid @RequestBody Pet pet) {
        return repository.save(pet);
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable(value = "id") Long id, @Valid @RequestBody Pet newPet) {

        Pet pet = repository.findById(id)
                .orElseThrow(() -> new PetNotFoundExeption(id));
        pet.setPetName(newPet.getPetName());

        final Pet updatePet = repository.save(pet);
        return ResponseEntity.ok(updatePet);
    }
}
