package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.User;

public class ClimbSafeFeatureSet1Controller {
	
	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

    public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
	  try {
	  climbSafe.setStartDate(startDate);climbSafe.setNrWeeks(nrWeeks);climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
  }
	  catch (RuntimeException e) {

	      throw new InvalidInputException(e.getMessage());
	    }
  }
	
 /**
   * @author Alexandre Chiasera
   */	
	
public static void deleteMember(String email) {
	 
	 User user = User.getWithEmail(email);
	 if(user != null && !(user instanceof Guide)) {
		 user.delete();
	 }
	  
  }
  
  public static void deleteGuide(String email) {}

  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {}

}
