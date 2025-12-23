package main.Creature;

import main.GameParameters;
import main.FlagsOverride;
import main.Creature.BodySegments.*;
import java.awt.*;
import java.util.ArrayList;

public class CreatureBody {

    private final CreatureGeneValues CurrentGeneValues;
    private final CreatureVitals Vitals;
    private final ArrayList<BodySegment> Body;
    private int HeadSegmentID;
    private int MouthSegmentID;
    private int EyesSegmentID;
    private int FlippersSegmentID;
    private int TailSegmentID;
    private float Mass;

    // CurrentGeneValues parameter variables
    private final float HeadShape;
    private final boolean MouthPresent;
    private boolean FlipperPresent;
    private final boolean TailPresent;

    private boolean EyesPresent;

    // create currentGeneValues parameter
    public CreatureBody(Creature currentCreature) {
        CurrentGeneValues = currentCreature.GetGenes();
        Vitals = currentCreature.GetVitals();

        // class variables
        Body = new ArrayList<>();

        HeadSegmentID = 0;
        MouthSegmentID = -1;
        EyesSegmentID = -1;
        FlippersSegmentID = -1;
        TailSegmentID = -1;

        // get gene values
        HeadShape = CurrentGeneValues.GetHeadShape();

        MouthPresent = isMouthPresent();
        if (FlagsOverride.MouthPresentOverride) {
            MouthPresent = true;
        }
        FlipperPresent = isFlipperPresent();
        if (FlagsOverride.FlipperPresentOverride) {
            FlipperPresent = true;
        }
        TailPresent = isTailPresent();
        if (FlagsOverride.TailPresentOverride) {
            TailPresent = true;
        }
        EyesPresent = areEyesPresent();
        if (FlagsOverride.EyesPresentOverride) {
            EyesPresent = true;
        }

    }

    public void CreateBody(float x, float y) {

        initHeadOnBody(x, y);

        System.out.println("CreatureBody.CreateBody - Added head to body - Body Length: " + GetBodyLength());

        initSegementsOnBody(x, y);

        if (MouthPresent) {
            initMouthOnBody(x, y);
        }
        if (EyesPresent) {
            initEyesOnBody(x, y);
        }
        if (FlipperPresent) {
            initFlippersOnBody(x, y);
        }
        if (TailPresent) {
            initTailOnBody(x, y);
        }

        Mass = CalculateBodyMass();
        System.out.println("CreatureBody.CreateBody - CalculateBodyMass: " + CalculateBodyMass());
    }

    public void UpdateBody() {

        BodySegment bodySegment = GetBodySegment(0);

        setHeadOnBody(bodySegment);
        setSegmentsOnBody(bodySegment);

        if (GetMouthPresent() && GetBodySegment(MouthSegmentID).BodySegmentType() == SegmentID.Mouth) {
            setMouthOnBody(GetBodySegment(MouthSegmentID));
        }
        if (GetEyesPresent() && GetBodySegment(EyesSegmentID).BodySegmentType() == SegmentID.Eyes) {
            setEyesOnBody(GetBodySegment(EyesSegmentID));
        }
        if (GetFlipperPresent() && GetBodySegment(FlippersSegmentID).BodySegmentType() == SegmentID.Flippers) {
            setFlippersOnBody(GetBodySegment(FlippersSegmentID));
        }
        if (GetTailPresent() && GetBodySegment(TailSegmentID).BodySegmentType() == SegmentID.Tail) {
            setTailOnBody(GetBodySegment(TailSegmentID));
        }

        // float finalRadius=0;
        // float r1=currentGeneValues.GetBodyHeight()*gr;
        // float w1=currentGeneValues.GetBodyWidth()*gr;
        // for (int i=0; i< currentGeneValues.GetBodyLen(); i++){
        // float r=r1 - i*(r1/(currentGeneValues.GetBodyLen()-1));
        // float w=w1 - i*(w1/(currentGeneValues.GetBodyLen()-1));
        // //+((w1/(currentGeneValues.GetBodyLen()-1)*(currentGeneValues.GetBodyTaper())));
        // finalRadius=r;
        // //Segment(x, y, angle, distance, radius and hue
        // Segment s=body.get(i);
        // s.segmentWidth=w;
        // if (Pregnent() && i==2){
        // finalRadius=r+(currentGeneValues.GetGestationPeriod()-gestationCountdown);
        // }
        // if (!Pregnent() && birthCooldown>0 && (i==2)){
        // float nr=r + (s.segmentHeight-r)/2;
        // if (nr<r){
        // finalRadius=r;
        // } else{
        // finalRadius=nr;
        // }
        // }
        // if (!Pregnent() && birthCooldown==0){
        // finalRadius=r;
        // }
        //
        // s.segmentHeight=finalRadius;
        //
        // s.segmentDistance=r;
        // s.segmentColor=DetermineBodyColor(i);
        // if (i==currentGeneValues.GetBodyLen()-1 && (GetTailPresent() ||
        // gTailPresentFlag)){
        // s.segmentHeight=GetTailHeight();
        // s.segmentWidth=GetTailWidth();
        // s.segmentDistance=GetTailWidth();
        // s.segmentColor=DetermineBodyColor(i);
        // }
        // body.set(i,s);
        // }
    }

