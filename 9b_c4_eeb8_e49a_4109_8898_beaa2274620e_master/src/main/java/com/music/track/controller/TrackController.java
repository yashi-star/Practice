package com.music.track.controller;

import com.music.track.dto.TrackRequest;
import com.music.track.model.Track;
import com.music.track.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
        return new ResponseEntity<Track>(t,HttpStatus.CREATED) ;
    }

    @GetMapping()
    public ResponseEntity<List<Track>> getAllTracks(){
    	List<Track> track=trackService.getAllTracks();
    	return new ResponseEntity<List<Track>>(track,HttpStatus.OK);
    }


    @DeleteMapping("/{trackId}")
    public ResponseEntity<Void> deleteTrack(@PathVariable Long trackId){
    	trackService.deleteTrack(trackId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
    }


    @GetMapping("/search")
    public ResponseEntity<Track> getTrackByTitle(@RequestParam String title) throws ParseException {
    	Track track=trackService.getTracksByTitle(title);
        return new ResponseEntity<Track>(track,HttpStatus.OK);
    }

}
