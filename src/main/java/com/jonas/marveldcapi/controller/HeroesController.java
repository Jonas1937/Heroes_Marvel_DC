package com.jonas.marveldcapi.controller;

import static com.jonas.marveldcapi.constant.HeroConstant.HEROES_ENDPOINT_LOCAL;

import com.jonas.marveldcapi.document.Heroes;
import com.jonas.marveldcapi.repository.HeroesRepository;
import com.jonas.marveldcapi.services.HeroesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HeroesController {
    
    @Autowired
    private HeroesService heroesService;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    public HeroesController(HeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllHeroes(){
        log.info("requesting a list of all heroes");
        return heroesService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
    public Mono<ResponseEntity<Heroes>> findHeroById(@PathVariable Long id){
        log.info("requesting the heroe of id" + id);
        return heroesService.findHeroById(id)
        .map((item) -> new ResponseEntity<>(item,HttpStatus.OK))
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
               
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes hero){
        log.info("Created Hero of name" + hero.getName());
        return heroesService.save(hero);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
    @ResponseStatus(HttpStatus.CONTINUE)
    public Mono<HttpStatus> deleteByID(@PathVariable long id){
        heroesService.deleteHeroById(id);
        log.info("Deleted hero by id" + id); 
        return Mono.just(HttpStatus.CONTINUE);

    } 


}
