package model;

public class Wall extends Entity {

    boolean hasDoor;
    boolean isLocked;

    public Wall(Location loc, int id, boolean living, boolean door, boolean locked) {
        super(loc, id);
    }

    public boolean isHasDoor() {
        return hasDoor;
    }

    public void setHasDoor(boolean hasDoor) {
        this.hasDoor = hasDoor;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @Override
    public String toString() {
        return "model.Wall{" +
                "hasDoor=" + hasDoor +
                ", isLocked=" + isLocked +
                "} " + super.toString();
    }
}