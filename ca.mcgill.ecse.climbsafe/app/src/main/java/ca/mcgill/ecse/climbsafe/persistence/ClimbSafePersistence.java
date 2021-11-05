package ca.mcgill.ecse.climbsafe.persistence;

import java.sql.Date;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

public class ClimbSafePersistence {
  
  private static String filename = "data.climbSafe";

  public static void setFilename(String filename) {
    ClimbSafePersistence.filename = filename;
  }

  public static void save() {
    PersistenceObjectStream.setFilename(filename);
    save(ClimbSafeApplication.getClimbSafe());
  }

  public static void save(ClimbSafe btms) {
    PersistenceObjectStream.setFilename(filename);
    PersistenceObjectStream.serialize(btms);
  }

  public static ClimbSafe load() {
    PersistenceObjectStream.setFilename(filename);
    var climbSafe = (ClimbSafe) PersistenceObjectStream.deserialize();
    // model cannot be loaded - create empty BTMS
    if (climbSafe == null) {
      climbSafe = new ClimbSafe(new Date(0), 0, 0);
    } else {
      climbSafe.reinitialize();
    }
    return climbSafe;
  }

}