package com.example.bookshelf;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LandingPageController {

HashMap<String, book> allBooks = new HashMap<>();

@PostConstruct
public void init() {
    book Harry = new book("Harry Potter","J.K Rowling",399,0);
    allBooks.put("Harry Potter", Harry);
    book Lord = new book("Lord of the Rings","KeinPlan",800,0);
    allBooks.put("Lord of the Rings", Lord);
    book Hitchhiker = new book("Hitchhikers Guide to the Galaxy","Douglas Adam",200,199);
    allBooks.put("Hitchhiker Guide", Hitchhiker);
    book Hunger = new book("The Hunger Games","Kein Plan",300,14);
allBooks.put("The Hunger Games", Hunger);

}

    @GetMapping("/")
    public String dashboard(Model model) {
model.addAttribute("books", allBooks.values());
        return "dashboard";
    }
}
