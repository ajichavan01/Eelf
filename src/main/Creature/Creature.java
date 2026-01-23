package main.Creature;

import main.Actions;
import main.Creature.BodySegments.*;
import main.FlagsOverride;
import main.GameParameters;
import main.Genetics.Genome;
import main.Nourishments.Nourishment;
import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

import static main.Main.*;

public class Creature {
    private final CreatureBody Body;
    private final CreaturePhysics Physics;
    private final CreatureGeneValues Genes;
    private final CreatureOlfactory Olfactory;
    private final CreatureVision Vision;
    private final CreatureVitals Vitals;
    private final CreatureDecisionEngine DecisionEngine;
    private final CreatureMetabolism Metabolism;
    private float Speed;
    private float BodyMass;
    private float TurnAngle;
    private final UUID guid;
    private Actions CreatureAction;
    private float DistanceToTarget;
    private float PreviousDistanceToTarget;
    private ObjectInRange TargetObject;
    private ArrayList<ObjectInRange> ObjectsInRange;
    private ArrayList<ObjectInRange> ScentObjectsInRange;
    private ArrayList<ObjectInRange> SeenObjectsInRange;

    public Creature(float startX, float startY, Genome genome, UUID uuid) {

        Genes = new CreatureGeneValues(genome);
        Vitals = new CreatureVitals(this);
        Vitals.InitializeCreatureVitals(startX, startY, 0, 0, 5, uuid, UUID.randomUUID());
        Body = new CreatureBody(this);
        Physics = new CreaturePhysics(this);
        Olfactory = new CreatureOlfactory(this);
        ObjectsInRange = new ArrayList<>();
        Vision = new CreatureVision(this);
        Metabolism = new CreatureMetabolism(this);
        DecisionEngine = new CreatureDecisionEngine(this);

        guid = uuid;
        Physics.SetBaseSpeed(Genes.GetSpeed());
        Speed = Physics.DetermineSpeed(BodyMass);
        Physics.SetBaseTurnRate(.025f);
        TurnAngle = Physics.DetermineTurnRate(Genes.GetFlipperWidth(), Genes.GetBodyWidth());
        Vision.InitializeVision(Genes.GetVisionAngle(), Vitals.GetCurrentVisionDistance(), Genes.GetVisionClarity());
        Body.CreateBody(Vitals.GetX(), Vitals.GetY());
        BodyMass = Body.CalculateBodyMass();
        CreatureAction = Actions.NewDestination;
        DistanceToTarget = 0;
        PreviousDistanceToTarget = 0;
        TargetObject = new ObjectInRange(0, 0, 0, ObjectInRangeType.Location, 0, 0);
        ObjectsInRange = new ArrayList<>();
        ScentObjectsInRange = new ArrayList<>();
        SeenObjectsInRange = new ArrayList<>();
    }

    public UUID GetUUID() {
        return guid;
    }

    public CreatureBody GetBody() {
        return Body;
    }

    public CreatureVitals GetVitals() {
        return Vitals;
    }

    public CreaturePhysics GetPhysics() {
        return Physics;
    }

    public CreatureOlfactory GetOlfactory() {
        return Olfactory;
    }

    public CreatureGeneValues GetGenes() {
        return Genes;
    }

    public CreatureMetabolism GetMetabolism() {
        return Metabolism;
    }

    public CreatureDecisionEngine GetDecisionEngine() {
        return DecisionEngine;
    }

    public CreatureVision GetCreatureVision() {
        return Vision;
    }

    public ObjectInRange GetTargetObject() {
        return TargetObject;
    }

