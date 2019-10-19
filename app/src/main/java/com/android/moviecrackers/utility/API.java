package com.android.moviecrackers.utility;

public class API {

    private API() {
    }

    /**
     * Given the all the Base url, url function name, Api key.
     * For getting API_KEY we have to register with themoviedb.org
     */
    public static final String API_KEY = "a6ce2a272ba3086d67ea656dcbc35aeb";
    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static final String THUMBNAIL_BASE_URL = "http://image.tmdb.org/t/p/w185/";

    public static final String GET_TOP_RATED_MOVIES = "top_rated";

}
