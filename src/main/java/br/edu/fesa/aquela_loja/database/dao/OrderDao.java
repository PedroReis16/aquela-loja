package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fesa.aquela_loja.database.contracts.IOrderDao;

@Service
public class OrderDao{

    @Autowired
    private IOrderDao orderDao;
}
