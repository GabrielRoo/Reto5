/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mintic_Usa_Reto3.Mintic_Usa_Reto3;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudService {
    @Autowired
    private CloudRepository metodosCrud;
    
    public List<Cloud> getAll(){
        return metodosCrud.getAll();    
    }
    public Optional<Cloud> getCloud(int idCloud){
        return metodosCrud.getCloud(idCloud);
    }
    public Cloud save(Cloud cloud){
        if (cloud.getId()==null){
            return metodosCrud.save(cloud);
        } else {
            Optional <Cloud> a=metodosCrud.getCloud(cloud.getId());
            if (a.isEmpty()){
                return metodosCrud.save(cloud);
            }else{
                return cloud;
            }
        }
    }
    
    public Cloud update (Cloud cloud){
        if (cloud.getId()!=null){
            Optional<Cloud> a=metodosCrud.getCloud(cloud.getId());
            if (!a.isEmpty()){
                if (cloud.getName()!=null){
                    a.get().setName(cloud.getName());
                }
                if (cloud.getBrand()!=null){
                    a.get().setBrand(cloud.getBrand());
                }
                if (cloud.getYear()!=null){
                    a.get().setYear(cloud.getYear());
                }
                if (cloud.getDescription()!=null){
                    a.get().setDescription(cloud.getDescription());
                }
                if (cloud.getCategory()!=null){
                    a.get().setCategory(cloud.getCategory());
                }
                metodosCrud.save(a.get());
                return a.get();
                
            }else{
                    return cloud;
            }
    
        }else{
            return cloud;
        }
    
    }
    
    public boolean deleteCloud (int cloudId){
        Boolean aBoolean = getCloud(cloudId).map(cloud ->{
            metodosCrud.delete(cloud);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}

