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
    

