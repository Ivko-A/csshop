package softuni.csshop.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.csshop.model.UserEntity;
import softuni.csshop.model.binding.QuestionAddBindingModel;
import softuni.csshop.service.QuestionService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    public String question(){
        return "question";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("questionAddBindingModel") QuestionAddBindingModel questionAddBindingModel,
                       BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "question/save";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        questionAddBindingModel.setEmail(currentPrincipalName);


        questionService.saveQuestion(questionAddBindingModel);



        return "redirect:/home";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView questionView(ModelAndView modelAndView){
        modelAndView.addObject("questions", this.questionService.findAllQuestions());
        modelAndView.setViewName("admin-question");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")String id){
        this.questionService.delete(id);
        return "redirect:/question/admin";
    }
}
