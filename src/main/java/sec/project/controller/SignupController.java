package sec.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;
import service.SignupService;

@DependsOn("signupService")
@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @Autowired
    @Qualifier("signupService")
    private SignupService signupService;

//    @Autowired
//    private HttpSession session;
    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm(Model model) {
        model.addAttribute("signedup", signupRepository.findAll());
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (signupRepository.findOneBySignername(name) != null) {
            model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        } else {
            model.addAttribute("user", null);
        }
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address, @RequestParam Long creditcard) {
        signupService.signUp(name, address, SecurityContextHolder.getContext().getAuthentication().getName(), creditcard);
        return "done";
    }

    @RequestMapping(value = "/userinfo/{user}", method = RequestMethod.GET)
    public String userInfo(Model model, @PathVariable String user) {
        model.addAttribute("signername", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", signupRepository.findOneBySignername(user));
        return "userinfo";
    }

    @RequestMapping(value = "/userinfo/update/{username}", method = RequestMethod.POST)
    public String updateUserInfo(Model model, @RequestParam String name, @RequestParam String address, @PathVariable String username) {
        Signup signup = signupRepository.findOneBySignername(username);
        signup.setName(name);
        signup.setAddress(address);
        signupRepository.save(signup);

        return "done";
    }

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
