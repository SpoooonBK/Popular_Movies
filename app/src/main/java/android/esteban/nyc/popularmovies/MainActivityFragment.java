package android.esteban.nyc.popularmovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        String apikey = PropertyReader.readProperty(getActivity());
        Log.d("Popular Movies","API KEY: " + apikey );

        List<Movie> movies = null;
        GetMoviesTask getMoviesTask = new GetMoviesTask();

        getMoviesTask.execute(apikey);
        try {
            movies = getMoviesTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (movies != null){
            populateGrid(rootView, (GridLayout) rootView.findViewById(R.id.movieGrid), movies);
        }

        return rootView;
    }

    private void populateGrid(final View rootview, GridLayout grid, List<Movie> movies){

        int rowCount;
        int colCount = 2;

        rowCount = Math.round(movies.size()/colCount);

        grid.setColumnCount(colCount);
        grid.setRowCount(rowCount);

        for(final Movie movie: movies){
            final ImageView imageView = new ImageView(rootview.getContext());
            grid.addView(imageView);
            Picasso.with(getActivity()).load(movie.getPosterPath())
                    .into(imageView);
            imageView.isFocusable();
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailIntent = new Intent(rootview.getContext(),DetailActivity.class);
                    detailIntent.putExtra("MOVIE_TITLE", movie.getTitle() );
                    detailIntent.putExtra("RELEASE_DATE", movie.getReleaseDate());
                    detailIntent.putExtra("OVERVIEW", movie.getOverview());
                    detailIntent.putExtra("POSTER_PATH", movie.getPosterPath());
                    startActivity(detailIntent);
                }
            });
        }

    }
}
