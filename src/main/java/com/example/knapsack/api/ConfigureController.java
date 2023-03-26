package com.example.knapsack.api;

import com.example.knapsack.dao.ManageProperties;
import com.example.knapsack.entity.Individual;
import com.example.knapsack.entity.Item;
import com.example.knapsack.service.KnapsackService;
import com.example.knapsack.utils.exceptions.WrongConfigParamsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConfigureController {
    private final KnapsackService service;

    @Autowired
    public ConfigureController(KnapsackService service) {
        this.service = service;
    }

    @PatchMapping("/weight")
    public Integer postWeight(@RequestParam Integer maxWeight) {
        if (maxWeight > 0)
            return service.setWeight(maxWeight);
        else
            throw new WrongConfigParamsException();
    }

    @PatchMapping("/length")
    public Integer postLength(@RequestParam Integer length) {
        if (length > 0)
            return service.setLength(length);
        else
            throw new WrongConfigParamsException();
    }

    @GetMapping("/randomItems")
    public List<Item> randomItems() {
        return service.randomItems();
    }

    @GetMapping("/{id}")
    public Item showItem(@PathVariable Integer id) {
        return service.showItem(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllItems() {
        service.deleteItems();
    }

    @GetMapping("/randomPop")
    public List<Individual> randomPop() {
        return service.randomPopulation();
    }

    @GetMapping("/iter")
    public List<Individual> oneIter() {
        return service.oneIteration();
    }


}
