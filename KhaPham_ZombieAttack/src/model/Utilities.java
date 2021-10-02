package model;

public class Utilities {
    //constants to be used in place of magic numbers
    public static int  START_NUM_DAYS = 10, START_NUM_FOOD, NUM_PEOPLE, AMMO, MAX_ZOMBIES, MIN_ZOMBIES;

    public static int GUN_HIT_PROB=20, GUN_CRIT_PROB=33, BLUNT_HIT_PROB = 33, BLUNT_CRIT_PROB = 10;

    public enum Status{RUN, STOP}

    public static int getStartNumDays() {
        return START_NUM_DAYS;
    }

    public static int getStartNumFood() {
        return START_NUM_FOOD;
    }

    public static void setStartNumFood(int startNumFood) {
        START_NUM_FOOD = startNumFood;
    }

    public static int getNumPeople() {
        return NUM_PEOPLE;
    }

    public static void setNumPeople(int numPeople) {
        NUM_PEOPLE = numPeople;
    }

    public static int getAMMO() {
        return AMMO;
    }

    public static void setAMMO(int AMMO) {
        Utilities.AMMO = AMMO;
    }

    //static methods that will be used in Apocalypse
    public static int generateRandomInRange(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
