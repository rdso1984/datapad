package datapad;

public class Decision {
    
    Appointment appointment;
    boolean isAlive = true;
    
    protected void getDecision(String speech){
        
        String[] arraySpeech = speech.split(" ");
        for(int i=0; i<arraySpeech.length; i++){
            switch (arraySpeech[i]){
                
                case "computer":
                    System.out.println("Yes, what do you want to do?");
                    break;
                
                case "appointment":
                    appointment = new Appointment(arraySpeech);
                    break;
                
                case "hour": //FINISH THIS COMMAND
                    //Time hour = new Time(); INCLUIR CLASSES, MÉTODOS ETC
                    break;
                    
                case "close":
                    isAlive = false;
                    break;
                
                case "test": //DELETE LATER THIS CASE
                    System.out.println("Say ONE or other thing.");
                    Datapad.speechProcess();
                    if(Datapad.str.equals("one")){
                        System.out.println("You said: ONE.");
                    }
                    else{
                        System.out.println("You didn't say ONE.");
                    }
                    break;
            }
        }
    }
}
