package projectOne;

/** 
 * This class defines the abstract data type Album, which encapsulates the data fields 
 * (title, artist, genre, release date) and methods of an album.
 * Also checks and set the availability of the album
 * @author Ryan Pollack, Michael Kang
 */
public class Album 
{
    private String title;
    private String artist;
    private Genre genre; 		//enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;
    
    /**
     * Constructor methods that sets all the information
     * Genre is converted from sting to enum
     * @param title name of album
     * @param artist name of album
     * @param genre of music of album in string form
     * @param release date of album
	 */
	 public Album(String title, String artist, String genre, String date)
	 {
		 this.title = title;
		 this.artist = artist;
		 this.releaseDate = new Date(date);
		 this.isAvailable = true;			//default as true;
     
		 if(genre.toUpperCase().equals("CLASSICAL"))
		 {
			 this.genre = Genre.Classical;
		 }
		 else if(genre.toUpperCase().equals("COUNTRY"))
		 {
			 this.genre = Genre.Country;
		 }
		 else if(genre.toUpperCase().equals("JAZZ"))
		 {
			 this.genre = Genre.Jazz;
		 }
		 else if(genre.toUpperCase().equals("POP"))
		 {
			 this.genre = Genre.Pop;
		 }
		 else
		 {
			 this.genre = Genre.Unknown;
		 }
     }
	  
	 /**
	 * A secondary constructor used to compare two album's titles and artists 
	 * in the equals() method
     * @param title name of album
     * @param artist name of album
     */
	 public Album(String title, String artist){
	     this.title = title;
	     this.artist = artist;
	     this.releaseDate = new Date();
	     this.isAvailable = true;
	     this.genre = Genre.Unknown;	 
	 }	 

    /**
     * Gets and returns title from album
     * @return title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Gets and returns artist from album
     * @return artist name
     */
    public String getArtist()
    {
        return artist;
    }
    
    /**
     * Gets and returns genre from album
     * @return genre in enum form
     */
    public Genre getGenre()
    {
        return genre;
    }
    
    /**
     * Gets and returns release date of album
     * @return release date
     */
    public Date getReleaseDate()
    {
        return releaseDate;
    }
    
    /**
     * Checks to see if album is available to lend or return
     * @return true if is available, false otherwise
     */
    public boolean getAvailable()
    {
        return isAvailable;
    }

    /**
     * Allows you to make an unavailable album available
     */
    public void setAvailable()
    {
        isAvailable = !isAvailable;
    }
    
    /**
     * Determines the availability of the album
     * @return string, "is available if isAvailable is true, "is not available" if false
     */
    public String declareAvailability()
    {
    	if(isAvailable == true)
    	{
    		return "is available";
    	}
    	else
    	{
    		return "is not available";
    	}
    }
    /**
     * Checks to see if and title and artist are the same for 2 albums
     * @return true if title and artist are same for both albums, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
        {
            return true;
        }
        if(!(obj instanceof Album))
        {
            return false;
        }
        
        Album comparison = (Album) obj;
        return title.equals(comparison.getTitle()) && artist.equals(comparison.getArtist());
    }
    
    /**
     * Provides a textual representation of an album using all the instance variables
     * @return "title::artist::genre::releaseDate::isAvailable
     */
    @Override
    public String toString()
    {
    	
        return title + "::" + artist + "::"+ genre.toString() + "::" + releaseDate.toString() + "::"
                + declareAvailability();
    }

}