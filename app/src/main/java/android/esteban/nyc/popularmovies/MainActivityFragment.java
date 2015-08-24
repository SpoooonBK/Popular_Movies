package android.esteban.nyc.popularmovies;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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

        ImageView picView = (ImageView)rootView.findViewById(R.id.picView);

        if(picView != null){
            Picasso.with(getActivity()).load("http://i.imgur.com/DvpvklR.png")
                .into(picView);
        }
//        TODO: TEST CODE
        String apikey = PropertyReader.readProperty(getActivity());
        Log.d("Popular Movies","API KEY: " + apikey );
        GetMoviesTask getMoviesTask = new GetMoviesTask();
        getMoviesTask.execute(apikey);
        return rootView;
    }
}
