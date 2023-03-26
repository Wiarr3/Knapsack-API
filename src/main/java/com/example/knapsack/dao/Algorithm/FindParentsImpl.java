package com.example.knapsack.dao.Algorithm;


import com.example.knapsack.entity.Individual;
import com.example.knapsack.repository.IndividualRepository;
import com.example.knapsack.utils.exceptions.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Component
public class FindParentsImpl implements FindParents {
    IndividualRepository repository;
    @Autowired
    Environment environment;

    public FindParentsImpl(IndividualRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @Override
    public Pair<Individual, Individual> findParents() {
        Integer populationSize = Integer.parseInt(Objects.requireNonNull(environment.getProperty("populationSize")));
        Random rand = new Random();
        Pair<Individual, Individual> result = new Pair<>();
        int index1 = rand.nextInt(populationSize - 1);
        int index2 = rand.nextInt(populationSize - 1);
        Individual potentialParent1 = repository.findById(index1).orElseThrow();
        Individual potentialParent2 = repository.findById(index2).orElseThrow();
        if (potentialParent1.getQuality() > potentialParent2.getQuality())
            result.setFirst(potentialParent1);
        else
            result.setFirst(potentialParent2);
        int index3 = rand.nextInt(populationSize - 1);
        int index4 = rand.nextInt(populationSize - 1);
        Individual potentialParent3 = repository.findById(index3).orElseThrow();
        Individual potentialParent4 = repository.findById(index4).orElseThrow();
        if (potentialParent3.getQuality() > potentialParent4.getQuality())
            result.setSecond(potentialParent3);
        else
            result.setSecond(potentialParent4);
        return result;


    }
}
