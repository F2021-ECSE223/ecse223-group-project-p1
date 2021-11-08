package ca.mcgill.ecse.climbsafe.features;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.function.Executable;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignmentFeatureStepDefinitions {

  private ClimbSafe climbSafe;
  private String error;
	
  /**
   * 
   * @author Asma Gandour
   */
  @Given("the following ClimbSafe system exists:")
  public void the_following_climb_safe_system_exists(io.cucumber.datatable.DataTable dataTable) {
    var rows = dataTable.asMaps();
    for (var row : rows) {
      climbSafe = ClimbSafeApplication.getClimbSafe();
      climbSafe.setStartDate(Date.valueOf(row.get("startDate")));
      climbSafe.setNrWeeks(Integer.parseInt(row.get("nrWeeks")));
      climbSafe.setPriceOfGuidePerWeek(Integer.parseInt(row.get("priceOfGuidePerWeek")));
    }
    error = "";
  }
  /**
   * 
   * @author Asma Gandour
   */
  @Given("the following pieces of equipment exist in the system:")
  public void the_following_pieces_of_equipment_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    var rows = dataTable.asMaps();

    for (var row : rows) {
      climbSafe.addEquipment(row.get("name"), Integer.parseInt(row.get("weight")),
          Integer.parseInt(row.get("pricePerWeek")));
    }
  }
  /**
   * 
   * @author Asma Gandour
   */
  @Given("the following equipment bundles exist in the system:")
  public void the_following_equipment_bundles_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    var rows = dataTable.asMaps();

    for (var row : rows) {
      String nameBundle = row.get("name");
      int discount = Integer.parseInt(row.get("discount"));
      String bundleItems = row.get("items");
      String bundleItemQuantities = row.get("quantity");
      // create empty Bundle
      var newBundle = new EquipmentBundle(nameBundle, discount, climbSafe);

      List<Integer> quantities = new ArrayList<Integer>();
      List<BookableItem> bookableItems = new ArrayList<BookableItem>();

      for (var item : Arrays.asList(bundleItems.split(","))) {
        var existingItem = Equipment.getWithName(item);
        bookableItems.add(existingItem);
      }

      for (var itemsQuantity : Arrays.asList(bundleItemQuantities.split(","))) {
        var itemQuantity = Integer.parseInt(itemsQuantity);
        quantities.add(itemQuantity);
      }

      for (var equipment : climbSafe.getEquipment()) {
        for (int i = 0; i < bookableItems.size(); i++) {
          var existingEquipmentName = equipment.getName();
          var addedItemName = bookableItems.get(i).getName();
          var itemQuantity = quantities.get(i);
          if (existingEquipmentName.equals(addedItemName)) {
            // add already existing pieces of equipment to empty bundle
            newBundle.addBundleItem(itemQuantity, climbSafe, equipment);
          }
        }
      }
    }
  }
  /**
   * 
   * @author Asma Gandour
   */
  @Given("the following guides exist in the system:")
  public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> guideList = dataTable.asMaps();
    for (int i = 0; i < guideList.size(); i++) {
      String guideEmail = guideList.get(i).get("email");
      String guidePassword = guideList.get(i).get("password");
      String guideName = guideList.get(i).get("name");
      String guideEmergencyContact = guideList.get(i).get("emergencyContact");

      climbSafe.addGuide(guideEmail, guidePassword, guideName, guideEmergencyContact);
    }
  }
  /**
   * 
   * @author Asma Gandour
   */
  @Given("the following members exist in the system:")
  public void the_following_members_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> memberList = dataTable.asMaps();
    for (int i = 0; i < memberList.size(); i++) {
      climbSafe.addMember(memberList.get(i).get("email"), memberList.get(i).get("password"),
          memberList.get(i).get("name"), memberList.get(i).get("emergencyContact"),
          parseInt(memberList.get(i).get("nrWeeks")),
          parseBoolean(memberList.get(i).get("guideRequired")),
          parseBoolean(memberList.get(i).get("hotelRequired")));
    }
  }
  /**
   * 
   * @author Asma Gandour
   */
  @When("the administrator attempts to initiate the assignment process")
  public void the_administrator_attempts_to_initiate_the_assignment_process() {
    callController(() -> AssignmentController.initiateAssignmentForAllMembers());
  }
  /**
   * 
   * @author Asma Gandour
   */
  @Then("the following assignments shall exist in the system:")
  public void the_following_assignments_shall_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> outputDataList = dataTable.asMaps();

    for (int i = 0; i < outputDataList.size(); i++) {

      Assignment assignment = climbSafe.getAssignment(i);

      Map<String, String> assignmentData = outputDataList.get(i);

      assertEquals(assignmentData.get("memberEmail"), assignment.getMember().getEmail());

      if (assignmentData.get("guideEmail") == null) {
        assertEquals(null, assignment.getGuide());
      } else {
        assertEquals(assignmentData.get("guideEmail"), assignment.getGuide().getEmail());
      }
      assertEquals(Integer.parseInt(assignmentData.get("startWeek")), assignment.getStartWeek());
      assertEquals(Integer.parseInt(assignmentData.get("endWeek")), assignment.getEndWeek());
    }
  }
  /**
   * 
   * @author Asma Gandour
   */
  @Then("the assignment for {string} shall be marked as {string}")
  public void the_assignment_for_shall_be_marked_as(String string, String string2) {
    assertEquals(string2, ((Member) User.getWithEmail(string)).getAssignementStatusFullName());
  }
  /**
   * 
   * @author Asma Gandour
   */
  @Then("the number of assignments in the system shall be {string}")
  public void the_number_of_assignments_in_the_system_shall_be(String string) {
    assertEquals(string, Integer.toString(climbSafe.numberOfAssignments()));
  }

  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();   
  }

  @Given("the following assignments exist in the system:")
  public void the_following_assignments_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

 /*
 * @author : Haroun Guessous
 */
  @When("the administrator attempts to confirm payment for {string} using authorization code {string}")
  public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(
      String string, String string2) {
      var member=(Member)Member.getWithEmail(string);
      callController(() -> AssignmentController.payment(member));
      throw new io.cucumber.java.PendingException();
  }

  @Then("the assignment for {string} shall record the authorization code {string}")
  public void the_assignment_for_shall_record_the_authorization_code(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the member account with the email {string} does not exist")
  public void the_member_account_with_the_email_does_not_exist(String string) {
     int temp=0;
      for (int i=0;i<climbSafe.getMembers().size();i++)
      {
        if (climbSafe.getMembers().get(i).getEmail()==string)
        {
          temp=i;
        }
      }
    assertFalse(string==climbSafe.getMembers().get(temp).getEmail());
    throw new io.cucumber.java.PendingException();
  }

  @Then("there are {string} members in the system")
  public void there_are_members_in_the_system(String string) {
     assertEquals(Integer.parseInt(string),climbSafe.getMembers().size());
    throw new io.cucumber.java.PendingException();
  }

  @Then("the error {string} shall be raised")
  public void the_error_shall_be_raised(String string) {
    assertEquals(string, error);
  }

  @When("the administrator attempts to cancel the trip for {string}")
  public void the_administrator_attempts_to_cancel_the_trip_for(String memberEmail) {
    var member = (Member)Member.getWithEmail(memberEmail);
    callController(() -> AssignmentController.cancelTrip(member));
  }

  @Given("the member with {string} has paid for their trip")
  public void the_member_with_has_paid_for_their_trip(String string) {
    var member=(Member)Member.getWithEmail(string);
    var assignment=member.getAssignment();
    assertTrue(assignment.getPaymentStatusFullName().equals("Paid"));
    throw new io.cucumber.java.PendingException();
  }

  @Then("the member with email address {string} shall receive a refund of {string} percent")
  public void the_member_with_email_address_shall_receive_a_refund_of_percent(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} has started their trip")
  public void the_member_with_has_started_their_trip(String string) {
    var member=(Member)Member.getWithEmail(string);
    var assignment=member.getAssignment();
    assertTrue(assignment.getPaymentStatusFullName().equals("Started"));
    throw new io.cucumber.java.PendingException();
  }

  @When("the administrator attempts to finish the trip for the member with email {string}")
  public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(
      String memberEmail) {   
      var member = (Member)Member.getWithEmail(memberEmail);
      callController(() -> AssignmentController.finishTrip(member));  
  }

  @Given("the member with {string} is banned")
  public void the_member_with_is_banned(String string) {
    var member=(Member)Member.getWithEmail(string);
    var assignment=member.getAssignment();
    assertTrue(assignment.getPaymentStatusFullName().equals("Banned"));
    throw new io.cucumber.java.PendingException();
  }

  @Then("the member with email {string} shall be {string}")
  public void the_member_with_email_shall_be(String string, String string2) {
     assertEquals(string2, "banned");
     throw new io.cucumber.java.PendingException();
  }

  @When("the administrator attempts to start the trips for week {string}")
  public void the_administrator_attempts_to_start_the_trips_for_week(String weekNb) {
    callController(() -> AssignmentController.startTrips(Integer.parseInt(weekNb)));
  }

  @Given("the member with {string} has cancelled their trip")
  public void the_member_with_has_cancelled_their_trip(String string) {
    var member=(Member)Member.getWithEmail(string);
    var assignment=member.getAssignment();
    assertTrue(assignment.getPaymentStatusFullName().equals("Cancelled"));
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} has finished their trip")
  public void the_member_with_has_finished_their_trip(String string) {
    
    throw new io.cucumber.java.PendingException();
  }

  private void callController(Executable executable) { // from the btms step definitions in tutorial
    // 6
    try { // try executing the function
      executable.execute();
    } catch (InvalidInputException e) { // in case an error occurs, store the message and increment
      // error count
      error += e.getMessage();
    } catch (Throwable t) {
      fail();
    }
  }
}