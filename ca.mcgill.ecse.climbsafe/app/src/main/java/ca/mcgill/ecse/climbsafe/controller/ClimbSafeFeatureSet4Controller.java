package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;


public class ClimbSafeFeatureSet4Controller {

  public static void addEquipment(String name, int weight, int pricePerWeek)
      throws InvalidInputException {}

  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {}


      throw new InvalidInputException(e.getMessage());
    }
  }
}