    private void initHeadOnBody(float x, float y) {
        Head head = new Head();
        head.InitializeSegment(x, y, GetCurrentBodyWidth(), GetCurrentBodyHeight(), 0,
                GetCurrentBodyDistanceBetweenSegments(), GetCurrentHeadColor());
        head.SetHeadShape(HeadShape);
        Body.add(head);
    }

    private void setHeadOnBody(BodySegment headSegment) {

        headSegment.SetSegmentWidth(GetCurrentBodyWidth());
        headSegment.SetSegmentHeight(GetCurrentBodyHeight());
        Body.set(0, headSegment);
    }

    private void initSegementsOnBody(float x, float y) {
        float HeightOfCurrentSegment = 0;
        for (int i = 1; i < GetBodyLength() - 1; i++) {
            HeightOfCurrentSegment = HeightOfCurrentSegment - i * (HeightOfCurrentSegment / (GetBodyLength() - 1));
            Segment segment = new Segment();
            segment.InitializeSegment(x - i * HeightOfCurrentSegment, y,
                    GetCurrentBodyWidth() - DetermineTaper(GetCurrentBodyWidth(), i, GetBodyLength()),
                    GetCurrentBodyHeight() - DetermineTaper(GetCurrentBodyWidth(), i, GetBodyLength()), 0,
                    GetCurrentBodyDistanceBetweenSegments(), GetCurrentSegmentsColor());
            segment.SetSegmentConnectedTo(i - 1);
            Body.add(segment);
        }
    }

    private void setSegmentsOnBody(BodySegment bodySegment) {
        for (int i = 1; i < GetBodyLength() - 1; i++) {
            bodySegment = GetBodySegment(i);
            bodySegment.SetSegmentDistance(GetCurrentBodyDistanceBetweenSegments());
            bodySegment
                    .SetSegmentWidth(GetCurrentBodyWidth() - DetermineTaper(GetCurrentBodyWidth(), i, GetBodyLength()));
            bodySegment.SetSegmentHeight(
                    GetCurrentBodyHeight() - DetermineTaper(GetCurrentBodyWidth(), i, GetBodyLength()));
            bodySegment.SetSegmentHeight(GetCurrentBodyHeight());
            Body.set(i, bodySegment);
        }
    }

    private void initMouthOnBody(float x, float y) {
        Mouth mouth = new Mouth();
        mouth.SetMouthSize(GetCurrentMouthSize());
        mouth.SetBiteStrength(GetCurrentBiteStrength());
        mouth.SetSegmentConnectedTo(GameParameters.MouthSegmentConnected);
        mouth.SetMouthColor(GetCurrentMouthColor());
        Body.add(mouth);
        MouthSegmentID = Body.size() - 1;
    }

    private void setMouthOnBody(BodySegment mouthSegment) {
        Mouth mouth = (Mouth) GetBodySegment(MouthSegmentID);
        mouth.SetMouthSize(GetCurrentMouthSize());
        mouth.SetBiteStrength(GetCurrentBiteStrength());
        mouth.UpdateSegment(GetBodySegment(mouth.GetSegmentConnectedTo()));
        Body.set(MouthSegmentID, mouth);
    }

    private void initEyesOnBody(float x, float y) {
        Eyes eyes = new Eyes();
        eyes.SetEyeSize(GetCurrentEyeSize());
        eyes.SetEyeColor(CurrentGeneValues.GetEyeColor());
        eyes.SetSegmentConnectedTo(GameParameters.EyesSegmentConnected);
        Body.add(eyes);
        EyesSegmentID = Body.size() - 1;
    }

    private void setEyesOnBody(BodySegment eyesSegment) {
        Eyes eyes = (Eyes) GetBodySegment(EyesSegmentID);
        eyes.SetEyeSize(GetCurrentEyeSize());
        eyes.SetEyeColor(CurrentGeneValues.GetEyeColor());
        eyes.UpdateSegment(GetBodySegment(eyes.GetSegmentConnectedTo()));
        Body.set(EyesSegmentID, eyes);
    }

