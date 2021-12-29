package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class creates and handles the deluxe pizza object
 * @author Ryan Pollack, Michael Kang
 */
public class Deluxe extends Pizza
{
    private final double SMALL_PRICE = 12.99;
    private final int ESSENTIAL_TOPPINGS = 5;

    /**
     * This is the constructor for the deluxe pizza object
     * @param toppings - toppings on the pizza
     * @param size - size of the pizza
     */
    public Deluxe(ArrayList<Topping> toppings, Size size)
    {
        this.toppings = toppings;
        this.size = size;
    }

    /**
     * This is the default constructor for the deluxe pizza (sets default to small)
     */
    public Deluxe()
    {
        this.toppings = new ArrayList<Topping>();
        this.size = Size.Small;
    }
    
    /**
     * This is the price method which sets the prices based on size and toppings
     */
    @Override
    public double price()
    {
        double totalPrice = SMALL_PRICE;
        int numberOfToppings = this.toppings.size();

        if(this.size == Size.Medium)
        {
            totalPrice += UPGRADE_TO_MEDIUM;
        }
        else if(this.size == Size.Large)
        {
            totalPrice += UPGRADE_TO_LARGE;
        }

        if(numberOfToppings > 5)
        {
            totalPrice += (numberOfToppings - ESSENTIAL_TOPPINGS) * EXTRA_TOPPINGS_CHARGE;
        }

        return totalPrice;
    }

    /**
     * Creates a string representation of the delxue pizza order
     * @return - string form of pizza order
     */
    @Override
    public String toString() 
    {
        DecimalFormat money_Format = new DecimalFormat("###,###.00");
  
        money_Format.setMinimumFractionDigits(INTEGER_DIGITS);
        money_Format.setMinimumIntegerDigits(DECIMAL_DIGITS);
        
        StringBuilder sb = new StringBuilder("Deluxe pizza, ");

        for(Topping topping: toppings)
        {
            sb.append(topping + ", ");
        }

        sb.append(money_Format.format(this.price()));
        
        return sb.toString();
    }
}