package nus.iss.tfip.pafday26.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nus.iss.tfip.pafday26.model.TvShow;
import nus.iss.tfip.pafday26.service.TvShowService;

@Controller
@RequestMapping
public class TvShowController {

    @Autowired
    TvShowService tvSvc;

    @GetMapping(path = "/tvshow")
    public String getTvShow(@RequestParam(defaultValue = "English") String lang, Model model) {
        // capitalise lang??
        List<TvShow> showList = tvSvc.findAllByLanguage(lang);
        model.addAttribute("tvshows", showList);
        model.addAttribute("filterTerm", lang);
        return "displayshows";
    }

    // View 1 - genre
    @GetMapping(path = "/genre")
    public String landingPage(Model model) {
        List<String> genres = tvSvc.getAllGenres();
        System.out.println(genres);
        model.addAttribute("genres", genres);
        return "genres";
    }

    // View 1 - type
    @GetMapping(path = { "/", "/index.html", "/type"})
    public String getAllTypes(Model model) {
        List<String> types = tvSvc.getAllTypes();
        model.addAttribute("types", types);
        return "types";
    }

    // View 2 - genre
    @GetMapping(path = "genre/{genre}")
    public String getByGenre(@PathVariable String genre, Model model) {
        List<TvShow> showList = tvSvc.getByGenre(genre);

        model.addAttribute("tvshows", showList);
        model.addAttribute("filterTerm", genre);
        return "view2";
    }

    // View 2 - type
    @GetMapping(path = "/type/{type}")
    public String getByType(@PathVariable String type, Model model) {
        List<TvShow> showList = tvSvc.getByType(type);

        model.addAttribute("tvshows", showList);
        model.addAttribute("filterTerm", type);
        return "view2";
    }
}
