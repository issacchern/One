package com.clayons.interviewquestions.APIs;

import com.clayons.interviewquestions.APIs.entities.ConfigurationResponse;
import com.clayons.interviewquestions.APIs.entities.ImagesWrapper;
import com.clayons.interviewquestions.APIs.entities.MovieDetail;
import com.clayons.interviewquestions.APIs.entities.MoviesWrapper;
import com.clayons.interviewquestions.APIs.entities.ReviewsWrapper;
import com.clayons.interviewquestions.Constants;
import com.squareup.otto.Bus;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RestClient {
    private final MoviesAPI moviesDBApi;
    private final Bus bus;

    public RestClient(Bus bus) {

        RestAdapter movieAPIRest = new RestAdapter.Builder()
                .setEndpoint(Constants.MOVIE_DB_HOST)
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .build();

        moviesDBApi = movieAPIRest.create(MoviesAPI.class);
        this.bus = bus;
    }

    public void getMovies() {

        moviesDBApi.getPopularMovies(Constants.API_KEY, retrofitCallback);
    }

    public void getDetailMovie(String id) {

        moviesDBApi.getMovieDetail(Constants.API_KEY, id,
                retrofitCallback);
    }

    public void getReviews(String id) {

        moviesDBApi.getReviews(Constants.API_KEY, id,
                retrofitCallback);
    }

    public void getConfiguration() {

        moviesDBApi.getConfiguration(Constants.API_KEY, retrofitCallback);
    }

    public void getImages(String movieId) {

        moviesDBApi.getImages(Constants.API_KEY, movieId,
                retrofitCallback);
    }

    public Callback retrofitCallback = new Callback() {
        @Override
        public void success(Object o, Response response) {

            if (o instanceof MovieDetail) {

                MovieDetail detailResponse = (MovieDetail) o;
                bus.post(detailResponse);

            } else if (o instanceof MoviesWrapper) {

                MoviesWrapper moviesApiResponse = (MoviesWrapper) o;
                bus.post(moviesApiResponse);

            } else if (o instanceof ConfigurationResponse) {

                ConfigurationResponse configurationResponse = (ConfigurationResponse) o;
                bus.post(configurationResponse);

            } else if (o instanceof ReviewsWrapper) {

                ReviewsWrapper reviewsWrapper = (ReviewsWrapper) o;
                bus.post(reviewsWrapper);

            } else if (o instanceof ImagesWrapper) {

                ImagesWrapper imagesWrapper = (ImagesWrapper) o;
                bus.post(imagesWrapper);
            }
        }

        @Override
        public void failure(RetrofitError error) {

            System.out.printf("[DEBUG] RestMovieSource failure - " + error.getMessage());
        }
    };

    public void getMoviesByPage(int page) {

        moviesDBApi.getPopularMoviesByPage(
                Constants.API_KEY,
                page + "",
                retrofitCallback
        );
    }

}
