package com.example.project4;

/**
 * Creates an instance of a pizza
 * @param type - the type of pizza
 * @return the pizza
 * @author Ryan Pollack, Michael Kang
 */
public class PizzaMaker 
{
    public static Pizza createPizza(String type)
    {
        if(type.equals("Deluxe"))
        {
            return new Deluxe();
        }
        else if(type.equals("Hawaiian"))
        {
            return new Hawaiian();
        }
        else if(type.equals("Pepperoni"))
        {
            return new Pepperoni();
        }

        return null;
    }
}

