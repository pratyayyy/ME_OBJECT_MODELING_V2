package com.crio.jukebox.entities;

import com.crio.jukebox.exceptions.SongNotFoundException;

public class UserPlaylistCurrentSong {
    
    private Playlist playlist;
    private Song currSong;
    

    public UserPlaylistCurrentSong(Playlist playlist, Song currSong)
    {
        this.playlist = playlist;
        if(playlist.checkIfSongExist(currSong))
        {
        this.currSong = currSong;
        }
        else
        {
            throw new SongNotFoundException("Song is not present in the playlist");
        }
    }

    public UserPlaylistCurrentSong()
    {

    }

    public Playlist getActiveplaylist()
    {
        return playlist;
    }

    public Song getCurrentSong()
    {
        return currSong;
    }

    public void changeActiveSong(Playlist playlist, Song song)
    {
         this.currSong = song;
    }

    @Override
    public String toString() {
        return "UserPlaylistCurrentSong [Playlist=" + playlist + "Song="+ currSong + "]";
    }

    
}
