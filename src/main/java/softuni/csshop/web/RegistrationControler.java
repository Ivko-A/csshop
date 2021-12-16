package softuni.csshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.csshop.model.binding.UserRegistrationBindingModel;
import softuni.csshop.service.impl.UserServiceImpl;

import javax.validation.Valid;

@Controller
public class RegistrationControler {

    private final UserServiceImpl userService;

    public RegistrationControler(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String showRegister(Model model) {
        model.addAttribute("formData", new UserRegistrationBindingModel());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@Valid @ModelAttribute("formData") UserRegistrationBindingModel userRegistrationBindingModel,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors() || !userRegistrationBindingModel.getPassword().equals(userRegistrationBindingModel.getConfirmPassword())) {
            return "registration";
        }

        if (userService.exists(userRegistrationBindingModel.getEmail())) {
            bindingResult.rejectValue("email",
                    "error.email",
                    "An account already exists for this email.");
            return "registration";
        }

        userService.registerAndLoginUser(userRegistrationBindingModel.getEmail(), userRegistrationBindingModel.getPassword());

        return "redirect:/home";

    }

//    @GetMapping("/registration")
//    public String registration(){
//        return "registration";
//    }
}
