package com.example.knapsack.dao;

import com.example.knapsack.entity.Individual;
import com.example.knapsack.repository.IndividualRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class ManageIndividuals {
    @Autowired
    Environment environment;
    IndividualRepository repository;

    public ManageIndividuals(IndividualRepository repository) {
        this.repository = repository;
    }

    public Individual showIndividual(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Individual> showAllIndividuals() {
        return repository.findAll();
    }

    @Transactional
    public void deleteAllIndividuals() {
        repository.deleteAll();
    }

    @Transactional
    public List<Individual> checkWeight() {
        Integer maxWeight = Integer.parseInt(Objects.requireNonNull(environment.getProperty("knacksackMaxWeight")));
        List<Individual> population = repository.findAll();
        population.stream().filter(indiv -> indiv.getWeight() > maxWeight)
                .forEach(indiv -> indiv.setQuality(2));
        return population.stream().map(individual -> repository.save(individual)).toList();
    }

}
