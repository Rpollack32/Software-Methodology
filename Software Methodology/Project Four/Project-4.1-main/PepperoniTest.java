package com.example.project4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * This is the PepperoniTest class, which tests the price() method
 * @author Ryan Pollack, Michael Kang
 *
 */
class PepperoniTest extends Pepperoni {

	@Test
	void testPrice() {
		ArrayList<Topping> list = new ArrayList<Topping>();
		list.add(Topping.Pepperoni);
		Pepperoni pizza1 = new Pepperoni(list, Size.Small);
		assertTrue(pizza1.price()); //small pepperoni pizza (1 topping)
		
		list.add(Topping.Beef);
		Pepperoni pizza2 = new Pepperoni(list, Size.Small);
		assertTrue(pizza2.price()); //small pepperoni pizza (2 toppings)
		
		list.remove(1);
		Pepperoni pizza3 = new Pepperoni(list, Size.Medium);
		assertTrue(pizza3.price()); //medium pepperoni pizza (1 topping)
		
		list.add(Topping.Chicken);
		Pepperoni pizza4 = new Pepperoni(list, Size.Medium);
		assertTrue(pizza4.price()); //medium pepperoni pizza (2 toppings)
		
		list.remove(1);
		Pepperoni pizza5 = new Pepperoni(list, Size.Large);
		assertTrue(pizza5.price()); //large pepperoni pizza (1 topping)
		
		list.add(Topping.Mushroom);
		Pepperoni pizza6 = new Pepperoni(list, Size.Large);
		assertTrue(pizza6.price()); //large pepperoni pizza (2 toppings)
		
		list.add(Topping.BlackOlives);
		list.add(Topping.Onion);
		list.add(Topping.Ham);
		list.add(Topping.GreenPepper);
		list.add(Topping.Pineapple); 
		list.add(Topping.Sausage); //8 toppings
		
		Pepperoni pizza7 = new Pepperoni(list, Size.Small);
		assertFalse(pizza7.price()); //small pepperoni pizza (8 toppings)
		
		Pepperoni pizza8 = new Pepperoni(list, Size.Medium);
		assertFalse(pizza8.price()); //medium pepperoni pizza (8 toppings)
		
		Pepperoni pizza9 = new Pepperoni(list, Size.Large);
		assertFalse(pizza9.price()); //large pepperoni pizza (8 toppings)
	}

}
