package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;
import java.util.Vector;

public class MasterTimer extends Timer {

    Vector actindex;
    accessdata use;
    public void MasterTimer() {
        actindex = new Vector();
        addEventTimer("TurboTimer");
        addEventTimer("DEBUGprints");
        addEventTimer("ShiftTimer");
    }
    
      public void Init() {
        actindex = new Vector();
        addEventTimer("TurboTimer");
        addEventTimer("DEBUGprints");
        addEventTimer("ShiftTimer");
    }

    public void addEventTimer(String tid) {
        use = new accessdata(tid);
        use.Init(tid);
        actindex.addElement(use);
    }

    public void listIndicesDEBUG() {
        try{
        int i;
        System.out.println("Test" + actindex.size());
        for (i = 0; i < actindex.size(); i++) {
           use = (accessdata)actindex.elementAt(i);
            System.out.println("Name: " + use.id + " at index location:[" + i + "]");
        }
        }catch(NullPointerException ex){
            System.out.println("This index doesn't exist D:");
        }
    }
    
    public double gdt(int loc){
        System.out.println("Getting gdt at location:" +loc);
        use = (accessdata)actindex.elementAt(loc);
        System.out.println(use.gdt());
        return use.gdt();
    }

    public void Freset() {//Full reset!!!
        this.reset();
        int i;
        for (i = 0; i < actindex.size(); i++) {
            use = (accessdata)actindex.elementAt(i);
            use.wipe();
        }
    }

    public void Ereset(int[] exclude) {//Exclusion reset!!!
        int i;
        for (i = 0; i < actindex.size()-1; i++) {
            use = (accessdata)actindex.elementAt(i);
            for (int k = 0; k < exclude.length; k++) {
                if (i == exclude[k]) {
                    break;
                } else {
                    use.wipe();
                }
            }
        }
    }

    public void Sreset(int[] include) {//Selective reset
        int i;
        for (i = 0; i < include.length; i++) {
            use = (accessdata)actindex.elementAt(i);
            use.wipe();
        }
    }

    public class accessdata {

         double ot;
         double dt;
         String id;

        accessdata(String tid) {
        }
        
        public void Init(String tid){
            id = tid;
            ot = get();
            dt = -10;//Just Cause 2  
        }

        public double gdt() {
            dt = get() - ot;
            ot = get();
            return dt;
        }

        public  void wipe() {
            ot = 0;
            dt = 0;
            id = null;
        }
    }
}