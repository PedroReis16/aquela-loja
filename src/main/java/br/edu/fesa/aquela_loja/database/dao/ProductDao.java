package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fesa.aquela_loja.database.contracts.IProductDao;

@Service
public class ProductDao {

    @Autowired
    private IProductDao productDao;
}
