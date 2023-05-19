package com.javacadabra.hero.errors;

public class HeroNotFoundException extends RuntimeException {
    public HeroNotFoundException(Long id) {
        super("Could not find hero with id= " + id);
    }
}
