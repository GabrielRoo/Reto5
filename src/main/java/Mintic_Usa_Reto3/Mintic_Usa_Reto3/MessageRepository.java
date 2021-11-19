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

@Repository
public class MessageRepository {
    @Autowired
    private MessageInterface crud3;
    
    public List<Message> getAll(){
    return (List<Message>) crud3.findAll();
    }
    public Optional <Message> getMessage(int id){
        return crud3.findById(id);
    }
    
    public Message save(Message message){
        return crud3.save(message);
    }
    
    public void delete (Message message){
        crud3.delete(message);
    }
}
