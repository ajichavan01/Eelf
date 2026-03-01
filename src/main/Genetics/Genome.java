package main.Genetics;

import java.util.ArrayList;

public class Genome {
    private final ArrayList<Chromosome> chromosomes;
    private final int maxChromosomes;

    private final GeneDefinitions genesDef;

    public Genome(int MaxChromosomes, GeneDefinitions GenesDef) {
        chromosomes = new ArrayList<>();
        chromosomes.add(new Chromosome(ChromosomeLength.Body)); // Body
        chromosomes.add(new Chromosome(ChromosomeLength.Vision)); // Vision
        chromosomes.add(new Chromosome(ChromosomeLength.Aging)); // Aging
        chromosomes.add(new Chromosome(ChromosomeLength.Mouth)); // Mouth
        chromosomes.add(new Chromosome(ChromosomeLength.Gender)); // Digestion
        chromosomes.add(new Chromosome(ChromosomeLength.Metabolism)); // Metabolism
        chromosomes.add(new Chromosome(ChromosomeLength.Reproduction)); // Reproduction
        chromosomes.add(new Chromosome(ChromosomeLength.Physics)); // Physics
        chromosomes.add(new Chromosome(ChromosomeLength.Olfactory)); // Olfactory
        chromosomes.add(new Chromosome(ChromosomeLength.ScentEmission)); // ScentEmission
        maxChromosomes = MaxChromosomes;

        genesDef = GenesDef;
    }

    public Chromosome GetChromosome(int ChromosomeID) {
        return chromosomes.get(ChromosomeID);
    }

    public void SetChromosome(int ChromosomeID, Chromosome Chromosome) {
        chromosomes.set(ChromosomeID, Chromosome);
    }

    public float GetGene(int GeneId) {
        GeneBase gene = genesDef.GetGene(GeneId);
        int cn = gene.GetChromosome();
        Chromosome c = chromosomes.get(cn);
        int gl = gene.GetGeneLocationOnChromosome();
        return c.GetGene(gl);
    }

    public void SetGeneInChromosome(int GeneId, float value) {
        // System.out.print("Genome.SetGeneInChromosome: " + GeneId);
        GeneBase gene = genesDef.GetGene(GeneId);

        int chrome = gene.GetChromosome();
        // System.out.print(GeneId + " - " + GetGeneDef(GeneId) + " - " + chrome);
        
        Chromosome c = chromosomes.get(chrome);
        int location = gene.GetGeneLocationOnChromosome();
        
        // System.out.println(":" + location + "=" + value);
        
        c.SetGene(location, value);

        chromosomes.set(chrome, c);
    }

    public GeneBase GetGeneDef(int GeneId) {
        return genesDef.GetGene(GeneId);
    }

    // public ArrayList<Float> CrossOver(ArrayList<Float>
    // parentDNA1,ArrayList<Float> parentDNA2){
    // int numberOfCrossovers=0;
    // ArrayList<Float> newDNA=new ArrayList<Float>();
    // ArrayList<Integer> changes=new ArrayList<Integer>();
    // for(int i=0;i<parentDNA1.size();i++){
    // changes.add(0);
    // if (GetGene(i).Active()){
    // float change=random(0,1);
    // if (change>.50 && numberOfCrossovers<=parentDNA1.size()/2){
    // changes.set(i,1);
    // numberOfCrossovers++;
    // }
    // }
    // }

    // for(int i=0;i<parentDNA1.size();i++){
    // //By default the child will get parent1 gene unless the change value is
    // greater the .50 then parent2 gene is passed on
    // newDNA.set(i,parentDNA1.get(i));

    // //Check if change should be made and if not more then half the DNA is changed
    // if (changes.get(i)==1){
    // newDNA.set(i,parentDNA2.get(i));
    // }
    // }

    // return newDNA;
    // }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Genome{\n");

        // Define chromosome names
        String[] chromosomeNames = {
                "Body", "Vision", "Aging", "Mouth", "Gender",
                "Metabolism", "Reproduction", "Physics", "Olfactory", "ScentEmission"
        };

        // Iterate through all chromosomes
        for (int i = 0; i < chromosomes.size(); i++) {
            String chromeName = (i < chromosomeNames.length) ? chromosomeNames[i] : "Chromosome" + i;
            sb.append("  ").append(chromeName).append(" Chromosome (").append(i).append("):\n");

            // Find all genes for this chromosome
            for (int geneId = 0; geneId < genesDef.GeneCount(); geneId++) {
                try {
                    GeneBase geneDef = genesDef.GetGene(geneId);
                    if (geneDef.GetChromosome() == i && !geneDef.Description().isEmpty()) {
                        float geneValue = GetGene(geneId);
                        sb.append("    Gene ").append(geneId)
                                .append(" (").append(geneDef.GeneName()).append("): ")
                                .append(String.format("%.4f", geneValue))
                                .append(" [").append(geneDef.Description()).append("]\n");
                    }
                } catch (Exception e) {
                    // Skip genes that cause errors
                }
            }
            sb.append("\n");
        }

        sb.append("}");
        return sb.toString();
    }

    public String toSimpleString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Genome{");

        // Count active genes
        int activeGenes = 0;
        for (int geneId = 0; geneId < genesDef.GeneCount(); geneId++) {
            try {
                GeneBase geneDef = genesDef.GetGene(geneId);
                if (!geneDef.Description().isEmpty()) {
                    if (activeGenes > 0)
                        sb.append(", ");
                    sb.append("G").append(geneId).append("=").append(String.format("%.2f", GetGene(geneId)));
                    activeGenes++;
                }
            } catch (Exception e) {
                // Skip genes that cause errors
            }
        }

        sb.append("}");
        return sb.toString();
    }

}
