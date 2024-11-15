package br.edu.fesa.aquela_loja.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.edu.fesa.aquela_loja.models.dto.ProductRegDto;
import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.repository.IProductRepository;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public void createNewProduct(final ProductRegDto pDto, final MultipartFile img) throws IOException {

        ProductModel product = ProductModel.builder()
                .name(pDto.getPName())
                .price(pDto.getPrice())
                .brand(pDto.getBrand())
                .category(pDto.getCategory())
                .qtStock(pDto.getQtdStock())
                .description(pDto.getDescription())
                .build();

        var imgSaved = imageService.generateFileModel(img);
        product.setImg(imgSaved);

        productRepository.save(product);
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public ProductModel findById(final String id) {
        return productRepository.findById(Long.parseLong(id)).orElse(new ProductModel());
    }

    public void update(final ProductModel product, final MultipartFile img) throws IOException {
        imageService.update(product, img);
        productRepository.save(product);
    }

    public boolean exists(String pName) {
        return productRepository.existsByName(pName);
    }

    public void fillImage(ProductModel product) {
        imageService.fillImage(product);
    }

    public void deleteById(Long pId) {
        productRepository.deleteById(pId);
    }
}
