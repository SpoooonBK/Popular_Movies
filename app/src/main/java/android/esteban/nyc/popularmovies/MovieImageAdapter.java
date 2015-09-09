package android.esteban.nyc.popularmovies;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Spoooon on 8/25/2015.
 */
public class MovieImageAdapter extends BaseAdapter {

    private Context context;
    private List<Movie> movies;

    public MovieImageAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(context).load(movies.get(position).getPosterPath()).into(imageView);
        
//Todo fix gridview size
//        imageView.setLayoutParams(
//                new GridView.LayoutParams(imageView.getWidth(), imageView.getHeight()));

        imageView.isFocusable();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(context, DetailActivity.class);
                detailIntent.putExtra("MOVIE_TITLE", movies.get(position).getTitle());
                detailIntent.putExtra("RELEASE_DATE", movies.get(position).getReleaseDate());
                detailIntent.putExtra("OVERVIEW", movies.get(position).getOverview());
                detailIntent.putExtra("POSTER_PATH", movies.get(position).getPosterPath());
                context.startActivity(detailIntent);
            }
        });




        return imageView;
    }


}
