package com.crio.jukebox.repositories.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.crio.jukebox.entities.*;
import com.crio.jukebox.repositories.ISongRepository;

public class SongData implements IData{

    private final ISongRepository songRepository;

    public SongData(ISongRepository songRepository){
        this.songRepository = songRepository;
    }

    @Override
    public void loadData(String dataPath, String delimiter) {
        
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(dataPath));
            String line = reader.readLine();
            while(line != null){
                List<String> tokens = Arrays.asList(line.split(delimiter));
                addSong(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4));
                line =  reader.readLine();
            }
            reader.close();
        } catch(IOException i){
            i.printStackTrace();;
        }
        System.out.println("Songs Loaded successfully");
    }
    private void addSong(String songName, String genre, String albumName, String artistname, String featureArtists){
        List<String> featuredArtistListfromString = Arrays.asList(featureArtists.split("#"));
        List<Artist> featuredArtistList = featuredArtistListfromString.stream().map(s->new Artist(s)).collect(Collectors.toList());
        Artist artist = new Artist(artistname); 
        Album album = new Album(albumName);
        this.songRepository.save(new Song(songName, genre, album, artist, featuredArtistList));
    }
    
}
