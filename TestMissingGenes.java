import main.Genetics.GeneDefinitions;
import main.Genetics.GeneID;

public class TestMissingGenes {
    public static void main(String[] args) {
        GeneDefinitions genesDef = new GeneDefinitions();
        
        // Test all genes used in Population.BuildRandomGenome()
        int[] geneIDs = {
            GeneID.BodyLength, GeneID.HeadShape, GeneID.BodyHeight, GeneID.BodyTaper,
            GeneID.BodyWidth, GeneID.FlipperPresent, GeneID.FlipperHeight, GeneID.FlipperWidth,
            GeneID.TailPresent, GeneID.TailHeightPercentage, GeneID.TailWidthPercentage,
            GeneID.BodyColorRed, GeneID.BodyColorGreen, GeneID.BodyColorBlue, GeneID.SkinToughness,
            GeneID.FlipperColorRed, GeneID.FlipperColorGreen, GeneID.FlipperColorBlue,
            GeneID.TailColorRed, GeneID.TailColorGreen, GeneID.TailColorBlue, 
            GeneID.BodyDistanceBetweenSegments, GeneID.StomachSize, GeneID.DigestionRate,
            GeneID.PlantToEnergyConversionRate, GeneID.MeatToEnergyConversionRate, GeneID.MaxStoredEnergy,
            GeneID.BirthGestationEnergyCost, GeneID.BirthRecoveryTime, GeneID.BirthEnergyCost, 
            GeneID.GestationPeriod, GeneID.VisionAngle, GeneID.VisionClarity, GeneID.VisionDistance,
            GeneID.VisionScanFreq, GeneID.EyeColorRed, GeneID.EyeColorGreen, GeneID.EyeColorBlue,
            GeneID.EyeSize, GeneID.EyesPresent, GeneID.VisionDominancePercentage,
            GeneID.MovementSpeed, GeneID.MaxTurnAngle, GeneID.MassPercentage,
            GeneID.MouthSize, GeneID.BiteStrength, GeneID.MouthColorRed, GeneID.MouthColorGreen,
            GeneID.MouthColorBlue, GeneID.MouthPresent, GeneID.LifeSpan, GeneID.MatureAgePercentage,
            GeneID.SeniorAgePercentage, GeneID.ReceptorsSensitivity, GeneID.ScentDominancePercentage
        };
        
        for (int geneID : geneIDs) {
            try {
                var gene = genesDef.GetGene(geneID);
                if (gene.Description().isEmpty()) {
                    System.out.println("Missing gene definition for ID: " + geneID);
                }
            } catch (Exception e) {
                System.out.println("Error accessing gene ID: " + geneID + " - " + e.getMessage());
            }
        }
    }
}
