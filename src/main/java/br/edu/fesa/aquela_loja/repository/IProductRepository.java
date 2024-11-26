package br.edu.fesa.aquela_loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.models.enums.CategoryEnum;

public interface IProductRepository extends JpaRepository<ProductModel, Long> {

    boolean existsByName(String name);

    List<ProductModel> findByCategory(CategoryEnum category);

    List<ProductModel> findTop10ByCategory(CategoryEnum category);

    List<ProductModel> findByNameContainingIgnoreCase(String name);

    List<ProductModel> findTop10ByOrderByName();
    void deleteByName(String name);
}
