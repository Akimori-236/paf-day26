package nus.iss.tfip.pafday26.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

    // db.tv.distinct("genres")
    public List<String> getAllGenres() {
        return template.findDistinct(
                new Query(), FIELD_GENRES, COLLECTION_TV, String.class);
    }

    // db.tv.distinct("type")
    public List<String> getAllTypes() {
        return template.findDistinct(
                new Query(), FIELD_TYPE, COLLECTION_TV, String.class);
    }

    /*
     * db.tv.find({genres: "Drama"}).projection({
     * _id:0,
     * name:1,
     * rating:1
     * })
     */
    public List<Document> getByGenre(String genre) {
        // create a criteria
        Criteria criteria = Criteria.where(FIELD_GENRES).is(genre);

        // create a query
        Query query = Query.query(criteria);

        // PROJECTIONS
        query.fields().exclude(FIELD_OBJ_ID).include(FIELD_ID, FIELD_NAME, FIELD_URL, FIELD_RATING_AVG);

        // run the query, return a Document class instance, from tv collection/table
        List<Document> results = template.find(query, Document.class, COLLECTION_TV);
        for (Document doc : results) {
            System.out.println(doc.getString("name"));
        }
        return results;
    }

    /*
     * db.tv.find({type: "Animation"})
     * .sort({"rating.average": -1})
     * .projection({
     * _id:0,
     * id:1,
     * name:1,
     * "rating.average":1
     * .limit(20)
     * })
     */
    public List<Document> getByType(String type) {
        return getByType(type, 20, 0);
    }

    public List<Document> getByType(String type, int limit, int skip) {
        // create a criteria
        Criteria criteria = Criteria.where(FIELD_TYPE).regex(type, "i");

        // create a query
        Query query = Query.query(criteria)
                .with(Sort.by(Direction.ASC, FIELD_RATING_AVG))
                .limit(limit)
                .skip(skip);

        // PROJECTIONS
        query.fields().exclude(FIELD_OBJ_ID).include(FIELD_ID, FIELD_NAME, FIELD_URL, FIELD_RATING_AVG);

        // run the query, return a Document class instance, from tv collection/table
        List<Document> results = template.find(query, Document.class, COLLECTION_TV);
        for (Document doc : results) {
            System.out.println(doc.getString("name"));
        }
        return results;
    }

}
