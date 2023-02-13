package nus.iss.tfip.pafday26.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static nus.iss.tfip.pafday26.Constants.*;

@Repository
public class TvShowRepository {

    @Autowired
    private MongoTemplate template;

    // db.tv.find({language: "English"})
    public List<Document> findByLanguage(String lang) {
        // create a criteria
        // Criteria criteria = Criteria.where(FIELD_LANGUAGE).is(lang); //.and()
        Criteria criteria = Criteria.where(FIELD_LANGUAGE).regex(lang, "i"); // for regex LIKE/ignorecase

        // create a query
        Query query = Query.query(criteria);

        // run the query, return a Document class instance, from tv collection/table
        List<Document> results = template.find(query, Document.class, COLLECTION_TV);

        return results;
    }

    public List<String> getAllGenres() {
        return template.findDistinct(
                new Query(), FIELD_GENRES, COLLECTION_TV, String.class);
    }
}
