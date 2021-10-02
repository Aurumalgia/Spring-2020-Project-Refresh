package test;

import model.*;

import java.util.ArrayList;

public class FlexTest {
    public static void main(String[] args) {
        ArrayList<Entity> bots=new ArrayList<>();
        bots.add(new FlexibleBot(new Location(0,0), 25, Movable.Directions.RIGHT, Bot.FAST_SPEED));
        bots.add(new FlexibleBot(new Location(0,5), 32, Movable.Directions.LEFT, Bot.SLOW_SPEED));
        bots.add(new FlexibleBot(new Location(0,2), 45, Movable.Directions.DOWN, Bot.SLOW_SPEED));

        Map map=new Map(bots);

        map.printMap();

        System.out.println("+++++++++++++++++++++");

        Bot b=map.removeBotFromMap((Bot)bots.get(0));

        map.printMap();

        map.putBotOnMap(b);

        System.out.println("+++++++++++++++++++++");
        map.printMap();
        System.out.println("+++++++++++++++++++++");

        ((FlexibleBot) bots.get(0)).move(map);
        map.updateMap();

        System.out.println("+++++++++++++++++++++");
        map.printMap();

        ((FlexibleBot) bots.get(0)).teleport(map, new Location(4, 4));
        map.updateMap();
        System.out.println("+++++++++++++++++++++");
        map.printMap();

        }


}
