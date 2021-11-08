package ca.mcgill.ecse.climbsafe.controller;


import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class AssignmentController {
  
    private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
  
    /**
     * This method initiates the assignment between members,
     * guides and climbing weeks. If there is a member that remains 
     * unassigned at the end of the process, the method throws an
     * InvalidInputException.
     * 
     * @author Asma Gandour
     * @throws InvalidInputException
     */
    public static void initiateAssignmentForAllMembers() throws InvalidInputException {
      
      for(Guide guide: climbSafe.getGuides()) {
         guide.performAssignmentToMembers(); 
      }
      
      try {
        ClimbSafePersistence.save();
      } catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
      
      // checking if all members are assigned
      for(Member member: climbSafe.getMembers()) {
        if(member.getAssignementStatusFullName().equals("Unassigned")) {
          throw new InvalidInputException("Assignments could not be completed for all members");
        }
      }
    }
    
      /**
     * This method toggles the payment status of a Member "member". If the member has already paid, it throws an exception.
     * @author Haroun Guessous
     * @param member
     * @throws InvalidInputException 
     */
    public static void payment(Member member) {
    	var assignment= member.getAssignment();
    	if (assignment.getPaymentStatusFullName().equals("Paid")) {throw new InvalidInputException("Member has already paid for their trip.");}
    	else {assignment.pay();}
    }
    
      public static void startTrips(Integer startingWeekNb) throws InvalidInputException {  // start all trips for a specific week
    	// retrieves all the members
    	for(var member : climbSafe.getMembers()) {
    		var memberAssignment = member.getAssignment();
    		if(memberAssignment.getStartWeek() <= startingWeekNb && startingWeekNb <= memberAssignment.getEndWeek()) {
    			//if at initial state then start the trip otherwise check for other conditions
    			if(memberAssignment.getTripStatusFullName().equals("notStarted")) {
    				memberAssignment.startTrip();	
    			} 
    			if(memberAssignment.getTripStatusFullName().equals("Cancelled")) {
    	    		throw new InvalidInputException("Cannot start a trip which has been cancelled");
    	    	}    			
    			if(memberAssignment.getTripStatusFullName().equals("Finished")) {
    				throw new InvalidInputException("Cannot start a trip which has finished");
    			}    			 
    		} else throw new InvalidInputException("The starting week must be comprised in the climbing weeks");
    	}
    	
      
    }
    
    public static void finishTrip(Member member) throws InvalidInputException {
      var assignment=member.getAssignment();
      if(assignment.getTripStatusFullName().equals("Started")) {
    	  assignment.finishTrip();
      }
      if(assignment.getTripStatusFullName().equals("notStarted")) {
    	  throw new InvalidInputException("Cannot finish a trip which has not started");
      }
      if(assignment.getTripStatusFullName().equals("Cancelled")) {
    	  throw new InvalidInputException("Cannot finish a trip which has been cancelled");
      }
      if(assignment.getTripStatusFullName().equals("Finished")) {
    	  assignment.finishTrip();
      }
    }

    public static void cancelTrip(Member member) {
      
    }


}
