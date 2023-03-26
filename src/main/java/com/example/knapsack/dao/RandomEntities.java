package com.example.knapsack.dao;

import com.example.knapsack.entity.Individual;
import com.example.knapsack.entity.Item;
import com.example.knapsack.repository.IndividualRepository;
import com.example.knapsack.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@NoArgsConstructor
public class RandomEntities {
    @Autowired
    Environment environment;
    ItemRepository itemRepository;
    IndividualRepository individualRepository;
    ManageItems manageItems;
    ManageIndividuals manageIndividuals;

    Random rand;

    @Autowired
    public RandomEntities(ItemRepository itemRepository, ManageItems manageItems,
                          IndividualRepository individualRepository, ManageIndividuals manageIndividuals) {

        this.itemRepository = itemRepository;
        this.individualRepository = individualRepository;
        this.manageItems = manageItems;
        this.manageIndividuals = manageIndividuals;
        rand = new Random();

    }

    @Transactional
    public List<Item> generateItems() {
        Integer length = Integer.parseInt(Objects.requireNonNull(environment.getProperty("knacksackAmountOfItems")));
        List<Item> result = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            result.add(generateItem());
        }
        return result.stream().map(itemRepository::save).toList();
    }

    private Item generateItem() {
        Integer quality = rand.nextInt(10) + 1;
        Integer weight = rand.nextInt(20) + 1;
        return new Item(quality, weight);
    }

    @Transactional
    public List<Individual> generatePopulation() {
        Integer length = Integer.parseInt(Objects.requireNonNull(environment.getProperty("knacksackAmountOfItems")));
        Integer popSize = Integer.parseInt(Objects.requireNonNull(environment.getProperty("populationSize")));
        Integer maxWeight = Integer.parseInt(Objects.requireNonNull(environment.getProperty("knacksackMaxWeight")));
        List<Individual> result = new LinkedList<>();
        for (int i = 0; i < popSize; i++) {
            StringBuilder array = new StringBuilder();
            for (int j = 0; j < length; j++) {
                array.append(rand.nextInt(2));
            }
            Individual individual = new Individual(array.toString(), manageItems.showAllItems());
            if (individual.getWeight() > maxWeight)
                individual.setQuality(0);
            result.add(individual);
        }

        return result.stream().map(individualRepository::save).toList();

    }
}
