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
    public List<ProductViewModel> findAllLaptops() {

        return this.productRepository
                .findAll()
                .stream()
                .filter(product -> product.getCategory().name().equals("LAPTOPS"))
                .map(product -> {
                    System.out.println();
                    return this.modelMapper
                            .map(product, ProductViewModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public List<ProductViewModel> findAllComputers() {
        return this.productRepository
                     .findAll()
                .stream()
                .filter(product -> product.getCategory().name().equals("COMPUTERS"))
                .map(product -> {
                    System.out.println();
                    return this.modelMapper
                            .map(product, ProductViewModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductViewModel> findAllMonitors() {
        return this.productRepository
                .findAll()
                .stream()
                .filter(product -> product.getCategory().name().equals("MONITORS"))
                .map(product -> {
                    System.out.println();
                    return this.modelMapper
                            .map(product, ProductViewModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductViewModel> findAllPhones() {
        return this.productRepository
                .findAll()
                .stream()
                .filter(product -> product.getCategory().name().equals("PHONES"))
                .map(product -> {
                    System.out.println();
                    return this.modelMapper
                            .map(product, ProductViewModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductViewModel> findAllTablets() {
        return this.productRepository
                .findAll()
                .stream()
                .filter(product -> product.getCategory().name().equals("TABLETS"))
                .map(product -> {
                    System.out.println();
                    return this.modelMapper
                            .map(product, ProductViewModel.class);
                })
                .collect(Collectors.toList());
    }
}
