package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 75 "umpleCode.ump"
// line 178 "umpleCode.ump"
public class Bundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bundle Attributes
  private boolean onDiscount;
  private String name;
  private int percentageDiscount;

  //Bundle Associations
  private List<Equipment> equipments;
  private List<Member> bundleHolders;
  private NMCP nMCP;
  private List<Assignment> assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bundle(boolean aOnDiscount, String aName, int aPercentageDiscount, NMCP aNMCP)
  {
    onDiscount = aOnDiscount;
    name = aName;
    percentageDiscount = aPercentageDiscount;
    equipments = new ArrayList<Equipment>();
    bundleHolders = new ArrayList<Member>();
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create bundle due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    assignments = new ArrayList<Assignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOnDiscount(boolean aOnDiscount)
  {
    boolean wasSet = false;
    onDiscount = aOnDiscount;
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

  public boolean setPercentageDiscount(int aPercentageDiscount)
  {
    boolean wasSet = false;
    percentageDiscount = aPercentageDiscount;
    wasSet = true;
    return wasSet;
  }

  public boolean getOnDiscount()
  {
    return onDiscount;
  }

  public String getName()
  {
    return name;
  }

  public int getPercentageDiscount()
  {
    return percentageDiscount;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isOnDiscount()
  {
    return onDiscount;
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
  /* Code from template association_GetMany */
  public Member getBundleHolder(int index)
  {
    Member aBundleHolder = bundleHolders.get(index);
    return aBundleHolder;
  }

  public List<Member> getBundleHolders()
  {
    List<Member> newBundleHolders = Collections.unmodifiableList(bundleHolders);
    return newBundleHolders;
  }

  public int numberOfBundleHolders()
  {
    int number = bundleHolders.size();
    return number;
  }

  public boolean hasBundleHolders()
  {
    boolean has = bundleHolders.size() > 0;
    return has;
  }

  public int indexOfBundleHolder(Member aBundleHolder)
  {
    int index = bundleHolders.indexOf(aBundleHolder);
    return index;
  }
  /* Code from template association_GetOne */
  public NMCP getNMCP()
  {
    return nMCP;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Equipment addEquipment(int aWeight, String aName, int aPrice, NMCP aNMCP)
  {
    return new Equipment(aWeight, aName, aPrice, aNMCP, this);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipments.contains(aEquipment)) { return false; }
    Bundle existingBundle = aEquipment.getBundle();
    boolean isNewBundle = existingBundle != null && !this.equals(existingBundle);
    if (isNewBundle)
    {
      aEquipment.setBundle(this);
    }
    else
    {
      equipments.add(aEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipment, as it must always have a bundle
    if (!this.equals(aEquipment.getBundle()))
    {
      equipments.remove(aEquipment);
      wasRemoved = true;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundleHolders()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBundleHolder(Member aBundleHolder)
  {
    boolean wasAdded = false;
    if (bundleHolders.contains(aBundleHolder)) { return false; }
    bundleHolders.add(aBundleHolder);
    if (aBundleHolder.indexOfBundle(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBundleHolder.addBundle(this);
      if (!wasAdded)
      {
        bundleHolders.remove(aBundleHolder);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBundleHolder(Member aBundleHolder)
  {
    boolean wasRemoved = false;
    if (!bundleHolders.contains(aBundleHolder))
    {
      return wasRemoved;
    }

    int oldIndex = bundleHolders.indexOf(aBundleHolder);
    bundleHolders.remove(oldIndex);
    if (aBundleHolder.indexOfBundle(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBundleHolder.removeBundle(this);
      if (!wasRemoved)
      {
        bundleHolders.add(oldIndex,aBundleHolder);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundleHolderAt(Member aBundleHolder, int index)
  {  
    boolean wasAdded = false;
    if(addBundleHolder(aBundleHolder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundleHolders()) { index = numberOfBundleHolders() - 1; }
      bundleHolders.remove(aBundleHolder);
      bundleHolders.add(index, aBundleHolder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundleHolderAt(Member aBundleHolder, int index)
  {
    boolean wasAdded = false;
    if(bundleHolders.contains(aBundleHolder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundleHolders()) { index = numberOfBundleHolders() - 1; }
      bundleHolders.remove(aBundleHolder);
      bundleHolders.add(index, aBundleHolder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundleHolderAt(aBundleHolder, index);
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
      existingNMCP.removeBundle(this);
    }
    nMCP.addBundle(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    assignments.add(aAssignment);
    if (aAssignment.indexOfBundle(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAssignment.addBundle(this);
      if (!wasAdded)
      {
        assignments.remove(aAssignment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAssignment(Assignment aAssignment)
  {
    boolean wasRemoved = false;
    if (!assignments.contains(aAssignment))
    {
      return wasRemoved;
    }

    int oldIndex = assignments.indexOf(aAssignment);
    assignments.remove(oldIndex);
    if (aAssignment.indexOfBundle(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAssignment.removeBundle(this);
      if (!wasRemoved)
      {
        assignments.add(oldIndex,aAssignment);
      }
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
    for(int i=equipments.size(); i > 0; i--)
    {
      Equipment aEquipment = equipments.get(i - 1);
      aEquipment.delete();
    }
    ArrayList<Member> copyOfBundleHolders = new ArrayList<Member>(bundleHolders);
    bundleHolders.clear();
    for(Member aBundleHolder : copyOfBundleHolders)
    {
      aBundleHolder.removeBundle(this);
    }
    NMCP placeholderNMCP = nMCP;
    this.nMCP = null;
    if(placeholderNMCP != null)
    {
      placeholderNMCP.removeBundle(this);
    }
    ArrayList<Assignment> copyOfAssignments = new ArrayList<Assignment>(assignments);
    assignments.clear();
    for(Assignment aAssignment : copyOfAssignments)
    {
      aAssignment.removeBundle(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "onDiscount" + ":" + getOnDiscount()+ "," +
            "name" + ":" + getName()+ "," +
            "percentageDiscount" + ":" + getPercentageDiscount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMCP = "+(getNMCP()!=null?Integer.toHexString(System.identityHashCode(getNMCP())):"null");
  }
}