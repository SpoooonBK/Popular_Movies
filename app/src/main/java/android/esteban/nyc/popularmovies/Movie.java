package android.esteban.nyc.popularmovies;

/**
 * Created by Spoooon on 8/22/2015.
 */
public class Movie {

    private String title;
    private String overview;
    private String releaseDate;
    private String posterPath;

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
        return posterPath;
    }
}
