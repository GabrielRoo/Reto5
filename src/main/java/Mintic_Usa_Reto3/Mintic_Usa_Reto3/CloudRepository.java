/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mintic_Usa_Reto3.Mintic_Usa_Reto3;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gabrielroa
 */
@Repository
public class CloudRepository {
    
    @Autowired
    private CloudInterface crud;
    
    public List<Cloud> getAll(){
        return (List<Cloud>) crud.findAll();
    }
    public Optional <Cloud> getCloud(int id){
        return crud.findById(id);
    }
    
    public Cloud save(Cloud cloud){
        return crud.save(cloud);
    }
    
    public void delete (Cloud cloud){
        crud.delete(cloud);
    }

    
}
