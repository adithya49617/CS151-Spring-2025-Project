package model;

public class Song {
    private String songId;
    private String songTitle;
    private String songLength;
    private String songArtist;
    private String songGenre;

    public Song(String songId, String songTitle, String songLength, String songArtist, String songGenre) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.songLength = songLength;
        this.songArtist = songArtist;
        this.songGenre = songGenre;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongLength() {
        return songLength;
    }

    public void setSongLength(String songLength) {
        this.songLength = songLength;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }

    @Override
    public String toString() {
        return songId +
                "," + songTitle +
                "," + songLength +
                "," + songArtist +
                "," + songGenre;
    }
}
