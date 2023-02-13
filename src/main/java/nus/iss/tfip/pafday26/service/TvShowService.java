package nus.iss.tfip.pafday26.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.tfip.pafday26.model.TvShow;
import nus.iss.tfip.pafday26.repository.TvShowRepository;

@Service
public class TvShowService {

    @Autowired
    private TvShowRepository tvRepo;

    public List<TvShow> findAllByLanguage(String lang) {
        return tvRepo.findByLanguage(lang)
                .stream()
                .map(v -> TvShow.create(v))
                .toList();
        // create a tvshow for every document
    }

    public List<String> getAllGenres() {
        return tvRepo.getAllGenres();
    }
}
