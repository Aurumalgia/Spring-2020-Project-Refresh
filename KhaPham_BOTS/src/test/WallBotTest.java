package test;

import model.*;

import java.util.ArrayList;

public class WallBotTest {
    public static void main(String[] args) {
        ArrayList<Entity> bots=new ArrayList<>();
        ArrayList<Movable> mBots = new ArrayList<>();

        Movable f1 = new FlexibleBot(new Location(1, 3), 10, Movable.Directions.DOWN, Movable.MEDIUM_SPEED);
        Movable f2 =new FlexibleBot(new Location(1, 3), 11, Movable.Directions.DOWN, Movable.MEDIUM_SPEED);
        KillerBot k1 =  new KillerBot(new Location(0, 5), 20, Movable.Directions.DOWN, Movable.MEDIUM_SPEED);
        Movable w1 = new WallBot(new Location(0, 2), 30, Movable.Directions.DOWN, Movable.MEDIUM_SPEED);
        Entity h1 = new HopBot(new Location(0, 0), 40, Movable.Directions.DOWN, Movable.MEDIUM_SPEED);
        Movable f3 = new FlexibleBot(new Location(1, 5), 60, Movable.Directions.DOWN, Movable.MEDIUM_SPEED);
        bots.add((Entity)f1);
        bots.add((Entity)f2);
        bots.add(k1);
        bots.add((Entity) w1);
        bots.add(h1);
        bots.add((Entity)f3);
        for(Entity e: bots){
            mBots.add((Movable) e);
        }
        Map map = new Map(bots);

        wallTest(map, bots);

    }

    public static void wallTest(Map m, ArrayList<Entity> e){
        Entity wallBot = null;

        for (Entity en: e){
            if (en instanceof  WallBot)
                wallBot = en;
        }

        if (wallBot != null){
            leaveWallBehind(m, (WallBot) wallBot);
            clearWalls(e);
            flexMoveWall(m, (WallBot) wallBot);
            clearWalls(e);
            wallMoveSpeed(m, (WallBot) wallBot);
            clearWalls(e);
            sturdyWallsTest(m, (WallBot) wallBot);
        }
    }


    public static void leaveWallBehind(Map m, WallBot w){
        putBotsInLine(m.getBots(), m);
        System.out.println("Testing if walls get left behind if moved. Walls are id 1.");
        w.teleport(m, new Location(0, 0));
        w.setDirection(Movable.Directions.RIGHT);
        m.updateMap();
        m.printMap();
        w.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        System.out.println("-------");
    }

    public static void flexMoveWall(Map m, WallBot w){
        putBotsInLine(m.getBots(), m);
        System.out.println("Testing if walls get left behind if flex moved. Walls are id 1.");
        w.teleport(m, new Location(1, 0));
        w.setDirection(Movable.Directions.UP);
        m.updateMap();
        m.printMap();
        w.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        System.out.println("-------");
    }

    public static void wallMoveSpeed(Map m, WallBot w){
        putBotsInLine(m.getBots(), m);
        System.out.println("Testing if walls get left behind with different movespeeds. Walls are id 1.");
        w.teleport(m, new Location(0, 0));
        w.setDirection(Movable.Directions.RIGHT);
        m.updateMap();
        m.printMap();
        w.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        w.speedUp(3);
        w.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        w.speedDown(4);
        w.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        w.setMoveSpeed(Movable.MEDIUM_SPEED);
        System.out.println("-------");
    }

    public static void sturdyWallsTest(Map m, WallBot w){
        putBotsInLine(m.getBots(), m);
        System.out.println("Testing if walls act as barriers. Walls are id 1.");
        w.teleport(m, new Location(2, 2));
        ((FlexibleBot) m.getBots().get(0)).teleport(m, new Location(2, 1));
        ((Bot) m.getBots().get(0)).setDirection(Movable.Directions.RIGHT);
        w.setDirection(Movable.Directions.UP);
        m.updateMap();
        m.printMap();
        w.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        ((Bot) m.getBots().get(0)).move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        System.out.println("-------");
    }
    public static ArrayList<Entity> clearWalls(ArrayList<Entity> e){
        for(int i = 0; i < e.size(); i++){
            if (!(e.get(i) instanceof Bot))
                e.remove(e.get(i));
        }
        return e;
    }
    public static ArrayList<Bot> putBotsInLine(ArrayList<Entity> bots, Map m){
        ArrayList<Bot> removed = new ArrayList<>();
        for(int i = 0; i < m.getBots().size(); i++){
            if (i < m.getBotMap()[i].length){
                if (m.getBots().get(i) instanceof Bot) {
                    Bot b = (Bot) m.getBots().get(i);
                    b.getLoc().setRow((m.getBotMap().length - 1));
                    b.getLoc().setCol(i);
                }
            }
            else {
                removed.add((Bot) m.getBots().remove(i));
            }
        }
        m.updateMap();
        return removed;
    }



}
