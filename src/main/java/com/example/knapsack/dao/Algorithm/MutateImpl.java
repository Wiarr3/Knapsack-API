package com.example.knapsack.dao.Algorithm;

import com.example.knapsack.entity.Individual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

@Component
public class MutateImpl implements Mutate {

    @Override
    public String mutate(String code) {
        Integer length = code.length();
        Random rand = new Random();
        int mutationPoint = rand.nextInt(length - 1);
        char[] array = code.toCharArray();
        if (array[mutationPoint] == '1')
            array[mutationPoint] = '0';
        else
            array[mutationPoint] = '1';
        return new String(array);


    }
}
