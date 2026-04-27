package com.music.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.music.track.dto.TrackRequest;
import com.music.track.model.Track;
import com.music.track.service.TrackService;

import java.util.List;

@RestController
@RequestMapping("music/platform/v1/tracks")
public class TrackController {

    private final TrackService trackService;
    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
 
    @PostMapping()
    public ResponseEntity<Track> createTrack(@RequestBody TrackRequest trackRequest){
    	Track t=trackService.createTrack(trackRequest);
        return new ResponseEntity<Track>(t,HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Track>> getAllTracks(){
    	List<Track> track=trackService.getAllTracks();
        return new ResponseEntity<List<Track>>(track,HttpStatus.OK) ;
    }

    @DeleteMapping("/{trackId}")
    public ResponseEntity<Void> deleteTrack(@PathVariable Long trackId){
    	trackService.deleteTrack(trackId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Track>> getTracksSorted() {
    	List<Track> t=trackService.sortedTracks();
        return new  ResponseEntity<List<Track>>(t,HttpStatus.OK);
    }
}
