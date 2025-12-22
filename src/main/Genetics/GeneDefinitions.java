package main.Genetics;

import java.util.ArrayList;
import java.util.Objects;

public class GeneDefinitions {
    private final ArrayList<GeneBase> genes;
    private int locationOnChromosome = 0;
    int maxGenes = 100;

    public GeneDefinitions() {
        this.locationOnChromosome = 0;
        this.genes = new ArrayList<>(maxGenes);



        //Physical attributes
        //new GeneBase(Active,Min,Max,MutationFactor,StartingValue,RandomStartingValue,GeneDescription,GeneID,this.locationOnChromosome,GeneIDName,Chromosome)

        initGenes();
        setBodyGenes();
        setFlipperGenes();
        setTailGenes();
        setBodyColorGenes();
        setVisionGenes();
        setEyeGenes();
        setPhysicsGenes();
        setMouthGenes();
        setLifeAgeGenes();
        setMetabolismGenes();
        setReproductionGenes();
        setOlfactoryGenes();

        //Scent Receptors
        //ReadyToMateScent=91;
        //FleeOrRepulseScent=92;
        //FoodScent=93;

        //ScentEmission
        //ScentEmitterRed=100;
        //ScentEmitterGreen=101;
        //ScentEmitterBlue=102;
        //ScentEmissionRateRed=103;
        //ScentEmissionRateGreen=104;
        //ScentEmissionRateBlue=105;
        //ScentRedStrength=106;
        //ScentGreenStrength=107;
        //ScentBlueStrength=108;
        //ScentRedDropOff=109;
        //ScentGreenDropOff=110;
        //ScentBlueDropOff=111;

        //Gender
    }

    private void initGenes() {
        for (int i = 0; i < this.maxGenes; i++) {
            this.genes.add(new GeneBase(false, -1, -1, -1, -1, true, "", -1, -1, "", i));
        }
    }

