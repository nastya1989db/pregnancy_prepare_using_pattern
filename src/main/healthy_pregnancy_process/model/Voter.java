package main.healthy_pregnancy_process.model;

// общий родительский класс для голосующих
public abstract class Voter {
    public abstract void accept(IVoteVisitor visitor);
}
