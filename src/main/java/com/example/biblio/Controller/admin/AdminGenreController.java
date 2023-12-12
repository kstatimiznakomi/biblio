package com.example.biblio.Controller.admin;

import com.example.biblio.dto.GenresDTO;
import com.example.biblio.dto.PublisherDTO;
import com.example.biblio.model.Genres;
import com.example.biblio.model.Publisher;
import com.example.biblio.model.UserRole;
import com.example.biblio.service.GenreService;
import com.example.biblio.service.PublisherService;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin/genres")
@AllArgsConstructor
public class AdminGenreController {
    private final GenreService genreService;
    private final UserService userService;
    @GetMapping("")
    public String genres(Model model, Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)){
            model.addAttribute("genres", genreService.getAllGenres());
            model.addAttribute("genre", new GenresDTO());
            return "admin/genres";
        }
        return "redirect:/login";
    }

    @GetMapping("/{genreId}/delete")
    public String deletePublisher(@PathVariable Long genreId, Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)){
            genreService.deleteGenreById(genreId);
            return "redirect:/admin/genres";
        }
        return "redirect:/login";
    }

    @PostMapping("/{genreId}/edit")
    public String saveGenreEdit(@ModelAttribute Genres genre, @PathVariable Long genreId,
                                Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)){
            genre.setId(genreId);
            genreService.save(genre);
            return "redirect:/admin/genres";
        }
        return "redirect:/login";
    }

    @PostMapping("/add")
    public String addGenre(GenresDTO genreDTO, Principal principal) {
        if(principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)) {
            genreService.save(genreDTO);

            return "redirect:/admin/genres";
        }
        return "redirect:/login";
    }
}
