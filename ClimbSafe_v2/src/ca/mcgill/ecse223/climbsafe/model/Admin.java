package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 25 "umpleCode-V3.ump"
// line 120 "umpleCode-V3.ump"
public class Admin
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Admin Attributes
  private String name;
  private String password;

  //Admin Associations
  private NMCP nMCP;
  private Account account;
  private List<Assignment> assignments;
  private ClimbingSeason climbingSeason;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(NMCP aNMCP, Account aAccount, ClimbingSeason aClimbingSeason)
  {
    name = "admin@nmc.nt";
    password = "admin";
    if (aNMCP == null || aNMCP.getAdmin() != null)
    {
      throw new RuntimeException("Unable to create Admin due to aNMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    nMCP = aNMCP;
    if (aAccount == null || aAccount.getAdminUser() != null)
    {
      throw new RuntimeException("Unable to create Admin due to aAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    account = aAccount;
    assignments = new ArrayList<Assignment>();
    if (aClimbingSeason == null || aClimbingSeason.getSeasonDefiner() != null)
    {
      throw new RuntimeException("Unable to create Admin due to aClimbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbingSeason = aClimbingSeason;
  }

  public Admin(ClimbingSeason aClimbingSeasonForNMCP, String aNameForAccount, String aPasswordForAccount, boolean aIsLoggedForAccount, UserRegistration aUserRegistrationForAccount, NMCP aNMCPForAccount, Date aStartDateForClimbingSeason, Date aEndDateForClimbingSeason, NMCP aNMCPForClimbingSeason)
  {
    name = "admin@nmc.nt";
    password = "admin";
    nMCP = new NMCP(this, aClimbingSeasonForNMCP);
    account = new Account(aNameForAccount, aPasswordForAccount, aIsLoggedForAccount, aUserRegistrationForAccount, this, aNMCPForAccount);
    assignments = new ArrayList<Assignment>();
    climbingSeason = new ClimbingSeason(aStartDateForClimbingSeason, aEndDateForClimbingSeason, this, aNMCPForClimbingSeason);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  public NMCP getNMCP()
  {
    return nMCP;
  }
  /* Code from template association_GetOne */
  public Account getAccount()
  {
    return account;
  }
  /* Code from template association_GetMany */
  public Assignment getAssignment(int index)
  {
    Assignment aAssignment = assignments.get(index);
    return aAssignment;
  }

  public List<Assignment> getAssignments()
  {
    List<Assignment> newAssignments = Collections.unmodifiableList(assignments);
    return newAssignments;
  }

  public int numberOfAssignments()
  {
    int number = assignments.size();
    return number;
  }

  public boolean hasAssignments()
  {
    boolean has = assignments.size() > 0;
    return has;
  }

  public int indexOfAssignment(Assignment aAssignment)
  {
    int index = assignments.indexOf(aAssignment);
    return index;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Assignment addAssignment(Date aDate, MountainGuide aGuide, Member aAssignedTo, NMCP aNMCP, ClimbingWeek... allClimbingWeeks)
  {
    return new Assignment(aDate, this, aGuide, aAssignedTo, aNMCP, allClimbingWeeks);
  }

  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    Admin existingAssigner = aAssignment.getAssigner();
    boolean isNewAssigner = existingAssigner != null && !this.equals(existingAssigner);
    if (isNewAssigner)
    {
      aAssignment.setAssigner(this);
    }
    else
    {
      assignments.add(aAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignment(Assignment aAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAssignment, as it must always have a assigner
    if (!this.equals(aAssignment.getAssigner()))
    {
      assignments.remove(aAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignmentAt(Assignment aAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addAssignment(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignmentAt(Assignment aAssignment, int index)
  {
    boolean wasAdded = false;
    if(assignments.contains(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignmentAt(aAssignment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    NMCP existingNMCP = nMCP;
    nMCP = null;
    if (existingNMCP != null)
    {
      existingNMCP.delete();
    }
    Account existingAccount = account;
    account = null;
    if (existingAccount != null)
    {
      existingAccount.delete();
    }
    for(int i=assignments.size(); i > 0; i--)
    {
      Assignment aAssignment = assignments.get(i - 1);
      aAssignment.delete();
    }
    ClimbingSeason existingClimbingSeason = climbingSeason;
    climbingSeason = null;
    if (existingClimbingSeason != null)
    {
      existingClimbingSeason.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMCP = "+(getNMCP()!=null?Integer.toHexString(System.identityHashCode(getNMCP())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "account = "+(getAccount()!=null?Integer.toHexString(System.identityHashCode(getAccount())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbingSeason = "+(getClimbingSeason()!=null?Integer.toHexString(System.identityHashCode(getClimbingSeason())):"null");
  }
}