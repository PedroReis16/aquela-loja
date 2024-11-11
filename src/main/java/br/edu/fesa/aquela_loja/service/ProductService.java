package br.edu.fesa.aquela_loja.service;

import br.edu.fesa.aquela_loja.models.dto.ProductRegDto;
import br.edu.fesa.aquela_loja.models.entity.ProductImageModel;
import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.repository.IProductImageRepository;
import br.edu.fesa.aquela_loja.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
        List<ProductModel> productModels = productRepository.findAll();
//        productModels.forEach(ProductModel::loadBase64Imagem);
        return productModels;
    }
}
