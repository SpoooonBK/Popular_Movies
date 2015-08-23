package android.esteban.nyc.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * Created by Spoooon on 8/22/2015.
 */
public class MovieDAO {

    private static final String LOG_TAG = "POPULAR_MOVIES";

    public static List<Movie> getMovieList(){
        JSONObject movieListData = null;
        try {
            movieListData = getMovieData(buildURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return MovieFactory.buildMovieList(movieListData);
    }

    public static JSONObject getMovieData(URL url){
        JSONObject jsonObject = null;


        return jsonObject;
    }

    private static URL buildURL() throws MalformedURLException {
        String url;
        Uri.Builder builder = new Uri.Builder();

        final String MOVIEDB_BASE_URI_ = "api.themoviedb.org";


        final String SORT_BY = "sort_by" ;
        final String POPULARITY = "popularity.desc";
        final String API = "api";
        String API_KEY = getAPIKey();

        builder.scheme("http")
                .authority(MOVIEDB_BASE_URI_)
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter(SORT_BY, POPULARITY)
                .appendQueryParameter(API, API_KEY);
//            "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="

        return new URL(builder.build().toString());
    }

    private static String getAPIKey(){
        final String PROPERTIES_KEY_FOR_API = "key";
        String apiKey = null;

        Properties properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("assets/API.properties");
            properties.load(inputStream);
            apiKey = properties.getProperty("PROPERTIES_KEY_FOR_API");
            Log.d(LOG_TAG, "API KEY = " + apiKey);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return apiKey;
    }


}
