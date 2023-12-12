
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Load movies from the database
        List<Movie> movies = MovieDatabase.loadDatabase();
        // Sort movies in descending order of duration
        Collections.sort(movies);
        System.out.println("\nMovies in descending order of duration:");
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }

        Movie lowestRatedSciFi = MovieDatabase.getLowestRatedSciFi(movies);
        System.out.println("\nLowest-rated Sci-Fi movie: " + lowestRatedSciFi.getTitle());


        Movie fifthMostRecentPG = MovieDatabase.getFifthMostRecentPG(movies);
        System.out.println("\nFifth most recent 'PG' rated movie: " + fifthMostRecentPG.getTitle());


        Movie longestNameMovie = MovieDatabase.getMovieWithLongestName(movies);
        System.out.println("\nMovie with the longest name: " + longestNameMovie.getTitle());


        int yearsDifference = MovieDatabase.getYearsBetweenOldestAndNewest(movies);
        System.out.println("\nNumber of years between oldest and newest movie: " + yearsDifference);
    }
}
