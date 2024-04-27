package com.example.biblio.Controller;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import com.example.biblio.model.PageType;
import com.example.biblio.model.UserStatus;
import com.example.biblio.service.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/searchhh")
@Lazy
public class SearchController {
    private final BookService bookService;
    private final PageService pageService;
    private final UserService userService;

    @GetMapping("")
    public String search(Principal principal, Model model, @ModelAttribute SearchParamsDTO dto){
        if (principal != null) {
            model.addAttribute("user", userService.getUserByName(principal.getName()));
            model.addAttribute("ifUserSigned", userService.ifUserSigned(principal));
        }
        Page<Book> page = bookService.getAllPage(dto.getPage());
        model.addAttribute("search", new SearchParamsDTO());
        model.addAttribute("params", dto);
        model.addAttribute("foundBooks", page.getContent());
        model.addAttribute(
                "currentPage", pageService.GetBiggerLower(dto.getPage(), page.getTotalPages())
        );
        model.addAttribute("nameType", PageType.Name.toString());
        model.addAttribute("maxPage",
                pageService.Max(dto.getPage(), bookService.getAllPage(dto.getPage()).getTotalPages())
        );

        model.addAttribute("maxPage",
                pageService.Max(dto.getPage(), bookService.getAllPage(dto.getPage()).getTotalPages())
        );
        model.addAttribute("minPage", pageService.Min(dto.getPage()));
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        return "search";
    }
}
