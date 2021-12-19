package softuni.csshop.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.csshop.service.StatsService;

@Controller
@RequestMapping("/stats")
@PreAuthorize("hasRole('ADMIN')")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public String stats(Model model) {
        model.addAttribute("requestCount", statsService.getRequestCount());
        model.addAttribute("startedOn", statsService.getStartedOn());

        return "stats";
    }


}
