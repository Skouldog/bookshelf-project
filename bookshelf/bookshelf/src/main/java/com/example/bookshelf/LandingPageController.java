package com.example.bookshelf;


import com.example.bookshelf.repos.IAllBookRepository;
import com.example.bookshelf.repos.IQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.*;
import java.util.stream.Collectors;

@Controller
public class LandingPageController {

    private static final String RESULTS_PATH = "/getMappingResults";

    @Autowired
    private IAllBookRepository allBookRepository;
    Set<AllBooks> personalLibrary = new HashSet<>();// DIe eigenen Bücher, Dashboard Bücher
    Set<AllBooks> quotedBooks = new HashSet<AllBooks>(); //Bücher zu den es Quotes gibt


    @Autowired
    private IQuoteRepository quoteRepository; //Interface für alle Quotes
    List<Quotes> allMyQuotes = new ArrayList<>(); //Liste mit Quotes


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




    @GetMapping("/chosenBook/{title}")
    public String get (@PathVariable String title, Model model) {
        AllBooks searchedBook = allBookRepository.findByTitle(title);

        model.addAttribute("BOOK", searchedBook);

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
        return ("redirect:/dashboard");
    }

    @GetMapping("/quotes")
    public String quotes(Model model) {


        record BookWithQuotes(AllBooks book, List<Quotes> quotesList) {
        }

        List<BookWithQuotes> booksWithQuotes = new ArrayList<>();

        for (AllBooks quotedBook : quotedBooks) {
            booksWithQuotes.add(
                    new BookWithQuotes(
                            quotedBook,
                            quoteRepository.findAllQuotesByBookId(quotedBook.getId()
                            )
                    )
            );
        }

        System.out.println(booksWithQuotes.toString());

        model.addAttribute("books", booksWithQuotes);

        return "quotes";
    }



    @PostMapping("/addquotes") //
    public String addNewQuotes(RedirectAttributes redirectAttributes, @RequestParam("quote") String quote, String title, Model model) {
        AllBooks searchBook = allBookRepository.findByTitle(title);

        if (personalLibrary.contains(searchBook)) {
            Quotes newQuote = new Quotes(quote, searchBook);
            quoteRepository.save(newQuote);
            allMyQuotes.add(quoteRepository.findByQuote(quote));

            record BookWithQuotes(AllBooks book, List<Quotes> quotesList) {
            }

            List<BookWithQuotes> booksWithQuotes = new ArrayList<>();

            quotedBooks.add(allBookRepository.findByTitle(title)); //Bücher die quotes haben

            for (AllBooks quotedBook : quotedBooks) {
                booksWithQuotes.add(
                        new BookWithQuotes(
                                quotedBook,
                                quoteRepository.findAllQuotesByBookId(quotedBook.getId()
                                )
                        )
                );
            }


            model.addAttribute("books", booksWithQuotes);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Book not in Library");
        }

        return ("redirect:/quotes");
    }
    }





