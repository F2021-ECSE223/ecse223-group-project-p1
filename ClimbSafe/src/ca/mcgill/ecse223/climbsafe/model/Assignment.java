package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 41 "umpleCode.ump"
// line 133 "umpleCode.ump"
public class Assignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private Date date;

  //Assignment Associations
  private Admin assigner;
  private MountainGuide guide;
  private List<ClimbingWeek> climbingWeeks;
  private Hotel hotel;
  private Member assignedTo;
  private List<Bundle> bundles;
  private NMCP nMCP;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(Date aDate, Admin aAssigner, MountainGuide aGuide, Member aAssignedTo, NMCP aNMCP, ClimbingWeek... allClimbingWeeks)
  {
    date = aDate;
    boolean didAddAssigner = setAssigner(aAssigner);
    if (!didAddAssigner)
    {
      throw new RuntimeException("Unable to create assignment due to assigner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setGuide(aGuide))
    {
      throw new RuntimeException("Unable to create Assignment due to aGuide. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbingWeeks = new ArrayList<ClimbingWeek>();
    boolean didAddClimbingWeeks = setClimbingWeeks(allClimbingWeeks);
    if (!didAddClimbingWeeks)
    {
      throw new RuntimeException("Unable to create Assignment, must have at least 1 climbingWeeks. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setAssignedTo(aAssignedTo))
    {
      throw new RuntimeException("Unable to create Assignment due to aAssignedTo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bundles = new ArrayList<Bundle>();
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create assignment due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }
  /* Code from template association_GetOne */
  public Admin getAssigner()
  {
    return assigner;
  }
  /* Code from template association_GetOne */
  public MountainGuide getGuide()
  {
    return guide;
  }
  /* Code from template association_GetMany */
  public ClimbingWeek getClimbingWeek(int index)
  {
    ClimbingWeek aClimbingWeek = climbingWeeks.get(index);
    return aClimbingWeek;
  }

  public List<ClimbingWeek> getClimbingWeeks()
  {
    List<ClimbingWeek> newClimbingWeeks = Collections.unmodifiableList(climbingWeeks);
    return newClimbingWeeks;
  }

  public int numberOfClimbingWeeks()
  {
    int number = climbingWeeks.size();
    return number;
  }

  public boolean hasClimbingWeeks()
  {
    boolean has = climbingWeeks.size() > 0;
    return has;
  }

  public int indexOfClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    int index = climbingWeeks.indexOf(aClimbingWeek);
    return index;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }

  public boolean hasHotel()
  {
    boolean has = hotel != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Member getAssignedTo()
  {
    return assignedTo;
  }
  /* Code from template association_GetMany */
  public Bundle getBundle(int index)
  {
    Bundle aBundle = bundles.get(index);
    return aBundle;
  }

  public List<Bundle> getBundles()
  {
    List<Bundle> newBundles = Collections.unmodifiableList(bundles);
    return newBundles;
  }

  public int numberOfBundles()
  {
    int number = bundles.size();
    return number;
  }

  public boolean hasBundles()
  {
    boolean has = bundles.size() > 0;
    return has;
  }

  public int indexOfBundle(Bundle aBundle)
  {
    int index = bundles.indexOf(aBundle);
    return index;
  }
  /* Code from template association_GetOne */
  public NMCP getNMCP()
  {
    return nMCP;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAssigner(Admin aAssigner)
  {
    boolean wasSet = false;
    if (aAssigner == null)
    {
      return wasSet;
    }

    Admin existingAssigner = assigner;
    assigner = aAssigner;
    if (existingAssigner != null && !existingAssigner.equals(aAssigner))
    {
      existingAssigner.removeAssignment(this);
    }
    assigner.addAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setGuide(MountainGuide aNewGuide)
  {
    boolean wasSet = false;
    if (aNewGuide != null)
    {
      guide = aNewGuide;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfClimbingWeeksValid()
  {
    boolean isValid = numberOfClimbingWeeks() >= minimumNumberOfClimbingWeeks();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbingWeeks()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasAdded = false;
    if (climbingWeeks.contains(aClimbingWeek)) { return false; }
    climbingWeeks.add(aClimbingWeek);
    if (aClimbingWeek.indexOfReservation(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aClimbingWeek.addReservation(this);
      if (!wasAdded)
      {
        climbingWeeks.remove(aClimbingWeek);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasRemoved = false;
    if (!climbingWeeks.contains(aClimbingWeek))
    {
      return wasRemoved;
    }

    if (numberOfClimbingWeeks() <= minimumNumberOfClimbingWeeks())
    {
      return wasRemoved;
    }

    int oldIndex = climbingWeeks.indexOf(aClimbingWeek);
    climbingWeeks.remove(oldIndex);
    if (aClimbingWeek.indexOfReservation(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aClimbingWeek.removeReservation(this);
      if (!wasRemoved)
      {
        climbingWeeks.add(oldIndex,aClimbingWeek);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setClimbingWeeks(ClimbingWeek... newClimbingWeeks)
  {
    boolean wasSet = false;
    ArrayList<ClimbingWeek> verifiedClimbingWeeks = new ArrayList<ClimbingWeek>();
    for (ClimbingWeek aClimbingWeek : newClimbingWeeks)
    {
      if (verifiedClimbingWeeks.contains(aClimbingWeek))
      {
        continue;
      }
      verifiedClimbingWeeks.add(aClimbingWeek);
    }

    if (verifiedClimbingWeeks.size() != newClimbingWeeks.length || verifiedClimbingWeeks.size() < minimumNumberOfClimbingWeeks())
    {
      return wasSet;
    }

    ArrayList<ClimbingWeek> oldClimbingWeeks = new ArrayList<ClimbingWeek>(climbingWeeks);
    climbingWeeks.clear();
    for (ClimbingWeek aNewClimbingWeek : verifiedClimbingWeeks)
    {
      climbingWeeks.add(aNewClimbingWeek);
      if (oldClimbingWeeks.contains(aNewClimbingWeek))
      {
        oldClimbingWeeks.remove(aNewClimbingWeek);
      }
      else
      {
        aNewClimbingWeek.addReservation(this);
      }
    }

    for (ClimbingWeek anOldClimbingWeek : oldClimbingWeeks)
    {
      anOldClimbingWeek.removeReservation(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {  
    boolean wasAdded = false;
    if(addClimbingWeek(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeeks()) { index = numberOfClimbingWeeks() - 1; }
      climbingWeeks.remove(aClimbingWeek);
      climbingWeeks.add(index, aClimbingWeek);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {
    boolean wasAdded = false;
    if(climbingWeeks.contains(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeeks()) { index = numberOfClimbingWeeks() - 1; }
      climbingWeeks.remove(aClimbingWeek);
      climbingWeeks.add(index, aClimbingWeek);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClimbingWeekAt(aClimbingWeek, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeBooking(this);
    }
    if (aHotel != null)
    {
      aHotel.addBooking(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setAssignedTo(Member aNewAssignedTo)
  {
    boolean wasSet = false;
    if (aNewAssignedTo != null)
    {
      assignedTo = aNewAssignedTo;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBundle(Bundle aBundle)
  {
    boolean wasAdded = false;
    if (bundles.contains(aBundle)) { return false; }
    bundles.add(aBundle);
    if (aBundle.indexOfAssignment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBundle.addAssignment(this);
      if (!wasAdded)
      {
        bundles.remove(aBundle);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBundle(Bundle aBundle)
  {
    boolean wasRemoved = false;
    if (!bundles.contains(aBundle))
    {
      return wasRemoved;
    }

    int oldIndex = bundles.indexOf(aBundle);
    bundles.remove(oldIndex);
    if (aBundle.indexOfAssignment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBundle.removeAssignment(this);
      if (!wasRemoved)
      {
        bundles.add(oldIndex,aBundle);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundleAt(Bundle aBundle, int index)
  {  
    boolean wasAdded = false;
    if(addBundle(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundleAt(Bundle aBundle, int index)
  {
    boolean wasAdded = false;
    if(bundles.contains(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundleAt(aBundle, index);
    }
    return wasAdded;
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
      existingNMCP.removeAssignment(this);
    }
    nMCP.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Admin placeholderAssigner = assigner;
    this.assigner = null;
    if(placeholderAssigner != null)
    {
      placeholderAssigner.removeAssignment(this);
    }
    guide = null;
    ArrayList<ClimbingWeek> copyOfClimbingWeeks = new ArrayList<ClimbingWeek>(climbingWeeks);
    climbingWeeks.clear();
    for(ClimbingWeek aClimbingWeek : copyOfClimbingWeeks)
    {
      aClimbingWeek.removeReservation(this);
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeBooking(this);
    }
    assignedTo = null;
    ArrayList<Bundle> copyOfBundles = new ArrayList<Bundle>(bundles);
    bundles.clear();
    for(Bundle aBundle : copyOfBundles)
    {
      aBundle.removeAssignment(this);
    }
    NMCP placeholderNMCP = nMCP;
    this.nMCP = null;
    if(placeholderNMCP != null)
    {
      placeholderNMCP.removeAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "assigner = "+(getAssigner()!=null?Integer.toHexString(System.identityHashCode(getAssigner())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignedTo = "+(getAssignedTo()!=null?Integer.toHexString(System.identityHashCode(getAssignedTo())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "nMCP = "+(getNMCP()!=null?Integer.toHexString(System.identityHashCode(getNMCP())):"null");
  }
}