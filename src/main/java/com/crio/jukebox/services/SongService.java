package com.crio.jukebox.services;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.crio.jukebox.dtos.SongSummaryDto;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.UserPlaylistCurrentSong;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.ISongRepository;


public class SongService implements ISongService{
    private final ISongRepository songRepository;
    private final IUserRepository userRepository;

    public SongService(ISongRepository songRepository, IUserRepository userRepository)
    {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }


    public SongSummaryDto playSong(String userId, String songId) throws UserNotFoundException, SongNotFoundException
    {
        final User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
        final Playlist p = user.getUserPlaylistCurrentSong().getActiveplaylist();
        final Song currentSong = songRepository.findById(songId).get();
        if(!p.checkIfSongExist(currentSong))
        { 
            throw new SongNotFoundException("Given song id is not a part of the active playlist");
        }

        UserPlaylistCurrentSong userPlaylistCurrentSong = new UserPlaylistCurrentSong(p, currentSong);
        user.setUserPlaylistCurrentSong( userPlaylistCurrentSong);
        userRepository.save(user);
        SongSummaryDto ssdto = new SongSummaryDto(currentSong.getTitle(), currentSong.getAlbum().getName(), currentSong.getfeatureArtists().stream().map(x-> x.getName()).collect(Collectors.toList()));
        return ssdto;
    }
       

    public SongSummaryDto nextSong(String userId) throws UserNotFoundException
    {
        final User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
        final Playlist p = user.getUserPlaylistCurrentSong().getActiveplaylist();
        final Song s = user.getUserPlaylistCurrentSong().getCurrentSong();
        final List<Song> currplaylistsongs = p.getSongList();
        int index = IntStream.range(0, currplaylistsongs.size()).filter(songIndex->currplaylistsongs.get(songIndex).equals(s)).findFirst().getAsInt();
        int size= currplaylistsongs.size();
        int currIdx = (index+1)%size;
        final Song currentSong = currplaylistsongs.get(currIdx);
        UserPlaylistCurrentSong userPlaylistCurrentSong = new UserPlaylistCurrentSong(p, currentSong);
        user.setUserPlaylistCurrentSong(userPlaylistCurrentSong);

        userRepository.save(user);

        SongSummaryDto ssdto = new SongSummaryDto(currentSong.getTitle(), currentSong.getAlbum().getName(), currentSong.getfeatureArtists().stream().map(x-> x.getName()).collect(Collectors.toList()));
        return ssdto;

    }


    public SongSummaryDto prevSong(String userId)  throws UserNotFoundException
    {
        final User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
        final Playlist p = user.getUserPlaylistCurrentSong().getActiveplaylist();
        final Song s = user.getUserPlaylistCurrentSong().getCurrentSong();
        final List<Song> currplaylistsongs = p.getSongList();
        int index = IntStream.range(0, currplaylistsongs.size()).filter(songIndex->currplaylistsongs.get(songIndex).equals(s)).findFirst().getAsInt();
        int size= currplaylistsongs.size();
        int currIdx = (index+size-1)%size;
        final Song currentSong = currplaylistsongs.get(currIdx);
        UserPlaylistCurrentSong userPlaylistCurrentSong = new UserPlaylistCurrentSong(p, currentSong);
        user.setUserPlaylistCurrentSong(userPlaylistCurrentSong);

        userRepository.save(user);

        SongSummaryDto ssdto = new SongSummaryDto(currentSong.getTitle(), currentSong.getAlbum().getName(), currentSong.getfeatureArtists().stream().map(x-> x.getName()).collect(Collectors.toList()));
        return ssdto;
    }
    
}
