package softuni.csshop.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.csshop.model.binding.ProductAddBindingModel;
import softuni.csshop.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct() {
        return "product-add";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addArticle(@Valid @ModelAttribute("productAddBindingModel") ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "/products/add";
        }



        this.productService.addArticle(productAddBindingModel);


        return "redirect:/home";
    }


}
