package com.example.project4;

import java.util.ArrayList;

/**
 * This is the pizza class that deals with size and change of price
 * @author Ryan Pollack, Michael Kang
 */
public abstract class Pizza 
{
    public final double UPGRADE_TO_MEDIUM = 2.00;
    public final double UPGRADE_TO_LARGE = 4.00;
    public final double EXTRA_TOPPINGS_CHARGE = 1.49;
    
    public static final int INTEGER_DIGITS = 1;
    public static final int DECIMAL_DIGITS = 2;

    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;

    /**
     * Determines the pizza price
     * @return - the pizza price
     */
    public abstract double price();
}