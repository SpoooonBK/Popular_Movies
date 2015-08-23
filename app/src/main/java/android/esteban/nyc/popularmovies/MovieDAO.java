package android.esteban.nyc.popularmovies;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.net.URI;
import java.util.List;

/**
 * Created by Spoooon on 8/22/2015.
 */
public class MovieDAO {

    public static List<Movie> getMovieList(){
        JSONObject movieListData = getMovieData(buildURI());
        return MovieFactory.buildMovieList(movieListData);
    }

    public static JSONObject getMovieData(URI uri){
        JSONObject jsonObject = null;

        return jsonObject;
    }

    public static URI buildURI(){
        URI uri = null;
        return uri;
    }


}
