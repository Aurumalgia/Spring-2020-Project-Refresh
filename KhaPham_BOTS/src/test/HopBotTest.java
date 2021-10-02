package test;

import model.*;

import java.util.ArrayList;

public class HopBotTest {
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

        testHopBot(map, bots);
    }

    public static void testHopBot(Map m, ArrayList<Entity> e){
        Entity hopBot = null;
        for(Entity en: e){
            if (en instanceof HopBot){
                hopBot = en;
            }
        }

        if (hopBot != null){
            basicMoveTest(m, (HopBot) hopBot);
            jumpOverABot(m, (HopBot) hopBot);
            jumpOverMultipleBots(m, (HopBot) hopBot);
        }
    }

    public static void basicMoveTest(Map m, HopBot h){
        putBotsInLine(m.getBots(), m);
        h.teleport(m, new Location(2, 2));
        h.setDirection(Movable.Directions.UP);
        m.updateMap();
        System.out.println("Testing Move with no bots in way");
        m.printMap();
        h.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        System.out.println("----------------");
    }

    public static void jumpOverABot(Map m, HopBot h){
        putBotsInLine(m.getBots(), m);
        h.teleport(m, new Location(2, 2));
        h.setDirection(Movable.Directions.UP);
        ((FlexibleBot) m.getBots().get(0)).teleport(m, new Location(1, 2));
        m.updateMap();
        System.out.println("Testing hop over one bot");
        m.printMap();
        h.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        System.out.println("----------------");
    }

    public static void jumpOverMultipleBots(Map m, HopBot h){
        putBotsInLine(m.getBots(), m);
        h.teleport(m, new Location(4, 2));
        h.setDirection(Movable.Directions.UP);
        ((FlexibleBot) m.getBots().get(0)).teleport(m, new Location(1, 2));
        ((FlexibleBot) m.getBots().get(1)).teleport(m, new Location(2, 2));
        ((FlexibleBot) m.getBots().get(2)).teleport(m, new Location(3, 2));
        m.updateMap();
        System.out.println("Testing hop over multiple bots");
        m.printMap();
        h.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        System.out.println("----------------");
    }

    public static void lineOfBotsAgainstWall(Map m, HopBot h){
        putBotsInLine(m.getBots(), m);
        h.teleport(m, new Location(3, 2));
        h.setDirection(Movable.Directions.UP);
        ((FlexibleBot) m.getBots().get(0)).teleport(m, new Location(0, 2));
        ((FlexibleBot) m.getBots().get(1)).teleport(m, new Location(1, 2));
        ((FlexibleBot) m.getBots().get(2)).teleport(m, new Location(2, 2));
        m.updateMap();
        System.out.println("Testing what happens when the hop would go into the wall");
        m.printMap();
        h.move(m);
        System.out.println();
        m.updateMap();
        m.printMap();
        System.out.println("----------------");
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
