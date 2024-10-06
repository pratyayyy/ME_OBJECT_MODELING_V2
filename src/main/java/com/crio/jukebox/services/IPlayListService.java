package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dtos.PlaylistSummaryDto;
import com.crio.jukebox.dtos.SongSummaryDto;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public interface IPlayListService {
    public PlaylistSummaryDto create( String userId, String name, List<String> songId );
    public void deletePlaylist(String userId, String playlistId) throws PlaylistNotFoundException;
    public PlaylistSummaryDto addSongplaylist(String userId, String playlistId, List<String> songId) throws SongNotFoundException;
    public SongSummaryDto playPlaylist(String userId, String playlistId) throws PlaylistNotFoundException;    
    public PlaylistSummaryDto deleteSongFromPlaylist(String userId, String playlistId, List<String> songId) throws SongNotFoundException;
    
}
