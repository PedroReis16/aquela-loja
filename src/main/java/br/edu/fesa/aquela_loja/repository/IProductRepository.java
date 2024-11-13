package br.edu.fesa.aquela_loja.repository;

import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductModel, Long> {

    boolean existsByName(String name);
}
