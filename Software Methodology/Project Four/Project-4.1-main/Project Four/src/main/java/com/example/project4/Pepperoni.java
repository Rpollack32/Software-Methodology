package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class creates and handles the pepporoni pizza object
 * @author Ryan Pollack, Michael Kang
 */
public class Pepperoni extends Pizza
{
    private final double SMALL_PRICE = 8.99;
    private final double ESSENTIAL_TOPPINGS = 1;

    /**
     * This is the constructor for the pepperoni pizza object
     * @param toppings - toppings on the pizza
     * @param size - size of the pizza
     */
    public Pepperoni(ArrayList<Topping> toppings, Size size)
    {
        this.toppings = toppings;
        this.size = size;
    }
    
    /**
     * This is the default constructor for the pepperoni pizza (sets default to small)
     */
    public Pepperoni()
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

        if(numberOfToppings > 1){
            totalPrice += EXTRA_TOPPINGS_CHARGE * (numberOfToppings - ESSENTIAL_TOPPINGS);
        }

        return totalPrice;
    }

    /**
     * Creates a string representation of the pepperoni pizza order
     * @return - string form of pizza order
     */
    @Override
    public String toString() 
    {  
        DecimalFormat money_Format = new DecimalFormat("###,###.00");
        
        money_Format.setMinimumIntegerDigits(INTEGER_DIGITS);
        money_Format.setMinimumFractionDigits(DECIMAL_DIGITS);

        StringBuilder sb = new StringBuilder("Pepperoni pizza, ");

        for(Topping topping: toppings){
            sb.append(topping + ", ");
        }

        sb.append(money_Format.format(this.price()));
        
        return sb.toString();
    }
}