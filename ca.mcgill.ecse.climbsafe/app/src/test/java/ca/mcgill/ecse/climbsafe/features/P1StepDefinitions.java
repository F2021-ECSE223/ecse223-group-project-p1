package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.an.E;

public class P1StepDefinitions {

  private ClimbSafe climbSafe;
  private String error;
  private int errorCount;
  
  @Before
  public static void setUp() {
    
    // clear all data
    ClimbSafeApplication.getClimbSafe().delete();
  }

  /**
   * @author Asma Gandour
   */
  @Given("the following ClimbSafe system exists: \\(p1)")
  public void the_following_climb_safe_system_exists_p1(io.cucumber.datatable.DataTable dataTable) {

    List<Object> columns = (dataTable.asLists(Object.class)).get(1);

    climbSafe = new ClimbSafe(Date.valueOf((String) columns.get(0)),
        Integer.parseInt((String) columns.get(1)), Integer.parseInt((String) columns.get(2)));



    error = "";
    errorCount = 0;

  }

  /**
   * @author Alexandre Chiasera
   */
  @Given("the following pieces of equipment exist in the system: \\(p1)")
  public void the_following_pieces_of_equipment_exist_in_the_system_p1(
      io.cucumber.datatable.DataTable dataTable) {
    var rows = dataTable.asMaps();
    
    for(var row: rows) {
      climbSafe.addEquipment(new Equipment(row.get("name"), Integer.parseInt(row.get("weight")), 
          Integer.parseInt(row.get("pricePerWeek")), climbSafe)) ;
    }
    
    
      
  }

  /**
   * 
   * @author Haroun Guessous
   */
  /*
   * @Given("the following equipment bundles exist in the system: \\(p1)") public void
   * the_following_equipment_bundles_exist_in_the_system_p1( io.cucumber.datatable.DataTable
   * dataTable) { var DataTables=dataTable.asMaps(); for (var values : DataTables) { String
   * nameBundle =values.get("name"); int discount =Integer.parseInt(values.get("discount")); String
   * items=values.get("items"); String quantity=values.get("discount"); List<String> itemList =
   * Arrays.asList(items.split(",")); List<Integer> quantityList =
   * Arrays.asList(Integer.parseInt(quantity.split(","))); //you have to convert each parsed String
   * individually var Bundle = new EquipmentBundle(nameBundle, discount, climbSafe); var
   * climbSafeEquipmentList=climbSafe.getEquipment(); for (var equipment : climbSafeEquipmentList) {
   * int r=0; for (int i=0;i<itemList.size();i++) { if (equipment.getName()==itemList.get(i)) {
   * Bundle.addBundleItem(quantityList.get(i), climbSafe, equipment); } } }
   * climbSafe.addBundle(nameBundle, discount); } throw new io.cucumber.java.PendingException(); }
   */
  // -----> Code does not compile

  // @author Haroun , reviewed by @Alexandre_Chiasera because of compilation error
  @Given("the following equipment bundles exist in the system: \\(p1)")
  public void the_following_equipment_bundles_exist_in_the_system_p1(
      io.cucumber.datatable.DataTable dataTable) {
    var rows = dataTable.asMaps();

    for (var row : rows) {
      String nameBundle = row.get("name");
      int discount = Integer.parseInt(row.get("discount"));
      String bundleItems = row.get("items");
      String bundleItemsQuantity = row.get("quantity");

      var newBundle = new EquipmentBundle(nameBundle, discount, climbSafe); // create empty Bundle

      List<Integer> quantityList = new ArrayList<Integer>();
      List<BookableItem> bufferItemList = new ArrayList<BookableItem>();

      for (var item : Arrays.asList(bundleItems.split(","))) {
        var existingItem = Equipment.getWithName(item);
        bufferItemList.add(existingItem);
      }

      for (var bufferItemQuantity : Arrays.asList(bundleItemsQuantity.split(","))) {
        var itemQuantity = Integer.parseInt(bufferItemQuantity);
        quantityList.add(itemQuantity);
      }

      for (int i = 0; i < climbSafe.getEquipment().size(); i++) {
        for (int x = 0; x < bufferItemList.size(); x++) {
          var existingEquipmentName = climbSafe.getEquipment(i).getName();
          var addedItemName = bufferItemList.get(x).getName();
          var itemQuantity = quantityList.get(x);
          if (existingEquipmentName.equals(addedItemName) ) {
            newBundle.addBundleItem(itemQuantity, climbSafe, climbSafe.getEquipment(i)); // add to
                                                                                         // empty
                                                                                         // Bundle
                                                                                         // pieces
                                                                                         // of
                                                                                         // equipments
                                                                                         // already
                                                                                         // existing
          }

        }
      }
    }
  }


