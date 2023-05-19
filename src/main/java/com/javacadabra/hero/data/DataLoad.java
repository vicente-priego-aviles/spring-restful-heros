package com.javacadabra.hero.data;

import com.github.javafaker.Faker;
import com.github.javafaker.Superhero;
import com.javacadabra.hero.Hero;
import com.javacadabra.hero.HeroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.IntStream;

@Configuration
class DataLoad {

    private final static int NUM_HEROES = 10;
    @Bean
    CommandLineRunner load(HeroRepository repository) {

        return args -> IntStream.range(0, NUM_HEROES).forEach(i -> {
            Superhero hero = new Faker().superhero();

            repository.save(
                    Hero.builder()
                            .name(hero.name())
                            .power(hero.power())
                            .descriptor(hero.descriptor())
                            .build()
            );
        });
    }
}
