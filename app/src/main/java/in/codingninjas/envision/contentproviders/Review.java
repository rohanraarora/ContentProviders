package in.codingninjas.envision.contentproviders;

public class Review {

    private long movieId;
    private String name;
    private String review;

    public Review(long movieId, String name, String review) {
        this.movieId = movieId;
        this.name = name;
        this.review = review;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
