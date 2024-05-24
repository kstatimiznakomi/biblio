package com.example.biblio.Controller.admin;

import com.example.biblio.dto.GenresDTO;
import com.example.biblio.model.Genres;
import com.example.biblio.model.UserRole;
import com.example.biblio.service.GenreService;
import com.example.biblio.service.UserService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
            return "redirect:/admin/genres/1";
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

    /////////////////
    // API SECTION //
    /////////////////

    @PostMapping(value = "/api/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addGenre(@RequestBody Genres genre, Principal principal) {
        if(principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)) {
            GenresDTO genresDTO = new GenresDTO();
            BeanUtils.copyProperties(genre, genresDTO);
            genreService.save(genresDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping(value = "/api/{genreId}/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteGenre(@PathVariable Long genreId, Principal principal) {
        if(principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)) {
            genreService.deleteGenreById(genreId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping(value = "/api/{genreId}/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editGenre(@RequestBody Genres genre, @PathVariable Long genreId, Principal principal) {
        if(principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)) {
            genre.setId(genreId);
            genreService.save(genre);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
