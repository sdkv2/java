
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Movie implements Comparable<Movie> {
    private String title;
    private int year;
    private String ageRating;
    private String[] genre;
    private int duration;
    private float rating;
    @Override
    public int compareTo(Movie other) {
        return other.duration - this.duration; // Desc order
    }
    @Override
    // String magic to print out the movie in the same format as the CSV file
    public String toString() {
        return "\"" + title + "\"," +
            year + "," +
            "\"" + ageRating + "\"," +
            "\"" + String.join("/", genre) + "\"," +
            duration + "," +
            rating;
    }
    public static Movie getLowestRatedSciFi(List<Movie> movies) {
        Movie lowestRatedSciFi = null;
        for (Movie movie : movies) {
            // Check if Sci-Fi is one of the genres for each movie
            for (String genre : movie.getGenre()) {
                if ("Sci-Fi".equals(genre)) {
                    if (lowestRatedSciFi == null || movie.getRating() < lowestRatedSciFi.getRating()) {
                        lowestRatedSciFi = movie;
                        break; // Break the inner loop to avoid checking the same movie multiple times
                    }
                }
            }   
        }
        return lowestRatedSciFi;
    }


    // Method to get the fifth most recent "PG" rated movie
    public static Movie getFifthMostRecentPG(List<Movie> movies) {
        List<Movie> pgMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if ("PG".equals(movie.getAgeRating())) {
                // Add the movie to the list of PG movies
                pgMovies.add(movie);
            }
        }
        // Sort the PG movies list in descending order of year
        Collections.sort(pgMovies, new Comparator<Movie>() {
            public int compare(Movie m1, Movie m2) {
                return m2.getYear() - m1.getYear();
            }
        });
        // Return the fifth movie in the list, 0-indexed
        return pgMovies.get(4);
    }

    // Method to find the movie with the longest name
    public static Movie getMovieWithLongestName(List<Movie> movies) {
        Movie longestNameMovie = null;
        for (Movie movie : movies) {
            // Check if the current movie has a longer name than the current longest name movie
            if (longestNameMovie == null || movie.getTitle().length() > longestNameMovie.getTitle().length()) {
                // If it is, set the current movie as the longest name movie
                longestNameMovie = movie;
            }
        }
        return longestNameMovie;
    }

    // Method to find the number of years between the oldest and newest movie
    public static int getYearsBetweenOldestAndNewest(List<Movie> movies) {
        // Get the min and max year. We could use an arbritarlily large/small numbers instead of Integer.MAX_VALUE and Integer.MIN_VALUE
        // but this is more robust in case we have a movie from the year 2147483647 or -2147483648 (quite unlikely)
        int minYear = Integer.MAX_VALUE;
        int maxYear = Integer.MIN_VALUE;
        for (Movie movie : movies) {
            if (movie.getYear() < minYear) {
                minYear = movie.getYear();
            }
            if (movie.getYear() > maxYear) {
                maxYear = movie.getYear();
            }
        }
        return maxYear - minYear;
    }
    // Constructor
    public Movie(String title, int year, String ageRating, String[] genre, int duration, float rating) {
        this.title = title;
        this.year = year;
        this.ageRating = ageRating;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public String[] getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public float getRating() {
        return rating;
    }
    
}
    

