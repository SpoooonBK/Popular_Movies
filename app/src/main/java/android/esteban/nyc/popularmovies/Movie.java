package android.esteban.nyc.popularmovies;

import java.io.Serializable;

/**
 * Created by Spoooon on 8/22/2015.
 */
public class Movie implements Serializable{

    private String title;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private final String BASE_URL = "http://image.tmdb.org/t/p/";
    private final String BASE_PIC_SIZE ="w185";

    public Movie(String title, String overview, String releaseDate, String posterPath) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        String url = BASE_URL + BASE_PIC_SIZE + posterPath;
        return url;
    }
}