    private void setEyeGenes() {
        GeneBase geneBase;
        //Eye Genes
        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the red color of the creature.", GeneID.EyeColorRed, this.locationOnChromosome++, "GeneID.EyeColorRed", ChromosomeID.Vision);
        genes.set(GeneID.EyeColorRed, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the green color of the creature.", GeneID.EyeColorGreen, this.locationOnChromosome++, "GeneID.EyeColorGreen", ChromosomeID.Vision);
        genes.set(GeneID.EyeColorGreen, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.0f, 10.0f, 0.0f, true, "Determines the blue color of the creature.", GeneID.EyeColorBlue, this.locationOnChromosome++, "GeneID.EyeColorBlue", ChromosomeID.Vision);
        genes.set(GeneID.EyeColorBlue, geneBase);

        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.02f, 0.0f, false, "Determines the size of the creatures eyes.  They start at 0 and only come into effect if EyesPresent is true.", GeneID.EyeSize, this.locationOnChromosome++, "GeneID.EyeSize", ChromosomeID.Vision);
        genes.set(GeneID.EyeSize, geneBase);

        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.01f, 0.0f, true, "Determines the if creature develops eyes.", GeneID.EyesPresent, this.locationOnChromosome++, "GeneID.GeneID.EyesPresent", ChromosomeID.Vision);
        genes.set(GeneID.EyesPresent, geneBase);

        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.025f, 0.0f, true, "Determines the dominance percentage of vision.", GeneID.VisionDominancePercentage, this.locationOnChromosome++, "GeneID.VisionDominancePercentage", ChromosomeID.Vision);
        genes.set(GeneID.VisionDominancePercentage, geneBase);
    }

    private void setVisionGenes() {
        GeneBase geneBase;
        //Vision Genes
        this.locationOnChromosome = 0;
        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.025f, 0.0f, true, "Determines the angle of vision.", GeneID.VisionAngle, this.locationOnChromosome++, "GeneID.VisionAngle", ChromosomeID.Vision);
        genes.set(GeneID.VisionAngle, geneBase);

        geneBase = new GeneBase(true, 0.03f, 0.03f, 0.0f, 0.0f, true, "Determine the clarity of the vision.", GeneID.VisionClarity, this.locationOnChromosome++, "GeneID.VisionClarity", ChromosomeID.Vision);
        genes.set(GeneID.VisionClarity, geneBase);

        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.05f, 0.0f, true, "Determines the distance the creature can see.", GeneID.VisionDistance, this.locationOnChromosome++, "GeneID.VisionDistance", ChromosomeID.Vision);
        genes.set(GeneID.VisionDistance, geneBase);

        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.025f, 0.0f, true, "Determines how often to evaluate vision.", GeneID.VisionScanFreq, this.locationOnChromosome++, "GeneID.VisionScanFreq", ChromosomeID.Vision);
        genes.set(GeneID.VisionScanFreq, geneBase);
    }

    private void setBodyColorGenes() {
        GeneBase geneBase;
        //Body Color
        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the red color of the creature.", GeneID.BodyColorRed, this.locationOnChromosome++, "GeneID.BodyColorRed", ChromosomeID.Body);
        genes.set(GeneID.BodyColorRed, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the green color of the creature.", GeneID.BodyColorGreen, this.locationOnChromosome++, "GeneID.BodyColorGreen", ChromosomeID.Body);
        genes.set(GeneID.BodyColorGreen, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the blue color of the creature.", GeneID.BodyColorBlue, this.locationOnChromosome++, "GeneID.BodyColorBlue", ChromosomeID.Body);
        genes.set(GeneID.BodyColorBlue, geneBase);
    }

    private void setTailGenes() {
        GeneBase geneBase;
        //Tail genes
        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.01f, 0.0f, true, "Determines the if tail should be present and if they should be considered in speed rate calculation.", GeneID.TailPresent, this.locationOnChromosome++, "GeneID.TailPresent", ChromosomeID.Body);
        genes.set(GeneID.TailPresent, geneBase);

        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.01f, 0.0f, true, "Determines the height Percentage of the flipper.", GeneID.TailHeightPercentage, this.locationOnChromosome++, "GeneID.TailHeightPercentage", ChromosomeID.Body);
        genes.set(GeneID.TailHeightPercentage, geneBase);

        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.01f, 0.0f, true, "Determines the width Percentage of the skin flipper", GeneID.TailWidthPercentage, this.locationOnChromosome++, "GeneID.TailWidthPercentage", ChromosomeID.Body);
        genes.set(GeneID.TailWidthPercentage, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the red color of the creature.", GeneID.TailColorRed, this.locationOnChromosome++, "GeneID.TailColorRed", ChromosomeID.Body);
        genes.set(GeneID.TailColorRed, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the green color of the creature.", GeneID.TailColorGreen, this.locationOnChromosome++, "GeneID.TailColorGreen", ChromosomeID.Body);
        genes.set(GeneID.TailColorGreen, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the blue color of the creature.", GeneID.TailColorBlue, this.locationOnChromosome++, "GeneID.TailColorBlue", ChromosomeID.Body);
        genes.set(GeneID.TailColorBlue, geneBase);
    }

    private int setFlipperGenes() {
        GeneBase geneBase;
        //Flipper Genes
        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.01f, 0.0f, true, "Determines the if flippers are present and if they should be considered in turn rate calculations.", GeneID.FlipperPresent, this.locationOnChromosome++, "GeneID.FlipperPresent", ChromosomeID.Body);
        genes.set(GeneID.FlipperPresent, geneBase);

        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.01f, 3.0f, false, "Determines the height of the flipper", GeneID.FlipperHeight, this.locationOnChromosome++, "GeneID.FlipperHeight", ChromosomeID.Body);
        genes.set(GeneID.FlipperHeight, geneBase);

        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.01f, 1.0f, false, "Determines the width of the skin flipper", GeneID.FlipperWidth, this.locationOnChromosome++, "GeneID.FlipperWidth", ChromosomeID.Body);
        genes.set(GeneID.FlipperWidth, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the red color of the creature.", GeneID.FlipperColorRed, this.locationOnChromosome++, "GeneID.FlipperColorRed", ChromosomeID.Body);
        genes.set(GeneID.FlipperColorRed, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the green color of the creature.", GeneID.FlipperColorGreen, this.locationOnChromosome++, "GeneID.FlipperColorGreen", ChromosomeID.Body);
        genes.set(GeneID.FlipperColorGreen, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the blue color of the creature.", GeneID.FlipperColorBlue, this.locationOnChromosome++, "GeneID.FlipperColorBlue", ChromosomeID.Body);
        genes.set(GeneID.FlipperColorBlue, geneBase);
        return this.locationOnChromosome;
    }

    private int setBodyGenes() {
        this.locationOnChromosome = 0;
        GeneBase geneBase;
        //Body Genes
        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.01f, 0.0f, true, "Determines the number of body segments.", GeneID.BodyLength, this.locationOnChromosome++, "GeneID.BodyLength", ChromosomeID.Body);
        genes.set(GeneID.BodyLength, geneBase);

        geneBase = new GeneBase(true, 1.00f, 1.00f, 0.0f, 0.0f, true, "Determines the shape if the head, as to how long it is.", GeneID.HeadShape, this.locationOnChromosome++, "GeneID.HeadShape", ChromosomeID.Body);
        genes.set(GeneID.HeadShape, geneBase);

        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.05f, 0.0f, true, "Determines the size of the head segment.", GeneID.BodyHeight, this.locationOnChromosome++, "GeneID.BodyHeight", ChromosomeID.Body);
        genes.set(GeneID.BodyHeight, geneBase);

        geneBase = new GeneBase(true, 0.0f, 1.00f, 0.05f, 0.0f, true, "Determines the color of the creature.", GeneID.BodyTaper, this.locationOnChromosome++, "GeneID.BodyTaper", ChromosomeID.Body);
        genes.set(GeneID.BodyTaper, geneBase);

        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.01f, 0.0f, true, "Determines the width of the creature.", GeneID.BodyWidth, this.locationOnChromosome++, "GeneID.BodyWidth", ChromosomeID.Body);
        genes.set(GeneID.BodyWidth, geneBase);

        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.5f, 0.0f, true, "Determines the toughness of the skin toughness", GeneID.SkinToughness, this.locationOnChromosome++, "GeneID.SkinToughness", ChromosomeID.Body);
        genes.set(GeneID.SkinToughness, geneBase);

        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.05f, 0.0f, true, "Determines the distance between body segments", GeneID.BodyDistanceBetweenSegments, this.locationOnChromosome++, "GeneID.BodyDistanceBetweenSegments", ChromosomeID.Body);
        genes.set(GeneID.BodyDistanceBetweenSegments, geneBase);
        
        return this.locationOnChromosome;
    }

    private void setPhysicsGenes() {
        this.locationOnChromosome = 0;
        GeneBase geneBase;
        geneBase = new GeneBase(true, 0.00f, 2.00f, 0.2f, 0.0f, true, "Determines the speed the creature moves at.", GeneID.MovementSpeed, this.locationOnChromosome++, "GeneID.MovementSpeed", ChromosomeID.Physics);
        genes.set(GeneID.MovementSpeed, geneBase);

        geneBase = new GeneBase(true, 0.01f, 0.05f, 0.005f, 0.0f, true, "Determines how tight an angle a creature can turn", GeneID.MaxTurnAngle, this.locationOnChromosome++, "GeneID.MaxTurnAngle", ChromosomeID.Physics);
        genes.set(GeneID.MaxTurnAngle, geneBase);

        geneBase = new GeneBase(true, 0.01f, 0.05f, 0.005f, 0.0f, true, "Determines the percentage heavy or lighter each unit of mass is.", GeneID.MassPercentage, this.locationOnChromosome++, "GeneID.MassPercentage", ChromosomeID.Physics);
        genes.set(GeneID.MassPercentage, geneBase);
    }

    private void setMouthGenes() {
        this.locationOnChromosome = 0;
        GeneBase geneBase;
        geneBase = new GeneBase(true, 0.50f, 1.00f, 0.1f, 0.0f, true, "Determines the size of the mouth.", GeneID.MouthSize, this.locationOnChromosome++, "GeneID.MouthSize", ChromosomeID.Mouth);
        genes.set(GeneID.MouthSize, geneBase);

        geneBase = new GeneBase(true, 0.00f, 1.00f, 0.1f, 0.0f, true, "Determines the strength of the bite.", GeneID.BiteStrength, this.locationOnChromosome++, "GeneID.BiteStrength", ChromosomeID.Mouth);
        genes.set(GeneID.BiteStrength, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the red color of the creature.", GeneID.MouthColorRed, this.locationOnChromosome++, "GeneID.MouthColorRed", ChromosomeID.Mouth);
        genes.set(GeneID.MouthColorRed, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the green color of the creature.", GeneID.MouthColorGreen, this.locationOnChromosome++, "GeneID.MouthColorGreen", ChromosomeID.Mouth);
        genes.set(GeneID.MouthColorGreen, geneBase);

        geneBase = new GeneBase(true, 0.0f, 255.00f, 10.0f, 0.0f, true, "Determines the blue color of the creature.", GeneID.MouthColorBlue, this.locationOnChromosome++, "GeneID.MouthColorBlue", ChromosomeID.Mouth);
        genes.set(GeneID.MouthColorBlue, geneBase);

        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.01f, 0.0f, true, "Determines the if creature develops eyes.", GeneID.MouthPresent, this.locationOnChromosome++, "GeneID.GeneID.MouthPresent", ChromosomeID.Mouth);
        genes.set(GeneID.MouthPresent, geneBase);
    }

    private void setLifeAgeGenes() {
        this.locationOnChromosome = 0;
        GeneBase geneBase;
        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.1f, 0.0f, true, "Determines the how long the creature lives.", GeneID.LifeSpan, this.locationOnChromosome++, "GeneID.LifeSpan", ChromosomeID.Aging);
        genes.set(GeneID.LifeSpan, geneBase);

        geneBase = new GeneBase(true, 0.2f, 0.4f, 0.01f, 0.0f, true, "Determines when the creature matures.", GeneID.MatureAgePercentage, this.locationOnChromosome++, "GeneID.MatureAgePercentage", ChromosomeID.Aging);
        genes.set(GeneID.MatureAgePercentage, geneBase);

        geneBase = new GeneBase(true, 0.7f, 0.9f, 0.01f, 0.0f, true, "Determines when the creature is old.", GeneID.SeniorAgePercentage, this.locationOnChromosome++, "GeneID.SeniorAgePercentage", ChromosomeID.Aging);
        genes.set(GeneID.SeniorAgePercentage, geneBase);
    }

    private void setMetabolismGenes() {
        this.locationOnChromosome = 0;
        GeneBase geneBase;
        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.01f, 0.0f, true, "Determines how much food can be held in stomach.", GeneID.StomachSize, this.locationOnChromosome++, "GeneID.StomachSize", ChromosomeID.Metabolism);
        genes.set(GeneID.StomachSize, geneBase);

        geneBase = new GeneBase(true, 0.1f, 1.0f, 0.1f, 0.0f, true, "Determine the rate of how fast food is converted to energy.", GeneID.DigestionRate, this.locationOnChromosome++, "GeneID.DigestionRate", ChromosomeID.Metabolism);
        genes.set(GeneID.DigestionRate, geneBase);

        geneBase = new GeneBase(true, 0.1f, 1.0f, 0.1f, 0.0f, true, "Determines how what percentage of digested plant is converted to energy.", GeneID.PlantToEnergyConversionRate, this.locationOnChromosome++, "GeneID.PlantToEnergyConversionRate", ChromosomeID.Metabolism);
        genes.set(GeneID.PlantToEnergyConversionRate, geneBase);

        geneBase = new GeneBase(true, 0.1f, 1.0f, 0.1f, 0.0f, true, "Determines how what percentage of digested meat is converted to energy.", GeneID.MeatToEnergyConversionRate, this.locationOnChromosome++, "GeneID.MeatToEnergyConversionRate", ChromosomeID.Metabolism);
        genes.set(GeneID.MeatToEnergyConversionRate, geneBase);

        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.1f, 0.0f, true, "Determines how much excess energy can be stored.", GeneID.MaxStoredEnergy, this.locationOnChromosome++, "GeneID.MaxStoredEnergy", ChromosomeID.Metabolism);
        genes.set(GeneID.MaxStoredEnergy, geneBase);

        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.01f, 0.0f, true, "Determines the maximum health for the creature.", GeneID.MaxHealth, this.locationOnChromosome++, "GeneID.MaxHealth", ChromosomeID.Metabolism);
        genes.set(GeneID.MaxHealth, geneBase);

        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.5f, 0.0f, true, "Determines the percentage of health increase when eaten.", GeneID.IncreaseHealthPercentage, this.locationOnChromosome++, "GeneID.IncreaseHealthPercentage", ChromosomeID.Metabolism);
        genes.set(GeneID.IncreaseHealthPercentage, geneBase);
    }

    private void setReproductionGenes() {
        this.locationOnChromosome = 0;
        GeneBase geneBase;
        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.1f, 0.0f, true, "Modifies how much energy is needed each cycle during gestation.", GeneID.BirthGestationEnergyCost, this.locationOnChromosome++, "GeneID.BirthEnergyNeed", ChromosomeID.Reproduction);
        genes.set(GeneID.BirthGestationEnergyCost, geneBase);

        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.1f, 0.0f, true, "Modifies the amount of time between reproduction cycles.", GeneID.BirthRecoveryTime, this.locationOnChromosome++, "GeneID.BirthCoolDown", ChromosomeID.Reproduction);
        genes.set(GeneID.BirthRecoveryTime, geneBase);

        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.1f, 0.0f, true, "Modifies the cost of giving birth.", GeneID.BirthEnergyCost, this.locationOnChromosome++, "GeneID.BirthEnergyCost", ChromosomeID.Reproduction);
        genes.set(GeneID.BirthEnergyCost, geneBase);

        geneBase = new GeneBase(true, 1.0f, 9.0f, 0.25f, 0.0f, true, "Gestation period of offspring", GeneID.GestationPeriod, this.locationOnChromosome++, "GeneID.GestationPeriod", ChromosomeID.Reproduction);
        genes.set(GeneID.GestationPeriod, geneBase);
    }

    private void setOlfactoryGenes() {
        this.locationOnChromosome = 0;
        GeneBase geneBase;
        geneBase = new GeneBase(true, 0.1f, 1.0f, 0.1f, 0.0f, true, "The sensitivity of the olfactory receptors.  How strong the smell to be to be detected.", GeneID.ReceptorsSensitivity, this.locationOnChromosome++, "GeneID.ReceptorsSensitivity", ChromosomeID.Olfactory);
        genes.set(GeneID.ReceptorsSensitivity, geneBase);

        geneBase = new GeneBase(true, 0.0f, 1.0f, 0.1f, 0.0f, true, "The dominance percentage of scent detection.", GeneID.ScentDominancePercentage, this.locationOnChromosome++, "GeneID.ScentDominancePercentage", ChromosomeID.Olfactory);
        genes.set(GeneID.ScentDominancePercentage, geneBase);
    }

    public GeneBase GetGene(int index) {
        //println("GeneDef.GetGene - Index: " + index);
        return genes.get(index);
    }

    public float GeneCount() {
        return genes.size();
    }

    public String Version() {
        return "1.0";
    }

    public String GenesLibrary() {
        StringBuilder output;
        output = new StringBuilder("Active, Minimum, Maximum, MutationFactor, StartingValue, RandomStartingValue, Description, GeneID, GeneLocation, GeneName, Chromosome\r\n");
        for (int i = 0; i < GeneCount(); i++) {
            GeneBase g = GetGene(i);
            if (!Objects.equals(g.Description(), "")) {
                output.append(g.Active()).append(",").append(g.Minimum()).append(",").append(g.Maximum()).append(",").append(g.GetMutationRate()).append(",").append(g.StartingValue()).append(",").append(g.RandomStartingValue()).append(",").append(g.Description()).append(",").append(g.GeneID()).append(",").append(g.GeneName()).append(",").append(g.GetChromosome()).append("\r\n");
            }
        }
        return output.toString();
    }

}

