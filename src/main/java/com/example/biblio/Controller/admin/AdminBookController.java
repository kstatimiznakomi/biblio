package com.example.biblio.Controller.admin;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.model.Book;
import com.example.biblio.model.UserRole;
import com.example.biblio.service.BookService;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin/books")
@AllArgsConstructor
public class AdminBookController {
    private final BookService bookService;
    private final UserService userService;

    @GetMapping("")
    public String books(Model model, Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)){
            model.addAttribute("books", bookService.getAllBooks());
            model.addAttribute("book", new BookDTO());
            return "admin/books";
        }
        return "redirect:/login";
    }

    @GetMapping("/{bookId}/delete")
    public String deleteBook(@PathVariable Long bookId, Principal principal)
    {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)){
            bookService.deleteBookById(bookId);
            return "redirect:/admin/books";
        }
        return "redirect:/login";
    }

    @PostMapping("/{bookId}/edit")
    public String saveBookEdit(@ModelAttribute Book book, @PathVariable Long bookId,
                               Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)) {
            book.setId(bookId);
            bookService.save(book);
            return "redirect:/admin/books";
        }
        return "redirect:/login";
    }

    @PostMapping("/add")
    public String addBook(BookDTO bookDTO, Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)) {
            bookService.save(bookDTO);
            return "redirect:/admin/books";
        }
        return "redirect:/login";
    }
}
