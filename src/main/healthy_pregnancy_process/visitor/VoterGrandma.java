package main.healthy_pregnancy_process.visitor;

import main.healthy_pregnancy_process.model.Voter;
import main.healthy_pregnancy_process.model.IVoteVisitor;
import main.healthy_pregnancy_process.strategy.GirlStrategy;

public class VoterGrandma extends Voter {
    private final GirlStrategy advice;

    public VoterGrandma(GirlStrategy advice) {
        this.advice = advice;
    }

    @Override
    public void accept(IVoteVisitor visitor) {
        visitor.visit(this); // делегируем визит посетителю
    }

    GirlStrategy getAdvice() {
        return advice;// возвращаем совет бабушки
    }
}
