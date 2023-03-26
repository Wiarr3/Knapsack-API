package com.example.knapsack.dao.Algorithm;

import com.example.knapsack.dao.ManageItems;
import com.example.knapsack.entity.Individual;
import com.example.knapsack.utils.exceptions.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class FindAndCross {
    Cross cross;
    FindParents findParents;
    ManageItems manageItems;
    @Autowired
    Environment environment;

    @Autowired
    public FindAndCross(Cross cross, FindParents findParents, ManageItems manageItems) {
        this.cross = cross;
        this.findParents = findParents;
        this.manageItems = manageItems;
    }

    public List<Individual> oneIteration() {
        Pair<Individual, Individual> parents = findParents.findParents();
        return cross.cross(parents.getFirst(), parents.getSecond(), manageItems.showAllItems());
    }

    public void allIterations() {
        Integer iterations = Integer.parseInt(Objects.requireNonNull(environment.getProperty("numberOfIterations")));
        for (int i = 0; i < iterations; i++) {
            oneIteration();
        }
    }

}
