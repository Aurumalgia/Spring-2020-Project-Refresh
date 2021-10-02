package model;

public class WallBot extends FlexibleBot {
    public WallBot(Location loc, int id, Directions dir, int moveSpeed) {
        super(loc, id, dir, moveSpeed);
    }


    @Override
    public boolean move(Map m) {
        Boolean moved = false;
        int tempRow = getLoc().getRow();
        int tempCol = getLoc().getCol();
        if (super.move(m)) {
            m.getBots().add(new Wall(new Location(tempRow, tempCol), 1, false, false, true));
            moved = true;
        }

        return moved;
    }
}
