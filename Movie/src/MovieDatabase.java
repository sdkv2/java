
import java.io.BufferedReader;
import java.io.FileReader;  
import java.io.IOException; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;  

public class MovieDatabase {

    public static List<Movie> loadDatabase() {
        final List<Movie> movies = new ArrayList<>();
        List<String[]> allData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Movie/src/movies.txt"))) {       
            String line;
            while ((line = br.readLine()) != null) {
                List<String> data = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                boolean inQuotes = false; // Track whether we're inside quotes or not

                for (char c : line.toCharArray()) {
                    if (c == '\"') { //If we encounter a quote, flip the boolean
                        inQuotes = !inQuotes;
                    } else if (c == ',' && !inQuotes) { // If we encounter a comma and we're not in quotes, add the string to the list and reset the string builder
                        data.add(sb.toString()); 
                        sb = new StringBuilder();
                    } else { // Otherwise, just append the character to the string builder
                        sb.append(c);
                    }
                }
                data.add(sb.toString());
                allData.add(data.toArray(new String[0]));
            }
            for (String[] row : allData) {
                String title = row[0];
                int year = Integer.parseInt(row[1]);
                String ageRating = row[2];
                String[] genre = row[3].split("/"); // Split genres into an array                
                int duration = Integer.parseInt(row[4]);
                float rating = Float.parseFloat(row[5]);
                // Constructs our movie object
                Movie movie = new Movie(title, year, ageRating, genre, duration, rating);
                // And then adds it to our array of movies
                movies.add(movie);

            }
            // Catching exceptions if they do occur (unlikely, but still possible)
        } catch (IOException e) {
            e.printStackTrace();}
        // Returns the array of movies
        return movies;
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
}
