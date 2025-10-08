package main.Creature;

import main.Actions;
import main.Nourishments.Nourishment;

import java.util.ArrayList;

import static main.Main.gWorld;

public class CreatureDecisionEngine{
    private final Creature CurrentCreature;
    private final CreaturePhysics Physics;
    private final CreatureBody Body;
    private final CreatureVitals Vitals;
    private ObjectInRange PlantInRange;
    private ObjectInRange MeatInRange;
    private ObjectInRange CreatureInRange;
    private ObjectInRange PlantScentInRange;
    private ObjectInRange MeatScentInRange;
    private ObjectInRange CreatureScentInRange;
    private ArrayList<ObjectInRange> AllObjectsInRange;
    private ArrayList<ObjectInRange> ScentObjectsInRange;
    private ArrayList<ObjectInRange> SeenObjectsInRange;

    public CreatureDecisionEngine(Creature currentCreature) {
        CurrentCreature=currentCreature;
        Physics=CurrentCreature.GetPhysics();
        Body=CurrentCreature.GetBody();
        Vitals=CurrentCreature.GetVitals();
    }

    public void SetObjectListInRange(ArrayList<ObjectInRange> allObjectsInRange){
        AllObjectsInRange=allObjectsInRange;
    }
    public void SetScentObjectListInRange(ArrayList<ObjectInRange> scentObjectsInRange){
        ScentObjectsInRange=scentObjectsInRange;
    }
    public void SetSeenObjectListInRange(ArrayList<ObjectInRange> seenObjectsInRange){
        SeenObjectsInRange=seenObjectsInRange;
    }
    public void SetObjectInRangeBySpecifiedType(ObjectInRange objectInRange, ObjectInRangeType objectInRangeType){
        switch (objectInRangeType){
            case Plant:
                PlantInRange=objectInRange;
                break;
            case Meat:
                MeatInRange=objectInRange;
                break;
            case Creature:
                CreatureInRange=objectInRange;
                break;
            case PlantScent:
                PlantScentInRange=objectInRange;
                break;
            case MeatScent:
                MeatScentInRange=objectInRange;
                break;
            case CreatureScent:
                CreatureScentInRange=objectInRange;
                break;
        }
    }
    private void ClearInRange(){
        PlantInRange=null;
        MeatInRange=null;
        CreatureInRange=null;
        PlantScentInRange=null;
        MeatScentInRange=null;
        CreatureScentInRange=null;
        AllObjectsInRange=new ArrayList<>();
        ScentObjectsInRange=new ArrayList<>();
        SeenObjectsInRange=new ArrayList<>();
    }

    public Actions Decision(Actions previous){
        float previousDistanceToTarget=CurrentCreature.GetPreviousDistanceToTarget();
        float distanceToTarget=CurrentCreature.GetDistanceToTarget();
        boolean VisionPresent=Body.GetEyesPresent(); //TODO: && GeneID.VisionDominance>GeneID.ScentDominance - Done this way to we can add a gene to prioritize which sense should be used
        boolean ScentPresent=false; //TODO:  GeneID.ScentDominance>GeneID.VisionDominance - Done this way so we can add gene to prioritize which sense should be used

        if (CurrentCreature.GetTargetObject()==null){
            return Actions.NewDestination;
        }

        if (previous==Actions.Eat && gWorld.gNourishment.get(CurrentCreature.GetTargetObject().IdOfObject()).GetNourishmentMass()==0){
            return Actions.Move;
        }

        if (Vitals.IsHungry() && previous==Actions.Eat){
            return Actions.Eat;
        }

        boolean mouthAtPoint = Physics.MouthAtPoint(CurrentCreature.GetTargetObject().X(),CurrentCreature.GetTargetObject().Y(),Body.GetCurrentBodyHeight());
        if (Vitals.IsHungry() && previous!=Actions.MoveToPlant) {
            if (VisionPresent) {
                if (MeatInRange != null) {
                    return Actions.TargetMeat;
                }
                if (PlantInRange != null) {
                    return Actions.TargetPlant;
                }
            }
            if (ScentPresent) {
                if (MeatScentInRange != null) {
                    return Actions.TargetMeatScent;
                }
                if (PlantScentInRange != null) {
                    return Actions.TargetPlantScent;
                }
            }
        }

        if (previous==Actions.MoveToPlant && mouthAtPoint){
            return Actions.Eat;
        }

        if ((previous==Actions.Move) && mouthAtPoint){
            return Actions.NewDestination;
        }
        if (previousDistanceToTarget>distanceToTarget-Physics.GetCurrentSpeed() && previousDistanceToTarget<distanceToTarget){
            //System.out.println("Distance Equal");
            return Actions.NewDestination;
        }

        ClearInRange();
        return Actions.Move;
    }
}
