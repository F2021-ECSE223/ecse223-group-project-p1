/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;
import java.util.*;

// line 20 "../../../../../ClimbSafeStates.ump"
// line 71 "../../../../../ClimbSafePersistence.ump"
// line 38 "../../../../../ClimbSafe.ump"
public class Guide extends NamedUser implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Guide Associations
  private ClimbSafe climbSafe;
  private List<Assignment> assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Guide(String aEmail, String aPassword, String aName, String aEmergencyContact, ClimbSafe aClimbSafe)
  {
    super(aEmail, aPassword, aName, aEmergencyContact);
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create guide due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    assignments = new ArrayList<Assignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
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
  /* Code from template association_SetOneToMany */
  public boolean setClimbSafe(ClimbSafe aClimbSafe)
  {
    boolean wasSet = false;
    if (aClimbSafe == null)
    {
      return wasSet;
    }

    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = aClimbSafe;
    if (existingClimbSafe != null && !existingClimbSafe.equals(aClimbSafe))
    {
      existingClimbSafe.removeGuide(this);
    }
    climbSafe.addGuide(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    Guide existingGuide = aAssignment.getGuide();
    if (existingGuide == null)
    {
      aAssignment.setGuide(this);
    }
    else if (!this.equals(existingGuide))
    {
      existingGuide.removeAssignment(aAssignment);
      addAssignment(aAssignment);
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
    if (assignments.contains(aAssignment))
    {
      assignments.remove(aAssignment);
      aAssignment.setGuide(null);
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
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeGuide(this);
    }
    while( !assignments.isEmpty() )
    {
      assignments.get(0).setGuide(null);
    }
    super.delete();
  }


  /**
   * Returns true if a guide has no assignment for
   * a given number of weeks
   * @author Asma Gandour
   */
  // line 26 "../../../../../ClimbSafeStates.ump"
   public boolean isAvailableFor(Integer nbrWeeks){
    if(hasAssignments()){
      Integer lastWeek = getAssignment(numberOfAssignments()-1).getEndWeek();
      if(getClimbSafe().getNrWeeks()-lastWeek < nbrWeeks)
        return false;
    }
    
    return true;
  }


  /**
   * Assigns this guide to members that require a guide
   * until the guide is not available anymore
   * @author Asma Gandour
   */
  // line 39 "../../../../../ClimbSafeStates.ump"
   public boolean performAssignmentToMembers(){
    Integer startWeek = 1;
		Integer endWeek;
		for(Member member: climbSafe.getMembers()){

			if(member.getAssignementStatusFullName().equals("Unassigned")){
				if(!member.isGuideRequired()){

					endWeek = startWeek + member.getNrWeeks() - 1;
					Assignment assignment = new Assignment(startWeek, endWeek, member, climbSafe);
					climbSafe.addAssignment(assignment);
					member.toggleStatus();

				}else{
					if(isAvailableFor(member.getNrWeeks())){
						if(hasAssignments()){
							startWeek = getAssignment(numberOfAssignments()-1).getEndWeek()+1;

						}
						endWeek = startWeek + member.getNrWeeks() - 1;
						Assignment assignment = new Assignment(startWeek, endWeek, member, climbSafe);
						addAssignment(assignment);
						climbSafe.addAssignment(assignment);
						member.toggleStatus();


					}

				}
			}
		}

		return true;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 74 "../../../../../ClimbSafePersistence.ump"
  private static final long serialVersionUID = -3900912597282882073L ;

  
}