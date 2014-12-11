package org.kndl.spark.test;

import org.junit.Before;
import org.kndl.spark.GameState;
import org.kndl.spark.GameStateImpl;
import org.kndl.spark.model.Game;
import org.kndl.spark.model.Player;
import org.kndl.spark.model.Question;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.AssertJUnit.*;

@Test
public class GameStateTest {

    private GameState game;

    private Set<String> players;

    private final String HOST = "a@mail.com";

    private final String P1 = "pants@pants.com";
    private final String P2 = "shoes@shoes.com";
    private final String P3 = "shirt@shirts.com";

    @BeforeClass
    public void before() {
        this.game = new GameStateImpl();
        this.players = new HashSet<String>();
        players.add(P1);
        players.add(P2);
        players.add(P3);
    }

    @Test
    public void newGame() {
        Game g = game.newGame(HOST,players);
        Player host = g.getHost();
        assertEquals(host.getEmail(),HOST);
        Set<Player> players = g.getPlayers();
        Player pants = new Player();
        pants.setEmail(P1);
        Player shoes = new Player();
        shoes.setEmail(P2);
        Player shirt = new Player();
        shirt.setEmail(P3);
        assertTrue(players.contains(shoes));
        assertTrue(players.contains(shirt));
        assertTrue(players.contains(pants));
    }

    @Test
    public void newQuestion() {
        Question q = game.newQuestion("What is your quest?");
        assertTrue(q.getQuestion().equals("What is your quest?"));
    }

    @Test
    public void randomQuestion() {
        game.newQuestion("What");
        Question q = game.randomQuestion();
        //assertTrue(q.getQuestion().equals("What"));
    }

    @Test
    public void setQuestion() {
        Question q = game.newQuestion("What");
        Game g = game.newGame(HOST,players);
        game.setQuestion(g.getId(),q.getId());
        Game gSet = game.get(g.getId());
        assertTrue(gSet.getCurrentQuestion().equals(q));
    }

    @Test
    public void setAnswer() {
        Question q = game.newQuestion("What");
        Game g = game.newGame(HOST, players);
        game.setQuestion(g.getId(), q.getId());
        game.setAnswer(g.getId(),P1,"This");
        game.setAnswer(g.getId(), P2, "That");
        game.setWinner(g.getId(), P1);
        assertFalse(g.isInProgress());
        Player p = new Player();
        p.setEmail(P1);
        assertNotNull(g.getWinner());
        assertTrue(g.getWinner().equals(p));
    }
}
