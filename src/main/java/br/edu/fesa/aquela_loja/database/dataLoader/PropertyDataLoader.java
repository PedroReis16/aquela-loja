package br.edu.fesa.aquela_loja.database.dataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.fesa.aquela_loja.database.contracts.IPropertyDao;
import jakarta.annotation.PostConstruct;

@Component
public class PropertyDataLoader {

    @Autowired
    private IPropertyDao propertyDao;

    @PostConstruct
    public void init() {
        
    }
}
