package com.crio.jukebox.commands;

import java.util.List;

import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.services.IPlayListService;


public class DeletePlaylistCommand implements ICommand {
    
    private final IPlayListService playlistService;

    public DeletePlaylistCommand(IPlayListService playlistService)
    {
        this.playlistService = playlistService;
    } 
    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playlistId = tokens.get(2);

        try{
            playlistService.deletePlaylist(userId, playlistId);
            System.out.println("Delete Successful");
        } catch(UserNotFoundException u){
            System.out.println(u.getMessage());
        } catch(PlaylistNotFoundException p){
            System.out.println(p.getMessage());
        } catch(RuntimeException r){
            r.printStackTrace();;
        }
        
    }
}
