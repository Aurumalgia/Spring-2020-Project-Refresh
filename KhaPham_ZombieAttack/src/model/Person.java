package model;

public class Person {
    int ammo, personNumber;
    boolean dead;



    public Person (int ammo, boolean dead, int personNumber){
        this.ammo = ammo;
        this.dead = dead;
        this.personNumber=personNumber;
    }

    //Instance variables.  What do you need to keep track of?
    //number of people?  Amount of food?  Ammo left for current Person?

    //Constructor

    //methods  We need getters and setters and toString.  what else?

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }


    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
