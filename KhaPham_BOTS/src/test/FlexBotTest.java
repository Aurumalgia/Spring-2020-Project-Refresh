package test;

import model.*;

import java.util.ArrayList;

public class FlexBotTest {
    public static void main(String[] args) {
        ArrayList<Entity> bots=new ArrayList<>();
        ArrayList<Movable> mBots = new ArrayList<>();

        Movable f1 = new FlexibleBot(new Location(1, 3), 10, Movable.Directions.DOWN, Movable.MEDIUM_SPEED);
        Movable f2 =new FlexibleBot(new Location(1, 3), 11, Movable.Directions.DOWN, Movable.MEDIUM_SPEED);
        KillerBot k1 =  new KillerBot(new Location(0, 5), 20, Movable.Directions.DOWN, Movable.MEDIUM_SPEED);
        Movable w1 = new WallBot(new Location(0, 2), 30, Movable.Directions.DOWN, Movable.MEDIUM_SPEED);
        Entity h1 = new HopBot(new Location(0, 0), 40, Movable.Directions.DOWN, Movable.MEDIUM_SPEED);
        bots.add((Entity)f1);
        bots.add((Entity)f2);
        bots.add(k1);
        bots.add((Entity) w1);
        bots.add(h1);
        for(Entity e: bots){
            mBots.add((Movable) e);
        }
        Map map = new Map(bots);

        putBotsInLine(bots, map);
        testFlexMove(map, bots);
    }


    public static void testFlexMove(Map m, ArrayList<Entity> bots){

        flexFacingWall((FlexibleBot) m.getBots().get(0), m);
        flexFacingWall((FlexibleBot) m.getBots().get(0), m);

        boolean moved = true;
        for(Entity e: bots){
            if (e instanceof  FlexibleBot && moved){
                moved = flexBotTrapped((FlexibleBot) e, m);
            }
        }

        botEncountersBot((FlexibleBot) bots.get(0), m);

        flexEdgeMove((FlexibleBot) bots.get(0), m);

        testCounterTurn((FlexibleBot) bots.get(0), m);
        testSpeedUpDown(m, (FlexibleBot)bots.get(0));
        moveAllBots(m, bots);


    }


    public static void testSpeedUpDown(Map m, FlexibleBot f){
        putBotsInLine(m.getBots(), m);
        f.teleport(m, new Location(6, 0));
        f.setDirection(Movable.Directions.UP);
        System.out.println("Testing speed up and speed down");
        m.updateMap();
        m.printMap();
        System.out.println();
        f.move(m);
        m.updateMap();
        m.printMap();
        System.out.println();
        f.speedDown(1);
        f.move(m);
        m.updateMap();
        m.printMap();
        System.out.println();
        f.speedUp(4);
        f.move(m);
        m.updateMap();
        m.printMap();
        f.setMoveSpeed(Movable.MEDIUM_SPEED);
        System.out.println("--------");


    }


    public static void moveAllBots(Map m, ArrayList<Entity> e){
        System.out.println("Moving all bots up");
        putBotsInLine(e, m);
        m.updateMap();
        m.printMap();
        for(int i = 0; i < e.size(); i++){
            if (e.get(i) instanceof Bot)
                moveBotUp((Bot) e.get(i), m);
        }
        System.out.println();
        m.updateMap();
        m.printMap();
        System.out.println("----------------");
        for(int i = 0; i < e.size(); i++){
            if (!(e.get(i) instanceof Bot))
                e.remove(e.get(i));
        }
    }

    //will test flex move against wall and flex move against a bot.
    public static boolean flexFacingWall(FlexibleBot f, Map m){
        boolean botMoved = false;
        System.out.println("Flex Facing Wall and Flex Against Bot");
        f.teleport(m, new Location(0, 0));
        m.updateMap();
        m.printMap();
        System.out.println("-----------");
        f.setDirection(Movable.Directions.UP);
        botMoved = f.move(m);
        m.updateMap();
        m.printMap();
        System.out.println();
        return botMoved;
    }

    //tests what happens when a flexbot is trapped
    public static boolean flexBotTrapped (FlexibleBot f, Map m){
        boolean botMoved = false;
        System.out.println("\nFlexbot Trapped");

        putBotsInLine(m.getBots(), m);
        m.updateMap();
        ((FlexibleBot) m.getBots().get(0)).teleport(m, new Location(0,0));
        ((FlexibleBot) m.getBots().get(1)).teleport(m, new Location(1,0));
        ((FlexibleBot) m.getBots().get(2)).teleport(m, new Location(0,1));

        m.updateMap();
        m.printMap();
        botMoved = ((FlexibleBot) m.getBots().get(0)).move(m);
        m.updateMap();
        m.printMap();

        return botMoved;

    }

    public static void testCounterTurn(FlexibleBot f, Map m){
        putBotsInLine(m.getBots(), m);
        f.teleport(m, new Location(3, 0));
        f.setDirection(Movable.Directions.UP);
        m.updateMap();
        System.out.println("Testing Counter Turn");
        m.printMap();
        f.turn();
        f.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        f.counterTurn();
        f.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        System.out.println("-----------------");
    }

    //tests if a bot will stop if encountering another bot
    public static boolean botEncountersBot(FlexibleBot b, Map m){
        putBotsInLine(m.getBots(), m);
        ((FlexibleBot) m.getBots().get(0)).teleport(m, new Location(0, 0));
        ((FlexibleBot) m.getBots().get(1)).teleport(m, new Location(0, 5));

        System.out.println("When a bot moves into another bot");
        m.updateMap();
        m.printMap();

        b.setMoveSpeed(3);
        b.setDirection(Movable.Directions.RIGHT);

        b.move(m);
        System.out.println("");
        m.updateMap();
        m.printMap();

        boolean moved = b.callBotMove(m);
        System.out.println("");
        m.updateMap();
        m.printMap();
        System.out.println("--------------------");
        return moved;
    }

    public static boolean flexEdgeMove(FlexibleBot f, Map m){
        putBotsInLine(m.getBots(), m);
        f.teleport(m, new Location(1, 6));
        f.setMoveSpeed(2);
        f.setDirection(Movable.Directions.UP);
        m.updateMap();
        System.out.println("Test to see what the flexBot does when it's movespeed is greater than the distance to the edge, \n" +
                "and if the bot will move to the edge of the map");
        m.printMap();
        f.move(m);
        m.updateMap();
        System.out.println();
        m.printMap();
        boolean moved =f.move(m);
        m.updateMap();
        System.out.println();
        m.printMap();

        System.out.println("------------------");
        return moved;

    }

    public static void moveBotUp(Bot b, Map m){
        b.setDirection(Movable.Directions.UP);
        b.move(m);
    }

    public static ArrayList<Bot> putBotsInLine(ArrayList<Entity> bots, Map m){
        ArrayList<Bot> removed = new ArrayList<>();
        for(int i = 0; i < m.getBots().size(); i++){
            if (i < m.getBotMap()[i].length){
                Bot b = (Bot) m.getBots().get(i);
                b.getLoc().setRow((m.getBotMap().length - 1));
                b.getLoc().setCol(i);
            }
            else {
                removed.add((Bot) m.getBots().remove(i));
            }
        }
        m.updateMap();
        return removed;
    }



}
