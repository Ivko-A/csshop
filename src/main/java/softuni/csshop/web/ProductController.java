package softuni.csshop.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String addProduct() {
        return "product-add";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addArticle(@Valid @ModelAttribute("productAddBindingModel") ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "redirect:add";
        }



        this.productService.addArticle(productAddBindingModel);


        return "redirect:/home";
    }

    @GetMapping("/buy/{id}")
    public String delete(@PathVariable("id")String id){
        this.productService.buy(id);
        return "redirect:/products/buy";
    }

    @GetMapping("/buy")
    public String buy(){
        return "buy";
    }



    @GetMapping("/laptops")
    public ModelAndView laptopsView(ModelAndView modelAndView){
        modelAndView.addObject("laptops", this.productService.findAllFromCategory("LAPTOPS"));
        modelAndView.setViewName("laptops");
        return modelAndView;
    }

    @GetMapping("/computers")
    public ModelAndView computersView(ModelAndView modelAndView){
        modelAndView.addObject("computers", this.productService.findAllFromCategory("COMPUTERS"));
        modelAndView.setViewName("computers");
        return modelAndView;
    }

    @GetMapping("/monitors")
    public ModelAndView monitorsView(ModelAndView modelAndView){
        modelAndView.addObject("monitors", this.productService.findAllFromCategory("MONITORS"));
        modelAndView.setViewName("monitors");
        return modelAndView;
    }

    @GetMapping("/phones")
    public ModelAndView phonesView(ModelAndView modelAndView){
        modelAndView.addObject("phones", this.productService.findAllFromCategory("PHONES"));
        modelAndView.setViewName("phones");
        return modelAndView;
    }
    @GetMapping("/tablets")
    public ModelAndView tabletsView(ModelAndView modelAndView){
        modelAndView.addObject("tablets", this.productService.findAllFromCategory("TABLETS"));
        modelAndView.setViewName("tablets");
        return modelAndView;
    }


}
