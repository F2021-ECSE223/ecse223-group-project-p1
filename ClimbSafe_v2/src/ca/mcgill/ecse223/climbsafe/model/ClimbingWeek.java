package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 103 "umpleCode-V3.ump"
// line 193 "umpleCode-V3.ump"
public class ClimbingWeek
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, ClimbingWeek> climbingweeksByNumber = new HashMap<Integer, ClimbingWeek>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbingWeek Attributes
  private int number;

  //ClimbingWeek Associations
  private NMCP nMCP;
  private List<Assignment> reservations;
  private ClimbingSeason climbingSeason;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbingWeek(int aNumber, NMCP aNMCP, ClimbingSeason aClimbingSeason)
  {
    if (!setNumber(aNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate number. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create climbingWeek due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    reservations = new ArrayList<Assignment>();
    boolean didAddClimbingSeason = setClimbingSeason(aClimbingSeason);
    if (!didAddClimbingSeason)
    {
      throw new RuntimeException("Unable to create climbingWeek due to climbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    Integer anOldNumber = getNumber();
    if (anOldNumber != null && anOldNumber.equals(aNumber)) {
      return true;
    }
    if (hasWithNumber(aNumber)) {
      return wasSet;
    }
    number = aNumber;
    wasSet = true;
    if (anOldNumber != null) {
      climbingweeksByNumber.remove(anOldNumber);
    }
    climbingweeksByNumber.put(aNumber, this);
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }
  /* Code from template attribute_GetUnique */
  public static ClimbingWeek getWithNumber(int aNumber)
  {
    return climbingweeksByNumber.get(aNumber);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithNumber(int aNumber)
  {
    return getWithNumber(aNumber) != null;
  }
  /* Code from template association_GetOne */
  public NMCP getNMCP()
  {
    return nMCP;
  }
  /* Code from template association_GetMany */
  public Assignment getReservation(int index)
  {
    Assignment aReservation = reservations.get(index);
    return aReservation;
  }

  public List<Assignment> getReservations()
  {
    List<Assignment> newReservations = Collections.unmodifiableList(reservations);
    return newReservations;
  }

  public int numberOfReservations()
  {
    int number = reservations.size();
    return number;
  }

  public boolean hasReservations()
  {
    boolean has = reservations.size() > 0;
    return has;
  }

  public int indexOfReservation(Assignment aReservation)
  {
    int index = reservations.indexOf(aReservation);
    return index;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setNMCP(NMCP aNMCP)
  {
    boolean wasSet = false;
    //Must provide nMCP to climbingWeek
    if (aNMCP == null)
    {
      return wasSet;
    }

    if (nMCP != null && nMCP.numberOfClimbingWeeks() <= NMCP.minimumNumberOfClimbingWeeks())
    {
      return wasSet;
    }

    NMCP existingNMCP = nMCP;
    nMCP = aNMCP;
    if (existingNMCP != null && !existingNMCP.equals(aNMCP))
    {
      boolean didRemove = existingNMCP.removeClimbingWeek(this);
      if (!didRemove)
      {
        nMCP = existingNMCP;
        return wasSet;
      }
    }
    nMCP.addClimbingWeek(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReservations()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addReservation(Assignment aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    reservations.add(aReservation);
    if (aReservation.indexOfClimbingWeek(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aReservation.addClimbingWeek(this);
      if (!wasAdded)
      {
        reservations.remove(aReservation);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeReservation(Assignment aReservation)
  {
    boolean wasRemoved = false;
    if (!reservations.contains(aReservation))
    {
      return wasRemoved;
    }

    int oldIndex = reservations.indexOf(aReservation);
    reservations.remove(oldIndex);
    if (aReservation.indexOfClimbingWeek(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aReservation.removeClimbingWeek(this);
      if (!wasRemoved)
      {
        reservations.add(oldIndex,aReservation);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReservationAt(Assignment aReservation, int index)
  {  
    boolean wasAdded = false;
    if(addReservation(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReservationAt(Assignment aReservation, int index)
  {
    boolean wasAdded = false;
    if(reservations.contains(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReservationAt(aReservation, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setClimbingSeason(ClimbingSeason aClimbingSeason)
  {
    boolean wasSet = false;
    //Must provide climbingSeason to climbingWeek
    if (aClimbingSeason == null)
    {
      return wasSet;
    }

    if (climbingSeason != null && climbingSeason.numberOfClimbingWeeks() <= ClimbingSeason.minimumNumberOfClimbingWeeks())
    {
      return wasSet;
    }

    ClimbingSeason existingClimbingSeason = climbingSeason;
    climbingSeason = aClimbingSeason;
    if (existingClimbingSeason != null && !existingClimbingSeason.equals(aClimbingSeason))
    {
      boolean didRemove = existingClimbingSeason.removeClimbingWeek(this);
      if (!didRemove)
      {
        climbingSeason = existingClimbingSeason;
        return wasSet;
      }
    }
    climbingSeason.addClimbingWeek(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    climbingweeksByNumber.remove(getNumber());
    NMCP placeholderNMCP = nMCP;
    this.nMCP = null;
    if(placeholderNMCP != null)
    {
      placeholderNMCP.removeClimbingWeek(this);
    }
    ArrayList<Assignment> copyOfReservations = new ArrayList<Assignment>(reservations);
    reservations.clear();
    for(Assignment aReservation : copyOfReservations)
    {
      if (aReservation.numberOfClimbingWeeks() <= Assignment.minimumNumberOfClimbingWeeks())
      {
        aReservation.delete();
      }
      else
      {
        aReservation.removeClimbingWeek(this);
      }
    }
    ClimbingSeason placeholderClimbingSeason = climbingSeason;
    this.climbingSeason = null;
    if(placeholderClimbingSeason != null)
    {
      placeholderClimbingSeason.removeClimbingWeek(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMCP = "+(getNMCP()!=null?Integer.toHexString(System.identityHashCode(getNMCP())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbingSeason = "+(getClimbingSeason()!=null?Integer.toHexString(System.identityHashCode(getClimbingSeason())):"null");
  }
}