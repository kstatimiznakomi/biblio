package com.example.biblio.Controller;

import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.User;
import com.example.biblio.service.JournalNotesService;
import com.example.biblio.service.ReaderTicketService;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
@Lazy
public class ProfileController {
    private final UserService userService;
    @Lazy
    private final JournalNotesService notesService;
    @GetMapping("")
    public String index(Model model, Principal principal){
        if (principal == null) return "redirect:/login";
        model.addAttribute(
                "notes",
                notesService.getReadBooks(principal)
        );
        model.addAttribute("user", userService.getUserByName(principal.getName()));
        return "profile";
    }

    @GetMapping("/edit")
    public String edit(Model model, Principal principal){
        if (principal == null) return "redirect:/login";
        model.addAttribute("userInfo", userService.getUserByName(principal.getName()));
        model.addAttribute("user", new UserDTO());
        return "profile-edit";
    }

    @PatchMapping ("/edit/patch")
    public String profile(Model model, Principal principal, @ModelAttribute UserDTO dto){
        if (principal == null) return "redirect:/login";
        User user = userService.getUserByName(principal.getName());
        userService.patchUser(dto, user);
        return "redirect:/profile";
    }
}
