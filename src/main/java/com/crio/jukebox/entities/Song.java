package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class Song extends BaseEntity {

    private final String title;
    private final String genre;
    private final Album album;
    private final Artist artist;
    private  List<Artist> featureArtists;

    
    public Song(String title, String genre, Album album, Artist artist, List<Artist> featureArtists)
    {
     this.title = title;
     this.genre = genre;
     this.album = album;
     this.artist = artist; 
     this.featureArtists = featureArtists;
    }

    public Song(String id,String title, String genre,  Album album,  Artist artist, List<Artist> featureArtists)
    {
        this(title,genre,album,artist,featureArtists);
        this.id = id;
    }
     

    public String getTitle()
    {
      return title;
    }
    public String getGenre()
    {
      return title;
    }
    public Album getAlbum()
    {
      return album;
    } 
    public Artist getArtist()
    {
      return artist;
    }
    public List<Artist> getfeatureArtists()
    {
      return featureArtists;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
       Song other = (Song) obj;
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
        return "Song [id= " + id + ", title= " + title + ", genre= " + genre + ", album= " + album.getName() + ", artist= " + artist.getName() +", featuredArtist= " + featureArtists+ "]";
    }
}
