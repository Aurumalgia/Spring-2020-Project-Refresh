package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    public static int NUM_ROWS=10, NUM_COLS=10;
    private Entity[][] botMap;
    private ArrayList<Entity> bots;

    public Map(ArrayList<Entity> bots){
        this.bots=bots;
        botMap=new Entity[NUM_ROWS][NUM_COLS];
        putBotsOnMap();
    }

    public void putBotOnMap(Bot b){
        botMap[b.getLoc().getRow()][b.getLoc().getCol()]=b;
    }

    public Bot removeBotFromMap(Bot b){
        Bot temp=b;
        botMap[b.getLoc().getRow()][b.getLoc().getCol()]=null;
        return temp;
    }

    public Bot removeBotFromList(Bot b){
        int index=bots.indexOf(b);
        System.out.println(index);
        return (Bot)(bots.remove(index));
    }

    public void putBotsOnMap(){
        for(int i=0; i<bots.size(); i++){
            Entity b=bots.get(i);
            botMap[b.getLoc().getRow()]
                    [b.getLoc().getCol()]=b;
        }
    }

    public void clearMap(){
        for(Entity[] bots: botMap){
            Arrays.fill(bots, null);
        }
    }

    public void updateMap(){
        clearMap();
        putBotsOnMap();
    }

    public void printMap(){
        for(Entity[] row: botMap){
            for(Entity b: row){
                if(b!=null){
                    System.out.print(b.getId()+", ");
                }
                else{
                    System.out.print("00, ");
                }
            }
            System.out.println();
        }
    }

    public Entity[][] getBotMap() {
        return botMap;
    }

    public void setBotMap(Entity[][] botMap) {
        this.botMap = botMap;
    }

    public ArrayList<Entity> getBots() {
        return bots;
    }

    public void setBots(ArrayList<Entity> bots) {
        this.bots = bots;
    }
}
