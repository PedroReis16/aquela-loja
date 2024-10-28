package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.edu.fesa.aquela_loja.database.contracts.IProductImageDao;

@Service
public class ProductImageDao {

    @Autowired
    private IProductImageDao productImageDao;
}
