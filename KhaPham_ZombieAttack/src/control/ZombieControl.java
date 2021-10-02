package control;

import model.ApocalypseSimulator;
import model.Utilities;
import view.ZombieFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZombieControl implements ActionListener {
    private ZombieFrame view;
    private ApocalypseSimulator model;


    public ZombieControl(ZombieFrame view, ApocalypseSimulator model) {
        this.view = view;
        this.model = model;
        view.getSubmitButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Utilities.setStartNumFood(Integer.parseInt(view.getRationField().getText()));
        Utilities.NUM_PEOPLE =Integer.parseInt(view.getSurvivorsField().getText());
        Utilities.setAMMO(Integer.parseInt(view.getAmmoField().getText()));
        Utilities.START_NUM_DAYS = Integer.parseInt(view.getDaysField().getText());
        Utilities.GUN_HIT_PROB = Integer.parseInt(view.getGunHitField().getText());
        Utilities.GUN_CRIT_PROB = Integer.parseInt(view.getGunCritField().getText());
        Utilities.BLUNT_HIT_PROB = Integer.parseInt(view.getBluntHitField().getText());
        Utilities.BLUNT_CRIT_PROB = Integer.parseInt(view.getBluntCritField().getText());
        Utilities.MAX_ZOMBIES = Integer.parseInt(view.getZombieMaxField().getText());
        Utilities.MIN_ZOMBIES = Integer.parseInt(view.getZombieMinField().getText());

        model.personConstructors();
        model.setDays(Utilities.START_NUM_DAYS);
        model.setFood(Utilities.START_NUM_FOOD);
        model.setSurvivors(Utilities.NUM_PEOPLE);
        view.getOutputArea().setText("");
        model.setDayCount(1);



        view.getOutputArea().append(+model.getSurvivors()+" Students start in the building under siege. ");
        view.getOutputArea().append("Starting Survivors: "+Utilities.NUM_PEOPLE+"\nStarting Rations: "+Utilities.START_NUM_FOOD+"\nStarting Ammo Per Person: "+Utilities.AMMO+"\n\n");
        do {
            if (model.getDayCount() != 1) {
                view.getOutputArea().append("Rations: "+model.getFood()+"\nSurvivors: "+model.getSurvivors()+"\n\nContinuing onto day " + model.getDayCount() +".....\n");
            }

            view.getOutputArea().append(model.feedPeople());

            view.getOutputArea().append(model.zombieAttack());
            model.setDayCount(model.getDayCount()+1);
        } while (model.getDayCount() <= model.getDays() && model.getFood() > 0 &&  model.getSurvivors() > 0);

        if (model.getDays() <= model.getDayCount())
            view.getOutputArea().append("\n"+model.getSurvivors()+" students have survived the apocalypse. \nCongratulations!");
        else if (model.getFood() <= 0)
            view.getOutputArea().append("\nThere are no rations left. Everyone has starved to death.\nGame ended on day "+ model.getDayCount() +".\nGame Over. ");
        else if (model.getSurvivors() <=0)
            view.getOutputArea().append("\nThe last man standing has died due to a zombie attack. \nGame ended on day "+ (model.getDayCount()-1) +".\nGame Over.");


    }

}

