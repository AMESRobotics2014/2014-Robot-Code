package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

public class MasterTimer extends Timer {

    accessdata[] actindex;
    int lstadd;

    public void MasterTimer() {
        actindex = new accessdata[20];
        lstadd = 0;
        addEventTimer("TurboTimer");
        addEventTimer("DEBUGprints");
    }
    
      public void Init() {
        actindex = new accessdata[20];
        lstadd = 0;
        addEventTimer("TurboTimer");
        addEventTimer("DEBUGprints");
    }

    public void addEventTimer(String tid) {
        System.out.println(lstadd);
        actindex[lstadd] = new accessdata(tid);
       lstadd++;
    }

    public void listIndicesDEBUG() {
        int i;
        System.out.println("Test" + actindex.length);
        for (i = 0; i < actindex.length - 1; i++) {
            System.out.println("Name: " + actindex[i].id + " at index location:[" + i + "]");
        }
    }

    public void Freset() {//Full reset!!!
        this.reset();
        int i;
        for (i = 0; i < actindex.length; i++) {
            actindex[i].wipe();
        }
    }

    public void Ereset(int[] exclude) {//Exclusion reset!!!
        int i;
        for (i = 0; i < actindex.length; i++) {
            for (int k = 0; k < exclude.length; k++) {
                if (i == exclude[k]) {
                    break;
                } else {
                    actindex[i].wipe();
                }
            }
        }
    }

    public void Sreset(int[] include) {//Selective reset
        int i;
        for (i = 0; i < include.length; i++) {
            actindex[include[1]].wipe();
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