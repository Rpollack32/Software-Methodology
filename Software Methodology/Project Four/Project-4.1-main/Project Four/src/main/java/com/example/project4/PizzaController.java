package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
import static com.example.project4.PizzaMaker.createPizza;

/**
 * Controller for GUI that adds pizza to order
 * @author Ryan Pollack, Michael Kang
 */
public class PizzaController
{
    protected MainController mainController;
    protected Pizza pizza;
    private Alert displayAlert;
    
    protected ObservableList<Topping> DEFAULT_TOPPINGS = FXCollections.observableArrayList();
    protected int MIN_TOPPINGS;
    private final int MAX_TOPPINGS = 7;

    private DecimalFormat money_Format;
    private static final int DECIMAL_DIGITS = 2;
    private static final int INTEGER_DIGITS = 1;

    @FXML
    protected Button pizzaLabel;
    @FXML
    protected ImageView pizzaImage;
    @FXML
    protected ComboBox<Size> pizzaSize;
    @FXML
    protected TextField price;
    @FXML
    protected ListView<Topping> toppingsList;
    @FXML
    protected ListView<Topping> selectedToppings;

    /**
     * Forms a connection between this controller and the main controller
     * @param controller - the main controller
     */
    public void setMainController(MainController controller)
    {
        this.mainController = controller;
    }

    /**
     * Initializes the pizza ordering stage
     */
    @FXML
    public void initialize() 
    {
        ObservableList<Size> pizzaSizes = FXCollections.observableArrayList(Size.values());
        ObservableList<Topping> toppings = FXCollections.observableArrayList(Topping.values());
        toppingsList.setItems(toppings);
        pizzaSize.setItems(pizzaSizes);

        pizza = createPizza("Deluxe");
        money_Format = new DecimalFormat("###,###.00");
        money_Format.setMinimumFractionDigits(DECIMAL_DIGITS);
        money_Format.setMinimumIntegerDigits(INTEGER_DIGITS);
    }

    /**
     * Sets default values for the toppings, size, and price
     */
    public void setDefaultValues() 
    {
        toppingsList.getItems().removeAll(DEFAULT_TOPPINGS);
        selectedToppings.getItems().addAll(DEFAULT_TOPPINGS);

        pizza.toppings = new ArrayList(selectedToppings.getItems());
        
        pizza.size = Size.Small;
        pizzaSize.getSelectionModel().selectFirst();
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }
    
    /**
     * Changes the pizza size and updates its price
     */
    @FXML
    void changePizzaSize()
    {
        pizza.size = pizzaSize.getValue();
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }

    /**
     * Adds another pizza to the current order
     */
    @FXML
    void addPizzaToOrder()
    {
        mainController.currentOrder.pizzas.add(pizza);
    
        displayAlert = new Alert(Alert.AlertType.INFORMATION); 		//Tell the customer that the pizza has been added
        displayAlert.setTitle("Order");
        displayAlert.setContentText("Pizza added to the order!");
        displayAlert.showAndWait();
    }

    /**
     * Adds toppings on the pizza
     */
    @FXML
    void addTopping() 
    {
        if(selectedToppings.getItems().size() == MAX_TOPPINGS)		//too many toppings (max is 7)
        {
        	displayAlert = new Alert(Alert.AlertType.ERROR);
        	displayAlert.setTitle("Adding toppings");
        	displayAlert.setContentText("Unable to add more toppings. Maximum toppings amount is 7!");
        	displayAlert.showAndWait();
            return;
        }

        Topping selectedTopping = toppingsList.getSelectionModel().getSelectedItem();

        if(selectedTopping == null){								//didn't select topping to add
        	displayAlert = new Alert(Alert.AlertType.ERROR);
        	displayAlert.setTitle("Adding toppings");
        	displayAlert.setContentText("Please select a topping to add!");
        	displayAlert.showAndWait();
            return;
        }

        // removes topping from "additional toppings" list and adds it to "selected toppings" list 
        //then updates toppings on pizza and adjusts the price
        toppingsList.getItems().remove(selectedTopping);
        selectedToppings.getItems().add(selectedTopping);
        pizza.toppings = new ArrayList(selectedToppings.getItems());
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }

    /**
     * Removes a topping from  pizza
     */
    @FXML
    void removeTopping() 
    {
        if(DEFAULT_TOPPINGS.contains(selectedToppings.getSelectionModel().getSelectedItem()))  //give error for attempts to move essential topping
        {
        	displayAlert = new Alert(Alert.AlertType.ERROR);
        	displayAlert.setTitle("Removing toppings");
        	displayAlert.setContentText("Cannot remove this essential topping.");
        	displayAlert.showAndWait();
            return;
        }

        Topping selectedTopping = selectedToppings.getSelectionModel().getSelectedItem();

        if(selectedTopping == null){								//didn't select topping to remove
        	displayAlert = new Alert(Alert.AlertType.ERROR);
        	displayAlert.setTitle("Removing toppings");
        	displayAlert.setContentText("Please select a topping to remove!");
        	displayAlert.showAndWait();
            return;
        }

        // removes topping from "selected toppings" list and adds it to "additional toppings" list
        //then updates toppings on pizza and adjusts the price
        selectedToppings.getItems().remove(selectedTopping);
        toppingsList.getItems().add(selectedTopping);
        pizza.toppings = new ArrayList(selectedToppings.getItems());
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }
}