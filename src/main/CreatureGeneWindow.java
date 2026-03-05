package main;

import main.Creature.*;
import main.Genetics.GeneBase;
import main.Genetics.GeneID;
import main.Genetics.Genome;

import javax.swing.*;

public class CreatureGeneWindow {
    static JTextPane Stats;

    public CreatureGeneWindow(){
        //Creating the Frame
        JFrame frame = new JFrame("Creature Genes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GameParameters.Width, GameParameters.Height);

        Stats = new JTextPane();
        Stats.setSize(400,1000);

        //Creating the panel at bottom and adding components
        JPanel panel =new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(Stats);
//        Stats1 = new JTextPane();
//        Stats1.setSize(400,1000);
//        panel.add(Stats1);

        JScrollPane pane= new JScrollPane(panel);
        //Adding Components to the frame.
        frame.getContentPane().add(pane);


        frame.setVisible(true);

    }
    String FloatToString(float value){
        return String.format("%.2f",value);
    }
    private String AddField(int offset, GeneBase gene, float geneValue){
        String tab = "    ";
        return tab + gene.GeneName() +
                //"      Description: " + gene.Description() + "\r\n" +
                tab.repeat(offset) + "Value: " + FloatToString(geneValue) +
                "     (" + FloatToString(gene.Minimum()) +
                " - " + FloatToString(gene.Maximum()) +
                ")     Mut:" + FloatToString(gene.GetMutationRate()) +
                //tab.repeat(offset) + "Starting: " + FloatToString(gene.StartingValue()) +
                //"     Allow Random : " + gene.RandomStartingValue() + "\r\n" +
                 "    Chr: " + gene.GetChromosome() +
                "     Gene Loc: " + gene.GetGeneLocationOnChromosome() + "\r\n";
    }
    private String AddSection(String Name, int offset){
        String tab = "    ";
        return tab.repeat(Math.max(0, offset)) +
                Name + "\r\n";
    }
    public void Update(Creature CurrentCreature) {
        CreatureGeneValues Genes=CurrentCreature.GetGenes();
        Genome genome=Genes.GetBaseDNA();

        String gp=AddSection("Body",0);
        gp+=AddField(1,genome.GetGeneDef(GeneID.BodyLength),genome.GetGene(GeneID.BodyLength));
        gp+=AddField(1,genome.GetGeneDef(GeneID.HeadShape),genome.GetGene(GeneID.HeadShape));
        gp+=AddField(1,genome.GetGeneDef(GeneID.BodyHeight),genome.GetGene(GeneID.BodyHeight));
        gp+=AddField(1,genome.GetGeneDef(GeneID.BodyTaper),genome.GetGene(GeneID.BodyTaper));
        gp+=AddField(1,genome.GetGeneDef(GeneID.BodyWidth),genome.GetGene(GeneID.BodyWidth));
        gp+=AddField(1,genome.GetGeneDef(GeneID.SkinToughness),genome.GetGene(GeneID.SkinToughness));
        gp+=AddField(1,genome.GetGeneDef(GeneID.FlipperPresent),genome.GetGene(GeneID.FlipperPresent));
        gp+=AddField(1,genome.GetGeneDef(GeneID.FlipperHeight),genome.GetGene(GeneID.FlipperHeight));
        gp+=AddField(1,genome.GetGeneDef(GeneID.FlipperWidth),genome.GetGene(GeneID.FlipperWidth));
        gp+=AddField(1,genome.GetGeneDef(GeneID.TailPresent),genome.GetGene(GeneID.TailPresent));
        gp+=AddField(1,genome.GetGeneDef(GeneID.TailHeightPercentage),genome.GetGene(GeneID.TailHeightPercentage));
        gp+=AddField(1,genome.GetGeneDef(GeneID.TailWidthPercentage),genome.GetGene(GeneID.TailWidthPercentage));
        gp+=AddField(1,genome.GetGeneDef(GeneID.TailColorRed),genome.GetGene(GeneID.TailColorRed));
        gp+=AddField(1,genome.GetGeneDef(GeneID.TailColorGreen),genome.GetGene(GeneID.TailColorGreen));
        gp+=AddField(1,genome.GetGeneDef(GeneID.TailColorBlue),genome.GetGene(GeneID.TailColorBlue));
        gp+=AddField(1,genome.GetGeneDef(GeneID.BodyColorRed),genome.GetGene(GeneID.BodyColorRed));
        gp+=AddField(1,genome.GetGeneDef(GeneID.BodyColorGreen),genome.GetGene(GeneID.BodyColorGreen));
        gp+=AddField(1,genome.GetGeneDef(GeneID.BodyColorBlue),genome.GetGene(GeneID.BodyColorBlue));
        gp+=AddField(1,genome.GetGeneDef(GeneID.BodyDistanceBetweenSegments),genome.GetGene(GeneID.BodyDistanceBetweenSegments));
        gp+="\r\n";

        String vp=AddSection("Vision",0);
        vp+=AddField(1,genome.GetGeneDef(GeneID.VisionAngle),genome.GetGene(GeneID.VisionAngle));
        vp+=AddField(1,genome.GetGeneDef(GeneID.VisionClarity),genome.GetGene(GeneID.VisionClarity));
        vp+=AddField(1,genome.GetGeneDef(GeneID.VisionDistance),genome.GetGene(GeneID.VisionDistance));
        vp+=AddField(1,genome.GetGeneDef(GeneID.VisionScanFreq),genome.GetGene(GeneID.VisionScanFreq));
        vp+=AddField(1,genome.GetGeneDef(GeneID.EyeColorRed),genome.GetGene(GeneID.EyeColorRed));
        vp+=AddField(1,genome.GetGeneDef(GeneID.EyeColorGreen),genome.GetGene(GeneID.EyeColorGreen));
        vp+=AddField(1,genome.GetGeneDef(GeneID.EyeColorBlue),genome.GetGene(GeneID.EyeColorBlue));
        vp+=AddField(1,genome.GetGeneDef(GeneID.EyeSize),genome.GetGene(GeneID.EyeSize));
        vp+=AddField(1,genome.GetGeneDef(GeneID.EyesPresent),genome.GetGene(GeneID.EyesPresent));
        vp+=AddField(1,genome.GetGeneDef(GeneID.VisionDominancePercentage),genome.GetGene(GeneID.VisionDominancePercentage));
        vp+="\r\n";

        String ap=AddSection("Aging",0);
        ap+=AddField(1,genome.GetGeneDef(GeneID.LifeSpan),genome.GetGene(GeneID.LifeSpan));
        ap+=AddField(1,genome.GetGeneDef(GeneID.MatureAgePercentage),genome.GetGene(GeneID.MatureAgePercentage));
        ap+=AddField(1,genome.GetGeneDef(GeneID.SeniorAgePercentage),genome.GetGene(GeneID.SeniorAgePercentage));
        ap+=AddField(1,genome.GetGeneDef(GeneID.MaxHealth),genome.GetGene(GeneID.MaxHealth));
        ap+=AddField(1,genome.GetGeneDef(GeneID.IncreaseHealthPercentage),genome.GetGene(GeneID.IncreaseHealthPercentage));
        ap+="\r\n";

        String mp=AddSection("Metabolism",0);
        mp+=AddField(1,genome.GetGeneDef(GeneID.StomachSize),genome.GetGene(GeneID.StomachSize));
        mp+=AddField(1,genome.GetGeneDef(GeneID.DigestionRate),genome.GetGene(GeneID.DigestionRate));
        mp+=AddField(1,genome.GetGeneDef(GeneID.PlantToEnergyConversionRate),genome.GetGene(GeneID.PlantToEnergyConversionRate));
        mp+=AddField(1,genome.GetGeneDef(GeneID.MeatToEnergyConversionRate),genome.GetGene(GeneID.MeatToEnergyConversionRate));
        mp+=AddField(1,genome.GetGeneDef(GeneID.MaxStoredEnergy),genome.GetGene(GeneID.MaxStoredEnergy));
        mp+="\r\n";

        String mop=AddSection("Mouth",0);
        mop+=AddField(1,genome.GetGeneDef(GeneID.MouthSize),genome.GetGene(GeneID.MouthSize));
        mop+=AddField(1,genome.GetGeneDef(GeneID.MouthColorRed),genome.GetGene(GeneID.MouthColorRed));
        mop+=AddField(1,genome.GetGeneDef(GeneID.MouthColorGreen),genome.GetGene(GeneID.MouthColorGreen));
        mop+=AddField(1,genome.GetGeneDef(GeneID.MouthColorBlue),genome.GetGene(GeneID.MouthColorBlue));
        mop+=AddField(1,genome.GetGeneDef(GeneID.BiteStrength),genome.GetGene(GeneID.BiteStrength));
        mop+=AddField(1,genome.GetGeneDef(GeneID.MouthPresent),genome.GetGene(GeneID.MouthPresent));
        mop+="\r\n";

        String gep=AddSection("Gender",0);
        gep+="\r\n";
        //gep+=AddField(1,genome.GetGeneDef(GeneID),genome.GetGene(GeneID.));

        String rp=AddSection("Reproduction",0);
        rp+=AddField(1,genome.GetGeneDef(GeneID.BirthGestationEnergyCost),genome.GetGene(GeneID.BirthGestationEnergyCost));
        rp+=AddField(1,genome.GetGeneDef(GeneID.BirthRecoveryTime),genome.GetGene(GeneID.BirthRecoveryTime));
        rp+=AddField(1,genome.GetGeneDef(GeneID.BirthEnergyCost),genome.GetGene(GeneID.BirthEnergyCost));
        rp+=AddField(1,genome.GetGeneDef(GeneID.GestationPeriod),genome.GetGene(GeneID.GestationPeriod));
        rp+="\r\n";

        String pp=AddSection("Physics",0);
        pp+=AddField(1,genome.GetGeneDef(GeneID.MassPercentage),genome.GetGene(GeneID.MassPercentage));
        pp+=AddField(1,genome.GetGeneDef(GeneID.MaxTurnAngle),genome.GetGene(GeneID.MaxTurnAngle));
        pp+=AddField(1,genome.GetGeneDef(GeneID.MovementSpeed),genome.GetGene(GeneID.MovementSpeed));
        pp+="\r\n";

        String op=AddSection("Olfactory",0);
        op+=AddField(1,genome.GetGeneDef(GeneID.ReceptorsSensitivity),genome.GetGene(GeneID.ReceptorsSensitivity));
        op+=AddField(1,genome.GetGeneDef(GeneID.ScentDominancePercentage),genome.GetGene(GeneID.ScentDominancePercentage));
        op+="\r\n";

        Stats.setText(gp + vp + ap + mp + mop + gep + rp + pp + op);
        //Stats1.setText();
    }
}
