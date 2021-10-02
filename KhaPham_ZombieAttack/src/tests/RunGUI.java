package tests;
import control.ZombieControl;
import model.ApocalypseSimulator;
import model.Utilities;
import view.ZombieFrame;

public class RunGUI {
    public static void main(String[] args) {
        ZombieFrame view=new ZombieFrame();
        ApocalypseSimulator model = new ApocalypseSimulator(0,0,0);
        ZombieControl control = new ZombieControl(view, model);
    }

}
