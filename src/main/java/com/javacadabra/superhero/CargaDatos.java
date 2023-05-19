package com.javacadabra.nomina;

import com.github.javafaker.Faker;
import com.github.javafaker.Superhero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.IntStream;

@Configuration
class CargaDatos {

    private static final Logger log = LoggerFactory.getLogger(CargaDatos.class);

    @Bean
    CommandLineRunner iniciarDatos(EmpleadoRepository repositorio) {

        return args -> {
            IntStream.range(0, 100).forEach(i ->
                {
                    Superhero hero = new Faker().superhero();

                    repositorio.save(
                            SuperHeroe.builder()
                                    .nombre(hero.name())
                                    .descripcion(hero.descriptor())
                                    .poder(hero.power())
                                    .build()
                    );
                });
        };
    }
}
