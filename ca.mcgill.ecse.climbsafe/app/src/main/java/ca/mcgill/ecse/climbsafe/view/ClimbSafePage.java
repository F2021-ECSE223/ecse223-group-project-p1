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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOGuide;
import ca.mcgill.ecse.climbsafe.controller.TOMember;
import ca.mcgill.ecse.climbsafe.controller.TOBookableItem;
import javax.swing.JPasswordField;

public class ClimbSafePage extends JFrame {
  private static final long serialVersionUID = -4426310869335015542L;

  // UI elements
  // element for error message
  private JLabel errorMessage = new JLabel();
  // tabs
  JTabbedPane tabbedPane = new JTabbedPane();

  private String error = "";

  // delete equipment
  private JComboBox<TOBookableItem> equipmentDeleteList = new JComboBox<>();
  private JLabel equipmentDeleteSelectLabel = new JLabel("Select equipment:");
  private JButton deleteEquipmentButton = new JButton("Delete equipment");
  
  // delete guide
  private JComboBox<TOGuide> guideDeleteList = new JComboBox<>();
  private JLabel guideSelectLabel = new JLabel("Select guide:");
  private JButton deleteGuideButton = new JButton("Delete guide");

  // delete member
  private JComboBox<TOMember> memberDeleteList = new JComboBox<>();
  private JLabel memberDeleteSelectLabel = new JLabel("Select member:");
  private JButton deleteMemberButton = new JButton("Delete member");


  // update member
  private JLabel memberSelectLabel = new JLabel("Select member:");
  private JComboBox<TOMember> memberUpdateList = new JComboBox<>();
  private JLabel updateMemberNameLabel = new JLabel("New name:");
  private JTextField updateMemberNameField = new JTextField();
  private JLabel updateMemberEmailLabel = new JLabel("New email:");
  private JTextField updateMemberEmailField = new JTextField();
  private JLabel updateMemberContactLabel = new JLabel("New emergency number:");
  private JTextField updateMemberContactField = new JTextField();
  private JLabel updateMemberPasswordLabel = new JLabel("New password:");
  private JPasswordField updateMemberPasswordField = new JPasswordField();
  private JLabel updateRequiredLabel = new JLabel("Check if applies:");
  private JLabel updateGuideRequiredLabel = new JLabel("Guide Required");
  private JRadioButton updateGuideRequiredBtn = new JRadioButton();
  private JLabel updateHotelRequiredLabel = new JLabel("Hotel Required");
  private JRadioButton updateHotelRequiredBtn = new JRadioButton();
  private JLabel updateMemberWeekLabel = new JLabel("New number of weeks:");
  private JTextField updateMemberWeekField = new JTextField();
  private JLabel updateItemSelectLabel = new JLabel("Select new items:");
  private JComboBox<TOBookableItem> updateItemSelect = new JComboBox<>();
  private JLabel updateMemberQuantityLabel = new JLabel("Item quantity:");
  private JTextField updateMemberQuantityField = new JTextField();
  private JButton updateMemberItemButton = new JButton("Add item");
  private JButton updateMemberButton = new JButton("Update Member");


  

  public ClimbSafePage() {
    
    initComponents();
    refreshData();
  }

