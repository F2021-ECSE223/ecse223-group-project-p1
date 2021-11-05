package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;

public class AssignmentController {
  
    private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
    
    public static void initiateAssignmentForAllMembers() throws InvalidInputException {
      
      for(Guide guide: climbSafe.getGuides()) {
         guide.performAssignmentToMembers(); 
      }
      
      // checking if all members are assigned
      for(Member member: climbSafe.getMembers()) {
        if(member.getAssignementStatusFullName().equals("Unassigned")) {
          throw new InvalidInputException("Assignments could not be completed for all members");
        }
      }
    }
    
    public static void payment(Member member) {
      
    }
    
    public static void startTrips(Integer startingWeekNb) {
      
    }
    
    public static void finishTrip(Member member) {
      
    }

    public static void cancelTrip(Member member) {
      
    }

}
