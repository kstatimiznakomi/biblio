package com.example.biblio.Controller.admin;

import com.example.biblio.model.Publisher;
import com.example.biblio.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/publishers")
@AllArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;
    @GetMapping("")
    public String publishers(Model model) {
        model.addAttribute("publishers", publisherService.getAllPublishers());
        return "admin/publishers";
    }

    @GetMapping("/{publisherId}/delete")
    public String deletePublisher(@PathVariable Long publisherId) {
        publisherService.deletePublisherById(publisherId);
        return "redirect:/admin/publishers";
    }

    @PostMapping("/{publisherId}/edit")
    public String savePublisherEdit(@ModelAttribute Publisher publisher, @PathVariable Long publisherId) {
        publisher.setId(publisherId);
        publisherService.save(publisher);
        return "redirect:/admin/publishers";
    }
}
