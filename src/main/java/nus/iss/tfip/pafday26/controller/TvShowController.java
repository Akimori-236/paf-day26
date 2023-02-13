package nus.iss.tfip.pafday26.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nus.iss.tfip.pafday26.model.TvShow;
import nus.iss.tfip.pafday26.service.TvShowService;

@Controller
@RequestMapping(path="/")
public class TvShowController {

    @Autowired
    TvShowService tvSvc;

    @GetMapping(path = "/tvshow")
    public String getTvShow(@RequestParam(defaultValue = "English") String lang, Model model) {
        // capitalise lang??
        List<TvShow> showList = tvSvc.findAllByLanguage(lang);
        model.addAttribute("tvshows", showList);
        model.addAttribute("lang", lang);
        return "displayshows";
    }

    // View1
    @GetMapping
    public String landingPage(Model model) {
        List<String> genres = tvSvc.getAllGenres();
        System.out.println(genres);
        model.addAttribute("genres", genres);
        return "index";
    }
}
