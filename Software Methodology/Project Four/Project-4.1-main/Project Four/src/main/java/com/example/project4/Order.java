package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is a class that holds the phone number, pizzas, and $$ total for each pizza order
 * @author Ryan Pollack, Michael Kang
 */
public class Order 
{
    protected ObservableList<Pizza> pizzas;
    String phoneNumber;
    double orderTotal;

    /**
     * This is the constructor for the order class
     */
    public Order()
    {
        this.pizzas = FXCollections.observableArrayList();
        this.phoneNumber = "";
        this.orderTotal = 0;
    }
}