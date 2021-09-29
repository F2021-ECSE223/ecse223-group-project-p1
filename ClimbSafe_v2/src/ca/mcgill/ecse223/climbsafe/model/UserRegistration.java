package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 92 "umpleCode-V3.ump"
// line 198 "umpleCode-V3.ump"
public class UserRegistration
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserRegistration Attributes
  private String name;
  private String email;
  private String emergencyPhone;
  private boolean isRegistered;
  private boolean isMember;

  //UserRegistration Associations
  private MountainGuide registeredGuide;
  private Member registeredMember;
  private NMCP nMCP;
  private Account account;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserRegistration(String aName, String aEmail, String aEmergencyPhone, boolean aIsRegistered, boolean aIsMember, MountainGuide aRegisteredGuide, Member aRegisteredMember, NMCP aNMCP, Account aAccount)
  {
    name = aName;
    email = aEmail;
    emergencyPhone = aEmergencyPhone;
    isRegistered = aIsRegistered;
    isMember = aIsMember;
    if (aRegisteredGuide == null || aRegisteredGuide.getRegistration() != null)
    {
      throw new RuntimeException("Unable to create UserRegistration due to aRegisteredGuide. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registeredGuide = aRegisteredGuide;
    if (aRegisteredMember == null || aRegisteredMember.getRegistration() != null)
    {
      throw new RuntimeException("Unable to create UserRegistration due to aRegisteredMember. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registeredMember = aRegisteredMember;
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create userRegistration due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aAccount == null || aAccount.getUserRegistration() != null)
    {
      throw new RuntimeException("Unable to create UserRegistration due to aAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    account = aAccount;
  }

  public UserRegistration(String aName, String aEmail, String aEmergencyPhone, boolean aIsRegistered, boolean aIsMember, int aWeeklyCostForRegisteredGuide, NMCP aNMCPForRegisteredGuide, boolean aIsStayingForRegisteredMember, boolean aWillHireAGuideForRegisteredMember, int aNumberOfWeeksForRegisteredMember, NMCP aNMCPForRegisteredMember, NMCP aNMCP, String aNameForAccount, String aPasswordForAccount, boolean aIsLoggedForAccount, Admin aAdminUserForAccount, NMCP aNMCPForAccount)
  {
    name = aName;
    email = aEmail;
    emergencyPhone = aEmergencyPhone;
    isRegistered = aIsRegistered;
    isMember = aIsMember;
    registeredGuide = new MountainGuide(aWeeklyCostForRegisteredGuide, aNMCPForRegisteredGuide, this);
    registeredMember = new Member(aIsStayingForRegisteredMember, aWillHireAGuideForRegisteredMember, aNumberOfWeeksForRegisteredMember, aNMCPForRegisteredMember, this);
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create userRegistration due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    account = new Account(aNameForAccount, aPasswordForAccount, aIsLoggedForAccount, this, aAdminUserForAccount, aNMCPForAccount);
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

  public boolean setEmergencyPhone(String aEmergencyPhone)
  {
    boolean wasSet = false;
    emergencyPhone = aEmergencyPhone;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsRegistered(boolean aIsRegistered)
  {
    boolean wasSet = false;
    isRegistered = aIsRegistered;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsMember(boolean aIsMember)
  {
    boolean wasSet = false;
    isMember = aIsMember;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public String getEmergencyPhone()
  {
    return emergencyPhone;
  }

  public boolean getIsRegistered()
  {
    return isRegistered;
  }

  public boolean getIsMember()
  {
    return isMember;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsRegistered()
  {
    return isRegistered;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsMember()
  {
    return isMember;
  }
  /* Code from template association_GetOne */
  public MountainGuide getRegisteredGuide()
  {
    return registeredGuide;
  }
  /* Code from template association_GetOne */
  public Member getRegisteredMember()
  {
    return registeredMember;
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
      existingNMCP.removeUserRegistration(this);
    }
    nMCP.addUserRegistration(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    MountainGuide existingRegisteredGuide = registeredGuide;
    registeredGuide = null;
    if (existingRegisteredGuide != null)
    {
      existingRegisteredGuide.delete();
    }
    Member existingRegisteredMember = registeredMember;
    registeredMember = null;
    if (existingRegisteredMember != null)
    {
      existingRegisteredMember.delete();
    }
    NMCP placeholderNMCP = nMCP;
    this.nMCP = null;
    if(placeholderNMCP != null)
    {
      placeholderNMCP.removeUserRegistration(this);
    }
    Account existingAccount = account;
    account = null;
    if (existingAccount != null)
    {
      existingAccount.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "email" + ":" + getEmail()+ "," +
            "emergencyPhone" + ":" + getEmergencyPhone()+ "," +
            "isRegistered" + ":" + getIsRegistered()+ "," +
            "isMember" + ":" + getIsMember()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "registeredGuide = "+(getRegisteredGuide()!=null?Integer.toHexString(System.identityHashCode(getRegisteredGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "registeredMember = "+(getRegisteredMember()!=null?Integer.toHexString(System.identityHashCode(getRegisteredMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "nMCP = "+(getNMCP()!=null?Integer.toHexString(System.identityHashCode(getNMCP())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "account = "+(getAccount()!=null?Integer.toHexString(System.identityHashCode(getAccount())):"null");
  }
}