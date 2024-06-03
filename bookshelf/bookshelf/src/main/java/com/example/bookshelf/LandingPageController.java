package com.example.bookshelf;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class LandingPageController {

    private static final String RESULTS_PATH = "/getMappingResults";

    HashMap<String, book> allBooks = new HashMap<>();
    HashMap<String, book> myBooks = new HashMap<>();

    @PostConstruct
    public void init() {
        book Harry = new book("Harry Potter", "J.K Rowling", 399, 0);
        allBooks.put("Harry Potter", Harry);
        book Lord = new book("Lord of the Rings", "KeinPlan", 800, 0);
        allBooks.put("Lord of the Rings", Lord);
        book Hitchhiker = new book("Hitchhikers Guide to the Galaxy", "Douglas Adam", 200, 199);
        allBooks.put("Hitchhiker Guide", Hitchhiker);
        book Hunger = new book("The Hunger Games", "Kein Plan", 300, 14);
        allBooks.put("The Hunger Games", Hunger);
        book Thirst = new book("The Hunger Games", "Kein Plan", 300, 14);
        allBooks.put("Thirt", Thirst);

    }

    @GetMapping(RESULTS_PATH)
    public String getMappingResults(RedirectAttributes redirectAttributes, String name) {
       if(allBooks.containsKey(name)) {
           myBooks.put(name, allBooks.get(name));
       }else {
           redirectAttributes.addFlashAttribute("errorMessage", "Book not found");
       }
    return "redirect:/dashboard";
    }




    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("books", myBooks.values());
        return "dashboard";
    }


    @GetMapping("/impressum")
    public String impressum(Model model) {

        return "impressum";
    }

    @GetMapping("/quotes")
    public String quotes(Model model) {

        return "quotes";
    }

    @GetMapping("/autocomplete")
    @ResponseBody
    public List<String> autocomplete(@RequestParam String query) {
        return allBooks.keySet().stream()
                .filter(title -> title.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}