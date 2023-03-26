package com.example.knapsack.dao.Algorithm;

import com.example.knapsack.entity.Individual;
import com.example.knapsack.entity.Item;

import java.util.List;

public interface Cross {
    public List<Individual> cross(Individual parent1, Individual parent2, List<Item> items);
}
