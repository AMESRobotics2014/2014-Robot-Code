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
    }
    
    /*  public void Init() {
        actindex = new accessdata[20];
        addEventTimer("TurboTimer");
        addEventTimer("DEBUGprints");
    }*/

    public void addEventTimer(String tid) {
        actindex.addElement(new accessdata(tid));
    }

    public void listIndicesDEBUG() {
        int i;
        System.out.println("Test" + actindex.size());
        for (i = 0; i < actindex.size(); i++) {
           use = (accessdata)actindex.elementAt(i);
            System.out.println("Name: " + use.id + " at index location:[" + i + "]");
        }
    }
    
    public double gdt(int loc){
        use = (accessdata)actindex.elementAt(loc);
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
            id = tid;
            ot = get();
            dt = -10;//Just Cause 2
        }

        public double gdt() {
            dt = get() - this.ot;
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