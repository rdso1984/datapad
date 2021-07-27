package datapad;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;

public class Appointment {
    
    static String where;
    static String date;
    static String hour;
    boolean didWell = false;
    int month;
    int day;
    static JFrame frameAppointments;
    
    //Constructor and decision - OK
    protected Appointment(String[] speech){
        ListAppointment listAppointment = new ListAppointment();
        
        for(int i=0; i<speech.length; i++){
            switch (speech[i]){
                case "new": //OK
                    System.out.println("What is the new appointment?");
                    Datapad.speechProcess();
                    newAppointment(Datapad.str);
                    break;
                    
                case "show": //OK
                    System.out.println("Which appointments?");
                    Datapad.speechProcess();
                    switch(Datapad.str){
                    
                        case "everyone":
                            frameAppointments = new JFrame("All Appointments scheduled:");
                            
                            for(int c=0; c<listAppointment.listAllAppointments().length-1; c++){
                                System.out.println("Appointment: " + listAppointment.filesAppointment[c]);
                                showAppointment(listAppointment.filesAppointment[c]);
                            }
                            
                            frameAppointments.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frameAppointments.setSize(400,100);
                            frameAppointments.setLayout(new FlowLayout());
                            frameAppointments.setVisible(true);
                            Runtime.getRuntime().gc();
                            break;
                            
                        case "today":
                            frameAppointments = new JFrame("Appointments for TODAY:");
                            
                            for(int c=0; c<listAppointment.listAllAppointments().length-1; c++){
                                System.out.println("Appointment: " + listAppointment.filesAppointment[c]);
                                showAppointment(listAppointment.filesAppointment[c]);
                            }
                            
                            frameAppointments.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frameAppointments.setSize(600,100);
                            frameAppointments.setLayout(new FlowLayout());
                            frameAppointments.setVisible(true);
                            Runtime.getRuntime().gc();
                            break;
                            
                        case "tomorrow":
                            frameAppointments = new JFrame("Appointments for TOMORROW:");
                            
                            for(int c=0; c<listAppointment.listAllAppointments().length-1; c++){
                                System.out.println("Appointment: " + listAppointment.filesAppointment[c]);
                                showAppointment(listAppointment.filesAppointment[c]);
                            }
                            
                            frameAppointments.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frameAppointments.setSize(600,100);
                            frameAppointments.setLayout(new FlowLayout());
                            frameAppointments.setVisible(true);
                            Runtime.getRuntime().gc();
                            break;
                        
                        default:
                            System.out.println("Data não confere.");
                            break;
                    }
                    break;
                        
                case "delete":
                    frameAppointments = new JFrame ("Choose one of the appointments below to delete:");
                    int index=1;
                    listAppointment.listAllAppointments();
                    for(int c=0; c<listAppointment.filesAppointment.length-1; c++){
                        System.out.println(index + ". Appointment: " + listAppointment.filesAppointment[c]);
                        showAppointmentDelete(index, listAppointment.filesAppointment[c]);
                        index++;
                    }

                    frameAppointments.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frameAppointments.setSize(600,100);
                    frameAppointments.setLayout(new FlowLayout());
                    frameAppointments.setVisible(true);
                    Runtime.getRuntime().gc();

                    Datapad.speechProcess();
                    IdentifyDate position = new IdentifyDate(Datapad.str);
                    deleteFile(listAppointment.filesAppointment[position.day-1]);
                    break;
                            
                default:
                    System.out.println("No option available!!");
            }
        }
    }
    