    public void UpdateCreatureLocation() {
        // Loop through each body segment and update location, color and angle
        for (int i = 0; i < Body.GetTotalBodySegmentLength(); i++) {
            BodySegment b = Body.GetBodySegment(i);
            switch (b.BodySegmentType()) {
                case Mouth:
                    Mouth m = (Mouth) b;
                    m.SetMouthSize(Body.GetCurrentMouthSize());
                    m.SetBiteStrength(Body.GetCurrentBiteStrength());
                    m.UpdateSegment(Body.GetBodySegment(b.GetSegmentConnectedTo()));
                    break;
                case Flippers:
                    Flippers f = (Flippers) b;
                    f.SetFlipperWidth(Body.GetCurrentFlipperWidth());
                    f.SetFlipperHeight(Body.GetCurrentFlipperHeight());
                    f.UpdateSegment(Body.GetBodySegment(b.GetSegmentConnectedTo()));
                    break;
                case Tail:
                    Tail t = (Tail) b;
                    t.SetTailHeight(Body.GetCurrentTailHeight());
                    t.SetTailWidth(Body.GetCurrentTailWidth());
                    t.UpdateSegment(Body.GetBodySegment(b.GetSegmentConnectedTo()));
                    break;
                case Head:
                case Eyes:
                case Segment:
                    b.UpdateSegment(Body.GetBodySegment(b.GetSegmentConnectedTo()));
                    break;
            }
        }
    }

    public void MoveTo(float targetX, float targetY) {
        PreviousDistanceToTarget = DistanceToTarget;
        float a = (float) Math.atan2(targetY - Vitals.GetY(), targetX - Vitals.GetX());
        float delta = a - Vitals.GetAngle();
        while (delta < -Math.PI) {
            delta += (float) (2 * Math.PI);
        }
        while (delta > Math.PI) {
            delta -= (float) (2 * Math.PI);
        }
        Vitals.SetAngle(Vitals.GetAngle() + (TurnAngle * delta));
        Vitals.SetX((float) (Vitals.GetX() + Speed * Math.cos(Vitals.GetAngle())));
        Vitals.SetY((float) (Vitals.GetY() + Speed * Math.sin(Vitals.GetAngle())));

        DistanceToTarget = gUtils.DistanceBetweenPoints(Vitals.GetX(), Vitals.GetY(), TargetObject.X(),
                TargetObject.Y());

        // creatureProperties.ReduceEnergyLevel((currentSpeed*gSpeedEnergyMod)/60);
    }

    public float GetDistanceToTarget() {
        return DistanceToTarget;
    }

    public float GetPreviousDistanceToTarget() {
        return PreviousDistanceToTarget;
    }

    private ObjectInRange NewDestination() {
        PreviousDistanceToTarget = 0;
        return new ObjectInRange(gUtils.GetRandomNumber(10, 1190), gUtils.GetRandomNumber(10, 990), 0,
                ObjectInRangeType.Location, 0, 0);
    }

    public Actions GetCurrentAction() {
        return CreatureAction;
    }

    public ArrayList<ObjectInRange> GetObjectsInRange(UUID CurrentUUID) {
        return ObjectsInRange;
    }

    public ArrayList<ObjectInRange> GetObjectsScentInRange(ArrayList<ObjectInRange> objectsInRangeList) {
        return ScentObjectsInRange;
    }

    public ArrayList<ObjectInRange> GetObjectsSeenInRange(ArrayList<ObjectInRange> objectsInRangeList) {
        return SeenObjectsInRange;
    }

    public ObjectInRange GetNearestPlantScent(ArrayList<ObjectInRange> objectsInRangeList) {
        return Olfactory.GetNearestScentSpecified(objectsInRangeList, ObjectInRangeType.PlantScent);
    }

    public ObjectInRange GetNearestMeatScent(ArrayList<ObjectInRange> objectsInRangeList) {
        return Olfactory.GetNearestScentSpecified(objectsInRangeList, ObjectInRangeType.MeatScent);
    }

    public ObjectInRange GetNearestCreatureScent(ArrayList<ObjectInRange> objectsInRangeList) {
        return Olfactory.GetNearestScentSpecified(objectsInRangeList, ObjectInRangeType.CreatureScent);
    }

