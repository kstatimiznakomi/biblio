package com.example.biblio.Controller;

import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.JournalNotes;
import com.example.biblio.model.NoteStatus;
import com.example.biblio.model.UserStatus;
import com.example.biblio.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final ReaderTicketService readerTicketService;
    private final JournalNotesService notesService;
    private final RusToEngStatus rusToEngStatus;
    @GetMapping("")
    public String index(Model model, Principal principal){
        if (principal == null) return "redirect:/login";
        model.addAttribute(
                "notes",
                notesService.getAllByTicket(
                        readerTicketService.getTicketByUser(
                                userService.getUserByName(principal.getName())))
        );
        model.addAttribute("openStat", NoteStatus.Open);
        model.addAttribute("closeStat", NoteStatus.Close);
        model.addAttribute("userData", userService.getUser(principal.getName()));
        return "profile";
    }

    @GetMapping("/edit")
    public String edit(Model model, Principal principal){
        if (principal == null) return "redirect:/login";
        return "profile-edit";
    }

    @PatchMapping ("/edit/patch")
    public String profile(Model model){
        model.addAttribute("user", new UserDTO());
        return "redirect:/profile";
    }
}
