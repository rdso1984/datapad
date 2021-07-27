package datapad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListAppointment { //Create a list of appointments to show
    
    int day, month;
    String line;
    String[] filesAppointment;
            
    protected ListAppointment(){
        //String timeStamp = new SimpleDateFormat("MMdd_HHmm").format(Calendar.getInstance().getTime()); // Get current date and time - The original
        String timeStampDay = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime()); // Get current date and time
        this.day = Integer.parseInt(timeStampDay);

        String timeStampMonth = new SimpleDateFormat("MM").format(Calendar.getInstance().getTime()); // Get current date and time
        this.month = Integer.parseInt(timeStampMonth);
        
        deleteOldAppointments(listOldAppointments());
        Runtime.getRuntime().gc();
        deleteAndRename();
    }
    
    // Delete and rename a file - OK
    protected static void deleteAndRename(){
        File fileToDelete = new File("C:/Legacy/Datapad/mapAppointment.txt");
        fileToDelete.delete();
        
        Path source = Paths.get("C:/Legacy/Datapad/tempMapAppointment.txt");
        try {
        Files.move(source, source.resolveSibling("mapAppointment.txt"));
        } catch (IOException ex) {
            Logger.getLogger(ListAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Count lines in a file - OK
    protected static int countLines() throws FileNotFoundException, IOException{
        int numberLines=0;
        FileReader pathFile = new FileReader("C:/Legacy/Datapad/mapAppointment.txt");
        LineNumberReader countLines = new LineNumberReader(pathFile);
        while(countLines.skip(Long.MAX_VALUE) > 0){}
        numberLines = countLines.getLineNumber()+1;

        return numberLines;
    }
    
    //List Appoitments for today
    protected String[] listAppointmentsToday(){
        try{
            FileReader mapAppointment = new FileReader("C:/Legacy/Datapad/mapAppointment.txt");
            BufferedReader bufferedReader = new BufferedReader(mapAppointment);
            filesAppointment = new String[countLines()];
            int indexM=0, indexD=0, i=0;
            int appointmentMonth, appointmentDay;
            String dayAppointment, monthAppointment, fileAppointment;
            
            while((line = bufferedReader.readLine()) != null){
                indexM = line.indexOf("M");
                indexD = line.indexOf("D");
                
                dayAppointment = line.substring(indexM+1, indexD);
                appointmentDay = Integer.parseInt(dayAppointment);
                monthAppointment = line.substring(0, indexM);
                appointmentMonth = Integer.parseInt(monthAppointment);
                
                
                    if(appointmentDay == this.day){
                        if(appointmentMonth == this.month){
                            fileAppointment = line.substring(indexD+1);
                            filesAppointment[i] = fileAppointment;
                            i++;
                            System.out.println(filesAppointment[i]);
                            Runtime.getRuntime().gc();
                        }
                    }
                
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filesAppointment;
    }
    
    //List Appointments for tomorrow
    protected String[] listAppointmentsTomorrow(){
        try{
            FileReader mapAppointment = new FileReader("C:/Legacy/Datapad/mapAppointment.txt");
            BufferedReader bufferedReader = new BufferedReader(mapAppointment);
            filesAppointment = new String[countLines()];
            int indexM=0, indexD=0, i=0;
            int appointmentMonth, appointmentDay;
            String dayAppointment, monthAppointment, fileAppointment;
            
            while((line = bufferedReader.readLine()) != null){
                indexM = line.indexOf("M");
                indexD = line.indexOf("D");
                
                dayAppointment = line.substring(indexM+1, indexD);
                appointmentDay = Integer.parseInt(dayAppointment);
                monthAppointment = line.substring(0, indexM);
                appointmentMonth = Integer.parseInt(monthAppointment);
                
                if(appointmentDay == this.day+1){
                    if(appointmentMonth == this.month){
                        fileAppointment = line.substring(indexD+1);
                        filesAppointment[i] = fileAppointment;
                        i++;
                        System.out.println(filesAppointment[i]);
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filesAppointment;
    }
    
    //List all appointment - OK
    protected String[] listAllAppointments(){
        try{
            FileReader mapAppointment = new FileReader("C:/Legacy/Datapad/mapAppointment.txt");
            BufferedReader bufferedReader = new BufferedReader(mapAppointment);
            filesAppointment = new String[countLines()];
            int indexD, i=0;
            String fileAppointment;
            
            while((line = bufferedReader.readLine()) != null){
                indexD = line.indexOf("D");
                fileAppointment = line.substring(indexD+1);
                filesAppointment[i] = fileAppointment;
                i++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filesAppointment;
    }
    
    //List old appointments
    protected String[] listOldAppointments(){
        File map = new File("C:/Legacy/Datapad/mapAppointment.txt");
        if(!map.exists()){
            try {
                map.createNewFile();
                System.out.println("Has been created file mapAppointment.txt");
            } catch (IOException ex) {
                Logger.getLogger(ListAppointment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try{
            int indexM=0, indexD=0, i=0, c=0;
            int appointmentMonth, appointmentDay;
            String dayAppointment, monthAppointment, fileAppointment;
            filesAppointment = new String[countLines()];
            
            FileReader mapAppointment = new FileReader("C:/Legacy/Datapad/mapAppointment.txt");
            BufferedReader bufferedReader = new BufferedReader(mapAppointment);
            FileWriter fileTemp = new FileWriter("C:/Legacy/Datapad/tempMapAppointment.txt", true);
            BufferedWriter pencil = new BufferedWriter(fileTemp);
            
            //filesAppointment = new String[countLines()];
            
            while((line = bufferedReader.readLine()) != null){
                indexM = line.indexOf("M");
                indexD = line.indexOf("D");
                
                dayAppointment = line.substring(indexM+1, indexD);
                appointmentDay = Integer.parseInt(dayAppointment);
                System.out.println("Appointment Day: " + appointmentDay); //delete after
                monthAppointment = line.substring(0, indexM);
                appointmentMonth = Integer.parseInt(monthAppointment);
                System.out.println("Appointment Month: " + appointmentMonth); //delete after
                System.out.println(); //delete after
                
                if(appointmentMonth <= this.month){ //Check which appointments are old
                    if(appointmentDay < this.day){
                        fileAppointment = line;
                        filesAppointment[i] = fileAppointment;
                        System.out.println(filesAppointment[i]);
                        i++;
                    }
                    else{
                        pencil.write(line);
                        pencil.write("\r\n");
                        System.out.println("New line has been writen on file tempMapAppointment."); //DEPOIS APAGAR ESSA LINHA
                    }
                }
                else{
                    pencil.write(line);
                    pencil.write("\r\n");
                    System.out.println("New line has been writen on file tempMapAppointment."); //DEPOIS APAGAR ESSA LINHA
                }
            }
            bufferedReader.close();
            pencil.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filesAppointment;
    }
    
    protected void deleteLineFile(String text){
        
        try{
            //int indexM=0, indexD=0, i=0, c=0;
            //int appointmentMonth, appointmentDay;
            //String dayAppointment, monthAppointment, fileAppointment;
            
            FileReader mapAppointment = new FileReader("C:/Legacy/Datapad/mapAppointment.txt");
            BufferedReader bufferedReader = new BufferedReader(mapAppointment);
            FileWriter fileTemp = new FileWriter("C:/Legacy/Datapad/tempMapAppointment.txt", true);
            BufferedWriter pencil = new BufferedWriter(fileTemp);
            
            //filesAppointment = new String[countLines()];
            
            while((line = bufferedReader.readLine()) != null){
                //indexM = line.indexOf("M");
                //indexD = line.indexOf("D");
                
                if(!line.contentEquals(text)){
                    pencil.write(line); //TALVEZ TROCAR O PENCIL POR FILETEMP
                    pencil.write("\r\n"); //TALVEZ TROCAR O PENCIL POR FILETEMP
                    System.out.println("New line has been writen on file tempMapAppointment."); //DEPOIS APAGAR ESSA LINHA
                }
                else{
                  continue;  
                }
            }
            bufferedReader.close();
            pencil.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteAndRename();
    }
    
    //Delete old appointments - OK
    protected void deleteOldAppointments(String[] filesToErase){
        if(filesToErase==null){ }
        else{
            for (String file : filesToErase) {
            File fileToDelete = new File("C:/Legacy/Datapad/Appointments/"+file);
            System.out.println("File to DELETE: "+ file); //AFTER DELETE
            fileToDelete.delete();
            }
        }
        
    }
}
