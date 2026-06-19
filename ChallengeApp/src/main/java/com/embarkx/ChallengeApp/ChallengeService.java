package com.embarkx.ChallengeApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
//    private final List<Challenge> challenges = new ArrayList<>();
    private Long nextId = 1L;

    @Autowired
    ChallengeRepository challengeRepository;

    ChallengeService() {
    }

    // get/challenge
    public List<Challenge> getAllChallenges() {
//        return challenges;
        return challengeRepository.findAll();
    }

    // post/challenge
    public boolean addChallenge(Challenge ch1) {
        if (ch1 !=  null) {
            ch1.setId(nextId++);
            challengeRepository.save(ch1);
            return true;
        }
        return false;
    }

    // get/challenge/{month}
    public Challenge getChallenges(String month) {
        Optional<Challenge> ch = challengeRepository.findByMonthIgnoreCase(month);
//        for(Challenge ch : challenges) {
//            if (ch.getMonth().equalsIgnoreCase(month)) {
//                return  ch;
//            }
//        }
        return ch.orElse(null);
    }

    // put/challenge/{id}
    public boolean updateChallenge(Long id, Challenge updated) {
        Optional<Challenge> ch = challengeRepository.findById(id);

        if (ch.isPresent()) {
            Challenge challengeToUpdate = ch.get();
            challengeToUpdate.setMonth(updated.getMonth());
            challengeToUpdate.setDescription(updated.getDescription());
            challengeRepository.save(challengeToUpdate);
            return true;
        }
//        for(Challenge ch : challenges) {
//            if (ch.getId().equals(id)) {
//                ch.setMonth(updated.getMonth());
//                ch.setDescription(updated.getDescription());
//                return true;
//            }
//        }
        return false;
    }

    // delete/challenge/{id}
    public boolean deleteChallenge(Long id) {
//        return challenges.removeIf(ch -> ch.getId().equals(id));

        Optional<Challenge> ch = challengeRepository.findById(id);
        if (ch.isPresent()) {
            challengeRepository.deleteById(id);
            return true;
        }

        return  false;
    }
}