package projectOne;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This is the main running class which takes valid inputs and performs the functions below, as given
 * by the instructions in problem 1
 * It takes in the command inputs of A, D, L, R, P, PD, PG, Q, which then calls for further instructions based on command.
 * Format is: Command:  Title, Author, Genre, Date in that order.
 * 			--Commands--	
 * 				A - Adds an album - All 4 inputs
 * 				D - Deletes an album - Title, Author inputs
 * 				L - Lends out an album - Title, Author inputs
 * 				R - Return an album - Title, Author inputs
 * 				P - Display collection without specific order - no inputs
 * 				PD- Display collection sorted by release date - no inputs
 * 				PG- Display collection sorted by genre - no inputs
 * 				Q - Stop program execution and display "Collection Manager Terminated"
 * @author Ryan Pollack, Michael Kang
 */
public class CollectionManager
{
	private Collection collection = new Collection();
	
    /** 
     * While-loop that continuously reads the command line from the console
     * Stops when Q is entered
     */
    public void run()
    {
        String currentString = null;
        boolean runAgain = true;
        Scanner scan = new Scanner(System.in);
    	
        System.out.println("Collection Manager starts running.");

        while(runAgain){
            currentString = scan.nextLine();
            StringTokenizer token = new StringTokenizer(currentString, ",");
            String[] input = new String[token.countTokens()];
            
            int i = 0;
            while(token.hasMoreTokens())
            {
                input[i] = token.nextToken();
                i++;
            }
            
            if(input.length != 0){
                runAgain = checkCommand(input);
            }
        }
    }

    /** 
     * Used to call method associated with given command input, or checks to see if input is valid at all
     * @param input is the commandInput array where Index 0 = Command, 1 = Title, 2 = Author, 3 = Genre, 4 = Date
     * @return returns false if command is "Q"
     */
    public boolean checkCommand(String[] commandInput)
    {
    	
    	if(commandInput[0].equals("A"))
    	{
    		addAlbum(commandInput);
    	}
    	else if(commandInput[0].equals("D"))
    	{
    		deleteAlbum(commandInput);
    	}
    	else if(commandInput[0].equals("L"))
    	{
    		lendAlbum(commandInput);
    	}
    	else if(commandInput[0].equals("R"))
    	{
    		returnAlbum(commandInput);
    	}
    	else if(commandInput[0].equals("P"))
    	{
    		printAlbums(commandInput, "no order");
    	}
    	else if(commandInput[0].equals("PD"))
    	{
    		printAlbums(commandInput, "release date");
    	}
    	else if(commandInput[0].equals("PG"))
    	{
    		printAlbums(commandInput, "genre");
    	}
    	else if(commandInput[0].equals("Q"))
    	{
    		System.out.println("Collection Manager Terminated");
    		return false;
    	}
    	else
    	{
    		System.out.println("Invalid command!");
    	}
    	
        return true;
    }


    /** 
     * This adds the album to collection and checks to make sure there are 5 inputs (Command and 4 inputs)
     * as well as valid date
     * @param input is the commandInput array from above (Index 0 = Command, 1 = Title, 2 = Author, 3 = Genre, 4 = Date)
     */
    public void addAlbum(String[] commandInput)
    {
    	Album temp = new Album(commandInput[1], commandInput[2], commandInput[3], commandInput[4]);
    	
    	if(commandInput.length != 5)
    	{
            System.out.println("Invalid command!");
            return;
        }
        
    	else if(collection.add(temp))
        {
            System.out.println(temp.toString() + " >> added.");
        }
        else
        {
            System.out.println(temp.toString() + " >> is already in the collection.");
        }
    }

    /** 
     * This deletes the album from collection and checks to make sure there are 3 inputs (Command and 2 inputs)
     * @param input is the commandInput array from above (Index 0 = Command, 1 = Title, 2 = Author, 3 = Genre, 4 = Date)
     */
    public void deleteAlbum(String[] commandInput)
    {
        if(commandInput.length != 3){
            System.out.println("Invalid command!");
        }
        else if(collection.remove(new Album(commandInput[1], commandInput[2])))
        {
            System.out.println(commandInput[1] + "::" + commandInput[2] + " >> deleted.");
        }
        else{
            System.out.println(commandInput[1] + "::" + commandInput[2] + " >> is not in the collection.");
        }
    }

    /** 
     * This lends the album from collection and checks to make sure there are 5 inputs (Command and 2 inputs)
     * @param input is the commandInput array from above (Index 0 = Command, 1 = Title, 2 = Author, 3 = Genre, 4 = Date)
     */
    public void lendAlbum(String[] commandInput)
    {
        if(commandInput.length != 3){
            System.out.println("Invalid command!");
        }     
        else if(collection.lendingOut(new Album(commandInput[1], commandInput[2])))
        {
            System.out.println(commandInput[1] + "::" + commandInput[2] + " >> lending out and set to not available.");
        }
        else
        {
            System.out.println(commandInput[1] + "::" + commandInput[2] + " >> is not available.");
        }
    }

    /** 
     * This returns the album to collection and checks to make sure there are 5 inputs (Command and 2 inputs)
     * @param input the the commandInput array from above (Index 0 = Command, 1 = Title, 2 = Author, 3 = Genre, 4 = Date)
     */
    public void returnAlbum(String[] commandInput)
    {
        if(commandInput.length != 3){
            System.out.println("Invalid command!");
        }       
        else if(collection.returnAlbum(new Album(commandInput[1], commandInput[2]))){
            System.out.println(commandInput[1] + "::" + commandInput[2] + " >> returning and set to available.");
        }
        else{
            System.out.println(commandInput[1] + "::" + commandInput[2] + " >> return could not be completed.");
        }
    }

    /** 
     * This prints the albums in a desired order
     * @param input is the commandInput array from above
     * @param String type which will be either "no order", "release date", or "genre"
     */
    public void printAlbums(String[] commandInput, String orderType)
    {
        if(collection.size() == 0)
        {
            System.out.println("The collection is empty!");
            return;
        }
        switch(orderType)
        {
            case "no order":
                System.out.println("*List of albums in the collection.");
                collection.print();
                break;
            case "release date":
                System.out.println("*Album collection by the release dates.");
                collection.printByReleaseDate();
                break;
            default:
                System.out.println("*Album collection by genre.");
                collection.printByGenre();
                break;
        }
        System.out.println("*End of list");
    }
}