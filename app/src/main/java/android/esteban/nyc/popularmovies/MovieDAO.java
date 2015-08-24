package android.esteban.nyc.popularmovies;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * Created by Spoooon on 8/22/2015.
 */
public class MovieDAO {

    private static final String LOG_TAG = "POPULAR_MOVIES";

    public static List<Movie> getMovieList(String apiKey){
        JSONObject movieListData = null;
        try {
            movieListData = getMovieData(buildURL(apiKey));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return MovieFactory.buildMovieList(movieListData);
    }

    public static JSONObject getMovieData(URL url) {
        JSONObject movieData = null;

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");

            }
            Log.d(LOG_TAG, line);
            movieData.getJSONObject(line);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return movieData;
    }

    private static URL buildURL(String apiKey) throws MalformedURLException {
        String url;
        Uri.Builder builder = new Uri.Builder();

        final String MOVIEDB_BASE_URI_ = "api.themoviedb.org";


        final String SORT_BY = "sort_by" ;
        final String POPULARITY = "popularity.desc";
        final String API = "api";

        Log.d(LOG_TAG, "IN buildURL: " + apiKey);

        builder.scheme("http")
                .authority(MOVIEDB_BASE_URI_)
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter(SORT_BY, POPULARITY)
                .appendQueryParameter(API, "[" + apiKey + "]");
//            "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=[]"

        Log.d(LOG_TAG, "URL: " + builder.build().toString());

        return new URL(builder.build().toString());
    }

}
