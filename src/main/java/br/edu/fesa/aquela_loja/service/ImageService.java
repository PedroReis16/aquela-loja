package br.edu.fesa.aquela_loja.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.fesa.aquela_loja.models.entity.ProductImageModel;
import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.repository.IProductRepository;

@Service
public class ImageService {

    @Autowired
    private IProductRepository productRepository;

    public ProductImageModel generateFileModel(MultipartFile file) throws IOException {
        return ProductImageModel.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
    }

    public void update(ProductModel model, MultipartFile img) throws IOException {
        var pModel = productRepository.findById(model.getId());

        if (pModel.isPresent()) {
            if (!"".equals(img.getOriginalFilename())) {


                ProductImageModel imageModel = pModel.get().getImg();

                imageModel.setData(img.getBytes());
                imageModel.setType(img.getContentType());
                imageModel.setName(img.getOriginalFilename());
                model.setImg(imageModel);
            }
            else {
                model.setImg(pModel.get().getImg());
            }
        }
    }

    public void fillImage(ProductModel model) {
        var pModel = productRepository.findById(model.getId());

        model.setImg(pModel.get().getImg());
    }
}