    public void CreatureAction(float ticks) {


        UpdateCreatureLocation();

        Vision.SetVisionDistance(Vitals.GetCurrentVisionDistance());
        Vision.UpdateLocation(Body.GetHeadSegment());
        Vision.UpdateSightLines();

        // TODO: Reengineer this to be an array with specific objects at specified
        // locations in array to allow for easy additions to senses.

        // Determine Objects in Range.
        ObjectsInRange = gWorld.ObjectsInRange(Vitals.GetX(), Vitals.GetY(), GameParameters.MaxObjectInRangeRadius,
                guid);
        ScentObjectsInRange = Olfactory.FindScents(ObjectsInRange);
        SeenObjectsInRange = Vision.FindObjects(ObjectsInRange);

        // Add different types of objects in range to decision engine.
        DecisionEngine.SetObjectListInRange(ObjectsInRange);
        DecisionEngine.SetScentObjectListInRange(ScentObjectsInRange);
        DecisionEngine.SetSeenObjectListInRange(SeenObjectsInRange);

        // Nearest scent determined for each type. If no scent of that type is in range,
        // null is returned.
        ObjectInRange PlantScent = GetNearestPlantScent(ScentObjectsInRange);
        ObjectInRange MeatScent = GetNearestMeatScent(ScentObjectsInRange);
        ObjectInRange CreatureScent = GetNearestCreatureScent(ScentObjectsInRange);

        // Nearest seen objects determined for each type. If no seen objects of that
        // type is in range, null is returned.
        ObjectInRange Plant = Vision.GetNearestObjectSpecified(SeenObjectsInRange, ObjectInRangeType.Plant);
        ObjectInRange Meat = Vision.GetNearestObjectSpecified(SeenObjectsInRange, ObjectInRangeType.Meat);
        ObjectInRange Creature = Vision.GetNearestObjectSpecified(SeenObjectsInRange, ObjectInRangeType.Creature);

        // Add Nearest objects to DecisionEngine.
        DecisionEngine.SetObjectInRangeBySpecifiedType(PlantScent, ObjectInRangeType.PlantScent);
        DecisionEngine.SetObjectInRangeBySpecifiedType(MeatScent, ObjectInRangeType.MeatScent);
        DecisionEngine.SetObjectInRangeBySpecifiedType(CreatureScent, ObjectInRangeType.CreatureScent);
        DecisionEngine.SetObjectInRangeBySpecifiedType(Plant, ObjectInRangeType.Plant);
        DecisionEngine.SetObjectInRangeBySpecifiedType(Meat, ObjectInRangeType.Meat);
        DecisionEngine.SetObjectInRangeBySpecifiedType(Creature, ObjectInRangeType.Creature);

        Actions PreviousAction = CreatureAction;

        CreatureAction = DecisionEngine.Decision(CreatureAction);

        switch (CreatureAction) {
            case NewDestination:
                TargetObject = NewDestination();
                DistanceToTarget = gUtils.DistanceBetweenPoints(Vitals.GetX(), Vitals.GetY(), TargetObject.X(),
                        TargetObject.Y());
                CreatureAction = Actions.Move;
                break;
            case Move, MoveToPlant:
                Physics.PauseSpeed(false);
                MoveTo(TargetObject.X(), TargetObject.Y());
                break;
            case TargetPlant:
                Nourishment plant = gWorld.gNourishment.get(Plant.IdOfObject());
                TargetObject = Plant;
                CreatureAction = Actions.MoveToPlant;
                break;
            case Eat:
                Physics.PauseSpeed(true);
                Nourishment nourishment = gWorld.gNourishment.get(TargetObject.IdOfObject());
                float amountBit = Metabolism.Bite(nourishment);
                nourishment.SetNourishmentMass(nourishment.GetNourishmentMass() - amountBit);
                nourishment.SetNourishmentSize(nourishment.GetNourishmentMass() / 10);
                gWorld.gNourishment.set(TargetObject.IdOfObject(), nourishment);
                break;
        }

        // TODO: Determine movement distance and pass it to energy cycle
        // Metabolism.SetEnergyUsedBase(0.0f);
        // Metabolism.SetEnergyUsedInGestation(0.0f);
        // Metabolism.SetEnergyUsedInBirth(0.0f);
        // Metabolism.SetEnergyUsedForMovement(0.0f);
        // Metabolism.SetEnergyUsedDuringBirthRecoveryTime(0.0f);
        // Metabolism.EnergyCycle();

        // TODO: Check health of unborn and determine its state (alive/dead)
        // TODO: If unborn is lost clear pregnant flag and start birthRecoveryTime
        Body.GetHeadSegment().SetSegmentX(Vitals.GetX());
        Body.GetHeadSegment().SetSegmentY(Vitals.GetY());
        Body.GetHeadSegment().SetSegmentAngle(Vitals.GetAngle());
        if (ticks == 1) {
            Vitals.IncreaseMaturity();
            Body.UpdateBody();
            // Age the creature every 60 ticks (once per cycle)
            Metabolism.Aging();
        }
        // Update body mass, speed and turn rate based on it growing. Otherwise, use the
        // last
        if (Vitals.GetMaturity() < 1.0f) {
            BodyMass = Body.GetCurrentBodyMass();
            Speed = Physics.DetermineSpeed(Body.GetCurrentBodyMass());
            TurnAngle = Physics.DetermineTurnRate(Body.GetCurrentFlipperWidth(),
                    Body.GetBodySegment(GameParameters.FlippersSegmentConnected).GetSegmentWidth());
            // System.out.println("TurnAngle=" + TurnAngle);
        }

    }

