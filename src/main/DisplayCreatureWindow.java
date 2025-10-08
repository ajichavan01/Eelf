package main;

import main.Creature.BodySegments.BodySegment;
import main.Creature.Creature;
import static main.Main.gWorld;
import main.Genetics.Genome;
import processing.core.PApplet;
import java.util.UUID;

public class DisplayCreatureWindow extends PApplet{
        private final int lastDisplayed=-1;

        public DisplayCreatureWindow() {
            PApplet.runSketch(new String[] {this.getClass().getSimpleName()}, this);
        }

        public void settings() {
            size(300,200);
        }

        public void setup() {
            frameRate(30);
            background(150);

        }

        public void draw() {

            //if (gRefreshDisplayCreatureFlag){
            //    lastDisplayed=-1;
            //    gRefreshDisplayCreatureFlag=false;
            //}

            background(255);
            if (gWorld.gPopulation.GetCurrentPopulationSize()>0 ){

                Creature original=gWorld.gPopulation.GetCreature(0);
                Genome DNA=original.GetGenes().GetBaseDNA();
                // Creature(int i, float startX,float startY,ArrayList<Float> dna,int gen,float startingEnergy,UUID parent1,int age){
                Creature current=new Creature(499,100,DNA,UUID.randomUUID());
                current.GetVitals().SetAge(original.GetVitals().GetMaturityAge()-1);
                current.GetMetabolism().Aging();
                current.GetVitals().SetMaturity(2);
                current.GetVitals().SetX(100);
                current.GetVitals().SetY(100);
                current.GetVitals().SetAngle(radians(0));
                current.GetBody().UpdateBody();
                current.MoveTo(100,100);
                current.GetBody().GetHeadSegment().SetSegmentX(current.GetVitals().GetX());
                current.GetBody().GetHeadSegment().SetSegmentY(current.GetVitals().GetY());
                current.GetBody().GetHeadSegment().SetSegmentAngle(current.GetVitals().GetAngle());
                current.UpdateCreatureLocation();
                float bl=current.GetBody().GetTotalBodySegmentLength();
                for (int i = 1; i<bl; i++){
                    BodySegment c = current.GetBody().GetBodySegment(i);
                    BodySegment p = current.GetBody().GetBodySegment(i-1);
                    c.UpdateSegment(p);
                }
                current.GetBody().UpdateBody();
                text(original.GetUUID().toString(),20,12);
                current.Display(this,1.0f);
            }
            //877-233-1800 UMR 2758 Direct
        }
    }

