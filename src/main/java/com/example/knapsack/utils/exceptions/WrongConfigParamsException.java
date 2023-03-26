package com.example.knapsack.utils.exceptions;

public class WrongConfigParamsException extends IllegalArgumentException {
    public WrongConfigParamsException() {
        super("Length and MaxWeight have can't be equal or below zero");
    }
}
