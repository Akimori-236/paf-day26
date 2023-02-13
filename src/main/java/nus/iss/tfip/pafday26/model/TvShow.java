package nus.iss.tfip.pafday26.model;

import org.bson.Document;
import static nus.iss.tfip.pafday26.Constants.*;

import java.util.List;

public class TvShow {
    private int id;
    private String name;
    private String url;
    private Number rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Number getRating() {
        return rating;
    }

    public void setRating(Number rating) {
        this.rating = rating;
    }

    public static TvShow create(Document doc) {
        TvShow show = new TvShow();
        show.setId(doc.getInteger(FIELD_ID));
        show.setName(doc.getString(FIELD_NAME));
        show.setUrl(doc.getString(FIELD_URL));
        // System.out.println(doc.getInteger(FIELD_ID) + doc.getString(FIELD_NAME) +
        // doc.getString(FIELD_URL));
        System.out.println(show.getId() + show.getName() + show.getUrl());
        return show;
    }

    public static TvShow createView2(Document doc) {
        TvShow show = new TvShow();
        show.setId(doc.getInteger(FIELD_ID));
        show.setName(doc.getString(FIELD_NAME));
        show.setRating(doc.getDouble(FIELD_AVG));
        System.out.println(show.getId() + show.getName() + show.getUrl());
        return show;
    }

    public static TvShow createSummary(Document doc) {
        TvShow show = new TvShow();
        show.setId(doc.getInteger(FIELD_ID));
        show.setName(doc.getString(FIELD_NAME));
        show.setRating(doc.);

        // extract the inner object
        // Document d = (Document) doc.get(FIELD_RATING);
        // try {
        //     show.setRating(d.getDouble(FIELD_AVG));
        // } catch (Exception e) {
        //     show.setRating(0d);
        // }

        System.out.println(show.getId() + show.getName() + show.getUrl());
        return show;
    }
}