package datapad;

import javax.swing.JLabel;

public class Label{
        
    private JLabel appointment;
        
    protected Label(String where, String date, String hour){
        
        appointment = new JLabel("At " + where + date + hour + "!");
        Appointment.frameAppointments.add(appointment);
        
    }
    
    protected Label(int index, String where, String date, String hour){
        
        appointment = new JLabel(index + ". At " + where + date + hour + "!");
        Appointment.frameAppointments.add(appointment);
        
    }
    
}
