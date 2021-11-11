package ca.mcgill.ecse.climbsafe.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignmentFeatureStepDefinitions {

  private ClimbSafe climbSafe;
  private String error;

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

  @Given("the following pieces of equipment exist in the system:")
  public void the_following_pieces_of_equipment_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    var rows = dataTable.asMaps();

    for (var row : rows) {
      climbSafe.addEquipment(row.get("name"), Integer.parseInt(row.get("weight")),
          Integer.parseInt(row.get("pricePerWeek")));
    }
  }

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

  @Given("the following members exist in the system:")
  public void the_following_members_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    var rows = dataTable.asMaps();
    for(var row: rows) {
    	var bookedItems = Arrays.asList(row.get("bookedItems").split(","));
    	var bookedItemQuantities = new ArrayList<Integer>();
    	
    	for(var itemQuantity: row.get("bookedItemQuantities").split(",")) {
    		bookedItemQuantities.add(Integer.parseInt(itemQuantity));   		
    	} 
    	climbSafe.addMember(row.get("email"), row.get("password"), row.get("name"), row.get("emergencyContact"), Integer.parseInt(row.get("nrWeeks")), 
    			Boolean.parseBoolean(row.get("guideRequired")), Boolean.parseBoolean(row.get("hotelRequired")));
    	var newMember = (Member)User.getWithEmail(row.get("email"));
    	for(int i = 0; i< bookedItems.size(); i++ ){
    		var bookableItem = BookableItem.getWithName(bookedItems.get(i));
    		newMember.addBookedItem(bookedItemQuantities.get(i), climbSafe, BookableItem.getWithName(bookedItems.get(i)));
    	}  	
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
    assertEquals(string2, ((Member) User.getWithEmail(string)).getAssignment().getAssignmentStatusFullName());
  }
  /**
   * 
   * @author Asma Gandour
   */
  @Then("the number of assignments in the system shall be {string}")
  public void the_number_of_assignments_in_the_system_shall_be(String string) {
    assertEquals(string, Integer.toString(climbSafe.numberOfAssignments()));
  }
  
   /**
   * 
   * @author Mohammad Shaheer Bilal 
   */
  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String errorMessage) {
    assertEquals(errorMessage, error);	 
  }
	
  @Given("the following assignments exist in the system:")
  public void the_following_assignments_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
      var rows = dataTable.asMaps();
      for(var row: rows) {        
          Member member = (Member) User.getWithEmail(row.get("memberEmail")); //should not return null
          
          Assignment assignment = new Assignment(Integer.parseInt(row.get("startWeek")), 
              Integer.parseInt(row.get("endWeek")), member, climbSafe);
          
          if(row.get("guideEmail") != null){
            Guide guide = (Guide) User.getWithEmail(row.get("guideEmail"));
            guide.addAssignment(assignment);
          }
          climbSafe.addAssignment(assignment);  
          member.getAssignment().toggleStatus();        
      }
  }


 /*
 * @author : Haroun Guessous
 */
  @When("the administrator attempts to confirm payment for {string} using authorization code {string}")
  public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(
      String string, String string2) {
	  var member = (Member)Member.getWithEmail(string);
	  callController(() -> AssignmentController.payment(member, string2));
  }
  
  /*
 * @author : Mohammad Shaheer Bilal 
 */
  @Then("the assignment for {string} shall record the authorization code {string}")
  public void the_assignment_for_shall_record_the_authorization_code(String string,
      String string2) {
	  int authorizationCode = Integer.parseInt(string2);
	  var member =(Member)User.getWithEmail(string);
	  var assignment = member.getAssignment();
	  assertEquals(authorizationCode,assignment.getPaymentAuthorizationCode());
	
  }

  @Then("the member account with the email {string} does not exist")
  public void the_member_account_with_the_email_does_not_exist(String email) {
     int temp=0;
      for (int i=0;i<climbSafe.getMembers().size();i++)
      {
        if (climbSafe.getMembers().get(i).getEmail()==email)
        {
          temp=i;
        }
      }
    assertFalse(email==climbSafe.getMembers().get(temp).getEmail());
  }

  @Then("there are {string} members in the system")
  public void there_are_members_in_the_system(String numOfMembers) {
     assertEquals(Integer.parseInt(numOfMembers),climbSafe.getMembers().size());
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
	
/**
   * 
   * @author Atreyi Srivastava
   */

  @Given("the member with {string} has paid for their trip")
  public void the_member_with_has_paid_for_their_trip(String memberEmail) {
    var member=(Member)User.getWithEmail(memberEmail);
    var assignment=member.getAssignment();
    assignment.pay();
  }

  /**
   * 
   * @author Mohammad Shaheer Bilal 
   */
  @Then("the member with email address {string} shall receive a refund of {string} percent")
  public void the_member_with_email_address_shall_receive_a_refund_of_percent(String memberEmail,
      String refundPercentage) {
	  int refund = Integer.parseInt(refundPercentage);
	  var member =(Member)User.getWithEmail(memberEmail);
	  assertEquals(refundPercentage, member.getRefundPercentage());
	  
  }
	
/**
   * 
   * @author Atreyi Srivastava
   */

  @Given("the member with {string} has started their trip")
  public void the_member_with_has_started_their_trip(String memberEmail) {
    var member=(Member)User.getWithEmail(memberEmail);
    var assignment=member.getAssignment();
    assignment.pay();
    assignment.startTrip();
  }

  @When("the administrator attempts to finish the trip for the member with email {string}")
  public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(
      String memberEmail) {	  
	  var member = (Member)Member.getWithEmail(memberEmail);
	  callController(() -> AssignmentController.finishTrip(member));  
  }
	
/**
   * 
   * @author Atreyi Srivastava
   */

  @Given("the member with {string} is banned")
  public void the_member_with_is_banned(String memberEmail) {
    var member=(Member)User.getWithEmail(memberEmail);
    member.ban();
  }
 /*
 * @author : Haroun Guessous
 */
  @Then("the member with email {string} shall be {string}")
  public void the_member_with_email_shall_be(String email, String banState) {
	  var member=Member.getWithEmail(email);
	  assertEquals(((Member) member).getMemberStatusFullName(),banState);
  }

  @When("the administrator attempts to start the trips for week {string}")
  public void the_administrator_attempts_to_start_the_trips_for_week(String weekNb) {
    callController(() -> AssignmentController.startTrips(Integer.parseInt(weekNb)));
  }

/**
   * 
   * @author Atreyi Srivastava
   */
	
  @Given("the member with {string} has cancelled their trip")
  public void the_member_with_has_cancelled_their_trip(String string) {
    var member=(Member)User.getWithEmail(string);
    var assignment=member.getAssignment();
    assignment.cancelTrip();
   
  }
  
/**
   * 
   * @author Mohammad Shaheer Bilal 
   */
  @Given("the member with {string} has finished their trip")
  public void the_member_with_has_finished_their_trip(String string) {
	  var member =(Member)User.getWithEmail(string);
	  var assignment = member.getAssignment();
	  assignment.finishTrip(); 
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
