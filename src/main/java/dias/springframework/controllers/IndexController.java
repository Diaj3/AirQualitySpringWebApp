package dias.springframework.controllers;

import dias.springframework.domain.Location;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    String index(Model model){
        model.addAttribute("location", new Location());
        return "index";
    }
}
