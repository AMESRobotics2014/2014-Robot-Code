package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is responsible for all communication with other devices such as the Raspberry pie, the driver's station, some 
 * cameras, and anything else external.
 * @author Alex, Evan, Devon(raspie), Collin, Erin
 */
//=================================================================================================================
public class Communication {
    NetworkTable infoTable;
    MasterTimer MT;
    double distance, angle;
    boolean hot;
    
    public void init() {
        MT = new MasterTimer();
        MT.Init();
        
        //infoTable.setClientMode();
        //infoTable.setIPAddress("10.32.43.2");
        infoTable = NetworkTable.getTable("default");
    }
    
    public void processTable() {
        //while ((true) && (MT.gdt(0) <= 1)) {
        distance = infoTable.getNumber("Distance", 0.0);
        hot = infoTable.getBoolean("Hot", false);
        angle = infoTable.getNumber("Angle", 0.0);
            
        System.out.println("Distance: " + distance + "Hot or Not: " + hot + "Angle: " + angle);
        //}
    }
    public boolean ConfirmShot(){
        //To-do
        return(true);
    }
    //Creates a new void to access the robot speed which is located in the main class getPure access int. Then it will project the speed on a moniter using the Dashboard.
    public void RobotSpeed(double speed){
         String RSpeed = Double.toString(speed);
         
         if (!RSpeed.equals(" ")){
             String Speed = "Robot Speed: ";
             SmartDashboard.putString(Speed, RSpeed);
                   }
    }
    
        public void LimTE(boolean value){
            String state;
         if(value){
             state = "Free";
         }else{state = "Triggered";}
         
         if (!state.equals("")){
             String prefix = "TopElevator:";
             SmartDashboard.putString(prefix, state);
                   }
    }
                public void LimLE(boolean value){
            String state;
         if(value){
             state = "Free";
         }else{state = "Triggered";}
         
         if (!state.equals("")){
             String prefix = "LowerElevator:";
             SmartDashboard.putString(prefix, state);
                   }
    }
         public void LimPB(boolean value){
            String state;
         if(value){
             state = "Free";
         }else{state = "Triggered";}
         
         if (!state.equals("")){
             String prefix = "Pullback:";
             SmartDashboard.putString(prefix, state);
                   }
    }
         public void LimGL(boolean value){
            String state;
         if(value){
             state = "Free";
         }else{state = "Triggered";}
         
         if (!state.equals("")){
             String prefix = "GrabberLowered:";
             SmartDashboard.putString(prefix, state);
                   }
    }
         public void LimEL(boolean value){
            String state;
         if(value){
             state = "Free";
         }else{state = "Triggered";}
         
         if (!state.equals("")){
             String prefix = "Clutch Limit:";
             SmartDashboard.putString(prefix, state);
                   }
    }
         public void LimLL(boolean value){
            String state;
         if(value){
             state = "Free";
         }else{state = "Triggered";}
         
         if (!state.equals("")){
             String prefix = "Grabberlift:";
             SmartDashboard.putString(prefix, state);
                   }
    }
         public void LimRL(boolean value){
            String state;
         if(value){
             state = "Free";
         }else{state = "Triggered";}
         
         if (!state.equals("")){
             String prefix = "ClutchRelease:";
             SmartDashboard.putString(prefix, state);
                   }
    }
         public void LimRaL(boolean value){
            String state;
         if(value){
             state = "Free";
         }else{state = "Triggered";}
         
         if (!state.equals("")){
             String prefix = "RatchetUpLimit:";
             SmartDashboard.putString(prefix, state);
                   }
    }
         public void LimDL(boolean value){
            String state;
         if(value){
             state = "Free";
         }else{state = "Triggered";}
         
         if (!state.equals("")){
             String prefix = "Ratchet Down:";
             SmartDashboard.putString(prefix, state);
                   }
    }
 //=================================================================================================================
    //Creates new viod to access the Robot angle and then like the other voids it prints it to the Dashboard.
    public void ElevPot(double UNKNOWN){
        String RAngle = Double.toString(UNKNOWN);
        
        if(!RAngle.equals(" ")){
            String Angle = "Potentiometer: ";
            SmartDashboard.putString(Angle, RAngle);
        }
    }
//==================================================================================================================
    /*
     * all of the errors will check using an if then statement where if something is wrong with the Robot then it will
     * check witch error it is and then print whatever error that malfunction is defined under
     */
    public void ErrorOne(String EOne){
        
        if(!EOne.equals(" ")){
            SmartDashboard.putString("Error: ", EOne);
        }
    }
    //================================================================================================================
    // This class is acessing .equals and then using an if else statement
    public void ErrorTwo(String ETwo){
        
        if(!ETwo.equals(ETwo)){
            SmartDashboard.putString("Error: ", ETwo);
        }
    }
    //======================================================================================================================
    public void ErrorThree(String EThree){
        
        if(!EThree.equals(" ")){
            SmartDashboard.putString("Error: ", EThree);
        }
    }
    //===================================================================================================================
        public void ErrorFour(String EFour){

            if(!EFour.equals(" ")){
                SmartDashboard.putString("Error: ", EFour);
            }
        }
//===============================================================================
        public void ErrorFive(String EFive){

            if(!EFive.equals(" ")){
                SmartDashboard.putString("Error: ", EFive);
        }
        }
//===============================================================================
        public void ErrorSix(String ESix){
            
            if(ESix.equals(" ")){
                SmartDashboard.putString("Error: ", ESix);
            }
        }
//=====================================================================================================
        public void ErrorSeven(String ESeven){
            
            if(ESeven.equals(" ")){
                SmartDashboard.putString("Error: ", ESeven);
            }
        }
        
        /*
        public void run() {
            NetworkTable.setClientMode();
            NetworkTable.setIPAddress("10.32.43.2");
            NetworkTable table = NetworkTable.getTable("Table");
            
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(NetworkTablesDesktopClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                double dist = table.getNumber("Dist", 0.0);
                System.out.println("Dist: " + dist);
            }
        }
        */
}
