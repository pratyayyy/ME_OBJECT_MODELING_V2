package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class User extends BaseEntity {
    
    private final String name;
    private List<Playlist> playlists;
    private UserPlaylistCurrentSong userPlaylistCurrentSong;

    public User(String name)
    {
        this.name = name;
        this.playlists = new ArrayList<Playlist>();    
    }

    public User(String id, String name)
    {
        this.id = id;
        this.name = name;
        this.playlists = new ArrayList<Playlist>();    
        this.userPlaylistCurrentSong = new UserPlaylistCurrentSong();
    }
    
    public User(String id, String name, List<Playlist> playlists)
    {
        this(id, name);
        this.playlists = playlists;
    }

    public User(String id, String name, List<Playlist> playlists, UserPlaylistCurrentSong userPlaylistCurrentSong){
        this(id, name, playlists);
        this.userPlaylistCurrentSong = userPlaylistCurrentSong;
    }

    public String getName() 
    {
        return name;
    }

    public List<Playlist> getPlaylist() 
        { 
        return playlists.stream().collect(Collectors.toList());
    }    
    
    public UserPlaylistCurrentSong getUserPlaylistCurrentSong() {
        return userPlaylistCurrentSong;
    }

    
    public void setUserPlaylistCurrentSong(UserPlaylistCurrentSong userPlaylistCurrentSong) {
        this.userPlaylistCurrentSong = userPlaylistCurrentSong;
    }



    public void addPlaylist(Playlist playlist)
    {
      playlists.add(playlist);
    }

    public void deletePlaylist(Playlist playlist)
    {
       playlists.removeIf(p-> p.getId()==playlist.getId());
    }

    public boolean checkIfPlaylistExist(Playlist playlist) throws NullPointerException
    {
        if(this.getPlaylist().isEmpty()){
            return false;
        }
        return this.getPlaylist().stream().anyMatch(s-> s.equals(playlist));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public String toString() 
    {
        return "User[id=" + id + ", name = " + name + ", playlists= " +playlists+ "]";         
               
    }       
                   

}

