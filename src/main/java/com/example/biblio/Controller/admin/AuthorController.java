package com.example.biblio.Controller.admin;

import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.dto.PublisherDTO;
import com.example.biblio.model.Author;
import com.example.biblio.model.UserRole;
import com.example.biblio.service.AuthorService;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final UserService userService;

    @GetMapping("")
    public String publishers(Model model, Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)){
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("author", new AuthorDTO());
            return "admin/authors";
        }
        return "redirect:/login";
    }

    @GetMapping("/{authorId}/delete")
    public String deleteAuthor(@PathVariable Long authorId, Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)){
            authorService.deleteAuthorById(authorId);
            return "redirect:/admin/authors";
        }
        return "redirect:/login";
    }

    @PostMapping("/{authorId}/edit")
    public String saveAuthorEdit(@ModelAttribute Author author, @PathVariable Long authorId,
                                 Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)){
            author.setId(authorId);
            authorService.save(author);
            return "redirect:/admin/authors";
        }
        return "redirect:/login";
    }

    @PostMapping("/add")
    public String addAuthor(AuthorDTO authorDTO, Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)) {
            authorService.save(authorDTO);
        }
        return "redirect:/login";
    }
}
