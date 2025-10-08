package main;

import main.Creature.Creature;
import main.Creature.CreatureGeneValues;
import main.Creature.ObjectInRange;
import main.Genetics.GeneBase;
import main.Genetics.GeneID;
import main.Genetics.Genome;

import javax.swing.*;
import java.util.ArrayList;

public class CreatureVisionWindow {
    static JTextPane Stats;
    static JTextPane Stats1;
    public CreatureVisionWindow(){
        //Creating the Frame
        JFrame frame = new JFrame("Creature Vision Info");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 1000);

        Stats = new JTextPane();
        Stats.setSize(200,1000);

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
    private String AddField(ObjectInRange object,int offset){
        String tab = "    ";
        return tab.repeat(Math.max(0,offset)) + object.ObjectTypeInRange() + " : " + object.IdOfObject() + "\r\n";
    }
    private String AddSection(String Name, int offset){
        String tab = "    ";
        return tab.repeat(Math.max(0, offset)) +
                Name + "\r\n";
    }
    public void Update(Creature CurrentCreature) {
        ArrayList<ObjectInRange> ObjectsInRangeList =CurrentCreature.GetObjectsInRange(CurrentCreature.GetUUID());
        StringBuilder os= new StringBuilder(AddSection("Objects in Range:", 0));
        for(ObjectInRange object: ObjectsInRangeList){
            os.append(AddField(object, 1));
        }
        StringBuilder scs= new StringBuilder(AddSection("Scent Objects in Range:", 0));
        for(ObjectInRange object: CurrentCreature.GetObjectsScentInRange(ObjectsInRangeList)){
            scs.append(AddField(object, 1));
        }
        StringBuilder ss= new StringBuilder(AddSection("Seen Objects in Range:", 0));
        ArrayList<ObjectInRange> seen= CurrentCreature.GetObjectsSeenInRange(ObjectsInRangeList);
        for(ObjectInRange object: seen){
            ss.append(AddField(object, 1));
        }
        String value=os.append(scs).append(ss).toString();
        Stats.setText(value);
        //Stats1.setText();
    }
}
