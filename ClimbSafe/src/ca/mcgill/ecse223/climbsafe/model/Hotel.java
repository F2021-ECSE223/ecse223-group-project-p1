package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 63 "umpleCode.ump"
// line 168 "umpleCode.ump"
public class Hotel
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Hotel Attributes
  private int starClass;
  private String name;
  private String address;

  //Hotel Associations
  private NMCP nMCP;
  private List<Assignment> bookings;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hotel(int aStarClass, String aName, String aAddress, NMCP aNMCP)
  {
    starClass = aStarClass;
    name = aName;
    address = aAddress;
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create hotel due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bookings = new ArrayList<Assignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStarClass(int aStarClass)
  {
    boolean wasSet = false;
    starClass = aStarClass;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public int getStarClass()
  {
    return starClass;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }
  /* Code from template association_GetOne */
  public NMCP getNMCP()
  {
    return nMCP;
  }
  /* Code from template association_GetMany */
  public Assignment getBooking(int index)
  {
    Assignment aBooking = bookings.get(index);
    return aBooking;
  }

  public List<Assignment> getBookings()
  {
    List<Assignment> newBookings = Collections.unmodifiableList(bookings);
    return newBookings;
  }

  public int numberOfBookings()
  {
    int number = bookings.size();
    return number;
  }

  public boolean hasBookings()
  {
    boolean has = bookings.size() > 0;
    return has;
  }

  public int indexOfBooking(Assignment aBooking)
  {
    int index = bookings.indexOf(aBooking);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setNMCP(NMCP aNMCP)
  {
    boolean wasSet = false;
    if (aNMCP == null)
    {
      return wasSet;
    }

    NMCP existingNMCP = nMCP;
    nMCP = aNMCP;
    if (existingNMCP != null && !existingNMCP.equals(aNMCP))
    {
      existingNMCP.removeHotel(this);
    }
    nMCP.addHotel(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addBooking(Assignment aBooking)
  {
    boolean wasAdded = false;
    if (bookings.contains(aBooking)) { return false; }
    Hotel existingHotel = aBooking.getHotel();
    if (existingHotel == null)
    {
      aBooking.setHotel(this);
    }
    else if (!this.equals(existingHotel))
    {
      existingHotel.removeBooking(aBooking);
      addBooking(aBooking);
    }
    else
    {
      bookings.add(aBooking);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBooking(Assignment aBooking)
  {
    boolean wasRemoved = false;
    if (bookings.contains(aBooking))
    {
      bookings.remove(aBooking);
      aBooking.setHotel(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBookingAt(Assignment aBooking, int index)
  {  
    boolean wasAdded = false;
    if(addBooking(aBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
      bookings.remove(aBooking);
      bookings.add(index, aBooking);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookingAt(Assignment aBooking, int index)
  {
    boolean wasAdded = false;
    if(bookings.contains(aBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
      bookings.remove(aBooking);
      bookings.add(index, aBooking);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookingAt(aBooking, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    NMCP placeholderNMCP = nMCP;
    this.nMCP = null;
    if(placeholderNMCP != null)
    {
      placeholderNMCP.removeHotel(this);
    }
    while( !bookings.isEmpty() )
    {
      bookings.get(0).setHotel(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "starClass" + ":" + getStarClass()+ "," +
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMCP = "+(getNMCP()!=null?Integer.toHexString(System.identityHashCode(getNMCP())):"null");
  }
}