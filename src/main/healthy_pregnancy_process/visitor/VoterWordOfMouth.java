package main.healthy_pregnancy_process.visitor;

import main.healthy_pregnancy_process.model.IVoteVisitor;
import main.healthy_pregnancy_process.model.Voter;
import main.healthy_pregnancy_process.strategy.GirlStrategy;

public class VoterWordOfMouth extends Voter {
    private final GirlStrategy advice;

    public VoterWordOfMouth(GirlStrategy advice) {
        this.advice = advice;
    }

    @Override
    public void accept(IVoteVisitor visitor) {
        visitor.visit(this);
    }

    public GirlStrategy getAdvice() {
        return advice;
    }
}