    private void initFlippersOnBody(float x, float y) {
        Flippers flippers = new Flippers();
        BodySegment connectedSegment = Body.get(GameParameters.FlippersSegmentConnected);
        flippers.InitializeSegment(connectedSegment.GetSegmentX(), connectedSegment.GetSegmentY(),
                connectedSegment.GetSegmentWidth(), connectedSegment.GetSegmentHeight(),
                connectedSegment.GetSegmentAngle(), GetCurrentBodyDistanceBetweenSegments(), GetCurrentFlipperColor());
        flippers.SetFlipperHeight(GetCurrentFlipperHeight());
        flippers.SetFlipperWidth(GetCurrentFlipperWidth());
        flippers.SetSegmentConnectedTo(GameParameters.FlippersSegmentConnected);
        Body.add(flippers);
        FlippersSegmentID = Body.size() - 1;
    }

    private void setFlippersOnBody(BodySegment flippersSegment) {
        Flippers flippers = (Flippers) GetBodySegment(FlippersSegmentID);
        BodySegment connectedSegment = Body.get(flippers.GetSegmentConnectedTo());
        flippers.InitializeSegment(connectedSegment.GetSegmentX(), connectedSegment.GetSegmentY(),
                connectedSegment.GetSegmentWidth(), connectedSegment.GetSegmentHeight(),
                connectedSegment.GetSegmentAngle(), GetCurrentBodyDistanceBetweenSegments(),
                GetCurrentFlipperColor());
        flippers.SetFlipperHeight(GetCurrentFlipperHeight());
        flippers.SetFlipperWidth(GetCurrentFlipperWidth());
        flippers.UpdateSegment(GetBodySegment(flippers.GetSegmentConnectedTo()));
        Body.set(FlippersSegmentID, flippers);
    }

    private void initTailOnBody(float x, float y) {
        Tail tail = new Tail();
        tail.InitializeSegment(x - (GetBodyLength() - GameParameters.TailSegmentOffsetFromEnd),
                y,
                GetCurrentBodyWidth() - DetermineTaper(GetCurrentBodyWidth(),
                        GetBodyLength() - 2 - GameParameters.TailSegmentOffsetFromEnd, GetBodyLength()),
                GetCurrentBodyHeight() - DetermineTaper(GetCurrentBodyWidth(),
                        GetBodyLength() - 2 - GameParameters.TailSegmentOffsetFromEnd, GetBodyLength()),
                0, GetCurrentBodyDistanceBetweenSegments(),
                GetCurrentTailColor());
        tail.SetTailWidth(GetCurrentTailWidth());
        tail.SetTailHeight(GetCurrentTailHeight());
        tail.SetSegmentConnectedTo(GetBodyLength() - 2 - GameParameters.TailSegmentOffsetFromEnd);
        Body.add(tail);
        TailSegmentID = Body.size() - 1;
    }

    private void setTailOnBody(BodySegment tailSegment) {
        Tail tail = (Tail) GetBodySegment(TailSegmentID);
        tail.SetSegmentDistance(GetCurrentBodyDistanceBetweenSegments());
        tail.SetSegmentAngle(GetBodySegment(tail.GetSegmentConnectedTo()).GetSegmentAngle());
        tail.SetTailHeight(GetCurrentTailHeight());// GetCurrentTailHeight());
        tail.SetTailWidth(GetCurrentTailWidth()); // GetCurrentTailWidth());
        tailSegment.UpdateSegment(GetBodySegment(tail.GetSegmentConnectedTo()));
        Body.set(TailSegmentID, tail);
    }

    public boolean isMouthPresent() {
        return (CurrentGeneValues.GetBiteStrength() >= GameParameters.MouthPresentThreshold);
    }

    public boolean isFlipperPresent() {
        return (CurrentGeneValues.GetFlipperPresent() >= GameParameters.FlipperPresentThreshold);
    }

    public boolean isTailPresent() {
        return (CurrentGeneValues.GetTailPresent() >= GameParameters.TailPresentThreshold);
    }

    public boolean areEyesPresent() {
        return (CurrentGeneValues.GetEyesPresent() > GameParameters.EyesPresentThreshold);
    }

    public BodySegment GetHeadSegment() {
        return Body.get(HeadSegmentID);
    }

    public void SetHeadSegment(BodySegment segment) {
        Body.set(HeadSegmentID, segment);
    }

    public BodySegment GetMouthSegment() {
        if (MouthSegmentID == -1)
            return null;
        else
            return Body.get(MouthSegmentID);
    }

    public void SetMouthSegment(BodySegment segment) {
        Body.set(MouthSegmentID, segment);
    }

    public BodySegment GetEyesSegment() {
        if (EyesSegmentID == -1)
            return null;
        else
            return Body.get(EyesSegmentID);
    }

    public void SetEyesSegment(BodySegment segment) {
        Body.set(EyesSegmentID, segment);
    }

    public BodySegment GetFlippersSegment() {
        if (FlippersSegmentID == -1)
            return null;
        else
            return Body.get(FlippersSegmentID);
    }

