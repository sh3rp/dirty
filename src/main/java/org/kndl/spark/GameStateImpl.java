package org.kndl.spark;

import org.kndl.spark.model.Answer;
import org.kndl.spark.model.Game;
import org.kndl.spark.model.Player;
import org.kndl.spark.model.Question;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by skendall on 12/11/2014.
 */
public class GameStateImpl implements GameState {

    private static final AtomicLong IDGEN = new AtomicLong();

    private Set<Game> games;
    private Set<Player> players;
    private Set<Question> questions;

    private Map<Long, Game> gamesById;
    private Map<Long, Question> questionsById;
    private Map<Long, Player> playersById;
    private Map<String,Player> playersByEmail;

    public GameStateImpl() {
        this.games = new HashSet<Game>();
        this.players = new HashSet<Player>();
        this.questions = new HashSet<Question>();
        this.playersByEmail = new HashMap<String,Player>();
        this.gamesById = new HashMap<Long,Game>();
        this.questionsById = new HashMap<Long,Question>();
        this.playersById = new HashMap<Long,Player>();
    }

    @Override
    public Game newGame(String host, Set<String> players) {
        Game game = new Game();
        game.setId(IDGEN.getAndIncrement());
        Player hostPlayer = null;
        if(!playersByEmail.containsKey(host))
            game.setHost(newPlayer(host));
        else
            game.setHost(playersByEmail.get(host));

        Set<Player> gamePlayers = new HashSet<Player>();

        for(String email : players) {
            Player p = null;
            if(!playersByEmail.containsKey(email))
                gamePlayers.add(newPlayer(email));
            else
                gamePlayers.add(playersByEmail.get(email));
        }
        game.setPlayers(gamePlayers);
        game.setInProgress(true);
        games.add(game);
        gamesById.put(game.getId(),game);

        return game;
    }

    @Override
    public Question randomQuestion() {
        Random r = new Random();
        int rIdx = r.nextInt(questions.size());
        int idx = 0;
        for(Question q : questions) {
            if(idx == rIdx)
                return q;
            else
                idx++;
        }
        return null;
    }

    @Override
    public Question newQuestion(String question) {
        return newQ(question);
    }

    @Override
    public Game setQuestion(long game, long question) {
        Game g = gamesById.get(game);
        Question q = questionsById.get(question);
        g.setCurrentQuestion(q);
        return g;
    }

    @Override
    public Game setAnswer(long gameId, String playerId, String answer) {
        Game game = gamesById.get(gameId);
        game.setAnswer(playerId,answer);
        return game;
    }

    @Override
    public Game setWinner(long gameId, String userId) {
        Game g = gamesById.get(gameId);
        Player p = playersById.get(userId);
        g.setWinner(p);
        g.setInProgress(false);
        return g;
    }

    @Override
    public Game get(long id) {
        return gamesById.get(id);
    }

    private Player newPlayer(String email) {
        Player player = new Player();
        player.setId(IDGEN.getAndIncrement());
        player.setEmail(email);
        player.setName(email);
        playersByEmail.put(email,player);
        playersById.put(player.getId(),player);
        return player;
    }

    private Question newQ(String question) {
        Question q = new Question();
        q.setId(IDGEN.getAndIncrement());
        q.setQuestion(question);
        questions.add(q);
        questionsById.put(q.getId(),q);
        return q;
    }
}
