package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
/**
 * This class is where all calls to the actual motor hardware should occur. They
 * should be methods callable from the main function.
 *
 * @author kolton.yager
 */
public class MotorControl {

    protected static Victor firstRightMotor;
    protected static Victor secondRightMotor;
    protected static Victor firstLeftMotor;
    protected static Victor secondLeftMotor;
    protected static Victor PullBack, shooterMotor2;
    protected static Relay GrabWheel, grabberMotor;
    protected static Jaguar elevatorMotor;
    protected static Relay low, ratchet, clutch;
    protected static Relay high;
    protected static InputManager IM;
    
    public void init() {
        firstRightMotor = new Victor(RobotMap.firstRightMotor);
        firstLeftMotor = new Victor(RobotMap.firstLeftMotor);
        PullBack = new Victor(RobotMap.shooterMotor2);
        clutch = new Relay(RobotMap.clutch);
        ratchet = new Relay(RobotMap.ratchet);
        ratchet.setDirection(Relay.Direction.kBoth);
        clutch.setDirection(Relay.Direction.kForward);
        grabberMotor = new Relay(RobotMap.grabberMotor);
        GrabWheel = new Relay(RobotMap.densoMotor);
        grabberMotor.setDirection(Relay.Direction.kBoth);
        GrabWheel.setDirection(Relay.Direction.kReverse);
        elevatorMotor = new Jaguar(RobotMap.elevatorMotor);
        high = new Relay(RobotMap.high);

        IM = new InputManager();
    }

    public void drive(double[] mv) {
        firstRightMotor.set(limit(mv[0]));
        firstLeftMotor.set(limit(mv[1]));
    }
    public static double limit(double val) {
        if (val < -1) {
            val = -1;
        }

        if (val > 1) {
            val = 1;
        }

        return val;
    }

    public void pullback(int dir){
        if(dir == 1){
            PullBack.set(-1);
        }else if(dir == 2){
            PullBack.set(0.55);
        }
        else if(dir == 3){
            PullBack.set(0.3);
        }
        else if(dir == 0){
            PullBack.set(0);
        }
        else if(dir == 4){
            PullBack.set(-.2);
        
        } else if (dir == 5) {
            // Time the movement of this - let's say about 1/2 second for now.
            //if (timer < 1/2 second) {
              //  PullBack.set(-1);
            //} else {
              //  PullBack.set(0);
            //}
        } else{
            PullBack.set(0);
        }
    }
    public void clutch(int dir){
        if(dir == 1){
            clutch.set(Relay.Value.kForward);
        } else if(dir == 2){
            clutch.set(Relay.Value.kReverse); // Changed "kOff" to "kReverse".
        }
        else if(dir == 0){
            clutch.set(Relay.Value.kOff);
        }
        else{
            clutch.set(Relay.Value.kOff);
        }
    } 
    public void ratchet(int dir){
        if(dir == 1){
            ratchet.set(Relay.Value.kForward);
        }
        else if(dir==2){
            ratchet.set(Relay.Value.kReverse);
        }
        else if(dir ==0){
            ratchet.set(Relay.Value.kOff);
        }
        else{
            ratchet.set(Relay.Value.kOff);
        }
    }
    
    
        public void grabber(byte dir) {
            //0 Is off, 1 is foreward , 2 is backwards
            
            if(dir == 1){
                    grabberMotor.set(Relay.Value.kForward);
            }
            else if(dir == 2){
                    grabberMotor.set(Relay.Value.kReverse);
                    GrabWheel.set(Relay.Value.kOff);
            }
            else if(dir == 0){
                grabberMotor.set(Relay.Value.kOff);
            }
            else{
                System.out.println("This shouldn't happen fix your grabber call");
                grabberMotor.set(Relay.Value.kOff); 
            }
        }
        public void grabberWheel(byte dir){
            if(dir == 1){
                    GrabWheel.set(Relay.Value.kReverse);
            }
          //  else if(dir == 2){
            //        GrabWheel.set(Relay.Value.kOff);
            //}
            else if(dir == 0){
                GrabWheel.set(Relay.Value.kOff);
            }
            else{
                System.out.println("This shouldn't happen fix your wheel call");
                GrabWheel.set(Relay.Value.kOff); 
            }
        }
        
    public void Elevator(byte dir){
        if(dir == 0){
        //    System.out.println("Elevator 0");
            elevatorMotor.set(0);
        }
        else if(dir == 1){
          //  System.out.println("Elevator forward");
            elevatorMotor.set(1);
        }
        else if(dir == 2){
            //System.out.println("Elevator reverse");
            elevatorMotor.set(-1);
        }
        
    }
    public void transmission(boolean fast){
        if(!fast){
            high.set(Relay.Value.kOff);
        }
        else if (fast){
            high.set(Relay.Value.kForward);
        }
        
    }
    public void manualMode(){
  //  if(IM.SettingsR.getState()){
        
        if(IM.R2.getState()){
            PullBack.set(1);//Jake is silly
        }else{
            PullBack.set(0);
        }
        if(IM.L1.getState()){
            grabberMotor.set(Relay.Value.kForward);
        }else{
            grabberMotor.set(Relay.Value.kOff);
        }
        if(IM.R1.getState()){
            grabberMotor.set(Relay.Value.kReverse);
        }else{
            grabberMotor.set(Relay.Value.kOff);
        }
        if(IM.L2.getState()){
            GrabWheel.set(Relay.Value.kReverse);
        }else{
            GrabWheel.set(Relay.Value.kOff);
        }
        if(IM.FaceTop.getState()){
            ratchet.set(Relay.Value.kReverse);
        }else{
            ratchet.set(Relay.Value.kOff);
        }
        if(IM.FaceBott.getState()){
            clutch.set(Relay.Value.kForward);
        }else{
            clutch.set(Relay.Value.kOff);
        }
       // double vals = IM.ps2Controller.getRawAxis(5);
    //    if(vals)
    }
//}

    void a_piviot2() {
    }
}
