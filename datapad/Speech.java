package datapad;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Speech{
    
    String result; //result to make a action
    LiveSpeechRecognizer recognizer; //create
    Configuration configuration = new Configuration();

    boolean isAlive = true;

    //Constructor
    protected Speech(){

        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us"); //show the path to Acoustic
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict"); //show the path to Dictionary
        //configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin"); //recognize every word of the language that are been using
        configuration.setGrammarPath("resource:/grammars"); //show path to grammar
        configuration.setGrammarName("grammar"); //show the name of the grammar to use
        configuration.setUseGrammar(true); //indica que quer utilizar a grammar
        
        try {
            recognizer = new LiveSpeechRecognizer(configuration);
        } catch (IOException ex) {
            Logger.getLogger(Speech.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        recognizer.startRecognition(true);
       
    }
    
    private void startSpeechThread(){
    
        SpeechResult speechResult = recognizer.getResult();
        if(speechResult != null){
            result = speechResult.getHypothesis();
        }
        else{
            System.out.println("I can't understand what you choose!");
        }

    }
    
    protected String getResult(){
        startSpeechThread();
        return result;
    }
}
