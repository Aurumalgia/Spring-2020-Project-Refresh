package model;

public abstract class Entity {
    private Location loc;
    private int id;

    public Entity(Location loc, int id) {
        this.loc = loc;
        this.id = id;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "model.Entity{" +
                "loc=" + loc +
                ", id=" + id +
                '}';
    }
}
