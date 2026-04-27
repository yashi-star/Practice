package com.music.track.service.impl;

import com.music.track.dto.TrackRequest;
import com.music.track.model.Track;
import com.music.track.repository.TrackRepository;
import com.music.track.service.TrackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    
    private TrackRepository trackRepoitory;
    
    
    public TrackServiceImpl(TrackRepository trackRepoitory) {
		super();
		this.trackRepoitory = trackRepoitory;
	}
	public Track createTrack(TrackRequest trackRequest) {
    	Track t=new Track();
    	t.setAlbumName(trackRequest.albumName());
    	t.setPlayCount(trackRequest.playCount());
    	t.setReleaseDate(trackRequest.releaseDate());
    	t.setTitle(trackRequest.title());
        return trackRepoitory.save(t);
    }
    @Override
    public List<Track> getAllTracks() {
        return trackRepoitory.findAll();
    }

    @Override
    public void deleteTrack(Long trackId) {
    	trackRepoitory.deleteById(trackId);
    }

    @Override
    public List<Track> sortedTracks() {
        return trackRepoitory.findAll(Sort.by(Sort.Direction.ASC,"title"));
        
    }
}
