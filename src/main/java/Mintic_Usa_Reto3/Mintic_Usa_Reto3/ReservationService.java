/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mintic_Usa_Reto3.Mintic_Usa_Reto3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository metodosCrud;
    
    public List<Reservation> getAll(){
        return metodosCrud.getAll();
    }
    public Optional <Reservation> getReservation(int reservationId){
        return metodosCrud.getReservation(reservationId);
    }
    public Reservation save (Reservation reservation){
        if (reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservation> h=metodosCrud.getReservation(reservation.getIdReservation());
            if (h.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }
    public Reservation update (Reservation reservation){
        if (reservation.getIdReservation()!=null){
            Optional<Reservation> h=metodosCrud.getReservation(reservation.getIdReservation());
            if (!h.isEmpty()){
                if (reservation.getStartDate()!=null){
                    h.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate()!=null){
                    h.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus()!=null){
                    h.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(h.get());
                return h.get();
            }else{
                return reservation;
            } 
     
        }else{
        return reservation;
        }
    }
    public boolean deleteReservation (int reservationId){
        Boolean aBoolean = getReservation(reservationId).map(reservation ->{
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    public List<CountClient>getTopClients(){
        return metodosCrud.getTopClients();
    
    }
    
    public StatusAmount getStatusReport(){
        List<Reservation> completed =metodosCrud.getReservationsByStatus("completed");
        List<Reservation> cancelled =metodosCrud.getReservationsByStatus("cancelled");
        
        StatusAmount statAmt=new StatusAmount(completed.size(),cancelled.size());
        return statAmt;
    }
    
    public List <Reservation> getReservationPeriod(String d1, String d2){
        
        //yyyy-MM-dd
        SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");
        Date dateOne= new Date();
        Date dateTwo= new Date();
        try {
            dateOne=parser.parse(d1);
            dateTwo=parser.parse(d2);
        
        }catch(ParseException e){
            e.printStackTrace();
        }
        if (dateOne.before(dateTwo)){
            return metodosCrud.getReservationPeriod(dateOne, dateTwo);
        }else{
            return new ArrayList<>();
        }
 
        
        
    }
}
