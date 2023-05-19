package com.javacadabra.hero;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HeroController {

    private final HeroRepository repositorio;

    HeroController(HeroRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/heroes")
    List<Hero> all() {
        return repositorio.findAll();
    }

    @GetMapping("/heroes/{id}")
    Hero one(@PathVariable Long id) {

        return repositorio.findById(id)
                .orElseThrow(() -> new HeroNotFoundException(id));
    }

    @PostMapping("/heroes")
    Hero newSuperHeroe(@RequestBody Hero hero) {
        return repositorio.save(hero);
    }

    @PutMapping("/heroes/{id}")
    Hero replaceSuperHeroe(@RequestBody Hero hero, @PathVariable Long id) {

        return repositorio.findById(id)
                .map(heroe -> {
                    heroe.setName(hero.getName());
                    heroe.setDescriptor(hero.getDescriptor());
                    heroe.setPower(hero.getPower());
                    return repositorio.save(heroe);
                })
                .orElseGet(() -> {
                    hero.setId(id);
                    return repositorio.save(hero);
                });
    }

    @DeleteMapping("/heroes/{id}")
    void deleteSuperHeroe(@PathVariable Long id) {
        repositorio.deleteById(id);
    }
}
