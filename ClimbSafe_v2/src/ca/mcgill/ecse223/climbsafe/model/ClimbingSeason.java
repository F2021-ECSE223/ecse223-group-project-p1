package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 50 "umpleCode-V3.ump"
// line 153 "umpleCode-V3.ump"
public class ClimbingSeason
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbingSeason Attributes
  private Date startDate;
  private Date endDate;

  //ClimbingSeason Associations
  private List<ClimbingWeek> climbingWeeks;
  private Admin seasonDefiner;
  private NMCP nMCP;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbingSeason(Date aStartDate, Date aEndDate, Admin aSeasonDefiner, NMCP aNMCP)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    climbingWeeks = new ArrayList<ClimbingWeek>();
    if (aSeasonDefiner == null || aSeasonDefiner.getClimbingSeason() != null)
    {
      throw new RuntimeException("Unable to create ClimbingSeason due to aSeasonDefiner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    seasonDefiner = aSeasonDefiner;
    if (aNMCP == null || aNMCP.getClimbingSeason() != null)
    {
      throw new RuntimeException("Unable to create ClimbingSeason due to aNMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    nMCP = aNMCP;
  }

  public ClimbingSeason(Date aStartDate, Date aEndDate, NMCP aNMCPForSeasonDefiner, Account aAccountForSeasonDefiner, Admin aAdminForNMCP)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    climbingWeeks = new ArrayList<ClimbingWeek>();
    seasonDefiner = new Admin(aNMCPForSeasonDefiner, aAccountForSeasonDefiner, this);
    nMCP = new NMCP(aAdminForNMCP, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
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
  public Admin getSeasonDefiner()
  {
    return seasonDefiner;
  }
  /* Code from template association_GetOne */
  public NMCP getNMCP()
  {
    return nMCP;
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
  /* Code from template association_AddMandatoryManyToOne */
  public ClimbingWeek addClimbingWeek(int aNumber, NMCP aNMCP)
  {
    ClimbingWeek aNewClimbingWeek = new ClimbingWeek(aNumber, aNMCP, this);
    return aNewClimbingWeek;
  }

  public boolean addClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasAdded = false;
    if (climbingWeeks.contains(aClimbingWeek)) { return false; }
    ClimbingSeason existingClimbingSeason = aClimbingWeek.getClimbingSeason();
    boolean isNewClimbingSeason = existingClimbingSeason != null && !this.equals(existingClimbingSeason);

    if (isNewClimbingSeason && existingClimbingSeason.numberOfClimbingWeeks() <= minimumNumberOfClimbingWeeks())
    {
      return wasAdded;
    }
    if (isNewClimbingSeason)
    {
      aClimbingWeek.setClimbingSeason(this);
    }
    else
    {
      climbingWeeks.add(aClimbingWeek);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasRemoved = false;
    //Unable to remove aClimbingWeek, as it must always have a climbingSeason
    if (this.equals(aClimbingWeek.getClimbingSeason()))
    {
      return wasRemoved;
    }

    //climbingSeason already at minimum (1)
    if (numberOfClimbingWeeks() <= minimumNumberOfClimbingWeeks())
    {
      return wasRemoved;
    }

    climbingWeeks.remove(aClimbingWeek);
    wasRemoved = true;
    return wasRemoved;
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

  public void delete()
  {
    while (climbingWeeks.size() > 0)
    {
      ClimbingWeek aClimbingWeek = climbingWeeks.get(climbingWeeks.size() - 1);
      aClimbingWeek.delete();
      climbingWeeks.remove(aClimbingWeek);
    }
    
    Admin existingSeasonDefiner = seasonDefiner;
    seasonDefiner = null;
    if (existingSeasonDefiner != null)
    {
      existingSeasonDefiner.delete();
    }
    NMCP existingNMCP = nMCP;
    nMCP = null;
    if (existingNMCP != null)
    {
      existingNMCP.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "seasonDefiner = "+(getSeasonDefiner()!=null?Integer.toHexString(System.identityHashCode(getSeasonDefiner())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "nMCP = "+(getNMCP()!=null?Integer.toHexString(System.identityHashCode(getNMCP())):"null");
  }
}