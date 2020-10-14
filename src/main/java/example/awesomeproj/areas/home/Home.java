package example.awesomeproj.areas.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class Home {
    @GetMapping("/home")
    public String user() {
        return "index";
    }
}
