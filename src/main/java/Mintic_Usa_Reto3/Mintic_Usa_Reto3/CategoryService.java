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
public class CategoryService {
    @Autowired
    private CategoryRepository metodosCrud;
    
    public List<Category> getAll(){
        return metodosCrud.getAll();
    }
    public Optional <Category> getCategory(int CategoryId){
        return metodosCrud.getCategory(CategoryId);
    }
    public Category save (Category category){
        if (category.getId()==null){
            return metodosCrud.save(category);
        }else{
            Optional<Category> b=metodosCrud.getCategory(category.getId());
            if (b.isEmpty()){
            return metodosCrud.save(category);
            }else{
                return category;
            }
        }
    }
    public Category update (Category category){
        if (category.getId()!=null){
            Optional<Category> c=metodosCrud.getCategory(category.getId());
            if (!c.isEmpty()){
                if (category.getDescription()!=null){
                    c.get().setDescription(category.getDescription());
                }
                if (category.getName()!=null){
                    c.get().setName(category.getName());
                }
                return metodosCrud.save(c.get());
                
            }
     
        }
        return category;
    }
    public boolean deleteCategory (int categoryId){
        Boolean d= getCategory(categoryId).map(category ->{
            metodosCrud.delete(category);
            return true;
        }).orElse(false);
        return d;
    }
}    
