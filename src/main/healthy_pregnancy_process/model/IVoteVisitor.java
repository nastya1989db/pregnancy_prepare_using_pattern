package main.healthy_pregnancy_process.model;

import main.healthy_pregnancy_process.visitor.*;

/**
 *Создадим class Voting, который реализует паттерн Visitor. Он получит коллекцию объектов-визитов
 *  и соберёт голоса от каждого элемента, который перечислен в классе GirlStrategy.
 */
public interface IVoteVisitor {
    void visit(VoterGrandma grandma);
    void visit(VoterDoctor doctor);
    void visit (VoterFriend friend);
    void visit (VoterSocialMedia socialMedia);
    void visit (VoterWordOfMouth wordOfMouth);
    void visit (VoterHusbandAdvice husbandAdvice);
    void visit (VoterHusbandFriends husbandFriends);


}
