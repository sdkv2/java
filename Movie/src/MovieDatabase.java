
import java.io.BufferedReader;
import java.io.FileReader;  
import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;  

public class MovieDatabase {

    public static List<Movie> loadDatabase() {
        final List<Movie> movies = new ArrayList<>();
        List<String[]> allData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/movies.txt"))) {       
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
}
