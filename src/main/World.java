package main;

import main.Creature.BodySegments.BodySegment;
import main.Nourishments.MeatNourishment;
import main.Nourishments.Nourishment;
import main.Nourishments.NourishmentTypes;
import main.Nourishments.PlantNourishment;
import main.Creature.*;
import processing.core.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;
import static main.Main.gUtils;

public class World {
    public Population gPopulation = new Population();
    public ArrayList<Nourishment> gNourishment = new ArrayList<>();
    public CreatureStatsWindow gCreatureStatsWindow;
    public CreatureGeneWindow gCreatureGeneWindow;
    static DisplayCreatureWindow gDisplayCreatureWindow;
    static CreatureVisionWindow gCreatureVisionWindow;
    public float gTicks = 1;

    private final int Width = 1000;
    private final int Height = 800;

    public World() {
        // gCreatureStatsWindow=new CreatureStatsWindow();
        // gCreatureGeneWindow=new CreatureGeneWindow();
        if (FlagsOverride.ShowCreatureDisplayWindow) {
            gDisplayCreatureWindow = new DisplayCreatureWindow();
        }
        // gCreatureVisionWindow=new CreatureVisionWindow();
    }

    public void CreatePopulation(int maxPop) {
        gPopulation.CreatePopulation(maxPop, Width, Height);
    }

    public void CreateNourishment(int maxPlants, int maxMeat) {
        for (int i = 0; i < maxPlants; i++) {
            PlantNourishment plantNourishment = new PlantNourishment();
            plantNourishment.InitializeNourishment(gUtils.GetRandomNumber(10, Width - 10),
                    gUtils.GetRandomNumber(10, Height - 10), gUtils.GetRandomNumber(10, 20), 100, new Color(0, 255, 0),
                    0, 100);
            gNourishment.add(plantNourishment);
        }
        for (int i = 0; i < maxMeat; i++) {
            MeatNourishment meatNourishment = new MeatNourishment();
            meatNourishment.InitializeNourishment(gUtils.GetRandomNumber(10, Width - 10),
                    gUtils.GetRandomNumber(10, Height - 10), gUtils.GetRandomNumber(10, 20), 100, new Color(0, 255, 0),
                    0, 100);
            gNourishment.add(meatNourishment);
        }
    }

    public void Display(PApplet w) {

        gTicks++;
        if (gTicks > 60) {
            gTicks = 1;
        }

        for (int i = 0; i < gPopulation.GetMaxPop(); i++) {
            Creature creature = gPopulation.GetCreature(i);

            if (!creature.GetVitals().IsAlive()) {
                // Dead creatures should not move or take actions, just display them as dead
                creature.Display(w, 1.0f);
                continue;
            }

            creature.CreatureAction(gTicks);
            creature.Display(w, 1.0f);

            // if (gTicks%2==0){gCreatureStatsWindow.Update(creature);}
            // if (gTicks%2==0){gCreatureGeneWindow.Update(creature);}
            // if (gTicks%2==0 &&
            // FlagsOverride.ShowCreatureDisplayWindow){gCreatureVisionWindow.Update(creature);}
        }

        for (Nourishment nourishment : gNourishment) {
            nourishment.DisplayNourishment(w, 1.0f);
        }

    }

    // Determine true home for this.
    public ArrayList<ObjectInRange> ObjectsInRange(float X, float Y, float RangeRadius, UUID uuid) {
        ArrayList<ObjectInRange> oirs = new ArrayList<>();

        // Check if any creatures are in the range. For now we use the head location.
        // TODO: Create a box around the creature to represent it for more realistic and
        // faster calculations later.
        for (int i = 0; i < gPopulation.GetMaxPop(); i++) {
            Creature creature = gPopulation.GetCreature(i);
            if (creature.GetUUID() != uuid) {
                CreatureBody creatureBody = creature.GetBody();
                BodySegment segment = creatureBody.GetHeadSegment();
                float distance = gUtils.DistanceBetweenPoints(X, Y, segment.GetSegmentX(), segment.GetSegmentY());
                ObjectInRange oir = new ObjectInRange(X, Y, distance, ObjectInRangeType.Creature, i, 0);
                oirs.add(oir);
            }
        }

        // Check if any nourishment are in the range. We will use the plants circle
        // (radius) for calculation.
        for (int i = 0; i < gNourishment.size(); i++) {
            Nourishment nourishment = gNourishment.get(i);
            float distance = gUtils.DistanceBetweenPoints(X, Y, nourishment.GetNourishmentX(),
                    nourishment.GetNourishmentY());
            if (distance <= RangeRadius) {
                ObjectInRangeType ot = ObjectInRangeType.Plant;
                if (nourishment.NourishmentType() == NourishmentTypes.Plant
                        || nourishment.NourishmentType() == NourishmentTypes.Meat) {
                    if (nourishment.NourishmentType() == NourishmentTypes.Plant) {
                        ot = ObjectInRangeType.Plant;
                    } else {
                        ot = ObjectInRangeType.Meat;
                    }
                    oirs.add(new ObjectInRange(nourishment.GetNourishmentX(), nourishment.GetNourishmentY(), distance,
                            ot, i, 0));
                }
                if (nourishment.NourishmentType() == NourishmentTypes.Plant
                        || nourishment.NourishmentType() == NourishmentTypes.Meat) {
                    if (nourishment.NourishmentType() == NourishmentTypes.Plant) {
                        ot = ObjectInRangeType.PlantScent;
                    } else {
                        ot = ObjectInRangeType.MeatScent;
                    }
                    oirs.add(new ObjectInRange(nourishment.GetNourishmentX(), nourishment.GetNourishmentY(), distance,
                            ot, i, nourishment.GetNourishmentScentStrength()));
                }
            }
        }

        return oirs;
    }

}