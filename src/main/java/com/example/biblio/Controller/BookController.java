package com.example.biblio.Controller;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.dto.BookParamsDTO;
import com.example.biblio.model.UserRole;
import com.example.biblio.model.UserStatus;
import com.example.biblio.service.BookService;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/book")
@AllArgsConstructor
@Lazy
public class BookController {
    private final BookService bookService;
    private final UserService userService;

    @GetMapping("::")
    public String aboutBook(Model model, @ModelAttribute("book") BookDTO bookDTO, @PathVariable Long bookId, Principal principal){
        if(principal != null) model.addAttribute("user", userService.getUserByName(principal.getName()));
        BookDTO book = bookService.getBookPage(bookId);
        model.addAttribute("book", book);
        return "about-book";
    }

    @GetMapping("/add")
    public String addBookPage(Model model, Principal principal){
        if(principal != null && userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)) {
            model.addAttribute("user", userService.getUserByName(principal.getName()));
            model.addAttribute("book", new BookParamsDTO());
            return "book-add";
        }
        return "redirect:/login";
    }

    @PostMapping("/adding")
    public String adding(@ModelAttribute BookParamsDTO dto, Model model, Principal principal){
        if(principal != null && userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)){
            model.addAttribute("user", userService.getUserByName(principal.getName()));
            //bookService.Create(dto);
            return "redirect:/book/add";
        }
        return "redirect:/login";
    }
}
