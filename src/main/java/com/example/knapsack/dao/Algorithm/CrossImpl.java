package com.example.knapsack.dao.Algorithm;

import com.example.knapsack.dao.Algorithm.Cross;
import com.example.knapsack.dao.ManageIndividuals;
import com.example.knapsack.entity.Individual;
import com.example.knapsack.entity.Item;
import com.example.knapsack.repository.IndividualRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Component
public class CrossImpl implements Cross {

    IndividualRepository repository;
    ManageIndividuals manageIndividuals;
    Mutate mutate;
    @Autowired
    Environment environment;

    public CrossImpl(IndividualRepository repository, Environment environment, ManageIndividuals manageIndividuals, Mutate mutate) {
        this.repository = repository;
        this.environment = environment;
        this.manageIndividuals = manageIndividuals;
        this.mutate = mutate;
    }

    @Transactional
    @Override
    public List<Individual> cross(Individual parent1, Individual parent2, List<Item> items) {
        Integer length = Integer.parseInt(Objects.requireNonNull(environment.getProperty("knacksackAmountOfItems")));
        Random rand = new Random();
        Integer pivot = rand.nextInt(length - 2);
        String s1 = parent1.getSolutionArray();
        String s2 = parent2.getSolutionArray();
        String code1 = s1.substring(0, pivot) + s2.substring(pivot);
        code1 = mutate.mutate(code1);
        String code2 = s2.substring(0, pivot) + s1.substring(pivot);
        code2 = mutate.mutate(code2);
        Individual baby1 = new Individual(code1, items);
        baby1.setId(parent1.getId());
        Individual baby2 = new Individual(code2, items);
        baby2.setId(parent2.getId());
        repository.save(baby1);
        repository.save(baby2);
        manageIndividuals.checkWeight(); //nie dziala
        return Arrays.asList(parent1, parent2, baby1, baby2);
    }
}
