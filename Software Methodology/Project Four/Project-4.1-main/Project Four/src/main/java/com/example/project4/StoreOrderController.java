package com.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This is the controller for exporting the store orders 
 * @author Ryan Pollack, Michael Kang
 */
public class StoreOrderController 
{
    private MainController mainController;
    @FXML
    private TextField orderTotal;
    @FXML
    protected ComboBox<String> phoneNumbers;
    @FXML
    protected ListView<Pizza> storeOrder;

    private Alert displayAlert;    
    private DecimalFormat money_Format;
    private static final int INTEGER_DIGITS = 1;
    private static final int DECIMAL_DIGITS = 2;
    
    /**
     * Forms the connection between this controller to the main controller
     * @param controller - the main controller
     */
    public void setMainController(MainController controller)
    {
        this.mainController = controller;
    }

    @FXML
    public void initialize() 
    {
        money_Format = new DecimalFormat("###,###.00");
        money_Format.setMinimumIntegerDigits(INTEGER_DIGITS);
        money_Format.setMinimumFractionDigits(DECIMAL_DIGITS);
    }
    
    /**
     * Loads orders for a specific phone number
     */
    @FXML
    void loadOrder() 
    {
        int indexOfOrder = phoneNumbers.getSelectionModel().getSelectedIndex();

        if(indexOfOrder == -1){
            return;
        }

        storeOrder.getItems().clear();
        storeOrder.getItems().addAll(mainController.storeOrders.orders.get(indexOfOrder).pizzas);

        orderTotal.clear();
        orderTotal.appendText(money_Format.format(mainController.storeOrders.orders.get(indexOfOrder).orderTotal));
    }

    /**
     * Cancels the spefic order
     */
    @FXML
    void cancelOrder() 
    {
        int indexOfOrder = phoneNumbers.getSelectionModel().getSelectedIndex();

        if(indexOfOrder == -1)
        {
        	displayAlert = new Alert(Alert.AlertType.ERROR);
        	displayAlert.setContentText("Please place an order first!");
        	displayAlert.showAndWait();
            return;
        }

        storeOrder.getItems().clear();
        orderTotal.clear();
        phoneNumbers.getItems().remove(indexOfOrder);
        mainController.storeOrders.orders.remove(indexOfOrder);
    }

    /**
     * Exports the store orders by text file
     */
    @FXML
    void exportStoreOrders() throws FileNotFoundException 
    {
        mainController.storeOrders.export();
    }

}