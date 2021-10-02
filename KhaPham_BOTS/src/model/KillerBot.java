package model;

import java.util.ArrayList;

public class KillerBot extends FlexibleBot {

    public KillerBot(Location loc, int id, Directions dir, int moveSpeed) {
        super(loc, id, dir, moveSpeed);

    }

    @Override
    public boolean move(Map m) {
        boolean moved;
        Entity prey;
        int distance=distanceToEntityOrEdge(m);
        int spacesToMove=getMoveSpeed();
        int turnCount=0;

        moved = callBotMove(m);

        if (!moved){
            while(spacesToMove>0&&turnCount<4){

                if(distance==0){
                    prey = canEatCheck(m);
                    if (prey !=null && prey instanceof Bot){
                        m.removeBotFromList(m.removeBotFromMap((Bot) prey));
                        getLoc().setCol(prey.getLoc().getCol());
                        getLoc().setRow(prey.getLoc().getRow());
                        setId(getId()+1);
                        spacesToMove = 0;
                        turnCount = 0;
                    }
                    else {
                        turn();
                        turnCount++;
                        m.updateMap();
                    }
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

    public Entity canEatCheck( Map m){
        Entity prey = null;

        switch (getDirection()) {
            case UP:
                if(validRow(m, getLoc().getRow() - 1)) {
                    if (m.getBotMap()[getLoc().getRow() - 1][getLoc().getCol()] != null && m.getBotMap()[getLoc().getRow() - 1][getLoc().getCol()].getId() < getId()) {
                        prey = m.getBotMap()[getLoc().getRow() - 1][getLoc().getCol()];
                    }
                }
                break;
            case DOWN:
                if(validRow(m, getLoc().getRow() + 1)) {
                    if (m.getBotMap()[getLoc().getRow() + 1][getLoc().getCol()] != null && m.getBotMap()[getLoc().getRow() + 1][getLoc().getCol()].getId() < getId()) {
                        prey = m.getBotMap()[getLoc().getRow() + 1][getLoc().getCol()];
                    }
                }
                    break;
            case LEFT:
                if(validCol(m, getLoc().getCol() - 1)) {
                    if (m.getBotMap()[getLoc().getRow()][getLoc().getCol() - 1] != null && m.getBotMap()[getLoc().getRow()][getLoc().getCol() - 1].getId() < getId()) {
                        prey = m.getBotMap()[getLoc().getRow()][getLoc().getCol() - 1];
                    }
                }
                break;
            case RIGHT:
                if(validCol(m, getLoc().getCol() + 1)) {
                    if (m.getBotMap()[getLoc().getRow()][getLoc().getCol() + 1] != null && m.getBotMap()[getLoc().getRow()][getLoc().getCol() + 1].getId() < getId()) {
                        prey = m.getBotMap()[getLoc().getRow()][getLoc().getCol() + 1];
                    }
                }
                break;
        }

        return prey;
    }

}
