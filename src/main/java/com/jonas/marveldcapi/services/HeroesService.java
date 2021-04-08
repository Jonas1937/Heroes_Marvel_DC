package com.jonas.marveldcapi.services;

import com.jonas.marveldcapi.document.Heroes;
import com.jonas.marveldcapi.repository.HeroesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {
    @Autowired
    private final HeroesRepository heroesRepository;

    public HeroesService(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }
    public Flux<Heroes> findAll(){
        return Flux.fromIterable(heroesRepository.findAll());
    }
    public Mono<Heroes> findHeroById(Long id){
        return Mono.justOrEmpty(heroesRepository.findById(id));
    }

    public Mono<Heroes> save(Heroes heroes){
        return Mono.justOrEmpty(heroesRepository.save(heroes));
    }

    public void deleteHeroById(Long id){
       heroesRepository.deleteById(id);
    }
    
}
