package ca.mcgill.ecse.climbsafe.controller;


import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;


public class AssignmentController {

  private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  /**
   * This method initiates the assignment between members, guides and climbing weeks. If there is a
   * member that remains unassigned at the end of the process, the method throws an
   * InvalidInputException.
   * 
   * @author Asma Gandour
   * @throws InvalidInputException
   */
  public static void initiateAssignmentForAllMembers() throws InvalidInputException {
    for (Guide guide : climbSafe.getGuides()) {
      guide.performAssignmentToMembers();
    }

    try {
      ClimbSafePersistence.save(climbSafe);
    } catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }

    // checking if all members are assigned
    for (Member member : climbSafe.getMembers()) {
      if (member.getAssignment() == null) {
        throw new InvalidInputException("Assignments could not be completed for all members");
      }
    }
  }

  /**
   * This method toggles the payment status of a Member "member". If the member has already paid, it
   * throws an exception.
   * 
   * @author Haroun Guessous
   * @param member
   * @throws InvalidInputException
   */
  public static void payment(String email, String paymentAuthorizationCode)
      throws InvalidInputException {
    Member member = (Member) Member.getWithEmail(email);
    if (member == null) {
      throw new InvalidInputException("Member with email address " + email + " does not exist");
    }

    if (paymentAuthorizationCode.isEmpty()) {
      throw new InvalidInputException("Invalid authorization code");
    }

    if (member.getMemberStatusFullName().equals("Banned")) {
      throw new InvalidInputException("Cannot pay for the trip due to a ban");
    }
    var assignment = member.getAssignment();
    switch (assignment.getAssignmentStatusFullName()) {
      case "Cancelled":
        throw new InvalidInputException("Cannot pay for a trip which has been cancelled");
      case "Finished":
        throw new InvalidInputException("Cannot pay for a trip which has finished");
      case "Paid":
        throw new InvalidInputException("Trip has already been paid for");
      case "Started":
        throw new InvalidInputException("Trip has already been paid for");

    }

    assignment.setPaymentAuthorizationCode(paymentAuthorizationCode);
    assignment.pay();
    try {
      ClimbSafePersistence.save(climbSafe);
    } catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }
  }

  /**
   * This method starts all the trips for a given week. If the member is banned (due to no payment),
   * the trip cannot be started.
   * 
   * @author Alexandre Chiasera
   * @param startingWeekNb
   * @throws InvalidInputException
   */
  public static void startTrips(Integer startingWeekNb) throws InvalidInputException { // start all
                                                                                       // trips for
                                                                                       // a specific
                                                                                       // week
    // retrieves all the members
    for (var member : climbSafe.getMembers()) {
      var memberAssignment = member.getAssignment();
      if (memberAssignment.getStartWeek() <= startingWeekNb
          && startingWeekNb <= memberAssignment.getEndWeek()) {
        // if at initial state then start the trip otherwise check for other conditions
        if (memberAssignment.getAssignmentStatusFullName().equals("Paid")
            || memberAssignment.getAssignmentStatusFullName().equals("Assigned")) {
          memberAssignment.startTrip();
          try {
            ClimbSafePersistence.save(climbSafe);
          } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
          }
        }
        if (memberAssignment.getAssignmentStatusFullName().equals("Finished")) {
          throw new InvalidInputException("Cannot start a trip which has finished");
        }
      }
    }
  }

  /**
   * This method finished the trip for a Member "member".
   * 
   * @author Atreyi Srivastava, Asma Gandour
   * 
   * @throws InvalidInputException
   */

  public static void finishTrip(String email) throws InvalidInputException {
    Member member = (Member) User.getWithEmail(email);
    if (member == null) {
      throw new InvalidInputException("Member with email address " + email + " does not exist");
    }
    var assignment = member.getAssignment();

    if (member.getMemberStatusFullName().equals("Banned")) {
      throw new InvalidInputException("Cannot finish the trip due to a ban");
    }

    if (assignment.getAssignmentStatusFullName().equals("Started")) {
      assignment.finishTrip();
    }

    if (assignment.getAssignmentStatusFullName().equals("Cancelled")) {
      throw new InvalidInputException("Cannot finish a trip which has been cancelled");
    }

    if (assignment.getAssignmentStatusFullName().equals("Paid")
        || assignment.getAssignmentStatusFullName().equals("Assigned")) {
      throw new InvalidInputException("Cannot finish a trip which has not started");
    }

    assignment.finishTrip();

    try {
      ClimbSafePersistence.save(climbSafe);
    } catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }
  }

  /**
   * This method cancels the trip for a Member "member".
   * 
   * @author Mohammad Shaheer Bilal
   * 
   * @throws InvalidInputException
   */

  public static void cancelTrip(String email) throws InvalidInputException {
    Member member = (Member) User.getWithEmail(email);
    if (member == null) {
      throw new InvalidInputException("Member with email address " + email + " does not exist");
    }

    if (member.getMemberStatusFullName().equals("Banned")) {
      member.ban();
      throw new InvalidInputException("Cannot cancel the trip due to a ban");

    }
    var assignment = member.getAssignment();
    if (assignment.getAssignmentStatusFullName().equals("Finished")) {
      throw new InvalidInputException("Cannot cancel a trip which has finished");

    }

    if (assignment.getAssignmentStatusFullName().equals("Paid")) {
      member.setRefundPercentage(50);
    }
    if (assignment.getAssignmentStatusFullName().equals("Started")) {
      member.setRefundPercentage(10);
    }

    assignment.cancelTrip();
    try {
      ClimbSafePersistence.save(climbSafe);
    } catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }
  }

  public static List<TOMember> getMembers() {
    var members = new ArrayList<TOMember>();
    for (var member : climbSafe.getMembers()) {
      members.add(new TOMember(member.getEmail(), member.getPassword(), member.getName(),
          member.getEmergencyContact(), member.getNrWeeks(), member.getGuideRequired(),
          member.getHotelRequired()));
    }
    return members;
  }

  public static List<TOGuide> getGuides() {
    var guides = new ArrayList<TOGuide>();
    for (var guide : climbSafe.getGuides()) {
      guides.add(new TOGuide(guide.getEmail(), guide.getPassword(), guide.getName(),
          guide.getEmergencyContact()));
    }
    return guides;
  }

  public static List<TOEquipment> getEquipments() {
    var equipments = new ArrayList<TOEquipment>();
    for (var equipment : climbSafe.getEquipment()) {
      equipments.add(
          new TOEquipment(equipment.getName(), equipment.getWeight(), equipment.getPricePerWeek()));
    }
    return equipments;
  }

  public static List<TOBookableItem> getItems() {
    var items = new ArrayList<TOBookableItem>();
    // adding all equipments
    for (var equipment : climbSafe.getEquipment()) {
      items.add(new TOBookableItem(equipment.getName()));
    }
    // adding all bundles
    for (var bundle : climbSafe.getBundles()) {
      items.add(new TOBookableItem(bundle.getName()));
    }
    return items;
  }

  public static List<Integer> getWeekNbrs() {
    var weekNbrs = new ArrayList<Integer>();
    for (int i = 1; i < climbSafe.getNrWeeks() + 1; i++)
      weekNbrs.add(i);
    return weekNbrs;
  }

}
