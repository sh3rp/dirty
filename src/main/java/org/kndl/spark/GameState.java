package org.kndl.spark;

import org.kndl.spark.model.Game;
import org.kndl.spark.model.Question;

import java.util.Set;

public interface GameState {
    public Game newGame(String host, Set<String> players);

    public Question randomQuestion();

    public Question newQuestion(String question);

    public Game setQuestion(long gameId, long questionId);

    public Game setAnswer(long gameId, String userId, String answer);

    public Game setWinner(long gameId, String userId);

    public Game get(long id);
}
