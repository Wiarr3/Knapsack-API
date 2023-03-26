package com.example.knapsack.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@NoArgsConstructor
@Entity
public class Individual {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    Integer id;
    @Getter
    String solutionArray;
    @Getter
    @Setter
    Integer quality;
    @Getter
    @Setter
    Integer weight;

    @Transient
    @Autowired
    Environment environment;


    public Individual(String solutionArray, List<Item> items) {
        this.solutionArray = solutionArray;
        quality = calculateQuality(items);
        weight = calculateWeight(items);

    }
    /*
    @PostConstruct
    public void init() throws InterruptedException {
        Thread.sleep(1);
        Integer maxWeight = Integer.parseInt(Objects.requireNonNull(environment.getProperty("knacksackMaxWeight")));
        if(weight>maxWeight)
            quality = 0;
    }

     */

    public Integer calculateQuality(List<Item> list) {
        String temp[] = solutionArray.split("");
        return list.stream()
                .filter(item -> temp[list.indexOf(item)].equals("1"))
                .mapToInt(Item::getQuality)
                .sum();
    }

    public Integer calculateWeight(List<Item> list) {
        String temp[] = solutionArray.split("");
        return list.stream()
                .filter(item -> temp[list.indexOf(item)].equals("1"))
                .mapToInt(Item::getWeight)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individual that = (Individual) o;
        return Objects.equals(solutionArray, that.solutionArray);
    }

    @Override
    public int hashCode() {
        return Objects.hash(solutionArray);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
