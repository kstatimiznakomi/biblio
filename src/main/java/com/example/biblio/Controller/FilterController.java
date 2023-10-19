package com.example.biblio.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class FilterController {
    @RequestMapping(value = "/filter/by-author-genre-publisher", method = RequestMethod.GET)
    @ResponseBody()
    String list(){
        return "hello";
    }

}
