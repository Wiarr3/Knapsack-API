package com.example.knapsack.dao;

import com.example.knapsack.entity.Item;
import com.example.knapsack.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManageItems {
    ItemRepository repository;

    @Autowired
    public ManageItems(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> showAllItems() {
        return repository.findAll();
    }

    public Item showItem(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public void deleteItems() {
        repository.deleteAll();
    }
}
