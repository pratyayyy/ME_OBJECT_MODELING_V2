package com.crio.jukebox.commands;

import java.util.List;

import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.dtos.SongSummaryDto;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.services.IPlayListService;

public class PlayPlaylistCommand implements ICommand {

    private final IPlayListService playlistService;

    public PlayPlaylistCommand(IPlayListService playlistService) {
        this.playlistService = playlistService;
    }
     
    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playlistId = tokens.get(2);
        
        try{
            SongSummaryDto sDto = playlistService.playPlaylist(userId, playlistId);
            System.out.println(sDto);
        } catch(UserNotFoundException u){
            System.out.println(u.getMessage());
        } catch(PlaylistNotFoundException p){
            System.out.println(p.getMessage());
        } catch(EmptyPlaylistException e){
            System.out.println(e.getMessage());
        } catch(RuntimeException r){
            r.printStackTrace();
        }
    }
    
}