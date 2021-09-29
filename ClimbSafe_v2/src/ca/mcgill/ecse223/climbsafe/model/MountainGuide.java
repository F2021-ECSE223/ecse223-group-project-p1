package ca.mcgill.ecse223.climbsafe.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 67 "umpleCode-V3.ump"
// line 170 "umpleCode-V3.ump"
public class MountainGuide
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MountainGuide Attributes
  private int weeklyCost;

  //MountainGuide Associations
  private NMCP nMCP;
  private UserRegistration registration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MountainGuide(int aWeeklyCost, NMCP aNMCP, UserRegistration aRegistration)
  {
    weeklyCost = aWeeklyCost;
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create mountainGuide due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aRegistration == null || aRegistration.getRegisteredGuide() != null)
    {
      throw new RuntimeException("Unable to create MountainGuide due to aRegistration. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registration = aRegistration;
  }

  public MountainGuide(int aWeeklyCost, NMCP aNMCP, String aNameForRegistration, String aEmailForRegistration, String aEmergencyPhoneForRegistration, boolean aIsRegisteredForRegistration, boolean aIsMemberForRegistration, Member aRegisteredMemberForRegistration, NMCP aNMCPForRegistration, Account aAccountForRegistration)
  {
    weeklyCost = aWeeklyCost;
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create mountainGuide due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registration = new UserRegistration(aNameForRegistration, aEmailForRegistration, aEmergencyPhoneForRegistration, aIsRegisteredForRegistration, aIsMemberForRegistration, this, aRegisteredMemberForRegistration, aNMCPForRegistration, aAccountForRegistration);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeeklyCost(int aWeeklyCost)
  {
    boolean wasSet = false;
    weeklyCost = aWeeklyCost;
    wasSet = true;
    return wasSet;
  }

  public int getWeeklyCost()
  {
    return weeklyCost;
  }
  /* Code from template association_GetOne */
  public NMCP getNMCP()
  {
    return nMCP;
  }
  /* Code from template association_GetOne */
  public UserRegistration getRegistration()
  {
    return registration;
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
      existingNMCP.removeMountainGuide(this);
    }
    nMCP.addMountainGuide(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    NMCP placeholderNMCP = nMCP;
    this.nMCP = null;
    if(placeholderNMCP != null)
    {
      placeholderNMCP.removeMountainGuide(this);
    }
    UserRegistration existingRegistration = registration;
    registration = null;
    if (existingRegistration != null)
    {
      existingRegistration.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "weeklyCost" + ":" + getWeeklyCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMCP = "+(getNMCP()!=null?Integer.toHexString(System.identityHashCode(getNMCP())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "registration = "+(getRegistration()!=null?Integer.toHexString(System.identityHashCode(getRegistration())):"null");
  }
}