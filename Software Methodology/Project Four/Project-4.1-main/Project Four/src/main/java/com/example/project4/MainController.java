package com.example.project4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.project4.PizzaMaker.createPizza;

/**
 * This is the controller class for the primary stage
 * @author Ryan Pollack, Michael Kang
 */

public class MainController 
{

    protected final Order currentOrder = new Order();
    protected final StoreOrders storeOrders = new StoreOrders();
    
    private Alert displayAlert;

    @FXML
    protected TextField phoneNumber;

    /**
     * Checks if a phone number is 10 digits long and validates it if so
     * @param phoneNumber - the number to validate
     * @return true if 10 digits, false if not
     */
    public static boolean checkPhoneNumberLength(String phoneNumber)
    {
        try
        {
            Long.parseLong(phoneNumber);
            return phoneNumber.length() == 10; 
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }
    
    /**
     * Gets the total price of all the pizzas in the current order
     * @return - subtotal of the order
     */
    public double getSubTotal() 
    {
        double Subtotal = 0;

        for(Pizza pizza: currentOrder.pizzas)
        {
            Subtotal += pizza.price();
        }

        return Subtotal;
    }

    /**
     * Displays alert for order of deluxe pizza
     * @throws IOException - throws exception if fxml is non-existent
     */
    @FXML
    void viewDeluxeOrder() throws IOException 
    {
    	displayAlert = new Alert(Alert.AlertType.CONFIRMATION);
    	displayAlert.setTitle("Ordering Delxue Pizza");
    	displayAlert.setContentText("Starting a new order!");
    	displayAlert.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);
        pizzaView.pizzaLabel.setText("Deluxe");
        pizzaView.MIN_TOPPINGS = 5;

        pizzaView.pizza = createPizza("Deluxe");

        pizzaView.DEFAULT_TOPPINGS = FXCollections.observableArrayList(
                Topping.Sausage, Topping.GreenPepper, Topping.Onion, Topping.Pepperoni, Topping.Mushroom);
        pizzaView.setDefaultValues();

        Stage stage = new Stage();
        Scene scene = new Scene(root, 430, 516);
        stage.setTitle("Customize your Deluxe Pizza");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Displays alert for order of hawaiian pizza
     * @throws IOException - throws exception if fxml is non-existent
     */
    @FXML
    void viewHawaiianOrder() throws IOException 
    {
    	displayAlert = new Alert(Alert.AlertType.CONFIRMATION);
    	displayAlert.setTitle("Ordering Hawaiian Pizza");
    	displayAlert.setContentText("Starting a new order!");
    	displayAlert.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        pizzaView.pizzaLabel.setText("Hawaiian");
        pizzaView.MIN_TOPPINGS = 2;
 
        pizzaView.pizza = createPizza("Hawaiian");

        pizzaView.DEFAULT_TOPPINGS = FXCollections.observableArrayList(Topping.Pineapple, Topping.Cheese);
        pizzaView.setDefaultValues();

        Stage stage = new Stage();
        Scene scene = new Scene(root, 430, 516);
        stage.setTitle("Customize your Hawaiian Pizza");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Displays alert for order of pepperoni pizza
     * @throws IOException - throws exception if fxml is non-existent
     */
    @FXML
    void viewPepperoniOrder() throws IOException 
    {
    	displayAlert = new Alert(Alert.AlertType.CONFIRMATION);
    	displayAlert.setTitle("Ordering Pepperoni Pizza");
    	displayAlert.setContentText("Starting a new order!");
        displayAlert.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        pizzaView.pizzaLabel.setText("Pepperoni");
        pizzaView.MIN_TOPPINGS = 1;

        pizzaView.pizza = createPizza("Pepperoni");

        pizzaView.DEFAULT_TOPPINGS = FXCollections.observableArrayList(Topping.Pepperoni);
        pizzaView.setDefaultValues();

        Stage stage = new Stage();
        Scene scene = new Scene(root, 430, 516);
        stage.setTitle("Customize your Pepperoni Pizza");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    /**
     * Displays the alert for current order window
     * @throws IOException - if fxml file doesn't exist
     */
    @FXML
    void viewCurrentOrder() throws IOException 
    {     
        if(phoneNumber.getText().isEmpty()){						//there is no phone number
        	displayAlert = new Alert(Alert.AlertType.ERROR);
        	displayAlert.setTitle("Phone number");
        	displayAlert.setContentText("Please provide a valid phone number.");
        	displayAlert.showAndWait();
            return;
        }

        if(!checkPhoneNumberLength(phoneNumber.getText()))			//phone number isn't valid
        {			
        	displayAlert = new Alert(Alert.AlertType.ERROR);
        	displayAlert.setTitle("Phone number");
        	displayAlert.setContentText("Phone number is not valid.");
        	displayAlert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("currentorder-view.fxml"));
        Parent root = loader.load();
        CurrentOrderController currentOrderView = loader.getController();
        currentOrderView.setMainController(this);

        currentOrderView.phoneNumber.appendText(phoneNumber.getText());
        currentOrderView.cart.setItems(currentOrder.pizzas);
        currentOrderView.setPriceValues();

        Stage stage = new Stage();
        Scene scene = new Scene(root, 600, 400);
        
        stage.setTitle("Order Detail");
        stage.setScene(scene);
        currentOrderView.stage = stage;
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Displays alerts for the store order
     * @throws IOException - throws exception if fxml is non-existent
     */
    @FXML
    void viewStoreOrders() throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("storeorder-view.fxml"));
        Parent root = loader.load();
        
        StoreOrderController storeOrderView = loader.getController();
        storeOrderView.setMainController(this);

        for(Order order: storeOrders.orders) 
        {
            storeOrderView.phoneNumbers.getItems().add(order.phoneNumber);
        }

        Stage stage = new Stage();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Store Orders");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}