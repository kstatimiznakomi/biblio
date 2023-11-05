package com.example.biblio.Controller.admin;

import com.example.biblio.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
