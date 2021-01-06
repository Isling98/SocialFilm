package com.example.yndlingsfilm;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.Model.News;
import com.example.yndlingsfilm.util.Constants;

import java.util.ArrayList;

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
    private RecyclerView homeFeed;
    private HomeFeedAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


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
                Log.d(TAG, "onClick: " + movie.getOverview());
                Fragment fragment = new WriteReviewFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("movie", movie);
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left).replace(R.id.fragment_nagivation, fragment)
                        .addToBackStack(null).commit();
            }
        });

        // blot for start. vi kontrollerer for alle vennr der har kommenteret på filmen og adder dem i stedet.
        final ArrayList<News> aNews = new ArrayList<>();
        for(int i=0; i<10; i++) {
            aNews.add(new News(R.drawable.profile_pic, R.drawable.movie_pic,
                    "Asger Åkanden:", "2hrs", "Harry Potter", 4));
        }

        homeFeed = (RecyclerView) view.findViewById(R.id.friendsComments);
        homeFeed.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        homeFeed.setLayoutManager(layoutManager);

        mAdapter = new HomeFeedAdapter(aNews);
        homeFeed.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(position -> {
            final Dialog dialog = new Dialog(getContext(), R.style.AnimationDialog);
            dialog.setContentView(R.layout.popup_news_details);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            // sætter popup til at fylde parent
            //WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            //lp.copyFrom(dialog.getWindow().getAttributes());
            //lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            //lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            //dialog.getWindow().setAttributes(lp);

            ImageView moviePic = dialog.findViewById(R.id.moviePic);
            TextView text = dialog.findViewById(R.id.text);
            RatingBar rating = dialog.findViewById(R.id.rating);
            ImageView closeButton = dialog.findViewById(R.id.closeButton);

            moviePic.setImageResource(aNews.get(position).getMoviePicResource());
            text.setText(aNews.get(position).getTime());
            rating.setRating(aNews.get(position).getRating());

            closeButton.setOnClickListener(view1 -> dialog.dismiss());

            moviePic.setOnClickListener(view1 -> {
                //open moviedetails fragment
                getFragmentManager().beginTransaction().replace
                        (R.id.fragment_nagivation, new MovieDetailsFragment() )
                        .addToBackStack(null).commit();
                dialog.dismiss();
            });
            dialog.show();
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