    protected void deleteFile(String appointment){
        
        String line;
        File fileToDelete = new File("C:/Legacy/Datapad/Appointments/"+appointment);
        fileToDelete.delete();
        
        try{
            FileReader mapAppointment = new FileReader("C:/Legacy/Datapad/mapAppointment.txt");
            BufferedReader bufferedReader = new BufferedReader(mapAppointment);
            FileWriter fileTemp = new FileWriter("C:/Legacy/Datapad/tempMapAppointment.txt", true);
            BufferedWriter pencil = new BufferedWriter(fileTemp);
            
            while((line = bufferedReader.readLine()) != null){
                //indexM = line.indexOf("M");
                //indexD = line.indexOf("D");
                
                if(!line.contentEquals(appointment)){
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
        
        ListAppointment.deleteAndRename();
    }
    
    //Create New Appointment - OK
    private void newAppointment(String speech){
        setInformation(speech);
        boolean fvar = true;
        File file;
        List<String> line = Arrays.asList(this.where, this.date, this.hour);
        
        try{
            int i=1;
            do{
                file = new File("C:/Legacy/Datapad/Appointments/appointment"+i+".txt");
                fvar = file.createNewFile(); //Check if the file was created
                if(fvar){
                    Path filePath = Paths.get("C:/Legacy/Datapad/Appointments/appointment"+i+".txt");
                    Files.write(filePath, line, Charset.forName("UTF-8"));
                    System.out.println("New Appointment Created:");
                    System.out.println("At " + this.where + this.date + this.hour);
                    System.out.println();
                    
                    try{
                        FileWriter pencil = new FileWriter("C:/Legacy/Datapad/mapAppointment.txt", true);
                        pencil.write(this.month+"M"+this.day+"D"+"appointment"+i+".txt");
                        pencil.write("\r\n");
                        pencil.close();
                    }
                    catch (IOException e){
                        System.out.println("Exception Occured: ");
                        e.printStackTrace();
                    }
                }
                else{
                    i++;
                }
            }while(fvar==false);
        }
        catch(IOException e){
            System.out.println("Exception Occurred: ");
            e.printStackTrace();
        }
    }
    
    //Set informations: where, date, hour - OK
    private void setInformation(String speech){
        int indexOn=0, indexAt=0;
        indexOn = speech.indexOf("on");
        indexAt = speech.indexOf("at");
        
        try{
            this.where = speech.substring(0, indexOn);
            this.date = speech.substring(indexOn, indexAt);
            this.hour = speech.substring(indexAt);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Exception Occured: ");
            e.printStackTrace();
        }
        
        IdentifyDate identifyDate = new IdentifyDate(date);
        this.month = identifyDate.month;
        this.day = identifyDate.day;
    }
    
    //Show appointments - OK
    private void showAppointment(String file){
        System.out.println("File name of the Appointment: " + file); //AFTER DELETE THIS LINE
        String line;
        
        try{
            FileReader reader = new FileReader("C:/Legacy/Datapad/Appointments/"+file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            line = bufferedReader.readLine();
            setWhere(line);
            line = bufferedReader.readLine();
            setDate(line);
            line = bufferedReader.readLine();
            setHour(line);
            
            bufferedReader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Label appointmentLabel = new Label(getWhere(), getDate(), getHour());
    }
    
    //Show appointments to delete one - OK
    private void showAppointmentDelete(int index, String file){
        System.out.println("File name of the Appointment: " + file); //AFTER DELETE THIS LINE
        String line;
        
        try{
            FileReader reader = new FileReader("C:/Legacy/Datapad/Appointments/"+file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            line = bufferedReader.readLine();
            setWhere(line);
            line = bufferedReader.readLine();
            setDate(line);
            line = bufferedReader.readLine();
            setHour(line);
            
            bufferedReader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Label appointmentLabel = new Label(index, getWhere(), getDate(), getHour());
    }
    
    //OK
    private void setWhere(String line){
        this.where = line;
        System.out.println("String WHERE: " + this.where);
    }
    
    //OK
    private void setDate(String line){
        this.date = line;
        System.out.println("String DATE: " + this.date);
    }
    
    //OK
    private void setHour(String line){
        this.hour = line;
        System.out.println("String HOUR: " + this.hour);
    }
    
    //OK
    static private String getWhere(){
        return where;
    }
    
    //OK
    protected static String getDate(){
        return date;
    }
    
    //OK
    protected static String getHour(){
        return hour;
    }
    
    //Delete appointment OK - CHECAR SE RETIRA
    /*private int deleteAppointment(String number){
        IdentifyDate appointmentToDelete = new IdentifyDate(number);
        return appointmentToDelete.day;
    }*/
    
    private void sendEmail(){
        //FAZER OUTRA CLASSE PARA ARMAZENAR EMAIL
    }
    
    private void newEmail(){
        //FAZER OUTRA CLASSE PARA ARMAZENAR EMAIL
    }
    
    
}
