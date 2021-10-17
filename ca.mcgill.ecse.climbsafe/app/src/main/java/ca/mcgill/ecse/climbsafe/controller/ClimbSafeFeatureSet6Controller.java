package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;

public class ClimbSafeFeatureSet6Controller {

  public static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  public static void deleteEquipment(String name) throws InvalidInputException {

    try {
      Equipment equipment = findEquipment(name);

      if (equipment != null) {
        equipment.delete();
      }

    } catch (RuntimeException e) {

      throw new InvalidInputException(e.getMessage());
    }
  }

  // this method does not need to be implemented by a team with five team members
  public static void deleteEquipmentBundle(String name) {}

  public static List<TOAssignment> getAssignments() {

    var assignments = new ArrayList<TOAssignment>();
    for (var assignment : climbSafe.getAssignments()) {
      assignments
          .add(new TOAssignment(assignment.getMember().getEmail(), assignment.getMember().getName(),
              assignment.getGuide().getEmail(), assignment.getGuide().getName(),
              assignment.getHotel().getName(), assignment.getStartWeek(), assignment.getEndWeek(),
              assignment.getTotalCostForGuide(), assignment.getTotalCostForEquipment()));
    }
    return assignments;
  }

  private static Equipment findEquipment(String name) {
    Equipment foundEquipment = null;

    for (Equipment equipment : climbSafe.getEquipment()) {
      if (equipment.getName().equals(name)) {

        foundEquipment =
            new Equipment(name, equipment.getPricePerWeek(), equipment.getWeight(), climbSafe);

        break;
      }
    }

    return foundEquipment;
  }

}
