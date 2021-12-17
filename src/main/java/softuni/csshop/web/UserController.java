package softuni.csshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import softuni.csshop.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView viewAllUsers(ModelAndView modelAndView){
        modelAndView.addObject("users", this.userService.findAllUsers());
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id")String id, ModelAndView modelAndView) {
        modelAndView.addObject("user", this.userService.findById(id));
        modelAndView.setViewName("user-details");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")String id){
        this.userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/makeAdmin/{id}")
    public String makeAdmin(@PathVariable("id")String id){
        this.userService.makeAdmin(id);
        return "redirect:/users";
    }
}
