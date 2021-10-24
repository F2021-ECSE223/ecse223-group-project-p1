package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Member;
public class ClimbSafeFeatureSet2Controller {
	
	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

 /**
   * @author Alexandre Chiasera
   */	
	
  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
	  try {
		  climbSafe.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired);
	  } catch (RuntimeException e){
		  throw new InvalidInputException(e.getMessage());		  
	  }
  }

 /**
   * @author Alexandre Chiasera
   */
	
  public static void updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
      throws InvalidInputException {
	  try {		  
		  for(Member member: climbSafe.getMembers()) { //the email being unique and final, it is the element we will be querying 
			  var memberEmail = member.getEmail();     // the same process is used in "forgot password" cases
			  if(email == memberEmail) { //if found in Member List
				  member.setPassword(newPassword);
				  member.setName(newName);
				  member.setEmergencyContact(newEmergencyContact);
				  member.setNrWeeks(newNrWeeks);
				  member.setGuideRequired(newGuideRequired);
				  member.setHotelRequired(newHotelRequired);
				  for(int i = 0; i < newItemNames.size(); i ++) { //newItemNames and newItemQuantities should be the same size or an error will be thrown since each item booked has a size of at least 0
					  var bookableItem = BookableItem.getWithName(newItemNames.get(i));
					  member.addBookedItem(newItemQuantities.get(i), climbSafe, bookableItem);					
				  }
			  }
		  }
		  
		  
	  } catch (RuntimeException e) {
		  throw new InvalidInputException(e.getMessage());
	  }
  }

}
