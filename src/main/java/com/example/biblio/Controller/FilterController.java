package com.example.biblio.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FilterController {
    @RequestMapping(value = "/filter/by-author-genre-publisher", method = RequestMethod.GET)
    @ResponseBody()
    String list(){
        return "hello";
    }

}