    public void Display(PApplet w, float scale) {
        w.stroke(0);

        if (FlagsOverride.ShowObjectsInRangeFlag) {
            w.fill(new Color(128, 128, 128).hashCode());
            w.circle(Body.GetHeadSegment().GetSegmentX(), Body.GetHeadSegment().GetSegmentY(),
                    GameParameters.MaxObjectInRangeRadius * 2);
        }
        BodySegment b = Body.GetMouthSegment();
        if (b != null && b.BodySegmentType() == SegmentID.Mouth) {
            b.DisplaySegment(w, scale);
        }
        b = Body.GetFlippersSegment();
        if (b != null && b.BodySegmentType() == SegmentID.Flippers) {
            b.DisplaySegment(w, scale);
        }
        for (int i = 0; i < Body.GetBodyLength() - 1; i++) {
            Body.GetBodySegment(i).DisplaySegment(w, scale);
        }
        b = Body.GetEyesSegment();
        if (b != null && b.BodySegmentType() == SegmentID.Eyes) {
            b.DisplaySegment(w, scale);
        }
        b = Body.GetTailSegment();
        if (b != null && b.BodySegmentType() == SegmentID.Tail) {
            b.DisplaySegment(w, scale);
        }

        // Display creature status text
        w.fill(55, 34, 150);
        w.textSize(10);
        String status = Vitals.IsAlive() ? "Alive" : "Dead";
        w.text("Status: " + status, Vitals.GetX() + 20, Vitals.GetY() - 20);
        w.text("Age: " + Vitals.GetAge(), Vitals.GetX() + 20, Vitals.GetY() - 10);
        w.text("Lifespan: " + Vitals.GetLifeSpan(), Vitals.GetX() + 20, Vitals.GetY());
        w.text("Maturity: " + Vitals.GetMaturity(), Vitals.GetX() + 20, Vitals.GetY() + 10);
        w.text("Maturity age: " + Vitals.GetMaturityAge(), Vitals.GetX() + 20, Vitals.GetY() + 55);
        Vision.Display(w, scale);
        w.circle(TargetObject.X(), TargetObject.Y(), 5);
    }
}
