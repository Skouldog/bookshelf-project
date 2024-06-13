package com.example.bookshelf;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class LandingPageController {

    private static final String RESULTS_PATH = "/getMappingResults";

    @Autowired
    private IAllBookRepository allBookRepository; // Interface für die Bücher
    List<AllBooks> personalLibrary = new ArrayList<>();// DIe eigenen Bücher, Dashboard Bücher


    @GetMapping(RESULTS_PATH) //Search Querery um Bücher zu finden
    public String getMappingResults(RedirectAttributes redirectAttributes, String name) {
        AllBooks searchedBook = allBookRepository.findByTitle(name);
        if (personalLibrary.contains(searchedBook)) { //Duplicated verhindern
            redirectAttributes.addFlashAttribute("errorMessage", "Book is already in Library");
        } else if (searchedBook != null && searchedBook.getTitle().equals(name)) { // Buch finden und hinzufügen
            personalLibrary.add(allBookRepository.findByTitle(name));
            redirectAttributes.addFlashAttribute("errorMessage", "Book has been added");
        } else { //error / buch nicht gefunden
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


    @GetMapping("/autocomplete")  // Autocomplete für die Suche
    @ResponseBody
    public List<String> autocomplete(@RequestParam("query") String query) {
        List<AllBooks> books = allBookRepository.findByTitleContainingIgnoreCase(query);
        return books.stream()
                .map(AllBooks::getTitle)
                .collect(Collectors.toList());
    }

    @GetMapping("/addBook")// Teil vom Live Reload, leitet auf die SUbpage weiter um das Buch zu adden
    public String addBook(@RequestParam("title") String title, Model model) {
        model.addAttribute("title", title);
        return "addNewBook";
    }

    @PostMapping("/addNewBook") // Hier wird das Buch abgespeichert
    public String addNewBook(@RequestParam("title") String title,
                             @RequestParam("author") String author,
                             @RequestParam("PagesCurrent") int pagesCurrent,
                             @RequestParam("PagesTotal") int pagesTotal, Model model)
    {
        AllBooks newBook = new AllBooks(title, author, pagesTotal, pagesCurrent);
        allBookRepository.save(newBook);
        personalLibrary.add(allBookRepository.findByTitle(title));
        return("redirect:/dashboard");
    }

}