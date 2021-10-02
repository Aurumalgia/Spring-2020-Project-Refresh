package model;

public interface Movable {
    enum Directions {
        UP, UP_RIGHT,
        RIGHT, DOWN_RIGHT, DOWN,
        DOWN_LEFT, LEFT, UP_LEFT;
    }
    public static final int SLOW_SPEED=1, MEDIUM_SPEED=2, FAST_SPEED=3;

    public static Directions getNextDirection(Directions d){
       Directions[] allDirs= Directions.values();
        return allDirs[(d.ordinal()+2) % allDirs.length];
    }
    public static Directions getPreviousDirection(Directions d){
        Directions[] allDirs= Directions.values();
        return allDirs[(d.ordinal()-2) % allDirs.length];
    }

    abstract boolean move(Map m);
    abstract void turn();
    abstract void counterTurn();
}
