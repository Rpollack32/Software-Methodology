package com.example.project4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.text.DecimalFormat;

/**
 * This is the controller for current order
 * This handles the cart for the pizza order
 * @author Ryan Pollack, Michael Kang
 */
public class CurrentOrderController
{
    private final double TAX_RATE = 0.06625;
    private final double TOTAL_RATE_AMOUNT = 1 + TAX_RATE;
    
    private DecimalFormat money_Format;
    private static final int INTEGER_DIGITS = 1;
    private static final int DECIMAL_DIGITS = 2;

    private MainController mainController;
    private Alert error_message;
    protected Stage stage;

    @FXML
    protected ListView<Pizza> cart;
    @FXML
    protected TextField orderTotal;
    @FXML
    protected TextField phoneNumber;
    @FXML
    public TextField salesTax;
    @FXML
    public TextField subtotal;

    /**
     * Sets value of prices
     */
    protected void setPriceValues() 
    {
        money_Format = new DecimalFormat("###,###.00");
        money_Format.setMinimumIntegerDigits(INTEGER_DIGITS);
        money_Format.setMinimumFractionDigits(DECIMAL_DIGITS);

        double subTotal = mainController.getSubTotal();

        subtotal.clear();
        subtotal.appendText("" + money_Format.format(subTotal));
        
        salesTax.clear();
        salesTax.appendText("" + money_Format.format(TAX_RATE * subTotal));
        
        orderTotal.clear();
        orderTotal.appendText("" + money_Format.format(TOTAL_RATE_AMOUNT * subTotal));
    }

    /**
     * Adds the current order to the store order
     */
    @FXML
    void placeOrder() 
    {
        if(cart.getItems().size() == 0)				//nothing in there
        {
            error_message = new Alert(Alert.AlertType.ERROR);
            error_message.setContentText("Please add a pizza to the cart first.");
            error_message.showAndWait();
        }

        Order orderToAdd = new Order();				//new order
        orderToAdd.phoneNumber = phoneNumber.getText();
        orderToAdd.pizzas = FXCollections.observableArrayList(mainController.currentOrder.pizzas);
        orderToAdd.orderTotal = Double.parseDouble(orderTotal.getText());
        
        mainController.storeOrders.orders.add(orderToAdd);

        mainController.currentOrder.pizzas.removeAll(mainController.currentOrder.pizzas);
        mainController.phoneNumber.clear();
        stage.close();
    }

    /**
     * Removes a pizza from the cart
     */
    @FXML
    void removePizza() 
    {
        if(cart.getItems().size() == 0)
        {
            error_message = new Alert(Alert.AlertType.ERROR);
            error_message.setContentText("Please add a pizza to the cart.");
            error_message.showAndWait();
            return;
        }

        Pizza pizzaToRemove = cart.getSelectionModel().getSelectedItem();

        if(pizzaToRemove == null)					//no pizza to remove
        {
            error_message = new Alert(Alert.AlertType.ERROR);
            error_message.setContentText("Please select a pizza from the cart to remove.");
            error_message.showAndWait();
            return;
        }

        mainController.currentOrder.pizzas.remove(pizzaToRemove);
        cart.getItems().remove(pizzaToRemove);
        this.setPriceValues();
    }

    /**
     * This method creates a connection between this controller and the main controller
     * @param controller - the main controller
     */
    public void setMainController(MainController controller){
        this.mainController = controller;
    }


}