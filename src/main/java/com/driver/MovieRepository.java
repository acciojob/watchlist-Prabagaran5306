package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String, Movie> movieDb;
    Map<String, Director> directorDb;

//  Map<String, String> movieDirectorDb;
    Map<String, Set<String>> movieDirectorDb;

    public MovieRepository() {
        this.movieDb = new HashMap<>();
        this.directorDb = new HashMap<>();
        this.movieDirectorDb = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        movieDb.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directorDb.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movie, String director) {

        Set<String> set = movieDirectorDb.getOrDefault(director, new LinkedHashSet<>());
        set.add(movie);
        movieDirectorDb.put(director, set);
    }

    public Movie getMovieByName(String movie) {

        return movieDb.get(movie);
    }

    public Director getDirectorByName(String director) {

        return directorDb.get(director);
    }

    public List<String> getMoviesByDirectorName(String director) {

        return new ArrayList<>(movieDirectorDb.get(director));
    }

    public List<String> findAllMovies() {

        return new ArrayList<>(movieDb.keySet());
    }

    public void deleteDirectorByName(String director) {

        Set<String> movies = movieDirectorDb.get(director);
        for(String movie : movies){
            movieDb.remove(movie);
        }

        movieDirectorDb.remove(director);

        directorDb.remove(director);
    }

    public void deleteAllDirectors() {

        for(Set<String> set : movieDirectorDb.values()){
            for(String movie : set){
                movieDb.remove(movie);
            }
        }

        directorDb.clear();
        movieDirectorDb.clear();
    }
}