  /**
   * @author Asma Gandour
   */
  @When("the administator attempts to update the piece of equipment in the system with name {string} to have name {string}, weight {string}, and price per week {string} \\(p1)")
  public void the_administator_attempts_to_update_the_piece_of_equipment_in_the_system_with_name_to_have_name_weight_and_price_per_week_p1(
      String string, String string2, String string3, String string4) {

    callController(() -> ClimbSafeFeatureSet4Controller.updateEquipment(string, string2,
        Integer.parseInt(string3), Integer.parseInt(string4)));

  }

  /**
   * 
   * @author Haroun Guessous
   */
  @Then("the number of pieces of equipment in the system shall be {string} \\(p1)")
  public void the_number_of_pieces_of_equipment_in_the_system_shall_be_p1(String string) {
    
    assertEquals(Integer.parseInt(string), climbSafe.getEquipment().size());
    
  }

  /**
   * 
   * @author Alexandre Chiasera
   */

  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall not exist in the system \\(p1)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_not_exist_in_the_system_p1(
      String string, String string2, String string3) {

    
    Integer weight = Integer.parseInt(string2);
    Integer pricePerWeek = Integer.parseInt(string3);

    for (Equipment pieceOfEquipment : climbSafe.getEquipment()) { // for each piece of equipment in
                                                                  // the climbSafe system (for the
                                                                  // admin)
      if (pieceOfEquipment.getName().equals(string) && pieceOfEquipment.getWeight() == weight
          && pieceOfEquipment.getPricePerWeek() == pricePerWeek) {
        fail("The piece of equipment <" + pieceOfEquipment.getName()
            + "> has not been deleted from the system"); // if everything matches, it means the
                                                         // object is not deleted.
      }
    }
  }

  /**
   * 
   * @author Alexandre Chiasera
   */
  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall exist in the system \\(p1)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_exist_in_the_system_p1(
      String string, String string2, String string3) {

    String name = string;
    Integer weight = Integer.parseInt(string2);
    Integer pricePerWeek = Integer.parseInt(string3);
    Boolean isEquipmentInTheSystem = false;

    for (Equipment pieceOfEquipment : climbSafe.getEquipment()) { // for each piece of equipment in
                                                                  // the climbSafe system (for the
                                                                  // admin)
      if (pieceOfEquipment.getName().equals(name) && pieceOfEquipment.getWeight() == weight
          && pieceOfEquipment.getPricePerWeek() == pricePerWeek) {
        isEquipmentInTheSystem = true;// if everything matches --> object in the system.
      }
    }

    assertEquals(true, isEquipmentInTheSystem);
    assertEquals("", error);
    assertEquals(0, errorCount);
    
    
  }

  /**
   * @author Atreyi Srivastava
   */

  @Then("the following pieces of equipment shall exist in the system: \\(p1)")
  public void the_following_pieces_of_equipment_shall_exist_in_the_system_p1(
      io.cucumber.datatable.DataTable dataTable) {
    
    var table = dataTable.asMaps();
    Boolean alreadyExist = false;
    for (var val : table) {
      String name = val.get("name");
      int weight = Integer.parseInt(val.get("weight"));
      int pricePerWeek = Integer.parseInt(val.get("pricePerWeek"));

      for (Equipment equipments : climbSafe.getEquipment()) {
        if (equipments.getName().equals(name) && equipments.getWeight() == weight
            && equipments.getPricePerWeek() == pricePerWeek) {
          alreadyExist = true;
        }
      }
    }
    assertEquals(true, alreadyExist);
    assertEquals("", error);
    assertEquals(0, errorCount);
  }

  @Then("the system shall raise the error {string} \\(p1)")
  public void the_system_shall_raise_the_error_p1(String string) {

    assertEquals(string, error);

  }

  @Then("the number of equipment bundles in the system shall be {string} \\(p1)")
  public void the_number_of_equipment_bundles_in_the_system_shall_be_p1(String string) {

    assertEquals(Integer.parseInt(string), climbSafe.getBundles().size());

  }

  /**
   * @author Atreyi Srivastava
   */

  @Then("the following equipment bundles shall exist in the system: \\(p1)")
  public void the_following_equipment_bundles_shall_exist_in_the_system_p1(
      io.cucumber.datatable.DataTable dataTable) {
    
    var table = dataTable.asMaps();
    Boolean alreadyExist = false;
    for (var val : table) {
      String nameBundle = val.get("name");
      int discount = Integer.parseInt(val.get("discount"));
      
      for (EquipmentBundle bundle : climbSafe.getBundles()) {
        if (bundle.getName().equals(nameBundle) && bundle.getDiscount() == discount) {
          alreadyExist = true;
        }
      }
    }
    assertEquals(true, alreadyExist);
    assertEquals("", error);
    assertEquals(0, errorCount);
  }
  
  @After
  public void tearDown() {
    climbSafe.delete();
  }

  private void callController(Executable executable) {
    try {
      executable.execute();
    } catch (InvalidInputException e) {
      error += e.getMessage();
      errorCount += 1;
    } catch (Throwable t) {
      fail();
    }
  }

}
