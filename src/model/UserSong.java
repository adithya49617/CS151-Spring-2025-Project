package model;

public class UserSong {
    private String userId;
    private String songId;

    public UserSong(String userId, String songId) {
        this.userId = userId;
        this.songId = songId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    @Override
    public String toString() {
        return userId + "," + songId;
    }
}
