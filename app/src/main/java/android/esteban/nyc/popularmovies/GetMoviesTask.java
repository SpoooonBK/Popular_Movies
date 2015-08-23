package android.esteban.nyc.popularmovies;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Spoooon on 8/23/2015.
 */
public class GetMoviesTask extends AsyncTask<Void, Void, List<Movie>> {


    @Override
    protected List<Movie> doInBackground(Void... params) {
        return MovieDAO.getMovieList();
    }
}
