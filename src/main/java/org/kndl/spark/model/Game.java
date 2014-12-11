package org.kndl.spark.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by skendall on 12/11/2014.
 */
public class Game {
    private long id;
    private Set<Player> players;
    private Player host;
    private boolean inProgress;
    private Question currentQuestion;
    private Map<String,String> playerAnswers;
    private Player winner;

    // request new game

    public static class New {
        private String host;
        private Set<String> players;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Set<String> getPlayers() {
            return players;
        }

        public void setPlayers(Set<String> players) {
            this.players = players;
        }
    }

    public static class SetQuestion {
        private long questionId;
        private long gameId;

        public long getQuestionId() {
            return questionId;
        }

        public void setQuestionId(long questionId) {
            this.questionId = questionId;
        }

        public long getGameId() {
            return gameId;
        }

        public void setGameId(long gameId) {
            this.gameId = gameId;
        }
    }

    public static class SetAnswer {
        private long playerId;
        private String answer;

        public long getPlayerId() {
            return playerId;
        }

        public void setPlayerId(long playerId) {
            this.playerId = playerId;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }

    public static class SetWinner {
        private long gameId;
        private long playerId;

        public long getGameId() {
            return gameId;
        }

        public void setGameId(long gameId) {
            this.gameId = gameId;
        }

        public long getPlayerId() {
            return playerId;
        }

        public void setPlayerId(long playerId) {
            this.playerId = playerId;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Player getHost() {
        return host;
    }

    public void setHost(Player host) {
        this.host = host;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setAnswer(String playerId, String answer) {
        if(playerAnswers == null)
            playerAnswers = new HashMap<String,String>();
        playerAnswers.put(playerId,answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != game.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
