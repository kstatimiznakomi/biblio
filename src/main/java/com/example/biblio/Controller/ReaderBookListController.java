package com.example.biblio.Controller;

import com.example.biblio.service.JournalNotesService;
import com.example.biblio.service.PageService;
import com.example.biblio.service.ReaderTicketService;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/books")
@Lazy
public class ReaderBookListController {
    private final JournalNotesService notesService;

    @GetMapping({"", "/"})
    public String books() {
        return "redirect:/profile";
    }

    @GetMapping("/{pageNumber}")
    public String getMyBooks(Model model, Principal principal, @PathVariable int pageNumber) {
        return "redirect:/profile";
    }

    @GetMapping("/add/{bookId}")
    public String addBookToTicket(Principal principal, @PathVariable Long bookId) {
        if (principal == null) return "redirect:/catalog";
        notesService.Save(principal, bookId);
        return "redirect:/profile";
    }

    @GetMapping("/unread/{bookId}")
    public String deleteNote(Principal principal, @PathVariable Long bookId) {
        if (principal == null) return "redirect:/catalog";
        notesService.UnreadByPrincipal(principal, bookId);
        return "redirect:/profile";
    }

    @GetMapping("/complete/{bookId}")
    public String bookHasCompleted(Principal principal, @PathVariable Long bookId){
        if (principal == null) return "redirect:/catalog";
        notesService.CompletePrincipal(principal, bookId);
        return "redirect:/profile";
    }
}
