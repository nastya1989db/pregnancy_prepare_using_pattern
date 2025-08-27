package main.healthy_pregnancy_process.visitor;

import main.healthy_pregnancy_process.model.Voter;
import main.healthy_pregnancy_process.model.IVoteVisitor;
import main.healthy_pregnancy_process.strategy.GirlStrategy;

public class VoterFriend extends Voter {
    private final GirlStrategy advice;

    public VoterFriend(GirlStrategy advice) {
        this.advice = advice;
    }

    @Override
    public void accept(IVoteVisitor visitor) {
        visitor.visit(this);// делегируем визит посетителю
    }

    GirlStrategy getAdvice() {
        // возвращаем совет друзей
        return advice;
    }
}
