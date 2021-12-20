package softuni.csshop.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.csshop.model.binding.ArticleAddBindingModel;
import softuni.csshop.service.ArticleService;


import javax.validation.Valid;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/blog")
    public ModelAndView articleView(ModelAndView modelAndView){
        modelAndView.addObject("articles", this.articleService.findAllArticles());
        modelAndView.setViewName("article-blog");
        return modelAndView;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String addArticle(Model model){
        model.addAttribute("articleAddBindingModel", new ArticleAddBindingModel());
        return "article-add";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addArticle(@Valid @ModelAttribute("articleAddBindingModel") ArticleAddBindingModel articleAddBindingModel,
                       BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "article-add";
        }

        this.articleService.addArticle(articleAddBindingModel);


        return "redirect:/home";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")String id){
        this.articleService.delete(id);
        return "redirect:/article/blog";
    }


}
