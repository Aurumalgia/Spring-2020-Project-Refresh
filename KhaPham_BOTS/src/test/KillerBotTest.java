package test;

import model.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;

public class KillerBotTest {
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

        killBotTest(map, bots, mBots);
    }

    public static void killBotTest(Map m, ArrayList<Entity> bots, ArrayList<Movable> backupBots){
        Entity killerBot = null;
        for(Entity e: bots){
            if (e instanceof KillerBot){
                killerBot = e;
            }
        }

        if (killerBot != null){
            basicMoveTest((KillerBot) killerBot, m);
            botsInFrontTest(m, (KillerBot) killerBot);
            resetBots(bots, backupBots);
            flexMoveThenKill(m, (KillerBot) killerBot);
            resetBots(bots, backupBots);
        }
    }


    public static void basicMoveTest(KillerBot k, Map m){
        putBotsInLine(m.getBots(), m);
        k.teleport(m, new Location(2, 2));
        k.setDirection(Movable.Directions.UP);
        m.updateMap();
        System.out.println("Testing Move with no bots in way");
        m.printMap();
        k.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        System.out.println("----------------");

    }

    public static void botsInFrontTest(Map m, KillerBot k){
        putBotsInLine(m.getBots(), m);
        k.teleport(m, new Location(3, 0));
        teleport((Bot) m.getBots().get(0), m, new Location(1, 0));
        teleport((Bot) m.getBots().get(1), m, new Location(2, 0));
        teleport((Bot) m.getBots().get(5), m, new Location(0, 0));
        m.updateMap();
        System.out.println("Testing when there are 3, then 2, then one bot with a higher number bots in front of the killer bot");
        m.printMap();
        k.setDirection(Movable.Directions.UP);
        k.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        k.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        k.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        System.out.println("-------------");

    }

    public static void flexMoveThenKill(Map m, KillerBot k){
        putBotsInLine(m.getBots(), m);
        k.teleport(m, new Location(1, 0));
        teleport((Bot)m.getBots().get(0), m, new Location(1, 0));
        m.updateMap();
        System.out.println("Test for what happens if a bot encounters a wall, turns, then encounters a different bot");
        m.printMap();
        k.move(m);
        m.updateMap();
        System.out.println();
        m.printMap();
        System.out.println("-----------------------");

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



    public static boolean teleport(Bot b, Map m, Location loc){
        return b.teleport(m, loc);
    }

    public static void resetBots(ArrayList<Entity> e, ArrayList<Movable> m){
        e.clear();
        for (Movable em: m){
            e.add((Entity) em);
        }
    }


}
