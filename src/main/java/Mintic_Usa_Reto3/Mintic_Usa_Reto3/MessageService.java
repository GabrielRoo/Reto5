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
public class MessageService {
    @Autowired
    private MessageRepository metodosCrud;
    
    public List<Message> getAll(){
        return metodosCrud.getAll();
    }
    public Optional <Message> getMessage(int messageId){
        return metodosCrud.getMessage(messageId);
    }
    public Message save (Message message){
        if (message.getIdMessage()==null){
            return metodosCrud.save(message);
        }else{
            Optional<Message> g=metodosCrud.getMessage(message.getIdMessage());
            if (g.isEmpty()){
                return metodosCrud.save(message);
            }else{
                return message;
            }
        }
    }
    public Message update (Message message){
        if (message.getIdMessage()!=null){
            Optional<Message> g=metodosCrud.getMessage(message.getIdMessage());
            if (!g.isEmpty()){
                if (message.getMessageText()!=null){
                    g.get().setMessageText(message.getMessageText());
                }
                metodosCrud.save(g.get());
                return g.get();
            }else{
                return message;
            } 
     
        }else{
        return message;
        }
    }
    public boolean deleteMessage (int messageId){
        Boolean aBoolean = getMessage(messageId).map(message ->{
            metodosCrud.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
