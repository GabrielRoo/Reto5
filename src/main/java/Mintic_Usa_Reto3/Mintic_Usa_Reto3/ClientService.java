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
public class ClientService {
    @Autowired
    private ClientRepository metodosCrud;
    
    public List<Client> getAll(){
        return metodosCrud.getAll();
    }
    public Optional <Client> getClient(int clientId){
        return metodosCrud.getClient(clientId);
    }
    public Client save (Client client){
        if (client.getIdClient()==null){
            return metodosCrud.save(client);
        }else{
            Optional<Client> e=metodosCrud.getClient(client.getIdClient());
            if (e.isEmpty()){
                return metodosCrud.save(client);
            }else{
                return client;
            }
        }
    }
    public Client update (Client client){
        if (client.getIdClient()!=null){
            Optional<Client> f=metodosCrud.getClient(client.getIdClient());
            if (!f.isEmpty()){
                if (client.getName()!=null){
                    f.get().setName(client.getName());
                }
                if (client.getAge()!=null){
                    f.get().setAge(client.getAge());
                }
                if (client.getPassword()!=null){
                    f.get().setPassword(client.getPassword());
                }
                metodosCrud.save(f.get());
                return f.get();
            }else{
                return client;
            } 
     
        }else{
        return client;
        }
    }
    public boolean deleteClient (int clientId){
        Boolean aBoolean = getClient(clientId).map(client ->{
            metodosCrud.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
