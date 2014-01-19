package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

class MasterTimer extends Timer {

    accessdata[] actindex;
    int lstadd;

    public void Ktimer() {
        lstadd = 0;
        addEventTimer("TurboTimer");
        addEventTimer("DEBUGprints");
    }

    public void addEventTimer(String tid) {
        actindex[this.lstadd] = new accessdata(tid);
        this.lstadd++;
    }

    public void listIndicesDEBUG() {
        int i;
        for (i = 0; i < actindex.length; i++) {
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
            this.id = tid;
            this.ot = get();
            this.dt = -10;//Just Cause 2
        }

        public double gdt() {
            this.dt = get() - this.ot;
            this.ot = get();
            return this.dt;
        }

        public void wipe() {
            this.ot = 0;
            this.dt = 0;
            this.id = null;
        }
    }
}