package model;

public class Artist {
    private String artistId;
    private String artistDOB;
    private String artistFirstName;
    private String artistLastName;
    private String artistEmail;
    private String artistGender;

    public Artist(String artistId, String artistDOB, String artistFirstName, String artistLastName, String artistEmail, String artistGender) {
        this.artistId = artistId;
        this.artistDOB = artistDOB;
        this.artistFirstName = artistFirstName;
        this.artistLastName = artistLastName;
        this.artistEmail = artistEmail;
        this.artistGender = artistGender;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistDOB() {
        return artistDOB;
    }

    public void setArtistDOB(String artistDOB) {
        this.artistDOB = artistDOB;
    }

    public String getArtistFirstName() {
        return artistFirstName;
    }

    public void setArtistFirstName(String artistFirstName) {
        this.artistFirstName = artistFirstName;
    }

    public String getArtistLastName() {
        return artistLastName;
    }

    public void setArtistLastName(String artistLastName) {
        this.artistLastName = artistLastName;
    }

    public String getArtistEmail() {
        return artistEmail;
    }

    public void setArtistEmail(String artistEmail) {
        this.artistEmail = artistEmail;
    }

    public String getArtistGender() {
        return artistGender;
    }

    public void setArtistGender(String artistGender) {
        this.artistGender = artistGender;
    }

    @Override
    public String toString() {
        return artistId +
                "," + artistDOB +
                "," + artistFirstName +
                "," + artistLastName +
                "," + artistEmail +
                "," + artistGender;
    }
}
