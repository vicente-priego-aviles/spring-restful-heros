package com.javacadabra.hero;

import com.javacadabra.hero.assembler.HeroModelAssembler;
import com.javacadabra.hero.errors.HeroNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
public class HeroController {

    private final HeroRepository repository;
    private final HeroModelAssembler assembler;

    @GetMapping("/heroes")
    public CollectionModel<EntityModel<Hero>> all() {
        List<EntityModel<Hero>> heroes = repository.findAll().stream()
                .map(hero -> EntityModel.of(hero,
                        linkTo(methodOn(HeroController.class).one(hero.getId())).withSelfRel(),
                        linkTo(methodOn(HeroController.class).all()).withRel("heroes")))
                .collect(Collectors.toList());

        return CollectionModel.of(heroes, linkTo(methodOn(HeroController.class).all()).withSelfRel());
    }

    @GetMapping("/heroes/{id}")
    public EntityModel<Hero> one(@PathVariable Long id) {

        Hero hero = repository.findById(id)
                .orElseThrow(() -> new HeroNotFoundException(id));

        return assembler.toModel(hero);
    }

    @PostMapping("/heroes")
    ResponseEntity<?> newHeroe(@RequestBody Hero newHeroe) {

        EntityModel<Hero> entityModel = assembler.toModel(repository.save(newHeroe));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/heroes/{id}")
    ResponseEntity<?> replaceHero(@RequestBody Hero newHero, @PathVariable Long id) {

        Hero updatedHero = repository.findById(id)
                .map(hero -> {
                    hero.setName(newHero.getName());
                    hero.setDescriptor(newHero.getDescriptor());
                    hero.setPower(newHero.getPower());
                    return repository.save(hero);
                })
                .orElseGet(() -> {
                    newHero.setId(id);
                    return repository.save(newHero);
                });

        EntityModel<Hero> entityModel = assembler.toModel(updatedHero);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/heroes/{id}")
    ResponseEntity<?> deleteHero(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
