/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;

// line 76 "../../../../../ClimbSafeStates.ump"
// line 154 "../../../../../ClimbSafePersistence.ump"
// line 77 "../../../../../ClimbSafe.ump"
public class Assignment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private int startWeek;
  private int endWeek;

  //Assignment State Machines
  public enum TripStatus { notStarted, Started, Finished, Cancelled }
  private TripStatus tripStatus;
  public enum PaymentStatus { notPaid, paid }
  private PaymentStatus paymentStatus;

  //Assignment Associations
  private Member member;
  private Guide guide;
  private Hotel hotel;
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(int aStartWeek, int aEndWeek, Member aMember, ClimbSafe aClimbSafe)
  {
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create assignment due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create assignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setTripStatus(TripStatus.notStarted);
    setPaymentStatus(PaymentStatus.notPaid);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(int aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
  }

  public String getTripStatusFullName()
  {
    String answer = tripStatus.toString();
    return answer;
  }

  public String getPaymentStatusFullName()
  {
    String answer = paymentStatus.toString();
    return answer;
  }

  public TripStatus getTripStatus()
  {
    return tripStatus;
  }

  public PaymentStatus getPaymentStatus()
  {
    return paymentStatus;
  }

  public boolean startTrip()
  {
    boolean wasEventProcessed = false;
    
    TripStatus aTripStatus = tripStatus;
    switch (aTripStatus)
    {
      case notStarted:
        setTripStatus(TripStatus.Started);
        wasEventProcessed = true;
        break;
      case Started:
        setTripStatus(TripStatus.Started);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancelTrip()
  {
    boolean wasEventProcessed = false;
    
    TripStatus aTripStatus = tripStatus;
    switch (aTripStatus)
    {
      case notStarted:
        setTripStatus(TripStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        setTripStatus(TripStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Cancelled:
        setTripStatus(TripStatus.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finishTrip()
  {
    boolean wasEventProcessed = false;
    
    TripStatus aTripStatus = tripStatus;
    switch (aTripStatus)
    {
      case Started:
        setTripStatus(TripStatus.Finished);
        wasEventProcessed = true;
        break;
      case Finished:
        setTripStatus(TripStatus.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pay()
  {
    boolean wasEventProcessed = false;
    
    PaymentStatus aPaymentStatus = paymentStatus;
    switch (aPaymentStatus)
    {
      case notPaid:
        setPaymentStatus(PaymentStatus.paid);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setTripStatus(TripStatus aTripStatus)
  {
    tripStatus = aTripStatus;
  }

  private void setPaymentStatus(PaymentStatus aPaymentStatus)
  {
    paymentStatus = aPaymentStatus;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
  }

  public boolean hasGuide()
  {
    boolean has = guide != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }

  public boolean hasHotel()
  {
    boolean has = hotel != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (aNewMember == null)
    {
      //Unable to setMember to null, as assignment must always be associated to a member
      return wasSet;
    }
    
    Assignment existingAssignment = aNewMember.getAssignment();
    if (existingAssignment != null && !equals(existingAssignment))
    {
      //Unable to setMember, the current member already has a assignment, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Member anOldMember = member;
    member = aNewMember;
    member.setAssignment(this);

    if (anOldMember != null)
    {
      anOldMember.setAssignment(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setGuide(Guide aGuide)
  {
    boolean wasSet = false;
    Guide existingGuide = guide;
    guide = aGuide;
    if (existingGuide != null && !existingGuide.equals(aGuide))
    {
      existingGuide.removeAssignment(this);
    }
    if (aGuide != null)
    {
      aGuide.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeAssignment(this);
    }
    if (aHotel != null)
    {
      aHotel.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
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
      existingClimbSafe.removeAssignment(this);
    }
    climbSafe.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.setAssignment(null);
    }
    if (guide != null)
    {
      Guide placeholderGuide = guide;
      this.guide = null;
      placeholderGuide.removeAssignment(this);
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeAssignment(this);
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeAssignment(this);
    }
  }

  // line 85 "../../../../../ClimbSafe.ump"
   public int getTotalCostForGuide(){
    int totalCostForGuide = 0;
	 
	 // If no guide, then total cost = 0
	 if(this.hasGuide()){
	 
		 int nbrWeek = this.getMember().getNrWeeks();
		 	
		 	// Compute total cost
		  	totalCostForGuide += this.getClimbSafe().getPriceOfGuidePerWeek()*nbrWeek;
		 
	 }
	  return totalCostForGuide;
  }

  // line 100 "../../../../../ClimbSafe.ump"
   public int getTotalCostForEquipment(){
    double totalCostForEquipment = 0;
	 int nbrWeek = this.getMember().getNrWeeks();
	 if(this.getMember().hasBookedItems()){
	 	
	 	
	 	// Iterating over the booked items of the member
	 	for(BookedItem bookedItem: this.getMember().getBookedItems()){
	 	int quantity = bookedItem.getQuantity();
	 	
	  	if(bookedItem.getItem() instanceof EquipmentBundle){
	  	
	  		
	  				// Then we get the bundle
	  				EquipmentBundle bundle = ((EquipmentBundle) bookedItem.getItem());
	  				
	  				
					// And we iterate over the items of equipment bundle
					for(BundleItem item: bundle.getBundleItems()){
					
						// We check if a guide is hired
						if(this.getMember().isGuideRequired()){
							
						
							// If so, the discount is applied on the equipments
							totalCostForEquipment += item.getQuantity() * quantity*
								item.getEquipment().getPricePerWeek() * ( (100-bundle.getDiscount()) /100.0 ) * nbrWeek;
						}else{
						
							// If not, the equipments are rented at regular price
							
							totalCostForEquipment += item.getQuantity() * 
								item.getEquipment().getPricePerWeek() * quantity* nbrWeek;
						}
					
					}
	  		// If the current item is an equipment not in a bundle
	   	}else if(bookedItem.getItem() instanceof Equipment){
	 		
	 			// Then the cost is the equipment's price per week times the number of weeks
	  			totalCostForEquipment += ((Equipment) bookedItem.getItem()).getPricePerWeek()* quantity* nbrWeek;
	  			
	  		
	  	}
	  		
	  		
	  	}
	 }
	 return ((int) totalCostForEquipment);
  }


  public String toString()
  {
    return super.toString() + "["+
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 158 "../../../../../ClimbSafePersistence.ump"
  private static final long serialVersionUID = 8613302774459815337L ;

  
}