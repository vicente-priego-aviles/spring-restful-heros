package com.javacadabra.hero;

import com.github.javafaker.Faker;
import com.github.javafaker.Superhero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.IntStream;

@Configuration
class LoadData {

    private static final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Bean
    CommandLineRunner iniciarDatos(HeroRepository repository) {

        return args -> {
            IntStream.range(0, 100).forEach(i ->
                {
                    Superhero hero = new Faker().superhero();

                    repository.save(
                            Hero.builder()
                                    .name(hero.name())
                                    .power(hero.power())
                                    .descriptor(hero.descriptor())
                                    .build()
                    );
                });
        };
    }
}
