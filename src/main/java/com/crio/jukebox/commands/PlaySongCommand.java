package com.crio.jukebox.commands;

import java.util.List;

import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.dtos.SongSummaryDto;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.ISongService;

public class PlaySongCommand implements ICommand {

    private final ISongService songService;

    public PlaySongCommand(ISongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        
        String userId = tokens.get(1);
        String param = tokens.get(2);

        SongSummaryDto sDto;
        try{
            if(param.equals("NEXT")){
                sDto = songService.nextSong(userId);   
            } else if(param.equals("BACK")){
                sDto = songService.prevSong(userId);
            } else {
                sDto = songService.playSong(userId, param);
            }
            System.out.println(sDto);

        } catch(UserNotFoundException u){
            System.out.println(u.getMessage());
        } catch(SongNotFoundException s){
            System.out.println(s.getMessage());
        } catch(RuntimeException r){
            r.printStackTrace();
        }
    }
    
}