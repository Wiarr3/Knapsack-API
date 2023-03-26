package com.example.knapsack.service;

import com.example.knapsack.dao.Algorithm.FindAndCross;
import com.example.knapsack.dao.ManageItems;
import com.example.knapsack.dao.ManageProperties;
import com.example.knapsack.dao.RandomEntities;
import com.example.knapsack.entity.Individual;
import com.example.knapsack.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnapsackService {
    private final ManageProperties manageProperties;
    private final RandomEntities randomEntities;
    private final ManageItems manageItems;
    private final FindAndCross findAndCross;

    @Autowired
    public KnapsackService(ManageProperties manageProperties, RandomEntities randomEntities, ManageItems manageItems, FindAndCross findAndCross) {
        this.manageProperties = manageProperties;
        this.randomEntities = randomEntities;
        this.manageItems = manageItems;
        this.findAndCross = findAndCross;
    }

    public Integer setWeight(Integer weight) {
        return manageProperties.setMaxWeight(weight);
    }

    public Integer setLength(Integer length) {
        return manageProperties.setLength(length);
    }

    public List<Item> randomItems() {
        return randomEntities.generateItems();
    }

    public List<Item> showAllItems() {
        return manageItems.showAllItems();
    }

    public Item showItem(Integer id) {
        return manageItems.showItem(id);
    }

    public void deleteItems() {
        manageItems.deleteItems();
    }

    public List<Individual> randomPopulation() {
        return randomEntities.generatePopulation();
    }

    public List<Individual> oneIteration() {
        return findAndCross.oneIteration();

    }

}
