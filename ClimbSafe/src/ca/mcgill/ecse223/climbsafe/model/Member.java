package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 84 "umpleCode.ump"
// line 188 "umpleCode.ump"
public class Member
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private boolean isStaying;
  private boolean willHireAGuide;
  private int numberOfWeeks;

  //Member Associations
  private List<Equipment> equipments;
  private NMCP nMCP;
  private List<Bundle> bundles;
  private UserRegistration registration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(boolean aIsStaying, boolean aWillHireAGuide, int aNumberOfWeeks, NMCP aNMCP, UserRegistration aRegistration)
  {
    isStaying = aIsStaying;
    willHireAGuide = aWillHireAGuide;
    numberOfWeeks = aNumberOfWeeks;
    equipments = new ArrayList<Equipment>();
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create member due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bundles = new ArrayList<Bundle>();
    if (aRegistration == null || aRegistration.getRegisteredMember() != null)
    {
      throw new RuntimeException("Unable to create Member due to aRegistration. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registration = aRegistration;
  }

  public Member(boolean aIsStaying, boolean aWillHireAGuide, int aNumberOfWeeks, NMCP aNMCP, String aNameForRegistration, String aEmailForRegistration, String aEmergencyPhoneForRegistration, boolean aIsRegisteredForRegistration, boolean aIsMemberForRegistration, MountainGuide aRegisteredGuideForRegistration, NMCP aNMCPForRegistration, Account aAccountForRegistration)
  {
    isStaying = aIsStaying;
    willHireAGuide = aWillHireAGuide;
    numberOfWeeks = aNumberOfWeeks;
    equipments = new ArrayList<Equipment>();
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create member due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bundles = new ArrayList<Bundle>();
    registration = new UserRegistration(aNameForRegistration, aEmailForRegistration, aEmergencyPhoneForRegistration, aIsRegisteredForRegistration, aIsMemberForRegistration, aRegisteredGuideForRegistration, this, aNMCPForRegistration, aAccountForRegistration);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsStaying(boolean aIsStaying)
  {
    boolean wasSet = false;
    isStaying = aIsStaying;
    wasSet = true;
    return wasSet;
  }

  public boolean setWillHireAGuide(boolean aWillHireAGuide)
  {
    boolean wasSet = false;
    willHireAGuide = aWillHireAGuide;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfWeeks(int aNumberOfWeeks)
  {
    boolean wasSet = false;
    numberOfWeeks = aNumberOfWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsStaying()
  {
    return isStaying;
  }

  public boolean getWillHireAGuide()
  {
    return willHireAGuide;
  }

  public int getNumberOfWeeks()
  {
    return numberOfWeeks;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsStaying()
  {
    return isStaying;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isWillHireAGuide()
  {
    return willHireAGuide;
  }
  /* Code from template association_GetMany */
  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipments.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipments()
  {
    List<Equipment> newEquipments = Collections.unmodifiableList(equipments);
    return newEquipments;
  }

  public int numberOfEquipments()
  {
    int number = equipments.size();
    return number;
  }

  public boolean hasEquipments()
  {
    boolean has = equipments.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipments.indexOf(aEquipment);
    return index;
  }
  /* Code from template association_GetOne */
  public NMCP getNMCP()
  {
    return nMCP;
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
  public UserRegistration getRegistration()
  {
    return registration;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipments()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipments.contains(aEquipment)) { return false; }
    equipments.add(aEquipment);
    if (aEquipment.indexOfEquipmentHolder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEquipment.addEquipmentHolder(this);
      if (!wasAdded)
      {
        equipments.remove(aEquipment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (!equipments.contains(aEquipment))
    {
      return wasRemoved;
    }

    int oldIndex = equipments.indexOf(aEquipment);
    equipments.remove(oldIndex);
    if (aEquipment.indexOfEquipmentHolder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEquipment.removeEquipmentHolder(this);
      if (!wasRemoved)
      {
        equipments.add(oldIndex,aEquipment);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipments.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
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
      existingNMCP.removeMember(this);
    }
    nMCP.addMember(this);
    wasSet = true;
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
    if (aBundle.indexOfBundleHolder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBundle.addBundleHolder(this);
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
    if (aBundle.indexOfBundleHolder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBundle.removeBundleHolder(this);
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

  public void delete()
  {
    ArrayList<Equipment> copyOfEquipments = new ArrayList<Equipment>(equipments);
    equipments.clear();
    for(Equipment aEquipment : copyOfEquipments)
    {
      aEquipment.removeEquipmentHolder(this);
    }
    NMCP placeholderNMCP = nMCP;
    this.nMCP = null;
    if(placeholderNMCP != null)
    {
      placeholderNMCP.removeMember(this);
    }
    ArrayList<Bundle> copyOfBundles = new ArrayList<Bundle>(bundles);
    bundles.clear();
    for(Bundle aBundle : copyOfBundles)
    {
      aBundle.removeBundleHolder(this);
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
            "isStaying" + ":" + getIsStaying()+ "," +
            "willHireAGuide" + ":" + getWillHireAGuide()+ "," +
            "numberOfWeeks" + ":" + getNumberOfWeeks()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMCP = "+(getNMCP()!=null?Integer.toHexString(System.identityHashCode(getNMCP())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "registration = "+(getRegistration()!=null?Integer.toHexString(System.identityHashCode(getRegistration())):"null");
  }
}