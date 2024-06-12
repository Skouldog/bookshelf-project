package com.example.bookshelf;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class LandingPageController {

    private static final String RESULTS_PATH = "/getMappingResults";

    @Autowired
    private IAllBookRepository allBookRepository;

    List<AllBooks> personalLibrary = new ArrayList<>();



    @GetMapping(RESULTS_PATH) //Search Querery um Bücher zu finden
    public String getMappingResults(RedirectAttributes redirectAttributes, String name) {
        AllBooks searchedBook = allBookRepository.findByTitle(name);
        if (personalLibrary.contains(searchedBook)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Book is already in Library");
        } else if (searchedBook != null && searchedBook.getTitle().equals(name)) {
            personalLibrary.add(allBookRepository.findByTitle(name));
            redirectAttributes.addFlashAttribute("errorMessage", "Book has been added");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Book not found");
        }
        return "redirect:/dashboard";
    }


    @GetMapping("/") //Redirect von leerer Webadresse zum Dashboard
    public String redirect(Model model) {
        return "redirect:/dashboard";
    }


    @GetMapping("/dashboard")//Dashboard anzeigen von den eigenen Büchern
    public String dashboard(Model model) {

        model.addAttribute("books", personalLibrary);
        return "dashboard";
    }


    @GetMapping("/impressum")// Impressum
    public String impressum(Model model) {

        return "impressum";
    }

    @GetMapping("/quotes")
    public String quotes(Model model) {

        return "quotes";
    }

    @GetMapping("/chosenBook")
    public String chosenBook(Model model) {

        return "chosenBook";
    }

/*
    @GetMapping("/autocomplete") //Auto Suggestion mithilfe von JavaScript
    @ResponseBody
    public List<String> autocomplete(@RequestParam String query) {
        return allBooks.keySet().stream()
                .filter(title -> title.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

 */
}