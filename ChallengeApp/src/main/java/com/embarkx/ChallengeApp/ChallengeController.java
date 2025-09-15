package com.embarkx.ChallengeApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = "http://localhost:3000")
public class ChallengeController {

    private final ChallengeService challengeService;

    ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getChallenges() {
        return new ResponseEntity<>(challengeService.getAllChallenges(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addChallenge(@RequestBody Challenge ch1) {
        boolean isAdded = challengeService.addChallenge(ch1);

        if (isAdded) {
            return  new ResponseEntity<>("Challenge added successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Challenge not added", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenges(@PathVariable String month) {
        Challenge ch = challengeService.getChallenges(month);
        if (ch != null) {
            return  new ResponseEntity<>(ch, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody Challenge updated) {
        boolean isChallengeUpdated = challengeService.updateChallenge(id, updated);

        if (isChallengeUpdated) {
            return  new ResponseEntity<>("Challenge updated successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Challenge not updated added", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {
        boolean isChallengeDeleted = challengeService.deleteChallenge(id);

        if (isChallengeDeleted ) {
            return  new ResponseEntity<>("Challenge deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Challenge not deleted", HttpStatus.NOT_FOUND);
        }
    }
}