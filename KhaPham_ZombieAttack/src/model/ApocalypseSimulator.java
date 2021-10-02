package model;

import java.util.Scanner;

public class ApocalypseSimulator {
    //instance data here.  What do you need to keep track of?
    //number of days left?
    //Need Days, food, and people left
    int days, food, survivors, dayCount = 1, attacker, bluntHP, shotHP, zombie;
    public Person[] people;


    public ApocalypseSimulator(int days, int survivors, int zombie){
        this.days = days;
        this.survivors = survivors;
        this.zombie = zombie;
    }
    public void personConstructors(){
        people = new Person[Utilities.NUM_PEOPLE];
        for(int x = 0; x<Utilities.NUM_PEOPLE; x++) {
            people[x] = new Person(Utilities.AMMO, false, x+1);

        }
    }


    public String feedPeople(){
        int starvedToDeath;
        int n = 0;
        String output = "";
        if (food >= survivors) {
            food = food - survivors;
            output ="All "+survivors+" people have been fed.";
        }
        else if (food < survivors) {
            starvedToDeath = survivors - food;
            output = food +" people have been fed. There wasn't enough food for everybody.\n"+starvedToDeath+" people have died. ";

            for (int y = starvedToDeath; y > 0; y--) {
                while (people[n].isDead()==true&&n < Utilities.NUM_PEOPLE) {
                    n++;
                }
                people[n].setDead(true);
                survivors--;
                n++;
            }
            food = food - survivors;

        }
        output = output+"\nRations Remaining: "+food+"\n\n";
        return output;
    }

    public String zombieAttack(){
        String output;
        bluntHP = 3;
        shotHP = 2;
        attacker = 0;
        zombie = Utilities.generateRandomInRange(Utilities.MIN_ZOMBIES, Utilities.MAX_ZOMBIES);

        output = zombie + " zombies have broken into CBT! Fight them off!";
        while (zombie > 0 && attacker < people.length) {
            //for the individual zombie attack

            while (people[attacker].isDead() == true && attacker < Utilities.NUM_PEOPLE)
                attacker++;


            while (bluntHP > 0 && shotHP > 0 && attacker < Utilities.NUM_PEOPLE) {

                if (people[attacker].getAmmo() > 0) {
                    output = gunAttack(output);
                } else {
                    output = bluntAttack(output);
                }
                output = deathCheck(output);
            }
                    bluntHP = 3;
                    shotHP = 2;
        }
        return output;
    }

    public String gunAttack(String output) {
        people[attacker].setAmmo(people[attacker].getAmmo() - 1);
        output += "\nPerson " + people[attacker].personNumber + " attacks with a gun! ";

        if (Utilities.generateRandomInRange(1, 100) <=Utilities.GUN_HIT_PROB) {
            if (Utilities.generateRandomInRange(1, 100) <= Utilities.GUN_CRIT_PROB) {
                shotHP = 0;
                output+="\nCritical Hit! ";
            } else {
                shotHP--;
                output+="\nThe zombie took a gunshot! ";
            }
        }

        else {
            output+="\nPerson " + people[attacker].personNumber + " missed!";
            if (Utilities.generateRandomInRange(1, 2) == 1) {
                people[attacker].setDead(true);
                output+="\nPerson " + people[attacker].personNumber + " got attacked by a zombie and died!\n";
                survivors--;
                attacker++;
            }
        }
        return output;
    }


    public String bluntAttack(String output){
        output+="Person "+people[attacker].personNumber+" attacks with a blunt object! ";
        if (Utilities.generateRandomInRange(1, 100) <= Utilities.BLUNT_HIT_PROB) {
            if (Utilities.generateRandomInRange(1, 100) <= Utilities.BLUNT_CRIT_PROB) {
                bluntHP = 0;
                output+= "\nCritical Hit!";
            }
            else {
                bluntHP--;
                output+="\nThe zombie took a strike! ";
            }
        }
        else {
            people[attacker].setDead(true);
            output+="\nPerson "+people[attacker].personNumber+" was overrun by a zombie and died! ";
            survivors--;
            attacker++;
        }
        return output;
    }

    public String deathCheck(String output){
        if (bluntHP == 0 || shotHP == 0) {
            zombie--;
            if (zombie == 0)
                output+="\nThe zombie has been slain!\n\nThe wave has been quelled! \n\nNHCC has "+survivors+" survivors. \nDay "+ dayCount +" has ended.\n\n";
            else
                output+="\nThe zombie has been slain! "+zombie+" left to go!\n";

        }
        return output;
    }


    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getSurvivors() {
        return survivors;
    }

    public void setSurvivors(int survivors) {
        this.survivors = survivors;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }
    public int getZombie() {
        return zombie;
    }

    public void setZombie(int zombie) {
        this.zombie = zombie;
    }

}
