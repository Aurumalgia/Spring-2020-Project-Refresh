package view;

import javax.swing.*;
import java.awt.*;

public class ZombieFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel inputPanel, outputPanel, titlePanel, variablePanel, inputLabelPanel, inputFieldPanel, firstTabPanel, secondTabPanel;
    private JTextField inputField, rationField, ammoField, survivorsField, zombieMinField, zombieMaxField, gunHitField, gunCritField, bluntHitField, bluntCritField, daysField;
    private JButton submitButton;
    private JLabel titleLabel, rationLabel, ammoLabel, survivorsLabel, zombieMinLabel, zombieMaxLabel, gunHitLabel, gunCritLabel, bluntHitLabel, bluntCritLabel, daysLabel;
    private JTextArea outputArea;
    private JScrollPane scroll;


    public ZombieFrame(){
        tabbedPane = new JTabbedPane();
        firstTabPanel = new JPanel(new BorderLayout());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUpInputs();
        setUpOutput();
        setUpTitle();
        setUpVariables();
        setUpSecretVariables();
        tabbedPane.addTab("Main Game", firstTabPanel);
        tabbedPane.addTab("Back-End Numbers", secondTabPanel);
        add(tabbedPane);
        pack();
        setVisible(true);
    }

    public void setUpInputs(){
        inputPanel=new JPanel(new FlowLayout());
        setSize(new Dimension(100, 400));
        submitButton=new JButton("New Game");
        inputPanel.add(submitButton);
        firstTabPanel.add(inputPanel, BorderLayout.EAST);
    }

    public void setUpVariables(){
        variablePanel = new JPanel(new BorderLayout());
        inputFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 5));
        inputLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 5));
        survivorsField = new JTextField("20", 11);
        rationField = new JTextField("50", 10);
        ammoField = new JTextField("150", 10);
        survivorsLabel = new JLabel("Starting Survivors");
        rationLabel = new JLabel("Starting Rations");
        ammoLabel = new JLabel("Starting Ammo");

        inputLabelPanel.add(survivorsLabel);
        inputLabelPanel.add(rationLabel);
        inputLabelPanel.add(ammoLabel);
        inputFieldPanel.add(survivorsField);
        inputFieldPanel.add(rationField);
        inputFieldPanel.add(ammoField);

        variablePanel.add(inputLabelPanel, BorderLayout.NORTH);
        variablePanel.add(inputFieldPanel, BorderLayout.SOUTH);
        firstTabPanel.add(variablePanel, BorderLayout.SOUTH);


    }

    public void setUpOutput(){
        outputPanel=new JPanel(new FlowLayout());
        outputArea=new JTextArea(25,50);
        scroll=new JScrollPane(outputArea);
        outputPanel.add(scroll);
        firstTabPanel.add(outputPanel, BorderLayout.CENTER);
    }
    public void setUpTitle(){
        titlePanel=new JPanel(new FlowLayout());
        titleLabel=new JLabel("ZOMBIE ATTACK");
        titlePanel.add(titleLabel);
        firstTabPanel.add(titlePanel,BorderLayout.NORTH);
    }

    public void setUpSecretVariables(){
        secondTabPanel = new JPanel(new GridLayout(0, 2));
        zombieMaxField = new JTextField("50", 4);
        zombieMinField = new JTextField("10", 4);
        gunCritField = new JTextField("33", 4);
        gunHitField = new JTextField("20", 4);
        bluntCritField = new JTextField("10", 4);
        bluntHitField = new JTextField("33", 4);
        daysField = new JTextField("10", 4);
        zombieMaxLabel = new JLabel("Max amount of zombies spawned per night: ");
        zombieMinLabel = new JLabel("Min amount of zombies spawned per night: ");
        gunCritLabel = new JLabel("% chance for Gun to crit: ");
        gunHitLabel = new JLabel("% chance for Gun to Hit: ");
        bluntHitLabel = new JLabel("% chance for melee attack to hit: ");
        bluntCritLabel = new JLabel("% chance for melee attack to crit: ");
        daysLabel  = new JLabel("Days to survive: ");

        secondTabPanel.add(zombieMaxLabel);
        secondTabPanel.add(zombieMaxField);
        secondTabPanel.add(zombieMinLabel);
        secondTabPanel.add(zombieMinField);

        secondTabPanel.add(gunHitLabel);
        secondTabPanel.add(gunHitField);

        secondTabPanel.add(gunCritLabel);
        secondTabPanel.add(gunCritField);

        secondTabPanel.add(bluntHitLabel);
        secondTabPanel.add(bluntHitField);

        secondTabPanel.add(bluntCritLabel);
        secondTabPanel.add(bluntCritField);

        secondTabPanel.add(daysLabel);
        secondTabPanel.add(daysField);



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

    public JTextArea getOutputArea() {
        return outputArea;
    }

    public void setOutputArea(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    public JTextField getRationField() {
        return rationField;
    }

    public JTextField getAmmoField() {
        return ammoField;
    }

    public JTextField getSurvivorsField() {
        return survivorsField;
    }

    public JTextField getZombieMinField() {
        return zombieMinField;
    }

    public JTextField getZombieMaxField() {
        return zombieMaxField;
    }

    public JTextField getGunHitField() {
        return gunHitField;
    }

    public JTextField getGunCritField() {
        return gunCritField;
    }

    public JTextField getBluntHitField() {
        return bluntHitField;
    }

    public JTextField getBluntCritField() {
        return bluntCritField;
    }

    public JTextField getDaysField() {
        return daysField;
    }
}


