/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mintic_Usa_Reto3.Mintic_Usa_Reto3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {
    @Autowired
    
    private ReservationInterface crud4;
    
    public List<Reservation> getAll(){
    return (List<Reservation>) crud4.findAll();
    }
    public Optional <Reservation> getReservation(int id){
        return crud4.findById(id);
    }
    
    public Reservation save(Reservation reservation){
        return crud4.save(reservation);
    }
    
    public void delete (Reservation reservation){
        crud4.delete(reservation);
    }
    
    public List <Reservation> getReservationsByStatus(String status){
        return crud4.findAllByStatus(status);
    }
    
    public List <Reservation> getReservationPeriod(Date dateOne, Date dateTwo){
        return crud4.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }
    
    public List<CountClient> getTopClients(){
        List <CountClient> res=new ArrayList<>();
        
        List <Object[]> report=crud4.countTotalReservationByClient();
        for(int i=0;i<report.size();i++){
            //Primera forma
            /* 
            Client cli=(Client)report.get(i)[0];
            Long cantidad = (Long)report.get(i)[1];
            CountClient cc=new CountClient(cantidad,cli);
            res.add(cc); 
            */
            //Segunda forma
            res.add(new CountClient((Long) report.get(i)[1],(Client)report.get(i)[0]));
        }
        
        return res;
    }
    
}
