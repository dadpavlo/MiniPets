package com.example.pets;

public class PetNotFoundExeption extends RuntimeException{
    PetNotFoundExeption(Long id) {
        super("Could not find pet with id" + id);
    }
}
