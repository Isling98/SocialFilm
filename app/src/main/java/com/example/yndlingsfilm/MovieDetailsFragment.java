package com.example.yndlingsfilm;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.util.Constants;
import com.google.android.material.textview.MaterialTextView;

public class MovieDetailsFragment extends Fragment {
    private static final String TAG = "MovieDetailsFragment";
    ImageView imdbLink;
    TextView movieTitle;
    ImageView moviePic;
    TextView overview;
    TextView releaseDate;
    TextView ratingInText;
    RatingBar rating;
    Button writeReviewButton;
    TextView movieGenres;
    Movie movie;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        imdbLink = view.findViewById(R.id.imdbLink);
        movieTitle = view.findViewById(R.id.movieTitle);
        moviePic = view.findViewById(R.id.moviePic);
        overview = view.findViewById(R.id.overview);
        releaseDate = view.findViewById(R.id.releaseDate);
        rating = view.findViewById(R.id.rating);
        writeReviewButton = view.findViewById(R.id.writeReviewButton);
        // runtime findes ikke i api, skal den bare slettes så?
        ratingInText = view.findViewById(R.id.ratingInText);
        movieGenres = view.findViewById(R.id.movieGenres);



        Bundle bundle = getArguments();
        if(bundle != null){
            movie = bundle.getParcelable("movie");
            Log.d(TAG, "onCreateView: bundle igennem");
        } else{
            Log.d(TAG, "onCreateView: bundle fejl");
        }

        // sets all
        movieTitle.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getRelease_date());
        rating.setRating(movie.getVote_average());
        ratingInText.setText(String.valueOf(movie.getVote_average()));
        movieGenres.setText(getAllGenres());
        // sets moviePoster
        Glide.with(this).load(Constants.BASE_URL_IMG + movie.getPoster_path())
                .into(moviePic);





        writeReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new WriteReviewFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("movie", movie);
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left).replace(R.id.fragment_nagivation, fragment)
                        .addToBackStack(null).commit();
            }
        });

       /* imdbLink.setOnClickListener(view1 -> {
            //åben imdb-link.
            bgThread = Executors.newSingleThreadExecutor(); // håndtag til baggrundstråd
            uiThread = new Handler(Looper.getMainLooper()); // håndtag til forgrundstråd

            bgThread.execute(()->{
                try{
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.imdb.com/title/" + movie.getId()));
                    startActivity(browserIntent);
                } catch (Exception e){
                    // behandl fejl. evt vis cashed svar.
                    e.printStackTrace();
                }
            });
        });*/


        return view;
    }

    private String getAllGenres(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < movie.getGenre_ids().length; i++){
            String genre = getGenre(movie.getGenre_ids()[i]);
            if(i == 0){
                sb.append(genre);
            } else{
                sb.append(" | " + genre );
            }
        }
        return sb.toString();
    }

    private String getGenre(int id){
        switch (id){
            case 28:
                return "Action";
            case 12:
                return "Adventure";
            case 16:
                return "Animation";
            case 35:
                return "Comedy";
            case 80:
                return "Crime";
                case 99:
                return "Documentary";
            case 18:
                return "Drama";
            case 10751:
                return "Family";
            case 14:
                return "Fantasy";
            case 36:
                return "History";
            case 27:
                return "Horro";
            case 10402:
                return "Music";
            case 9648:
                return "Mystery";
            case 10749:
                return "Romance";
            case 878:
                return "Science Fiction";
            case 10770:
                return "Tv Movie";
            case 53:
                return "Thriller";
            case 10752:
                return "War";
            case 37:
                return "Western";
            default: return "error loading genre...";
        }
    }
}