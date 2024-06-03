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


    @GetMapping(RESULTS_PATH) //Search Querery
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
    @GetMapping("/chosenBook")
    public String chosenBook(Model model) {

        return "chosenBook";
    }


    @GetMapping("/autocomplete") //Auto Suggestion
    @ResponseBody
    public List<String> autocomplete(@RequestParam String query) {
        return allBooks.keySet().stream()
                .filter(title -> title.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}