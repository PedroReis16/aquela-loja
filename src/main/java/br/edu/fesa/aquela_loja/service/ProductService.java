package br.edu.fesa.aquela_loja.service;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.edu.fesa.aquela_loja.models.dto.NewProductDto;
import br.edu.fesa.aquela_loja.models.dto.cart.CartItemDto;
import br.edu.fesa.aquela_loja.models.entity.ProductModel;
import br.edu.fesa.aquela_loja.models.enums.CategoryEnum;
import br.edu.fesa.aquela_loja.models.enums.DepartamentEnum;
import br.edu.fesa.aquela_loja.repository.IProductRepository;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public void createNewProduct( NewProductDto pDto) throws IOException {

        ProductModel product = ProductModel.builder()
                .name(pDto.getPName())
                .price(pDto.getPrice())
                .brand(pDto.getBrand())
                .category(pDto.getCategory())
                .stockCount(pDto.getStockCount())
                .description(pDto.getDescription())
                .build();

        var imgSaved = imageService.generateFileModel(pDto.getImage());
        product.setImg(imgSaved);

        productRepository.save(product);
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public List<ProductModel> findAllLimit10(){
        return productRepository.findTop10ByOrderByName();
    }

    public List<ProductModel> find10ByCategory(CategoryEnum category) {
        return productRepository.findTop10ByCategory(category);
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

    public List<ProductModel> findByIdIn(List<String> pIds) {
        var ids = pIds.stream().map(Long::parseLong).collect(Collectors.toList());
        return productRepository.findAllById(ids);
    }

    public List<CartItemDto> getCartDto(List<String> cartItems) {
        return findByIdIn(cartItems).stream().map(CartItemDto::fromProdutc).collect(Collectors.toList());
    }

    public List<ProductModel> getProductsForCategoryOrDepartament(String filter) {

        try {
            CategoryEnum category = getEnumValue(CategoryEnum.class, filter);
            if (category != null) {
                return productRepository.findByCategory(category);
            } else {
                  List<ProductModel> products = new java.util.ArrayList<>(List.of());
                DepartamentEnum departament = getEnumValue(DepartamentEnum.class, filter);

                if (departament != null) {
                    for (CategoryEnum cat : departament.getCategories()) {
                        productRepository.findByCategory(CategoryEnum.MEMORIA_RAM);
                        var pCat = productRepository.findByCategory(cat);
                        if (!pCat.isEmpty()) {
                            products.addAll(pCat);
                        }
                    }
                }

                return products;
            }
        } catch (Exception e) {
            System.out.println("Erro ao determinar categoria ou departamento");
            return List.of();
        }
    }

    private <E extends Enum<E>> E getEnumValue(Class<E> enumClass, String filter) {
        for (E value : enumClass.getEnumConstants()) {
            if (equalsIgnoreCaseAndAccent(value.name().replace('_', ' '), filter)) {
                return value;
            }
        }
        return null; // Retorna nulo se o valor não for encontrado no enum
    }

    public static boolean equalsIgnoreCaseAndAccent(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return str1 == null && str2 == null;
        }
        String normalizedStr1 = Normalizer.normalize(str1, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        String normalizedStr2 = Normalizer.normalize(str2, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return normalizedStr1.equalsIgnoreCase(normalizedStr2);
    }

    public List<ProductModel> findProductsByNameLike(String searched) {
        return productRepository.findByNameContainingIgnoreCase(searched);
    }
}
