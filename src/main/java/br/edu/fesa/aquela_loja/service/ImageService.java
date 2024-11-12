package br.edu.fesa.aquela_loja.service;

import br.edu.fesa.aquela_loja.models.entity.ProductImageModel;
import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.repository.IProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private IProductImageRepository imageRepository;

    public void generateFileModel(MultipartFile file, ProductModel pOwner) throws IOException {
        ProductImageModel image = ProductImageModel.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(file.getBytes())
                .product(pOwner)
                .build();

        imageRepository.save(image);
    }
}
