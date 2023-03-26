package com.example.knapsack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    Integer id;
    @Getter
    @Setter
    Integer quality;
    @Getter
    @Setter
    Integer weight;

    public Item(Integer quality, Integer weight) {
        this.weight = weight;
        this.quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(quality, item.quality) && Objects.equals(weight, item.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quality, weight);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
