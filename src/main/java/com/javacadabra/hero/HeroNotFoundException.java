package com.javacadabra.hero;

public class HeroNotFoundException extends RuntimeException {
    HeroNotFoundException(Long id) {
        super("Could not find hero with id= " + id);
    }
}
