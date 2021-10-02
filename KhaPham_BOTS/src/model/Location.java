package model;

public class Location {
    private int row;
    private int col;

    public Location(int r, int c){
        row=r;
        col=c;
    }

    public boolean equals(Location loc){
        boolean sameRow=this.row==loc.getRow();
        boolean sameCol=this.col==loc.getCol();
        return sameCol&&sameRow;
    }

    public int getRow(){
        return row;
    }

    public void setRow(int r){
        row=r;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int c) {
        col = c;
    }

    @Override
    public String toString(){
        return "[ROW: "+row+" COL: "+col+"]";
    }


}
