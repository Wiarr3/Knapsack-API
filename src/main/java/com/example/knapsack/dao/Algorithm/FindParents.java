package com.example.knapsack.dao.Algorithm;


import com.example.knapsack.entity.Individual;
import com.example.knapsack.utils.exceptions.Pair;

import java.util.Map;

public interface FindParents {
    Pair<Individual, Individual> findParents();
}
