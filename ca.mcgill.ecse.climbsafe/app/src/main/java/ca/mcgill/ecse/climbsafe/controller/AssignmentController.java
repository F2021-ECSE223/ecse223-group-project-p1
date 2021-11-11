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
        if(member.getAssignment() == null) {
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
public static void payment(String email, String paymentAuthorizationCode) throws InvalidInputException{
	  Member member= (Member)Member.getWithEmail(email);
      if(member==null){
       throw new InvalidInputException("Member with email address"+email+"does not exist"); 
      }
      switch(member.getMemberStatusFullName())
      {
      case "Cancelled":
    	  throw new InvalidInputException("Cannot pay for a trip which has been cancelled");
      case "Finished":
    	  throw new InvalidInputException("Cannot pay for a trip which has finished");
      case "Banned":
    	  throw new InvalidInputException("Cannot cancel the trip due to a ban");
      }
    	var assignment= member.getAssignment();
    	if (assignment.getAssignmentStatusFullName().equals("Paid") || assignment.getAssignmentStatusFullName().equals("Started")) {throw new InvalidInputException("Member has already paid for their trip.");}
    	else {
    		assignment.setPaymentAuthorizationCode(paymentAuthorizationCode);
    		assignment.pay();
    	try {
            ClimbSafePersistence.save();
          } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
          } 
        }
    	}
 /**
     * This method starts all the trips for a given week. If the member is banned (due to no payment), the trip cannot be started. 
     * @author Alexandre Chiasera
     * @param startingWeekNb 
     * @throws InvalidInputException 
     */
    public static void startTrips(Integer startingWeekNb) throws InvalidInputException {  // start all trips for a specific week
    	// retrieves all the members
    	for(var member : climbSafe.getMembers()) {
    		var memberAssignment = member.getAssignment();
    		if(memberAssignment.getStartWeek() <= startingWeekNb && startingWeekNb <= memberAssignment.getEndWeek()) {  			
    			if(member.getMemberStatusFullName().equals("Banned")){
    		        throw new InvalidInputException("Cannot start the trip due to a ban");
    		      }
    			//if at initial state then start the trip otherwise check for other conditions
    			if(memberAssignment.getAssignmentStatusFullName().equals("Paid") || memberAssignment.getAssignmentStatusFullName().equals("Assigned")) {
    				memberAssignment.startTrip();
    				try {
    			        ClimbSafePersistence.save();
    			      } catch (RuntimeException e) {
    			        throw new InvalidInputException(e.getMessage());
    			      }    			      
    			} 
    			if(memberAssignment.getAssignmentStatusFullName().equals("Cancelled")) {
    	    		throw new InvalidInputException("Cannot start a trip which has been cancelled");
    	    	}    			
    			if(memberAssignment.getAssignmentStatusFullName().equals("Finished")) {
    				throw new InvalidInputException("Cannot start a trip which has finished");
    			}    			 
    		}
    	}   
    }
  
  /**
     * This method finished the trip for a Member "member". 
     * @author Atreyi Srivastava, Asma Gandour
     * 
     * @throws InvalidInputException 
     */
    
    public static void finishTrip(String email) throws InvalidInputException {
      Member member= (Member)User.getWithEmail(email);
      if(member==null){
       throw new InvalidInputException("Member with email address"+email+"does not exist"); 
      }
      var assignment=member.getAssignment();
	    
      if(member.getMemberStatusFullName().equals("Banned")){
        throw new InvalidInputException("Cannot finish the trip due to a ban");
      }
	    
      if(assignment.getAssignmentStatusFullName().equals("Started")) {
    	  assignment.finishTrip();
      }
      
      if(assignment.getAssignmentStatusFullName().equals("Cancelled")) {
    	  throw new InvalidInputException("Cannot finish a trip which has been cancelled");
      }
      
      if(assignment.getAssignmentStatusFullName().equals("Paid")||assignment.getAssignmentStatusFullName().equals("Assigned")){
        throw new InvalidInputException("Cannot finish a trip which has not started");
      }
      
      assignment.finishTrip();
      
      try {
        ClimbSafePersistence.save();
      } catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
    }
  
  /**
     * This method cancels the trip for a Member "member". 
     * @author Mohammad Shaheer Bilal 
     * 
     * @throws InvalidInputException 
     */

     public static void cancelTrip(String email) throws InvalidInputException {
    	Member member = (Member)User.getWithEmail(email);
    	if(member==null){
    		throw new InvalidInputException("Member with email address"+email+"does not exist"); 
    	    }
	     
	if(member.getMemberStatusFullName().equals("Banned")){
    		member.ban();
	        throw new InvalidInputException("Cannot cancel the trip due to a ban");
	        
	      }
	var assignment=member.getAssignment();   
	if(assignment.getAssignmentStatusFullName().equals("Finished")) {
    		throw new InvalidInputException("Cannot cancel a trip which has finished");
    		
    	}
    	
    	if(assignment.getAssignmentStatusFullName().equals("Paid")) {
    		member.setRefundPercentage(50);
    	}
    	if(assignment.getAssignmentStatusFullName().equals("Started")) {
    		member.setRefundPercentage(10);
       	}

    	assignment.cancelTrip();
    	try {
            ClimbSafePersistence.save();
          } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
          }
    }
}
