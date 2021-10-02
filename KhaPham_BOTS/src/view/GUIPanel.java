package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIPanel extends JPanel {
    private JTextField inputField;
    private ArrayList<JRadioButton> controlChoice;
    private String[] controlNames={"move", "turn"};
    private ButtonGroup group;
    private JPanel radioButtonPanel;
    private JButton submitButton;
    private JComboBox<String> botPicker;

    public GUIPanel(){
        setLayout(new FlowLayout());
        setBackground(Color.BLACK);
        setUpGUI();
    }

    public void setUpGUI(){
        inputField=new JTextField(15);
        submitButton=new JButton("Submit Command");
        botPicker=new JComboBox<>();
        setupRadios();
        add(botPicker);
        add(radioButtonPanel);
        add(submitButton);
    }

    public void setupRadios(){
        radioButtonPanel=new JPanel(new FlowLayout());
        controlChoice=new ArrayList<>();
        group=new ButtonGroup();
        for(int i=0; i<controlNames.length; i++){
            controlChoice.add(new JRadioButton(controlNames[i]));
            group.add(controlChoice.get(i));
            controlChoice.get(i).setName(controlNames[i]);
            radioButtonPanel.add(controlChoice.get(i));

        }

    }

    public JTextField getInputField() {
        return inputField;
    }

    public void setInputField(JTextField inputField) {
        this.inputField = inputField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }

    public JComboBox<String> getBotPicker() {
        return botPicker;
    }

    public ArrayList<JRadioButton> getControlChoice() {
        return controlChoice;
    }


}
