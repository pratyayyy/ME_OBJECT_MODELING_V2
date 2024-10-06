package com.crio.jukebox.dtos;

import java.util.Arrays;
import java.util.List;

public class PlaylistSummaryDto {
    
    private final String playlistId;
    private final String playlistName;
    private final List<String> songId;

    public PlaylistSummaryDto(String playlistId, String playlistName,  List<String> songId )
    {
      this.playlistId = playlistId;
      this.playlistName = playlistName;
      this.songId = songId;
    }
   
    public String getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public List<String> getsongId() {
        return songId;
    }

    @Override
    public String toString()
    {
        return "Playlist ID - " + playlistId + "\n" + "Playlist Name - " + playlistName + "\n" +"Song IDs - "
        + Arrays.toString(songId.toArray()).replace("[", "").replace("]", "").replace(", ", " ");
    }



    






}
