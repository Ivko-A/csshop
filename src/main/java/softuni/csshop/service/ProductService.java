package softuni.csshop.service;

import softuni.csshop.model.binding.ProductAddBindingModel;
import softuni.csshop.model.view.ProductViewModel;

import java.util.List;

public interface ProductService {
    void addArticle(ProductAddBindingModel productAddBindingModel);

    void delete(String id);

    List<ProductViewModel> findAllFromCategory(String category);
}
