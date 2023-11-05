package com.example.biblio.Controller.admin;

import com.example.biblio.model.Publisher;
import com.example.biblio.model.UserRole;
import com.example.biblio.service.PublisherService;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin/publishers")
@AllArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;
    private final UserService userService;
    @GetMapping("")
    public String publishers(Model model, Principal principal) {
        if (principal != null &&
                userService.getUser(principal.getName()).getRole().equals(UserRole.Администратор)){
            model.addAttribute("publishers", publisherService.getAllPublishers());
            return "admin/publishers";
        }
        return "redirect:/login";
    }

    @GetMapping("/{publisherId}/delete")
    public String deletePublisher(@PathVariable Long publisherId, Principal principal) {
        if (principal != null &&
                userService.getUser(principal.getName()).getRole().equals(UserRole.Администратор)){
            publisherService.deletePublisherById(publisherId);
            return "redirect:/admin/publishers";
        }
        return "redirect:/login";
    }

    @PostMapping("/{publisherId}/edit")
    public String savePublisherEdit(@ModelAttribute Publisher publisher, @PathVariable Long publisherId,
                                    Principal principal) {
        if (principal != null &&
                userService.getUser(principal.getName()).getRole().equals(UserRole.Администратор)){
            publisher.setId(publisherId);
            publisherService.save(publisher);
            return "redirect:/admin/publishers";
        }
        return "redirect:/login";
    }
}
