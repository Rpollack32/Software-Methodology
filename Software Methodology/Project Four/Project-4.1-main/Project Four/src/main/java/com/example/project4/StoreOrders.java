package com.example.project4;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This is the store orders class which keeps track of the orders for exporting them
 * @author Ryan Pollack, Michael Kang
 */
public class StoreOrders 
{
    protected ArrayList<Order> orders;

    /**
     * This is the constructor for the store orders class
     */
    public StoreOrders(){
        this.orders = new ArrayList<>(); }

    /**
     * This method exports the store orders to a text file
     */
    public void export() throws FileNotFoundException 
    {
        FileChooser fileChooser = new FileChooser();
        
        fileChooser.setTitle("Open Target File for the Export");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        
        Stage stage = new Stage();
        File targetFile = fileChooser.showSaveDialog(stage);
        PrintWriter printWriter = new PrintWriter(targetFile);
        
        for(int i = 0; i < orders.size(); i++)
        {
            printWriter.println("Phone Number: " + orders.get(i).phoneNumber);
            printWriter.println("Pizzas:");
            
            for(int j = 0; j < orders.get(i).pizzas.size(); j++)
            {
                printWriter.println(orders.get(i).pizzas.get(j).toString());
            }
            
            printWriter.println("Total: " + orders.get(i).orderTotal);
            printWriter.println();
        }
        
        printWriter.close();
    }
}