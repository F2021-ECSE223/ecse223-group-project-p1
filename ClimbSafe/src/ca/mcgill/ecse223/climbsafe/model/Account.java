package ca.mcgill.ecse223.climbsafe.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 17 "umpleCode.ump"
// line 114 "umpleCode.ump"
public class Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private String name;
  private String password;
  private boolean isLogged;

  //Account Associations
  private UserRegistration userRegistration;
  private Admin adminUser;
  private NMCP nMCP;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(String aName, String aPassword, boolean aIsLogged, UserRegistration aUserRegistration, Admin aAdminUser, NMCP aNMCP)
  {
    name = aName;
    password = aPassword;
    isLogged = aIsLogged;
    if (aUserRegistration == null || aUserRegistration.getAccount() != null)
    {
      throw new RuntimeException("Unable to create Account due to aUserRegistration. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    userRegistration = aUserRegistration;
    if (aAdminUser == null || aAdminUser.getAccount() != null)
    {
      throw new RuntimeException("Unable to create Account due to aAdminUser. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    adminUser = aAdminUser;
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create account due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Account(String aName, String aPassword, boolean aIsLogged, String aNameForUserRegistration, String aEmailForUserRegistration, String aEmergencyPhoneForUserRegistration, boolean aIsRegisteredForUserRegistration, boolean aIsMemberForUserRegistration, MountainGuide aRegisteredGuideForUserRegistration, Member aRegisteredMemberForUserRegistration, NMCP aNMCPForUserRegistration, NMCP aNMCPForAdminUser, ClimbingSeason aClimbingSeasonForAdminUser, NMCP aNMCP)
  {
    name = aName;
    password = aPassword;
    isLogged = aIsLogged;
    userRegistration = new UserRegistration(aNameForUserRegistration, aEmailForUserRegistration, aEmergencyPhoneForUserRegistration, aIsRegisteredForUserRegistration, aIsMemberForUserRegistration, aRegisteredGuideForUserRegistration, aRegisteredMemberForUserRegistration, aNMCPForUserRegistration, this);
    adminUser = new Admin(aNMCPForAdminUser, this, aClimbingSeasonForAdminUser);
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create account due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public boolean setIsLogged(boolean aIsLogged)
  {
    boolean wasSet = false;
    isLogged = aIsLogged;
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

  public boolean getIsLogged()
  {
    return isLogged;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsLogged()
  {
    return isLogged;
  }
  /* Code from template association_GetOne */
  public UserRegistration getUserRegistration()
  {
    return userRegistration;
  }
  /* Code from template association_GetOne */
  public Admin getAdminUser()
  {
    return adminUser;
  }
  /* Code from template association_GetOne */
  public NMCP getNMCP()
  {
    return nMCP;
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
      existingNMCP.removeAccount(this);
    }
    nMCP.addAccount(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    UserRegistration existingUserRegistration = userRegistration;
    userRegistration = null;
    if (existingUserRegistration != null)
    {
      existingUserRegistration.delete();
    }
    Admin existingAdminUser = adminUser;
    adminUser = null;
    if (existingAdminUser != null)
    {
      existingAdminUser.delete();
    }
    NMCP placeholderNMCP = nMCP;
    this.nMCP = null;
    if(placeholderNMCP != null)
    {
      placeholderNMCP.removeAccount(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "isLogged" + ":" + getIsLogged()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "userRegistration = "+(getUserRegistration()!=null?Integer.toHexString(System.identityHashCode(getUserRegistration())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "adminUser = "+(getAdminUser()!=null?Integer.toHexString(System.identityHashCode(getAdminUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "nMCP = "+(getNMCP()!=null?Integer.toHexString(System.identityHashCode(getNMCP())):"null");
  }
}