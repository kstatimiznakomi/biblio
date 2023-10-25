package com.example.biblio.Controller;

import com.example.biblio.dto.JournalNotesDTO;
import com.example.biblio.model.Book;
import com.example.biblio.model.JournalNotes;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import com.example.biblio.service.JournalNotesService;
import com.example.biblio.service.PageService;
import com.example.biblio.service.ReaderTicketService;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        return "redirect:/books/1";
    }

    @GetMapping("/{pageNumber}")
    public String getMyBooks(Model model, Principal principal, @PathVariable int pageNumber) {
        if (principal == null) return "redirect:/catalog";
        User user = userService.getUserByName(principal.getName());
        ReaderTicket ticket = readerTicketService.getTicketByUser(user);
        Page<JournalNotes> page = notesService.getAllPageByTicket(pageNumber, ticket);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        int curPage = pageService.GetBiggerLower(pageNumber, totalPages);
        List<JournalNotes> notes = page.getContent();
        model.addAttribute("notes", notes);
        model.addAttribute("currentPage", curPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        return "my-books";
    }

    @GetMapping("/add/{bookId}")
    public String addBookToTicket(Principal principal, @PathVariable Long bookId) {
        if (principal == null) return "redirect:/catalog";
        notesService.Save(principal, bookId);
        return "redirect:/books";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteNote(Principal principal, @PathVariable Long bookId) {
        if (principal == null) return "redirect:/catalog";
        notesService.Delete(principal, bookId);
        return "redirect:/books";
    }
}
