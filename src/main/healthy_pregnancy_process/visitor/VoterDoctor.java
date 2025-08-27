package main.healthy_pregnancy_process.visitor;

import main.healthy_pregnancy_process.model.Voter;
import main.healthy_pregnancy_process.model.IVoteVisitor;
import main.healthy_pregnancy_process.strategy.GirlStrategy;

public class VoterDoctor extends Voter {
    private final GirlStrategy advice;

    public VoterDoctor(GirlStrategy advice) {
        this.advice = advice;
    }

    @Override
    public void accept(IVoteVisitor visitor) {
        visitor.visit(this);
    }

    GirlStrategy getAdvice() {
        return advice;// возвращаем совет друга
    }
}
