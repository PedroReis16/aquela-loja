package br.edu.fesa.aquela_loja.service;

import java.io.IOException;
import java.util.Base64;

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

    public ProductImageModel generateFileModel(String imgBase64) throws IOException {
        if (imgBase64.startsWith("data:image")) {
            imgBase64 = imgBase64.substring(imgBase64.indexOf(",") + 1);
        }
    
        byte[] img = Base64.getDecoder().decode(imgBase64);
    
        return ProductImageModel.builder()
                .data(img)
                .build();
    }

    public void update(ProductModel model, MultipartFile img) throws IOException {
        var pModel = productRepository.findById(model.getId());

        if (pModel.isPresent()) {
            if (!"".equals(img.getOriginalFilename())) {

                ProductImageModel imageModel = pModel.get().getImg();

                imageModel.setData(img.getBytes());
                // imageModel.setType(img.getContentType());
                // imageModel.setName(img.getOriginalFilename());
                model.setImg(imageModel);
            } else {
                model.setImg(pModel.get().getImg());
            }
        }
    }

    public void fillImage(ProductModel model) {
        var pModel = productRepository.findById(model.getId());

        model.setImg(pModel.get().getImg());
    }
}
