package view;

import model.Bot;
import model.Entity;
import model.Map;
import model.Wall;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapPanel extends JPanel {
    public static int CELL_SIZE=40;
    public static int PANEL_HEIGHT=CELL_SIZE* Map.NUM_ROWS, PANEL_WIDTH=CELL_SIZE*Map.NUM_COLS;

    private Dimension panelSize;
    ArrayList<Entity> botList;
    Map map;

    public MapPanel(ArrayList<Entity> bots, Map map){
        panelSize=new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
        setPreferredSize(panelSize);
        botList=bots;
        this.map=map;
    }

    public void paint(Graphics g){
        super.paint(g);

        drawBots(g);

        drawMap(g);
    }

    private void drawMap(Graphics g){
        g.setColor(Color.RED);
        for(int i=0; i<map.getBotMap().length; i++){
            g.drawLine(0, i*CELL_SIZE, getWidth(), i*CELL_SIZE);
        }
        for(int i=0; i<map.getBotMap()[0].length; i++){
            g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, getHeight());
        }
    }

    public void drawBots(Graphics g){
        int headSize=CELL_SIZE/3;
        for(int i=0; i<map.getBotMap().length; i++) {
            for(int j=0; j<map.getBotMap()[0].length; j++){
                if(map.getBotMap()[i][j]!=null) {
                    int locX = map.getBotMap()[i][j].getLoc().getCol() * CELL_SIZE;
                    int locY = map.getBotMap()[i][j].getLoc().getRow() * CELL_SIZE;

                    if(map.getBotMap()[i][j] instanceof Wall){
                        g.setColor(Color.BLACK);
                        g.fillRect(locX, locY, CELL_SIZE, CELL_SIZE);
                    }
                    else {
                        int[] shifts = setShifts(map.getBotMap()[i][j], headSize);
                        g.setColor(Color.BLACK);
                        g.fillOval(locX, locY, CELL_SIZE, CELL_SIZE);
                        g.setColor(Color.RED);
                        g.drawString("" + map.getBotMap()[i][j].getId(), locX + CELL_SIZE / 2 - 7, locY + CELL_SIZE / 2);
                        g.setColor(Color.WHITE);
                        g.fillOval(locX + shifts[0], locY + shifts[1], headSize, headSize);
                    }
                }
            }

            }
    }

    private int[] setShifts(Entity e, int headSize){
        Bot b=(Bot)e;
        int[] shifts=new int[2];
        switch(b.getDirection()){
            case UP:
                shifts[0]=CELL_SIZE/2-headSize/2;
                shifts[1]=0;
                break;
            case DOWN:
                shifts[0]=CELL_SIZE/2-headSize/2;
                shifts[1]=CELL_SIZE-headSize;
                break;
            case RIGHT:
                shifts[0]=CELL_SIZE-headSize;
                shifts[1]=CELL_SIZE/2-headSize/2;
                break;
            case LEFT:
                shifts[0]=0;
                shifts[1]=CELL_SIZE/2-headSize/2;
                break;
        }

        return shifts;
    }


    public Dimension getPanelSize() {
        return panelSize;
    }

    public void setPanelSize(Dimension panelSize) {
        this.panelSize = panelSize;
    }

    public ArrayList<Entity> getBotList() {
        return botList;
    }

    public void setBotList(ArrayList<Entity> botList) {
        this.botList = botList;
    }

    public Map getMap() {
        return map;
    }


}
