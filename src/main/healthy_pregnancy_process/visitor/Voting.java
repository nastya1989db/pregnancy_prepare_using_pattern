package main.healthy_pregnancy_process.visitor;

import main.healthy_pregnancy_process.model.IVoteVisitor;
import main.healthy_pregnancy_process.strategy.GirlStrategy;

import java.util.HashMap;
import java.util.Map;

// класс, организующий сам процесс голосования и определяющий победителя (реализовано через паттерн Visitor).
public class Voting implements IVoteVisitor {
    private final Map<GirlStrategy, Integer> voteCount = new HashMap<>();
    private GirlStrategy result;
    private int maxVotes = 0;

    @Override
    public void visit(VoterGrandma voter) {
        processVote(voter.getAdvice());
    }

    @Override
    public void visit(VoterDoctor voter) {
        processVote(voter.getAdvice());
    }

    @Override
    public void visit(VoterFriend voter) {
        processVote(voter.getAdvice());
    }

    @Override
    public void visit(VoterSocialMedia voter) {
        processVote(voter.getAdvice());
    }

    @Override
    public void visit(VoterWordOfMouth voter) {
        processVote(voter.getAdvice());
    }

    @Override
    public void visit(VoterHusbandAdvice voter) {
        processVote(voter.getAdvice());
    }

    @Override
    public void visit(VoterHusbandFriends voter) {
        processVote(voter.getAdvice());

    }

    public void processVote(GirlStrategy strategy) {
        voteCount.put(strategy, voteCount.getOrDefault(strategy, 0) + 1);
        // Учитываем каждый голос
    }

    public GirlStrategy getWinner() throws IllegalAccessException {
        if (voteCount.isEmpty()) {
            throw new IllegalAccessException("Никто не проголосовал");
        }
        GirlStrategy winner = null;
        int maxVotes = 0;
        for (Map.Entry<GirlStrategy, Integer> entry :
                voteCount.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }
        return winner;
    }


}
