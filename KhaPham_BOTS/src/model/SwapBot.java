package model;

import java.util.ArrayList;

public class SwapBot extends FlexibleBot{
    private int tolerance;

    public SwapBot(Location loc, int id, Directions dir, int moveSpeed, int tolerance) {
        super(loc, id, dir, moveSpeed);
        this.tolerance=tolerance;
    }

    @Override
    public boolean move(Map m) {
        /*
        * 1. check for bots on map within the tolerance
        *   a. check botlist in model.Map for all bots --done
        *   b. check to see what bots are within the tolerance --done
        *   c. create a list of all bots within the tolerance --done
        * 2. if the list is empty or size() < 1 super.move --done
        * 3. if list is not empty, swap with closest
        *   a. find closest bot on bots list --done
        *   b. make swap
        * */
        ArrayList<Bot> bots=getBotsInRange(m);
        boolean moved=false;

        if(bots.size()<1){
            moved=super.move(m);
        }
        else{
            //find closest bot
            Bot closestBot=bots.get(getIndexOfClosestBot(bots));
            //do swap
            Location tempLoc=getLoc();
            getLoc().setCol(closestBot.getLoc().getCol());
            getLoc().setRow(closestBot.getLoc().getRow());
            closestBot.getLoc().setCol(tempLoc.getCol());
            closestBot.getLoc().setRow(tempLoc.getRow());

            m.clearMap();
            m.putBotsOnMap();

            moved=true;

        }

        return moved;
    }

    public int getIndexOfClosestBot(ArrayList<Bot> bots){
        int min=tolerance+1;
        int indexOfMin=0;

        for(int i=0; i<bots.size(); i++){
            int distance=distance(bots.get(i));
            if(distance<min){
                min=distance;
                indexOfMin=i;
            }
        }

        return indexOfMin;
    }

    public int distance(Bot b){
        int distance=0;
        int y1=b.getLoc().getRow(), y2=getLoc().getRow();
        int x1=b.getLoc().getCol(), x2=getLoc().getCol();

        distance=(int)(Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)));

        return distance;
    }

    public ArrayList<Bot> getBotsInRange(Map map){
        ArrayList<Bot> bots=new ArrayList<>();
        ArrayList<Entity> ents=map.getBots();

        //find all bots within tolerance
        for(Entity e: ents){
            int distance=distance((Bot)e);
            if(distance<=tolerance&& e instanceof Bot){
                bots.add((Bot)e);
            }
        }

        return bots;
    }

//
//    public ArrayList<model.Bot> getBotsInRange(model.Map map){
//        ArrayList<model.Bot> bots=new ArrayList<>();
//
//        for(int i=getLoc().getRow()-tolerance; i<=getLoc().getRow()+tolerance; i++){
//            for(int j=getLoc().getCol()-tolerance; j<=getLoc().getCol()+tolerance; j++){
//                if(validCol(map, j)&&validRow(map,i)){
//                    if(map.getBotMap()[i][j]!=null&&map.getBotMap()[i][j] instanceof model.Bot){
//                        bots.add((model.Bot)map.getBotMap()[i][j]);
//                    }//get botsx
//                }//cell validity check
//            }//inner loop
//        }//outer loop
//
//        return bots;
//    }//end method
}
