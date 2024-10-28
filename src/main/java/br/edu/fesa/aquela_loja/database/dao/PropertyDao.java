package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.scheduling.config.Task;

import br.edu.fesa.aquela_loja.database.contracts.IPropertyDao;
import br.edu.fesa.aquela_loja.models.entities.Property;

public class PropertyDao extends BaseDao<Property> implements IPropertyDao {

    @Override
    public Task validateBeforeInsert(Property[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Task validateBeforeUpdate(Property[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
