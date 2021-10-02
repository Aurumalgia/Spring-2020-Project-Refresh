package test;

import controllers.GUIControl;
import model.*;
import view.GUIPanel;
import view.MapPanel;
import view.ViewFrame;

import java.util.ArrayList;

public class TestGUI {
    public static void main(String[] args) {
        ArrayList<Entity> bots=new ArrayList<>();
        bots.add(new FlexibleBot(new Location(0,0), 25, Movable.Directions.DOWN, Bot.FAST_SPEED));
        bots.add(new FlexibleBot(new Location(5,5), 32, Movable.Directions.DOWN, Bot.SLOW_SPEED));
        bots.add(new FlexibleBot(new Location(4,2), 45, Movable.Directions.DOWN, Bot.SLOW_SPEED));
        bots.add(new KillerBot(new Location(5, 4), 59, Movable.Directions.DOWN, Bot.MEDIUM_SPEED));
        bots.add(new WallBot(new Location(1, 1), 24, Movable.Directions.DOWN, Bot.FAST_SPEED));
        bots.add(new HopBot(new Location(2, 2), 79, Movable.Directions.DOWN, Bot.MEDIUM_SPEED));

        Map map=new Map(bots);
        MapPanel p = new MapPanel(bots, map);
        GUIPanel gui=new GUIPanel();
        ViewFrame view= new ViewFrame(p, gui);
        GUIControl control=new GUIControl(view, bots, map);
    }



}
