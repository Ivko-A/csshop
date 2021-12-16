package softuni.csshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.csshop.model.Product;
import softuni.csshop.model.binding.ProductAddBindingModel;
import softuni.csshop.repository.ProductRepository;
import softuni.csshop.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addArticle(ProductAddBindingModel productAddBindingModel) {
        Product product = modelMapper.map(productAddBindingModel, Product.class);
        product.setActive(true);

        this.productRepository.save(product);
    }
}
