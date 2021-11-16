package ca.mcgill.ecse.climbsafe.view;
import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;



public class ClimbSafePage extends JFrame {

	private static final long serialVersionUID = -4426310869335015542L;
	
	// UI elements
	  // element for error message
	  private JLabel errorMessage = new JLabel();
	  //tabs
	  JTabbedPane tabbedPane = new JTabbedPane();
	  //register member
	  private JLabel registerInstructionMessageLabel = new JLabel("Please fill the form below");
	  private JLabel registerMemberNameLabel = new JLabel("Name:");
	  private JTextField registerMemberNameField = new JTextField();
	  private JLabel registerMemberEmailLabel = new JLabel("Email:");
	  private JTextField registerMemberEmail = new JTextField();
	  private JLabel registerEmergencyContactLabel = new JLabel("Emergency number:");
	  private JTextField registerEmergencyContactField = new  JTextField();
	  private JLabel registerPasswordLabel = new JLabel("Password:");
	  private JTextField registerPasswordField = new JTextField();
	  private JLabel registerConfirmPasswordLabel = new JLabel("Confirm password:");
	  private JTextField registerConfirmPassword = new JTextField();
	  private JButton registerButton = new JButton("REGISTER");
	  //sign in
	  private JLabel logInMessge = new JLabel("Sign In");
	  private JLabel logInEmailLabel = new JLabel("Email");
	  private JTextField logInEmail = new JTextField();
	  private JLabel logInPasswordLabel = new JLabel("Password");
	  private JTextField logInPassword = new JTextField();
	  private JButton loginButton = new JButton("LOGIN");
	  //update account
	  private JLabel updateAccountMessageLabel = new JLabel("Update account information");
	  private JLabel updateNameLabel = new JLabel("Name");
	  private JTextField updateNameTextField = new JTextField();
	  private JLabel updatePasswordLabel = new JLabel("Password");
	  private JTextField updatePasswordField = new JTextField();
	  private JLabel updateEmergencyContactLabel = new JLabel("Emergency contact");
	  private JTextField updateEmergencyContactField = new JTextField();
	  private JButton updateProfileButton = new JButton("Update profile");
	  private JButton cancelUpdateButton = new JButton("Cancel");
	  private JButton deleteAccountButton = new JButton("Delete account ?"); // not mandatory, will implement it if enough time;
	  //--Insert here elements for delete account confirmation message--//
	    
	
	  public ClimbSafePage(){
		  initComponents();
	  }
	  
	  private void initComponents() {
		  
		  errorMessage.setForeground(ClimbSafeApplication.DARK_MODE ? Color.PINK : Color.RED);
		  errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));

		  
		// global settings
		    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		    setTitle("Bus Transportation Management System");
		    GroupLayout layout = new GroupLayout(getContentPane());
		    getContentPane().setLayout(layout);
		    layout.setAutoCreateGaps(true);
		    layout.setAutoCreateContainerGaps(true);
	  }
}
