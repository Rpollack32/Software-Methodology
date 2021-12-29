package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class creates and handles the hawaiian pizza object
 * @author Ryan Pollack, Michael Kang
 */
public class Hawaiian extends Pizza
{
    private final double SMALL_PRICE = 10.99;
    private final int ESSENTIAL_TOPPINGS = 2;

    /**
     * This is the constructor for the hawaiian pizza object
     * @param toppings - toppings on the pizza
     * @param size - size of the pizza
     */
    public Hawaiian(ArrayList<Topping> toppings, Size size)
    {
        this.toppings = toppings;
        this.size = size;
    }

    /**
     * This is the default constructor for hawaiian pizza (sets default to small)
     */
    public Hawaiian()
    {
        this.toppings = new ArrayList<Topping>();
        this.size = Size.Small;
    }
    
    /**
     * This is the price method, which returns the price of a pizza
    */
    @Override
    public double price()
    {
        double totalPrice = SMALL_PRICE;
        int numToppings = this.toppings.size();

        if(this.size == Size.Medium)
        {
            totalPrice += UPGRADE_TO_MEDIUM;
        }
        else if(this.size == Size.Large)
        {
            totalPrice += UPGRADE_TO_LARGE;
        }

        if(numToppings > 2)
        {
            totalPrice += (numToppings - ESSENTIAL_TOPPINGS) * EXTRA_TOPPINGS_CHARGE;
        }

        return totalPrice;
    }

    /**
     * Creates a string representation of the hawaiian pizza order
     * @return - string form of pizza order
     */
    @Override
    public String toString() 
    {
        DecimalFormat money_Format = new DecimalFormat("###,###.00");
        
        money_Format.setMinimumIntegerDigits(INTEGER_DIGITS);
        money_Format.setMinimumFractionDigits(DECIMAL_DIGITS);

        StringBuilder sb = new StringBuilder("Hawaiian pizza, ");

        for(Topping topping: toppings){
            sb.append(topping + ", ");
        }

        sb.append(money_Format.format(this.price()));
        
        return sb.toString();
    }
}
