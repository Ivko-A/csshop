package softuni.csshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.csshop.model.Product;
import softuni.csshop.model.binding.ProductAddBindingModel;
import softuni.csshop.model.view.ProductViewModel;
import softuni.csshop.repository.ProductRepository;
import softuni.csshop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

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



    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }



    @Override
    public List<ProductViewModel> findAllFromCategory(String category) {

        return this.productRepository
                .findAll()
                .stream()
                .filter(product -> product.getCategory().name().equals(category))
                .filter(product -> product.isActive())
                .map(product -> {

                    return this.modelMapper
                            .map(product, ProductViewModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void buy(String id) {
        Product product = this.productRepository.findById(id).get();



        product.setQuantity(product.getQuantity() - 1);

        if (product.getQuantity() == 0) {
            product.setActive(false);
        }

        this.productRepository.save(product);
    }

    @Override
    public void deleteNonActiveProducts() {
        this.productRepository
             .findAll()
                .stream()
                .forEach(product -> {
                    if (!product.isActive()) {
                        this.productRepository.delete(product);
                    }
                });
    }


}
