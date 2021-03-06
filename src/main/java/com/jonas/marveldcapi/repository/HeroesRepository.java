package com.jonas.marveldcapi.repository;

import java.util.Optional;

import com.jonas.marveldcapi.document.Heroes;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes,String>{
    Optional<Heroes> findById(String id);
}
