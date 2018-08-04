package in.codingninjas.envision.contentproviders;

public class Movie {

    private String name;
    private String overview;

    public Movie(String name, String overview) {
        this.name = name;
        this.overview = overview;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
