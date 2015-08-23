package android.esteban.nyc.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 *
 * Created by Spoooon on 8/22/2015.
 */
public class MovieFactory {

    private static final String ARRAY_KEY = "results";
    private static final String TITLE = "title";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "release_date";
    private static final String POSTER_PATH = "poster_path";

    public static List<Movie> buildMovieList (JSONObject jsonObject) {
        List<Movie> movieList = null;

        try {
            JSONArray movieArray = jsonObject.getJSONArray(ARRAY_KEY);

            for(int i = 0; i< movieArray.length(); i++){
                Movie movie = buildMovie(movieArray.getJSONObject(i));
                movieList.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieList;
    }

    public static Movie buildMovie (JSONObject movieData) throws JSONException {

        String title = movieData.getString(TITLE);
        String overview = movieData.getString(OVERVIEW);
        String releaseDate = movieData.getString(RELEASE_DATE);
        String posterPath = movieData.getString(POSTER_PATH);


        Movie movie =  new Movie(title,overview,releaseDate,posterPath );
        return movie;
    }
}
