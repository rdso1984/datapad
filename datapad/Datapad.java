package datapad;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Datapad {

    static String str;
    static Speech speech;
    static Decision decision;
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
    
        speech = new Speech();
        decision = new Decision();
        System.out.println("Hello, what do you want to do?");
        
        do{
            speechProcess();
            decision.getDecision(str);
        }while(decision.isAlive);
    }
    
    public static void speechProcess(){
        str = speech.getResult();
        System.out.println("You said: " + str);
        System.out.println();
    }
}
