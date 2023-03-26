package com.example.knapsack.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Pair<T, A> {
    @Getter
    @Setter
    T first;
    @Getter
    @Setter
    A second;


}
