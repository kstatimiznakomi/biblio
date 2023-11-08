package com.example.biblio.Controller;

import com.example.biblio.model.JournalNotes;
import com.example.biblio.service.JournalNotesService;
import com.example.biblio.service.PageService;
import com.example.biblio.service.ReaderTicketService;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/books")
public class ReaderBookListController {
    private final UserService userService;
    private final ReaderTicketService readerTicketService;
    private final JournalNotesService notesService;
    private PageService pageService;

    @GetMapping({"", "/"})
    public String books() {
        return "redirect:/catalog/1";
    }

    @GetMapping("/{pageNumber}")
    public String getMyBooks(Model model, Principal principal, @PathVariable int pageNumber) {
        if (principal == null) return "redirect:/catalog";
        long totalItems = notesService.getAllPageByTicket(
                pageNumber,
                readerTicketService.getTicketByUser(userService.getUserByName(principal.getName()))
        ).getTotalElements();

        int totalPages = notesService.getAllPageByTicket(
                pageNumber,
                readerTicketService.getTicketByUser(userService.getUserByName(principal.getName()))
        ).getTotalPages();

        int curPage = pageService.GetBiggerLower(pageNumber, totalPages);
        List<JournalNotes> notes = notesService.getAllPageByTicket(
                pageNumber,
                readerTicketService.getTicketByUser(userService.getUserByName(principal.getName()))
        ).getContent();

        model.addAttribute("notes", notes);
        model.addAttribute("currentPage", curPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        return "redirect:/catalog";
    }

    @GetMapping("/add/{bookId}")
    public String addBookToTicket(Principal principal, @PathVariable Long bookId) {
        if (principal == null) return "redirect:/catalog";
        notesService.Save(principal, bookId);
        return "redirect:/profile";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteNote(Principal principal, @PathVariable Long bookId) {
        if (principal == null) return "redirect:/catalog";
        notesService.Delete(principal, bookId);
        return "redirect:/profile";
    }

    @GetMapping("/complete/{bookId}")
    public String bookHaveCompleted(Principal principal, @PathVariable Long bookId){
        if (principal == null) return "redirect:/catalog";
        notesService.Complete(principal, bookId);
        return "redirect:/profile";
    }

    @GetMapping("/continue/{bookId}")
    public String returnToRead(Model model, Principal principal, @PathVariable Long bookId){
        if (principal == null) return "redirect:/catalog";
        notesService.ReturnToRead(principal, bookId);
        return "redirect:/profile";
    }

}
