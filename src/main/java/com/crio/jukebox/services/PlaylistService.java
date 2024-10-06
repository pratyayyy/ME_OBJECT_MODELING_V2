package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.dtos.PlaylistSummaryDto;
import com.crio.jukebox.dtos.SongSummaryDto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.UserPlaylistCurrentSong;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;

public class PlaylistService implements IPlayListService {

 private final IPlaylistRepository playlistRepository;
 private final IUserRepository userRepository;
 private final ISongRepository songRepository; 

 public PlaylistService(IPlaylistRepository playlistRepository, IUserRepository userRepository, ISongRepository songRepository)
 {
    this.playlistRepository = playlistRepository;
    this.userRepository = userRepository;
    this.songRepository = songRepository;
 }

 @Override
 public PlaylistSummaryDto create( String userId, String name, List<String> songId ) throws UserNotFoundException, SongNotFoundException
 {
    final User user= userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    final List<Song> songs = songId.stream().map(s -> songRepository.findById(s).orElseThrow(() -> new SongNotFoundException("Song not avialable"))).collect(Collectors.toList());
    Playlist p = playlistRepository.save(new Playlist(name,songs)); 
    PlaylistSummaryDto pdto = new PlaylistSummaryDto(p.getId(), p.getName(), p.getSongList().stream().map(s-> s.getTitle()).collect(Collectors.toList()));
    
    user.addPlaylist(p);
    userRepository.save(user);
    return pdto;

}

@Override
public void deletePlaylist(String userId, String playlistId) throws UserNotFoundException, PlaylistNotFoundException
{
    final User user= userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    final Playlist p= playlistRepository.findById(playlistId).orElseThrow(()->new PlaylistNotFoundException("PlaylistID not found"));
    if(! user.checkIfPlaylistExist(p))
    {
        throw new PlaylistNotFoundException("Playlist not found");
    }
    user.deletePlaylist(p);
    playlistRepository.deleteById(playlistId);
    userRepository.save(user);
}
 @Override
 public PlaylistSummaryDto addSongplaylist(String userId, String playlistId, List<String> songId)    throws UserNotFoundException, PlaylistNotFoundException, SongNotFoundException 
 {
   final User user= userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
   final Playlist p= playlistRepository.findById(playlistId).orElseThrow(()->new PlaylistNotFoundException("PlaylistID not found")); 
   if(! user.checkIfPlaylistExist(p))
   {
       throw new PlaylistNotFoundException("Playlist not found");
   }
       final List<Song> songs = songId.stream().map(s-> songRepository.findById(s).orElseThrow(() -> new SongNotFoundException("Songs Not Available"))).collect(Collectors.toList());
   

for(Song s: songs)
   {
    if(!p.checkIfSongExist(s))
    {
        p.addSong(s);
    }
   }
   playlistRepository.save(p);
   PlaylistSummaryDto pdto = new PlaylistSummaryDto(p.getId(), p.getName(), p.getSongList().stream().map(s-> s.getId()).collect(Collectors.toList()));
   userRepository.save(user);
   return pdto;
 }

 @Override
public  PlaylistSummaryDto deleteSongFromPlaylist(String userId, String playlistId, List<String> songId )
{
 final User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
 final Playlist p = playlistRepository.findById(playlistId).orElseThrow(()-> new PlaylistNotFoundException("PlaylistID not found"));
 if(!user.checkIfPlaylistExist(p))
 {
    throw new PlaylistNotFoundException("Playlist not found");
 }

 final List<Song> songs = songId.stream().map(s-> songRepository.findById(s).orElseThrow(() -> new SongNotFoundException("Songs Not Available"))).collect(Collectors.toList());
 for(Song s: songs)
 {
    if(p.checkIfSongExist(s))
    {
        p.deleteSong(s);
    }
    else
    {
        throw new SongNotFoundException("You can't delete this song");
    }
 }
 playlistRepository.save(p);
 PlaylistSummaryDto pdto = new PlaylistSummaryDto(p.getId(), p.getName(), p.getSongList().stream().map(s-> s.getId()).collect(Collectors.toList()));
 userRepository.save(user);
 return pdto;
}

@Override
public SongSummaryDto playPlaylist(String userId,String playlistId) throws PlaylistNotFoundException, EmptyPlaylistException, UserNotFoundException
{
   final User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
   final Playlist p = playlistRepository.findById(playlistId).orElseThrow(()-> new PlaylistNotFoundException("PlaylistID not found"));
   if(!user.checkIfPlaylistExist(p))
   {
    throw new PlaylistNotFoundException("Playlist not found");
   }
    
   List<Song> songs = p.getSongList();
   if(songs.isEmpty())
   {
    throw new EmptyPlaylistException("Playlist is empty.");
   }
     
   Song currentSong = songs.get(0);
   SongSummaryDto ssdto = new SongSummaryDto(currentSong.getTitle(), currentSong.getAlbum().getName(), currentSong.getfeatureArtists().stream().map(x->x.getName()).collect(Collectors.toList()));
   UserPlaylistCurrentSong userPlaylistCurrentSong = new UserPlaylistCurrentSong(p, currentSong);
   user.setUserPlaylistCurrentSong(userPlaylistCurrentSong);
   userRepository.save(user);
   return ssdto;

}

    
}
