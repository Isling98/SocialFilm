package com.example.yndlingsfilm.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.Model.Review;
import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.executors.AppExecutors;
import com.example.yndlingsfilm.requests.responses.DiscoverMoviesResponse;
import com.example.yndlingsfilm.requests.responses.GetUserResponse;
import com.example.yndlingsfilm.requests.responses.HomePageResponse;
import com.example.yndlingsfilm.requests.responses.LoginResponse;
import com.example.yndlingsfilm.requests.responses.RelationshipResponse;
import com.example.yndlingsfilm.requests.responses.ReviewResponse;
import com.example.yndlingsfilm.util.Constants;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class UserApiClient {
    private static final String TAG = "UserApiClient";
    private static UserApiClient instance;
    private MutableLiveData<List<User>> users;
    private MutableLiveData<List<Review>> userReview;
    private MutableLiveData<User> loggedInUser;
    private LoginCallable loginCallable;
    private GetUserRunnable getUserRunnable;
    private GetHomePageUserRunnable getHomePageUserRunnable;
    private GetUserReviewRunnable getUserReviewRunnable;
    private saveReviewRunnable saveReviewRunnable;
    private boolean isUserLoggedIn;
    private boolean isHomePageLoaded;
    private ArrayList<Review> tempReviewList = new ArrayList<>();
    private ArrayList<Review> tempReviewListHomePage = new ArrayList<>();
    private List<User> tempUserListHomePage = new ArrayList<>();

    User user;

    private AddFriendCallable addFriendCallable;
    private DeleteUserRunnable deleteUserRunnable;


    public static UserApiClient getInstance() {
        if(instance == null){
            instance = new UserApiClient();

        }
        return instance;
    }

    public UserApiClient() {
        loggedInUser = new MutableLiveData<>();
        users = new MutableLiveData<>();
        userReview = new MutableLiveData<>();
    }

    public MutableLiveData<List<User>> getUsers() {
        return users;
    }

    public MutableLiveData<User> getLoggedInUser() {
        return loggedInUser;
    }

    public MutableLiveData<List<Review>> getUserReview() {
        return userReview;
    }

    public boolean login(String username, String password) throws ExecutionException, InterruptedException {
        if(loginCallable != null){
            loginCallable = null;
        }
        loginCallable = new LoginCallable(username, password);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(loginCallable);
        isUserLoggedIn = (boolean) handler.get();
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
        return isUserLoggedIn;
    }
//review
    public MutableLiveData<List<Review>> getUserReviewss() {
        return userReview;
    }

    private class LoginCallable implements Callable {

        private String username;
        private String password;
        private Boolean cancelRequest;

        public LoginCallable(String username, String password) {
            this.username = username;
            this.password = password;
            cancelRequest = false;
        }

        @Override
        public Boolean call() {
            try{
                Response response = login(username, password).execute();

                if(cancelRequest){
                    return false;
                }
                if (response.code() == 200){
                    //returner en true her og giv brugeren det givne token
                    isUserLoggedIn = true;
                    String token = ((LoginResponse)response.body()).getAccess_token();
                    Log.d(TAG, "run: " + token);
                    Log.d(TAG, "run: succes from code 200. user logged in");
                    return true;

                }else if (response.code() == 400){
                    Log.d(TAG, "Wrong grant type ____________________");
                    return false;

                } else{
                    Log.d(TAG,"Somethign else than 400 or 200");
                    return false;
                }
            } catch (IOException e){
                e.printStackTrace();
                Log.d(TAG, "run: error line____________ from login()");
                return false;
            }
        }

        private Call<LoginResponse> login(String username, String password){
            return ServiceGenerator.getUserApi().login("application/x-www-form-urlencoded","password",username, password);
        }

        private void setCancelRequest(){
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }
    }


    public User getUser(String username, String token) throws ExecutionException, InterruptedException {
        if(getUserRunnable != null){
            getUserRunnable = null;
        }
        getUserRunnable = new GetUserRunnable(username, token);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(getUserRunnable);
        user = (User) handler.get();
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
        return user;
    }

    private class GetUserRunnable implements Callable{

        private String username;
        private String token;
        private Boolean cancelRequest;

        public GetUserRunnable(String username, String token) {
            this.username = username;
            this.token = token;
            cancelRequest = false;
        }

        @Override
        public User call() {
            try{
                Response response = getUser(username).execute();
                if(cancelRequest){
                    return null;
                }
                if (response.code() == 200){
                    int userId = ((GetUserResponse)response.body()).getUserId();
                    String username = ((GetUserResponse)response.body()).getUserName().trim();
                    String password = ((GetUserResponse)response.body()).getPassword();
                    String email = ((GetUserResponse)response.body()).getEmail();
                    String bio = ((GetUserResponse)response.body()).getBio();
                    String fuckoff = ((((GetUserResponse)response.body()).getReviews().toString()));
              //List of reviews. Since they are in ReviewResponse type, they will be convertet to review
              //type in the for each loop
                    List<ReviewResponse> reviewList =
                            new ArrayList<>(((GetUserResponse) response.body()).getReviews());
                    for(ReviewResponse s : reviewList){
                        Review review = new Review(s.getReviewId(),s.getReviewText(),s.getMovieId(),s.getUserId(), s.getRating());
                        tempReviewList.add(review);
                    }
                    Log.d(TAG,tempReviewList.get(1).toString());
                    userReview.postValue(tempReviewList);


                    User user = new User(userId, username, password, email, bio, tempReviewList);

                    Log.d(TAG, "call: " + user.getReviews().get(1).getReviewText());

                    Log.d(TAG, "run: ____________________________");
                    Log.d(TAG, "run: " + user.getUsername());
                    Log.d(TAG, "call: " + user.getUserId());
                    return user;

                    //må kunne optimeres. laver templiste med alle users i lviedata og adder derefter
                    //den nye user. alle disse users addes så på ny i livedata.

                    /* herfra skal stå når man laver en bruger første gang og ikke her
                    List<User> tempUsers = new ArrayList<>();
                    tempUsers.addAll(users.getValue());
                    tempUsers.add(user);
                    // nødvendigt?
                    users.setValue(null);
                    users.postValue(tempUsers);
                     hertil*/
                }else{
                    Log.d(TAG, "run: succes from else");
                    return null;
                }
            } catch (IOException e){
                e.printStackTrace();
                Log.d(TAG, "run: error line____________ from getUser()");
                return null;
            }
        }

        private Call<GetUserResponse> getUser(String username){
            return ServiceGenerator.getUserApi().getUser(username);
        }

        private void setCancelRequest(){
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }
    }


    public Object addFriend(RelationshipResponse relationshipResponse) throws ExecutionException, InterruptedException {
        if (addFriendCallable != null){
            addFriendCallable = null;
        }

        addFriendCallable = new AddFriendCallable(relationshipResponse);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(addFriendCallable);
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
        return null;
    }

    private class AddFriendCallable implements Callable{

        RelationshipResponse relationshipResponse;

        public AddFriendCallable(RelationshipResponse relationshipResponse) {
            this.relationshipResponse = relationshipResponse;
        }

        @Override
        public Object call() throws Exception {
            try{
                Response response = addFriend(relationshipResponse).execute();

                if (response.code() == 200){
                    int userOneId = ((RelationshipResponse)response.body()).getUserOneId();
                    int userTwoId = ((RelationshipResponse)response.body()).getUserTwoId();
                    Log.d(TAG, "call: __________" + userOneId + " " + userTwoId);

                    return null;

                }
                else {
                    Log.d(TAG, "run: succes from else");
                    return null;
                }

            }catch (IOException e){
                e.printStackTrace();
                Log.d(TAG, "call: error line_____________________ from addFriend()");

            }
            return null;
        }

        private Call<RelationshipResponse> addFriend(RelationshipResponse relationshipResponse){
            return ServiceGenerator.getUserApi().addFriend(relationshipResponse);
        }
    }

    public void deleteUser(int userId){

        if (deleteUserRunnable != null){
            deleteUserRunnable = null;
        }

        deleteUserRunnable = new DeleteUserRunnable(userId);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(deleteUserRunnable);
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
    }

    private class DeleteUserRunnable implements Runnable{

        private int userId;

        public DeleteUserRunnable(int userId) {
            this.userId = userId;
        }

        @Override
        public void run() {

            try {

                Response response = deleteUser(userId).execute();

                if (response.code() == 200){
                    Log.d(TAG, "run: User deleted");
                }
                else {
                    Log.d(TAG, "run: succes from else");
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        private Call<Void> deleteUser(int userId){
            return ServiceGenerator.getUserApi().deleteUser(userId);
        }
    }



    public void getUserReviews(int userId){
        if(getUserReviewRunnable != null){
            getUserReviewRunnable = null;
        }
        getUserReviewRunnable = new GetUserReviewRunnable(userId);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(getUserReviewRunnable);
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
    }

    private class GetUserReviewRunnable implements Runnable{

        private int userId;
        private Boolean cancelRequest;

        public GetUserReviewRunnable(int userId) {
            this.userId = userId;
            cancelRequest = false;
        }

        @Override
        public void run() {

            try{
                Response response = getUserReview(userId).execute();
                if(cancelRequest){
                    return;
                }

                if (response.code() == 200){

                 String hello = ((Review) response.body()).getReviewText();
                 List<Review> fuckof = ((List<Review>) response.body());

                 Log.d(TAG,"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + fuckof.toString());

                }else{

                    Log.d(TAG, "run: succes from else");
                }
            } catch (IOException e){
                e.printStackTrace();
                Log.d(TAG, "run: error line____________ from getUser()");
            }
        }

        private Call<List<ReviewResponse>> getUserReview(int userId){

            return ServiceGenerator.getUserApi().getUserReview(userId);
        }

        private void setCancelRequest(){
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }
    }

    public void saveReview(int movieID, int rating, String review){
        if(saveReviewRunnable != null){
            saveReviewRunnable = null;
        }
        saveReviewRunnable = new saveReviewRunnable(movieID, rating, review);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(saveReviewRunnable);
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
    }

    private class saveReviewRunnable implements Runnable{

        private int movieID;
        private int rating;
        private String review;
        private int userID;

        public saveReviewRunnable(int movieID, int rating, String review) {
            this.movieID = movieID;
            this.rating = rating;
            this.review = review;
            this.userID = userID;
        }

        @Override
        public void run() {

            try {
                Response response = saveUserReview(movieID, rating, review).execute();
                if (response.code() == 200) {

                    //Review review = new Review(1, review.toString())
                    String hello = ((Review) response.body()).getReviewText();
                    List<Review> fuckof = ((List<Review>) response.body());

                    Log.d(TAG,"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ review was added");

                }else{

                    Log.d(TAG, "run: succes from else");
                }
            } catch (IOException e){
                e.printStackTrace();
                Log.d(TAG, "run: error line____________ from getUser()");
            }
        }

        private Call<Review> saveUserReview(int movieID, int rating, String review) {
            return ServiceGenerator.getUserApi().saveReview(review, movieID,getLoggedInUser().getValue().getUserId(), rating);
        }





    }
    public List<User> getHomePage(int id) throws ExecutionException, InterruptedException {
        if(getHomePageUserRunnable != null){
            getHomePageUserRunnable = null;
        }
        getHomePageUserRunnable = new GetHomePageUserRunnable(id);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(getHomePageUserRunnable);
        List<User> users = (List<User>) handler.get();
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
        return users;
    }

    private class GetHomePageUserRunnable implements Callable {

        private int id;
        private Boolean cancelRequest;

        public GetHomePageUserRunnable(int id) {
            this.id = id;
            cancelRequest = false;
        }

        @Override
        public List<User> call() {
            try{
                Response response = getHomePage(id).execute();
                if(cancelRequest){
                    return null;
                }
                if (response.code() == 200){

                    List<GetUserResponse> userList =
                            new ArrayList<>(((HomePageResponse) response.body()).getResponses());


                    Log.d(TAG, "call: " + ((HomePageResponse) response.body()).getResponses().size());

                    for(GetUserResponse s : userList){
                        ArrayList<Review> tempReviewListHomePage = new ArrayList<Review>();
                        for(ReviewResponse t : s.getReviews()){

                            Review review = new Review(t.getReviewId(),t.getReviewText(),t.getMovieId(),t.getUserId(),t.getRating());
                            Log.d(TAG, "REVIEW EZAMPLE" +review.getReviewText());

                            tempReviewListHomePage.add(review);
                        }

                        User user = new User(s.getUserId(),s.getUserName(),s.getPassword(),s.getEmail(),s.getBio(),tempReviewListHomePage);


                        tempUserListHomePage.add(user);
                    }

                    users.postValue(tempUserListHomePage);



                    return tempUserListHomePage;


                }else{
                    Log.d(TAG, "run: succes from else");
                    return null;
                }
            } catch (IOException e){
                e.printStackTrace();
                Log.d(TAG, "run: error line____________ from getUser()");
                return null;
            }
        }

        private Call<HomePageResponse> getHomePage(int id){
            return ServiceGenerator.getUserApi().getHomePage(id);
        }

        private void setCancelRequest(){
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }
    }
}
