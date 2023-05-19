package com.javacadabra.hero.assembler;

import com.javacadabra.hero.Hero;
import com.javacadabra.hero.HeroController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HeroModelAssembler implements RepresentationModelAssembler<Hero, EntityModel<Hero>> {

    @Override
    public EntityModel<Hero> toModel(Hero hero) {

        return EntityModel.of(hero,
                linkTo(methodOn(HeroController.class).one(hero.getId())).withSelfRel(),
                linkTo(methodOn(HeroController.class).all()).withRel("heroes"));
    }
}
