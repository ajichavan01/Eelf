package main.Creature;

import main.Genetics.Genome;

public class CreatureReproduction {
    private final CreatureGeneValues CurrentGeneValues;
    private final CreatureVitals Vitals;
    private float maxBirthRecoveryTime;
    private float birthGestationEnergyCost;
    private float birthEnergyCost;
    private float gestationPeriod;
    public CreatureReproduction(Creature currentCreature){
        CurrentGeneValues=currentCreature.GetGenes();
        Vitals=currentCreature.GetVitals();
    }

    public void InitializeReproduction(){
        maxBirthRecoveryTime=CurrentGeneValues.GetBirthRecoveryTime();
        birthEnergyCost=CurrentGeneValues.GetBirthEnergyCost();
        birthGestationEnergyCost=CurrentGeneValues.GetBirthGestationEnergyCost();
        gestationPeriod=CurrentGeneValues.GetGestationPeriod();
    }

    public void ReproductionCycle(){

    }

    //TODO: Check to see if creature is ready to reproduce
    //TODO: Both male and female must be Adult, in good health and have need energy
    //TODO: Females also have to have the next egg ready (birthCoolDown)
    //TODO: Returns true if above requirements are met
    public boolean IsReadyToReproduce(){
        return true;
    }

    //TODO: When two creatures mate this method set pregnant to true and start Gestation
    //TODO Returns true of Conceived if successful
    public boolean Conceived(){
        return true;
    }

    //TODO:
    public boolean Gestation(){
        return true;
    }

    public boolean GiveBirth(){
        return true;
    }

    public Genome CreateOffSpringDNA(Genome genome){
        return genome;
    }
}
