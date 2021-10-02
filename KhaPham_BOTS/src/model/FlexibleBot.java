package model;

import java.util.InputMismatchException;

public class FlexibleBot extends Bot{

    public FlexibleBot(Location loc, int id, Directions dir, int moveSpeed){
        super(loc, id, dir);
        setMoveSpeed(moveSpeed);
    }

    @Override
    public boolean move(Map m){
        boolean moved=super.move(m);//call on move() from model.Bot
        int spacesToMove=getMoveSpeed();
        int turnCount=0;
        int distance=distanceToEntityOrEdge(m);
        //do flex move
        if(!moved){
            while(spacesToMove>0&&turnCount<4) {
                if(distance==0){
                    turn();
                    turnCount++;
                }//turned
                else{
                    if(spacesToMove>=distance){
                        moveNumSpaces(distance);
                        spacesToMove-=distance;
                    }
                    else{
                        moveNumSpaces(spacesToMove);
                        spacesToMove-=spacesToMove;
                    }
                    m.updateMap();
                    turnCount=0;
                }//moved
                distance=distanceToEntityOrEdge(m);
            }//end while
            moved=turnCount<4;
        }

        return moved;
    }


    /**
    * @return the number of cells in the direction that the bot is facing
     * between the bot and the closest entity or edge of map
    * */
    public int distanceToEntityOrEdge(Map m){
        int distance=getMoveSpeed();
        int count=0;
        boolean pathClear=true;


        if(distance>=botDistanceFromEdge()){
            distance=botDistanceFromEdge();
        }
        while (pathClear && count < distance) {
            switch(getDirection()){
                case UP:{
                    if(validCell(m,getLoc().getRow() - count - 1,getLoc().getCol())){
                         count++;
                    }
                    else{
                        pathClear=false;
                    }
                    break;
                }
                case DOWN:{
                    if(validCell(m,getLoc().getRow() + count + 1,getLoc().getCol())){
                        count++;
                    }
                    else{
                        pathClear=false;
                    }
                    break;
                }
                case LEFT:{
                    if(validCell(m, getLoc().getRow(),getLoc().getCol() - count - 1)){
                        count++;
                    }
                    else{
                        pathClear=false;
                    }
                    break;
                }
                case RIGHT:{
                    if(validCell(m,getLoc().getRow(),getLoc().getCol() + count + 1)){
                        count++;
                    }
                    else{
                        pathClear=false;
                    }
                    break;
                }
            }

        }//end while

        return count;

    }
    public boolean callBotMove(Map m){
        return super.move(m);
    }

    public void speedUp (int spdVal)throws InputMismatchException {
        if(spdVal>=0) {
            setMoveSpeed(getMoveSpeed() + spdVal);
        }
        else{
            throw new InputMismatchException("Speed must be greater than or equal to 0");
        }
    }

    public void speedDown(int spdVal)throws InputMismatchException{
        if(getMoveSpeed()-spdVal>=0){
            setMoveSpeed(getMoveSpeed()-spdVal);
        }
        else {
            throw new InputMismatchException("Speed must not go below 0.");
        }
    }

    @Override
    public void counterTurn() {
        if(getDirection().compareTo(Directions.UP)>0){
            setDirection(Movable.getPreviousDirection(getDirection()));
        }
        else{
            setDirection(Directions.LEFT);
        }
    }
}