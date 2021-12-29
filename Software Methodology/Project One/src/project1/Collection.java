package projectOne;
	
	/**
	*This is the Collection class, which is an array-based container class that holds a list of albums that the user owns.
	*	
	*@author Ryan Pollack, Michael Kang 
	*/

public class Collection {
	
	private static final int NOT_FOUND = -999;
	private Album[] albums;
	private int numAlbums; //number of albums currently in the collection
	
	/**
	 * This is the collection constructor, which sets new album to length of 4
	 */
    public Collection(){
        albums = new Album[4];
    }
	
	/**
	 * This is the find method, which finds and returns the index of an album if it is in the array.
	 * If the album cannot be found, then it returns NOT_FOUND
	 * @param album
	 * @return integer
	 */
	private int find(Album album) { //find the album index, or return NOT_FOUND
		for(int i = 0; i < albums.length; i++) {
			if(albums[i] == null) {
				return NOT_FOUND;
			}
			if(albums[i].equals(album)) {
				return i;
			}
		}
		return NOT_FOUND;
	}
	
	/**
	 * This is the grow method, which increases the capacity of the array by 4
	 * @param 
	 * @return 
	 */
	private void grow() { //increase the capacity of the array list by 4
		int n = albums.length + 4;
		Album[] temp = new Album[n];
		
		for(int i = 0; i < albums.length; i++) {
			temp[i] = albums[i];
		}
	}
	
	/**
	 * This it the add method, which adds an album to the array
	 * Checks if not found or full 
	 * @param album
	 * @return boolean
	 */
	public boolean add(Album album) {
		int counter = 0;
		
        if(find(album) != NOT_FOUND){
            return false;
        }
        
		for(int i = 0; i < albums.length; i++) { 
			if(albums[i] != null) { //check if array is full
				counter++;
			}
		}
		if(counter == albums.length) {
			this.grow();
		}
        albums[albums.length-1] = album;;
        return true;
	}
	
	/**
	 * This is the remove method, which deletes an album in an array
	 * Checks if not found
	 * @param album
	 * @return true if album is deleted, false if doesn't exist
	 */
	public boolean remove(Album album) {
	    if(find(album) == NOT_FOUND){
	            return false;
	    }
	    
		for(int i = 0; i < albums.length; i++) { 
			if(albums[i].equals(album)) {
				albums[i] = null;
			}
			else
				return false;
		}
		numAlbums--;
		return true;
	}
	
	/**
	 * This is the lendingOut method, which sets album to not available
	 * Checks if not found
	 * @param album
	 * @return true if album is lent, false if doesn't exist
	 */
	public boolean lendingOut(Album album) { //set to not available
        if(find(album) == NOT_FOUND){
            return false;
        }
        
        if(albums[find(album)].getAvailable()){
            albums[find(album)].setAvailable();
            return true;
		}
        
        return false;
	}
	
	/**
	 * This is the return method, which sets album to available
	 * Checks if not found
	 * @param album
	 * @return true if album is returned, false if doesn't exist
	 */
	public boolean returnAlbum(Album album) { //set to available
		if(find(album) == NOT_FOUND){
            return false;
		}
		
        if(albums[find(album)].getAvailable() == false){
            albums[find(album)].setAvailable();
            return true;		
		}
        
		return false;
	}
	
	/**
	 * This is the print method, which prints the array of albums without specifying the order
	 */
	public void print() { //display the list without specifying the order
		for(int i = 0; i < albums.length; i++) {
			System.out.println(albums[i]);
		}
	}
	
	/**
	 * This is the insertionSortRd method, which sorts an array by release date
	 * @param albums
	 */
	public static void insertionSortRD(Album[] albums) {
		for(int i = 1; i < albums.length; i++) {
			Album current = albums[i];
			int j = i -1;
			while(j >= 0 && current.getReleaseDate().getYear() <= albums[j].getReleaseDate().getYear() 
					&& current.getReleaseDate().getMonth() <= albums[j].getReleaseDate().getMonth() &&
					current.getReleaseDate().getDay() <= albums[j].getReleaseDate().getDay()) {
				albums[j+1] = albums[j];
				j--;
			}
			albums[j+1] = current;
		}
	}
	
	/**
	 * This is the printByReleaseDate method, which prints the array of albums by release date
	 */
	public void printByReleaseDate() { 
		insertionSortRD(albums);
		for(int i = 0; i < albums.length; i++) {
			System.out.println(albums[i]);
		}
	}
	
	/**
	 * This is the alphabetize method, which sorts an array in alphabetical order
	 * @param albums
	 */
	public static void alphabetize(Album[] albums){
		int n = albums.length;
		Album temp;
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				if(albums[i].getTitle().compareTo(albums[j].getTitle()) < 0) {
					temp = albums[i];
					albums[i] = albums[j];
					albums[i] = temp;
				}
			}
		}
	}
	
	/**
	 * This is the printByGenre method, which prints the array of albums by genre
	 */
	public void printByGenre() { //sort albums by genre first, then alphabetize
		
		int classicalCounter = 0;
		int countryCounter = 0;
		int jazzCounter = 0;
		int popCounter = 0;
		int unknownCounter = 0;
		
		Album[] classical = {};
		Album[] country = {};
		Album[] jazz = {};
		Album[] pop = {};
		Album[] unknown = {};
		
		for(int i = 0; i < albums.length; i++) {
			if(albums[i].getGenre() == Genre.Classical) {
				classical[i] = albums[i];
				classicalCounter++;
			}
			else if(albums[i].getGenre() == Genre.Country) {
				country[i] = albums[i];
				countryCounter++;
			}
			else if(albums[i].getGenre() == Genre.Jazz) {
				jazz[i] = albums[i];
				jazzCounter++;
			}
			else if(albums[i].getGenre() == Genre.Pop) {
				pop[i] = albums[i];
				popCounter++;
			}
			else if(albums[i].getGenre() == Genre.Unknown) {
				unknown[i] = albums[i];
				unknownCounter++;
			}
		}
		
		alphabetize(classical);
		alphabetize(country);
		alphabetize(jazz);
		alphabetize(pop);
		alphabetize(unknown);
		
		for(int i = 0; i < classicalCounter; i++) {
			albums[i] = classical[i];
		}
		
		for(int i = albums.length - classicalCounter; i < countryCounter; i++) {
			albums[i] = country[i];
		}
		
		for(int i = albums.length - countryCounter; i < jazzCounter; i++) {
			albums[i] = jazz[i];
		}
		
		for(int i = albums.length - jazzCounter; i < popCounter; i++) {
			albums[i] = pop[i];
		}
		
		for(int i = albums.length-popCounter; i < unknownCounter; i++) {
			albums[i] = unknown[i];
		}
		
		for(int j = 0; j < albums.length; j++) {
			System.out.println(albums);
		}
	}

	public int size() {
		// TODO Auto-generated method stub
		return numAlbums;
	}
}
