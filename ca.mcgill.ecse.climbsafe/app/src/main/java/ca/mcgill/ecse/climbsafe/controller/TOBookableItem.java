/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 52 "../../../../../ClimbSafeTransferObjects.ump"
public class TOBookableItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBookableItem Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBookableItem(String aName)
  {
    name = aName;
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

  public String getName()
  {
    return name;
  }

  public void delete()
  {}


  @Override
  // line 55 "../../../../../ClimbSafeTransferObjects.ump"
   public String toString(){
    return name;
  }

}