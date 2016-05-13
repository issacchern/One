package com.clayons.interviewquestions.APIs;

import com.clayons.interviewquestions.APIs.entities.ConfigurationResponse;
import com.clayons.interviewquestions.APIs.entities.ImagesWrapper;
import com.clayons.interviewquestions.APIs.entities.MovieDetail;
import com.clayons.interviewquestions.APIs.entities.MoviesWrapper;
import com.clayons.interviewquestions.APIs.entities.ReviewsWrapper;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface MoviesAPI {

    @GET("/movie/popular")
    void getPopularMovies(
            @Query("api_key") String apiKey,
            Callback<MoviesWrapper> callback);

    @GET("/movie/{id}")
    void getMovieDetail(
            @Query("api_key") String apiKey,
            @Path("id") String id,
            Callback<MovieDetail> callback
    );

    @GET("/movie/popular")
    void getPopularMoviesByPage(
            @Query("api_key") String apiKey,
            @Query("page") String page,
            Callback<MoviesWrapper> callback
    );

    @GET("/configuration")
    void getConfiguration(
            @Query("api_key") String apiKey,
            Callback<ConfigurationResponse> response
    );

    @GET("/movie/{id}/reviews")
    void getReviews(
            @Query("api_key") String apiKey,
            @Path("id") String id,
            Callback<ReviewsWrapper> response
    );

    @GET("/movie/{id}/images")
    void getImages(
            @Query("api_key") String apiKey,
            @Path("id") String movieId,
            Callback<ImagesWrapper> response
    );

    @GET("/search/autocompleteGeo")
    Observable<MoviesWrapper> searchRx(
            @Query("query") String query
    );
}
