package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class ClimbSafeFeatureSet6Controller {

  public static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  /**
   * Corresponding Feature: DeleteEquipment
   * 
   * This method deletes an equipment if it not in a bundle. Else, it throws an exception.
   * 
   * @author Asma Gandour
   * @param name the name of the equipment to be deleted
   * @throws InvalidInputException
   */
  public static void deleteEquipment(String name) throws InvalidInputException {
    var error = "";
    Equipment equipment = findEquipment(name);

    if (equipment != null && equipment.hasBundleItems()) {
      error = "The piece of equipment is in a bundle and cannot be deleted";
    }

    if (!error.isEmpty()) {
      throw new InvalidInputException(error.trim());
    }

    if (equipment != null) {
      equipment.delete();

      try {
        ClimbSafePersistence.save(climbSafe);
      } catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
    }
  }

  // this method does not need to be implemented by a team with five team members
  public static void deleteEquipmentBundle(String name) {}

  /**
   * Corresponding Feature: ViewAssignment
   * 
   * This method iterates over climbSafe's assignments, converts each one of them into a TO
   * assignment and returns a list of these TO assignments.
   * 
   * @author Haroun Guessous
   * @return the list of TOAssignments for the current climbSafe
   */
  public static List<TOAssignment> getAssignments() {
    var assignments = new ArrayList<TOAssignment>();
    String status;
    for (var assignment : climbSafe.getAssignments()) {
      if(assignment.getMember().getMemberStatusFullName().equals("Banned")) {
        status = "Banned";
      }else {
        status = assignment.getAssignmentStatusFullName();
      }
      if (assignment.getHotel() != null && assignment.getGuide() != null) {
        assignments.add(
            new TOAssignment(assignment.getMember().getEmail(), assignment.getMember().getName(),
                assignment.getGuide().getEmail(), assignment.getGuide().getName(),
                assignment.getHotel().getName(), assignment.getStartWeek(), assignment.getEndWeek(),
                assignment.getTotalCostForGuide(), assignment.getTotalCostForEquipment(), 
                status, assignment.getPaymentAuthorizationCode(),
                assignment.getMember().getRefundPercentage()));
      }
      if (assignment.getHotel() == null && assignment.getGuide() != null) {
        assignments.add(
            new TOAssignment(assignment.getMember().getEmail(), assignment.getMember().getName(),
                assignment.getGuide().getEmail(), assignment.getGuide().getName(), null,
                assignment.getStartWeek(), assignment.getEndWeek(),
                assignment.getTotalCostForGuide(), assignment.getTotalCostForEquipment(), 
                status, assignment.getPaymentAuthorizationCode(),
                assignment.getMember().getRefundPercentage()));
      }
      if (assignment.getHotel() != null && assignment.getGuide() == null) {
        assignments.add(
            new TOAssignment(assignment.getMember().getEmail(), assignment.getMember().getName(),
                null, null, assignment.getHotel().getName(), assignment.getStartWeek(),
                assignment.getEndWeek(), 0, assignment.getTotalCostForEquipment(), 
                status, assignment.getPaymentAuthorizationCode(),
                assignment.getMember().getRefundPercentage()));
      }
      if (assignment.getHotel() == null && assignment.getGuide() == null) {
        assignments.add(new TOAssignment(assignment.getMember().getEmail(),
            assignment.getMember().getName(), null, null, null, assignment.getStartWeek(),
            assignment.getEndWeek(), 0, assignment.getTotalCostForEquipment(), 
            status, assignment.getPaymentAuthorizationCode(),
            assignment.getMember().getRefundPercentage()));
      }
    }
    return assignments;
  }


  /**
   * 
   * A helper method for deleteEquipment(). It iterates over climbSafe's list of equipments to find
   * the equipment with the name corresponding to the input name
   * 
   * @author Asma Gandour
   * @param name the name of the equipment to be found
   * @return the equipment whose name corresponds to name
   */
  private static Equipment findEquipment(String name) {
    Equipment foundEquipment = null;

    for (Equipment equipment : climbSafe.getEquipment()) {
      if (equipment.getName().equals(name)) {

        foundEquipment = equipment;

        break;
      }
    }

    return foundEquipment;
  }

}
