package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 34 "umpleCode.ump"
// line 128 "umpleCode.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private int weight;
  private String name;
  private int price;

  //Equipment Associations
  private NMCP nMCP;
  private Bundle bundle;
  private List<Member> equipmentHolders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(int aWeight, String aName, int aPrice, NMCP aNMCP, Bundle aBundle)
  {
    weight = aWeight;
    name = aName;
    price = aPrice;
    boolean didAddNMCP = setNMCP(aNMCP);
    if (!didAddNMCP)
    {
      throw new RuntimeException("Unable to create equipment due to nMCP. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBundle = setBundle(aBundle);
    if (!didAddBundle)
    {
      throw new RuntimeException("Unable to create equipment due to bundle. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    equipmentHolders = new ArrayList<Member>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeight(int aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
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

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public int getWeight()
  {
    return weight;
  }

  public String getName()
  {
    return name;
  }

  public int getPrice()
  {
    return price;
  }
  /* Code from template association_GetOne */
  public NMCP getNMCP()
  {
    return nMCP;
  }
  /* Code from template association_GetOne */
  public Bundle getBundle()
  {
    return bundle;
  }
  /* Code from template association_GetMany */
  public Member getEquipmentHolder(int index)
  {
    Member aEquipmentHolder = equipmentHolders.get(index);
    return aEquipmentHolder;
  }

  public List<Member> getEquipmentHolders()
  {
    List<Member> newEquipmentHolders = Collections.unmodifiableList(equipmentHolders);
    return newEquipmentHolders;
  }

  public int numberOfEquipmentHolders()
  {
    int number = equipmentHolders.size();
    return number;
  }

  public boolean hasEquipmentHolders()
  {
    boolean has = equipmentHolders.size() > 0;
    return has;
  }

  public int indexOfEquipmentHolder(Member aEquipmentHolder)
  {
    int index = equipmentHolders.indexOf(aEquipmentHolder);
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
      existingNMCP.removeEquipment(this);
    }
    nMCP.addEquipment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBundle(Bundle aBundle)
  {
    boolean wasSet = false;
    if (aBundle == null)
    {
      return wasSet;
    }

    Bundle existingBundle = bundle;
    bundle = aBundle;
    if (existingBundle != null && !existingBundle.equals(aBundle))
    {
      existingBundle.removeEquipment(this);
    }
    bundle.addEquipment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipmentHolders()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addEquipmentHolder(Member aEquipmentHolder)
  {
    boolean wasAdded = false;
    if (equipmentHolders.contains(aEquipmentHolder)) { return false; }
    equipmentHolders.add(aEquipmentHolder);
    if (aEquipmentHolder.indexOfEquipment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEquipmentHolder.addEquipment(this);
      if (!wasAdded)
      {
        equipmentHolders.remove(aEquipmentHolder);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeEquipmentHolder(Member aEquipmentHolder)
  {
    boolean wasRemoved = false;
    if (!equipmentHolders.contains(aEquipmentHolder))
    {
      return wasRemoved;
    }

    int oldIndex = equipmentHolders.indexOf(aEquipmentHolder);
    equipmentHolders.remove(oldIndex);
    if (aEquipmentHolder.indexOfEquipment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEquipmentHolder.removeEquipment(this);
      if (!wasRemoved)
      {
        equipmentHolders.add(oldIndex,aEquipmentHolder);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentHolderAt(Member aEquipmentHolder, int index)
  {  
    boolean wasAdded = false;
    if(addEquipmentHolder(aEquipmentHolder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentHolders()) { index = numberOfEquipmentHolders() - 1; }
      equipmentHolders.remove(aEquipmentHolder);
      equipmentHolders.add(index, aEquipmentHolder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentHolderAt(Member aEquipmentHolder, int index)
  {
    boolean wasAdded = false;
    if(equipmentHolders.contains(aEquipmentHolder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentHolders()) { index = numberOfEquipmentHolders() - 1; }
      equipmentHolders.remove(aEquipmentHolder);
      equipmentHolders.add(index, aEquipmentHolder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentHolderAt(aEquipmentHolder, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    NMCP placeholderNMCP = nMCP;
    this.nMCP = null;
    if(placeholderNMCP != null)
    {
      placeholderNMCP.removeEquipment(this);
    }
    Bundle placeholderBundle = bundle;
    this.bundle = null;
    if(placeholderBundle != null)
    {
      placeholderBundle.removeEquipment(this);
    }
    ArrayList<Member> copyOfEquipmentHolders = new ArrayList<Member>(equipmentHolders);
    equipmentHolders.clear();
    for(Member aEquipmentHolder : copyOfEquipmentHolders)
    {
      aEquipmentHolder.removeEquipment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "weight" + ":" + getWeight()+ "," +
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMCP = "+(getNMCP()!=null?Integer.toHexString(System.identityHashCode(getNMCP())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bundle = "+(getBundle()!=null?Integer.toHexString(System.identityHashCode(getBundle())):"null");
  }
}