    public void SetFlippersSegment(BodySegment segment) {
        Body.set(FlippersSegmentID, segment);
    }

    public BodySegment GetTailSegment() {
        if (TailSegmentID == -1)
            return null;
        else
            return Body.get(TailSegmentID);
    }

    public void SetTailSegment(BodySegment segment) {
        Body.set(TailSegmentID, segment);
    }

    public BodySegment GetBodySegment(int value) {
        return Body.get(value);
    }

    public void SetBodySegment(int value, BodySegment segment) {
        Body.set(value, segment);
    }

    public float GetBodyMass() {
        return Mass;
    }

    public float GetCurrentBodyMass() {
        return Mass * Vitals.GetMaturity();
    }

    public float CalculateBodyMass() {
        Mass = 0;
        for (BodySegment bs : Body) {
            SegmentID si = bs.BodySegmentType();
            if (si == SegmentID.Head || si == SegmentID.Segment) {
                Mass += bs.GetSegmentWidth() * bs.GetSegmentHeight();
            }
        }
        return Mass;
    }

    float DetermineTaper(float width, float count, float length) {
        return count * (width / length);
    }

    public float GetCurrentBodyDistanceBetweenSegments() {
        return CurrentGeneValues.GetBodyDistanceBetweenSegments() * Vitals.GetMaturity();
    }

    public int GetBodyLength() {
        return (int) Math.floor(CurrentGeneValues.GetBodyLength());
    }

    public int GetTotalBodySegmentLength() {
        return Body.size();
    }

    public float GetCurrentBodyHeight() {
        return CurrentGeneValues.GetBodyHeight() * Vitals.GetMaturity();
    }

    // public void SetCurrentBodyHeight(float value){CurrentBodyHeight=value;}
    public float GetCurrentBodyWidth() {
        return CurrentGeneValues.GetBodyWidth() * Vitals.GetMaturity();
    }

    // public void SetCurrentBodyWidth(float value){CurrentBodyWidth=value;}
    public float GetCurrentFlipperHeight() {
        return CurrentGeneValues.GetFlipperHeight() * Vitals.GetMaturity();
    }

    // public void SetCurrentFlipperHeight(float value){CurrentFlipperHeight=value;}
    public float GetCurrentFlipperWidth() {
        return CurrentGeneValues.GetFlipperWidth() * Vitals.GetMaturity();
    }

    // public void SetCurrentFlipperWidth(float value){CurrentFlipperWidth=value;}
    public float GetCurrentTailHeight() {
        return ((CurrentGeneValues.GetTailHeightPercentage() * CurrentGeneValues.GetBodyHeight()))
                * Vitals.GetMaturity();
    }

    // public void SetCurrentTailHeight(float value){CurrentTailHeight=value;}
    public float GetCurrentTailWidth() {
        return ((CurrentGeneValues.GetTailWidthPercentage() * CurrentGeneValues.GetBodyWidth())) * Vitals.GetMaturity();
    }

    // public void SetCurrentTailWidth(float value){CurrentTailWidth=value;}
    public float GetCurrentMouthSize() {
        return CurrentGeneValues.GetMouthSize() * Vitals.GetMaturity();
    }

    public float GetCurrentEyeSize() {
        return CurrentGeneValues.GetEyeSize() * Vitals.GetMaturity();
    }

    public Color GetCurrentHeadColor() {
        return CurrentGeneValues.GetBodyColor();
    }

    public Color GetCurrentSegmentsColor() {
        return CurrentGeneValues.GetBodyColor();
    }

    public Color GetCurrentMouthColor() {
        return CurrentGeneValues.GetMouthColor();
    }

    public Color GetCurrentEyesColor() {
        return CurrentGeneValues.GetEyeColor();
    }

    public Color GetCurrentFlipperColor() {
        return CurrentGeneValues.GetFlipperColor();
        // return gUtils.ChangeColorByPercentage(new
        // Color(CurrentGeneValues.GetBodyColorRGB().get(0),CurrentGeneValues.GetBodyColorRGB().get(1),CurrentGeneValues.GetBodyColorRGB().get(2)),CurrentGeneValues.GetFlipperColorDeviation());
    }

    public Color GetCurrentTailColor() {
        return CurrentGeneValues.GetTailColor();
    }

    public boolean GetMouthPresent() {
        return MouthPresent;
    }

    public boolean GetEyesPresent() {
        return EyesPresent;
    }

    public boolean GetFlipperPresent() {
        return FlipperPresent;
    }

    public boolean GetTailPresent() {
        return TailPresent;
    }

    public float GetCurrentBiteStrength() {
        return CurrentGeneValues.GetBiteStrength() * Vitals.GetMaturity();
    }

    public float GetCurrentTailWidthPercentage() {
        return CurrentGeneValues.GetTailWidthPercentage() * Vitals.GetMaturity();
    }
}