  private void initComponents() {
    // GLOBAL SETTINGS
    errorMessage.setForeground(ClimbSafeApplication.DARK_MODE ? Color.PINK : Color.RED);
    errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("ClimbSafe Management System");


    // ADD ACTION LISTENERS
    // delete guide listeners
    deleteGuideButton.addActionListener(this::deleteGuideButtonActionPerformed);

    // delete member listeners
    deleteMemberButton.addActionListener(this::deleteMemberButtonActionPerformed);

    // LAYOUT
    JSeparator horizontalLineTop = new JSeparator();
    

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    layout.setHorizontalGroup(
        layout.createSequentialGroup().addGroup(layout.createParallelGroup()
            .addComponent(errorMessage)
            .addComponent(horizontalLineTop)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    // Column 1
                    .addComponent(guideSelectLabel)
                    .addComponent(memberSelectLabel)
                    .addComponent(updateMemberEmailLabel)
                    .addComponent(updateMemberContactLabel)
                    .addComponent(updateItemSelectLabel)
                    
                )
                .addGroup(layout.createParallelGroup()
                 // Column 2
                    .addComponent(guideDeleteList, 200, 200, 400)
                    .addComponent(deleteGuideButton)
                    .addComponent(memberUpdateList)
                    .addComponent(updateMemberEmailField)
                    .addComponent(updateMemberContactField)
                    .addComponent(updateItemSelect)
                    
                )
                .addGroup(layout.createParallelGroup()
                 // Column 3
                    .addComponent(memberDeleteSelectLabel)
                    .addComponent(updateMemberPasswordLabel)
                    .addComponent(updateMemberWeekLabel)
                    .addComponent(updateMemberQuantityLabel)
                    
                )
                .addGroup(layout.createParallelGroup()
                 // Column 4
                    .addComponent(memberDeleteList, 200, 200, 400)
                    .addComponent(deleteMemberButton)
                    .addComponent(updateMemberPasswordField)
                    .addComponent(updateMemberWeekField)
                    .addComponent(updateMemberQuantityField)
                )
                .addGroup(layout.createParallelGroup()
                    // Column 5
                    .addComponent(equipmentDeleteSelectLabel)
                    .addComponent(updateMemberNameLabel)
                    .addComponent(updateRequiredLabel)
                    .addComponent(updateMemberItemButton)
                )
                .addGroup(layout.createParallelGroup()
                    // Column 6
                    .addComponent(equipmentDeleteList, 200, 200, 400)
                    .addComponent(deleteEquipmentButton)
                    .addComponent(updateMemberNameField, 200, 200, 400)
                    // fourth element = 2 sub-columns
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updateGuideRequiredBtn).addComponent(updateGuideRequiredLabel))
                    // fifth element = 2 sub-columns
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updateHotelRequiredBtn).addComponent(updateHotelRequiredLabel))
                    .addComponent(updateMemberButton, 200, 200, 400)
                )
                
            )
       )
    );
    
    layout.linkSize(SwingConstants.HORIZONTAL, guideDeleteList, deleteGuideButton);
    layout.linkSize(SwingConstants.HORIZONTAL, memberDeleteList, deleteMemberButton);
    layout.linkSize(SwingConstants.HORIZONTAL, equipmentDeleteList, deleteEquipmentButton);
    layout.setVerticalGroup(
        layout.createParallelGroup().addGroup(layout.createSequentialGroup()
            .addComponent(errorMessage)
            .addGroup(layout.createParallelGroup()
                // Row 1
                .addComponent(guideSelectLabel)
                .addComponent(guideDeleteList)
                .addComponent(memberDeleteSelectLabel)
                .addComponent(memberDeleteList)
                .addComponent(equipmentDeleteSelectLabel)
                .addComponent(equipmentDeleteList)
            )
            .addGroup(layout.createParallelGroup()
             // Row 2
                .addComponent(deleteGuideButton)
                .addComponent(deleteMemberButton)
                .addComponent(deleteEquipmentButton)
            )
            .addGroup(layout.createParallelGroup()
             // Row 3 is a horizontal line
                .addComponent(horizontalLineTop))
            .addGroup(layout.createParallelGroup()
             // Row 4
                .addComponent(memberSelectLabel)
                .addComponent(memberUpdateList)
            )
            .addGroup(layout.createParallelGroup()
             // Row 5
                .addComponent(updateMemberEmailLabel)
                .addComponent(updateMemberEmailField)
                .addComponent(updateMemberPasswordLabel)
                .addComponent(updateMemberPasswordField)
                .addComponent(updateMemberNameLabel)
                .addComponent(updateMemberNameField)
                
            )
            .addGroup(layout.createParallelGroup()
             // Row 6
                .addComponent(updateMemberContactLabel)
                .addComponent(updateMemberContactField)
                .addComponent(updateMemberWeekLabel)
                .addComponent(updateMemberWeekField)
                .addComponent(updateRequiredLabel)
                .addComponent(updateGuideRequiredBtn)
                .addComponent(updateGuideRequiredLabel)
            )
            .addGroup(layout.createParallelGroup()
             // Row 7
                .addComponent(updateHotelRequiredBtn)
                .addComponent(updateHotelRequiredLabel)
            )
            .addGroup(layout.createParallelGroup()
             // Row 8
                .addComponent(updateItemSelectLabel)
                .addComponent(updateItemSelect)
                .addComponent(updateMemberQuantityLabel)
                .addComponent(updateMemberQuantityField)
                .addComponent(updateMemberItemButton)
                .addComponent(updateMemberButton)
            )
            .addGroup(layout.createParallelGroup()
             // Row 9 is a horizontal line
                .addComponent(horizontalLineTop))
       )
   );
            
            
    pack();
    setLocationRelativeTo(null); // set window location to be in the center of the screen
    setResizable(false);
    setVisible(true);
  }

  private void refreshData() {
    // error
    errorMessage.setText(error);
    if (error == null || error.isEmpty()) {
      
      // update member fields
      updateMemberNameField.setText("");
      updateMemberEmailField.setText("");
      updateMemberContactField.setText("");
      updateMemberPasswordField.setText("");
      updateMemberWeekField.setText("");
      updateMemberQuantityField.setText("");

    }
    pack();
  }

  private void deleteGuideButtonActionPerformed(ActionEvent evt) {
    // clear error message
    error = "";

    callController(() -> ClimbSafeFeatureSet1Controller.deleteGuide(deleteGuideButton.getText()));

    // update visuals
    refreshData();
  }


  private void deleteMemberButtonActionPerformed(ActionEvent evt) {
    // clear error message
    error = "";

    callController(() -> ClimbSafeFeatureSet1Controller.deleteMember(deleteMemberButton.getText()));

    // update visuals
    refreshData();
  }

  /**
   * Calls the controller and sets the error message.
   *
   * @param executable a controller call preceded by "() -> "
   */
  private String callController(Executable executable) {
    try {
      executable.execute();
    } catch (InvalidInputException e) {
      error = e.getMessage();
      return error;
    } catch (Throwable t) {
      t.printStackTrace();
    }
    return "";
  }

  @FunctionalInterface
  interface Executable {
    public void execute() throws Throwable;
  }
}
