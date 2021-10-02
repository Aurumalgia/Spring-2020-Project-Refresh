package controllers;


import model.Bot;
import model.Entity;
import model.Map;
import view.ViewFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class GUIControl implements ActionListener, ItemListener {
    ViewFrame view;
    ArrayList<Entity> botList;
    Map map;
    Bot currentBot;
    boolean move, turn;
    int botListSize;


    public GUIControl(ViewFrame v, ArrayList<Entity> bots, Map m){
        map=m;
        botList=bots;
        view=v;
        currentBot=(Bot)botList.get(0);
        botListSize = botList.size();
        view.getGui().getSubmitButton().addActionListener(this);
        view.getGui().getBotPicker().addItemListener(this);
        for(JRadioButton jb: view.getGui().getControlChoice()){
            jb.addItemListener(this);
        }
        setUpCombo();
    }

    private void setUpCombo(){
        for(Entity b: botList)
            if (b instanceof Bot)
                view.getGui().getBotPicker().addItem(""+b.getId());
    }

    private void refreshCombo(){
        view.getGui().getBotPicker().removeAllItems();
        setUpCombo();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (move) {
            currentBot.move(map);
        } else if (turn) {
            currentBot.turn();
        }
        map.updateMap();
        view.getPanel().repaint();
        refreshCombo();
    }

    private void pickBot(){
        int currentID=view.getGui().getBotPicker().getSelectedIndex();
        if (currentID > 0) {
            currentBot = (Bot) botList.get(currentID);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==view.getGui().getBotPicker()) {
            pickBot();

        }
       else {
           JRadioButton jb=(JRadioButton)(e.getSource());
            if (jb.getName().equals("turn")) {
               turn = true;
               move= false;
           }
           if (jb.getName().equals("move")) {
               move = true;
               turn = false;
           }
       }
    }//itemstatechange

    public JRadioButton getSelectedButton(){
        JRadioButton returnButton=view.getGui().getControlChoice().get(0);
        for(JRadioButton j: view.getGui().getControlChoice()){
            if(j.isSelected()){
               returnButton=j;
            }
        }
        return returnButton;
    }
}
