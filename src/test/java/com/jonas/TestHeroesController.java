package com.jonas;

import static com.jonas.marveldcapi.constant.HeroConstant.HEROES_ENDPOINT_LOCAL;

import com.jonas.marveldcapi.repository.HeroesRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

public class TestHeroesController {
    
    
  @Autowired
  WebTestClient webTestClient;

  @Autowired
  HeroesRepository heroesRepository;


  @Test
  public void getOneHeroById(){

    webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"1")
      .exchange()
      .expectStatus().isOk()
      .expectBody();


  }

  @Test
  public void getOneHeronotFound(){

    webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"10")
      .exchange()
      .expectStatus().isNotFound();

  }


  @Test
  public void deleteHero(){

    webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"1")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus().isNotFound()
      .expectBody(Void.class);

  }

}