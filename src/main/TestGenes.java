package main;
import main.Genetics.GeneDefinitions;
import main.Genetics.GeneID;

public class TestGenes {
    public static void main(String[] args) {
        try {
            GeneDefinitions genesDef = new GeneDefinitions();
            
            // Test the problematic gene that caused the IndexOutOfBoundsException
            var gene = genesDef.GetGene(GeneID.VisionDominancePercentage);
            System.out.println("Gene VisionDominancePercentage (ID 39): " + gene.Description());
            System.out.println("Chromosome: " + gene.GetChromosome());
            System.out.println("Location: " + gene.GetGeneLocationOnChromosome());
            System.out.println("Success! Gene definitions are working properly.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
