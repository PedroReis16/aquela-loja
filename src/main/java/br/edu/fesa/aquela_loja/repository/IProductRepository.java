package br.edu.fesa.aquela_loja.repository;

import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.models.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<ProductModel, Long> {

    boolean existsByName(String name);

    List<ProductModel> findByCategory(CategoryEnum category);
}
