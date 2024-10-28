package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fesa.aquela_loja.database.contracts.IPropertyDao;

@Service
public class PropertyDao {

    @Autowired
    private IPropertyDao propertyDao;
}
