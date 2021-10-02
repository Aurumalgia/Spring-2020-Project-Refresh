package model;

public class HopBot extends FlexibleBot {

    public HopBot(Location loc, int id, Directions dir, int moveSpeed) {
        super(loc, id, dir, moveSpeed);
    }

    @Override
    public boolean move(Map m) {
        int distance=distanceToEntityOrEdge(m);
        int spacesToMove=getMoveSpeed();
        int turnCount=0;

        Boolean moved = callBotMove(m);

        if (!moved){
            while(spacesToMove>0&&turnCount<4) {

                if(distance==0){
                    if(botDistanceToEntity(m) == 0){
                        if(hop(m)){
                            spacesToMove = 0;
                        }
                        else {
                            turn();
                            turnCount++;
                        }
                    }
                    else {
                        turn();
                        turnCount++;
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

    public Boolean hop(Map m){
        int count = 1;
        Boolean emptySpace = false;
        switch (getDirection()){
            case LEFT:
                while(count <= botDistanceFromEdge() && !emptySpace){
                    if(validCell(m, getLoc().getRow(), getLoc().getCol() - count)){
                        emptySpace = true;
                        getLoc().setCol(getLoc().getCol() - count);
                        getLoc().setRow(getLoc().getRow());
                    }
                    count++;
                }
                break;
            case RIGHT:
                while(count <= botDistanceFromEdge() && !emptySpace){
                    if(validCell(m, getLoc().getRow(), getLoc().getCol() + count)){
                        emptySpace = true;
                        getLoc().setCol(getLoc().getCol() + count);
                        getLoc().setRow(getLoc().getRow());
                    }
                    count++;
                }
                break;
            case UP:
                while(count <= botDistanceFromEdge() && !emptySpace){
                    if(validCell(m, getLoc().getRow() - count, getLoc().getCol())){
                        emptySpace = true;
                        getLoc().setCol(getLoc().getCol());
                        getLoc().setRow(getLoc().getRow() - count);
                    }
                    count++;
                }
                break;
            case DOWN:
                while(count <= botDistanceFromEdge() && !emptySpace){
                    if(validCell(m, getLoc().getRow() + count, getLoc().getCol())){
                        emptySpace = true;
                        getLoc().setCol(getLoc().getCol());
                        getLoc().setRow(getLoc().getRow() + count);
                    }
                    count++;
                }
                break;

        }
        return emptySpace;
    }


}

