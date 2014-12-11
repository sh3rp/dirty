package org.kndl.spark;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.kndl.spark.model.Game;
import org.kndl.spark.model.Player;
import org.kndl.spark.model.Question;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * POST - /game/new             - create a new game; if players don't exist, create them; email all players
 * GET  - /question             - get random question
 * POST - /question             - create new question
 * POST - /game/:id/question    - set the question for the game and start game
 * POST - /game/:id/answer/:id  - answer a question for player id
 * GET  - /game/:id             - get game state
 */

public class Dirty {
    private Gson gson = new Gson();

    public Dirty() {
        GsonBuilder builder = new GsonBuilder();
        this.gson = builder.create();
    }

    public void init() {
        GameState game = new GameStateImpl();
        post("/game/new", (request,response) -> {
            Game.New newGame = gson.fromJson(request.body(),Game.New.class);
            return game.newGame(newGame.getHost(),newGame.getPlayers());
        },new JsonTransformer());
        get("/question", (request,response) -> {
            return game.randomQuestion();
        },new JsonTransformer());
        post("/question", (request,response) -> {
            Question.New newQuestion = gson.fromJson(request.body(),Question.New.class);
            return game.newQuestion(newQuestion.getQuestion());
        },new JsonTransformer());
        post("/game/:id/question", (request,response) -> {
            Game.SetQuestion setQuestion = gson.fromJson(request.body(),Game.SetQuestion.class);
            return game.setQuestion(setQuestion.getGameId(),setQuestion.getQuestionId());
        },new JsonTransformer());
        post("/game/:gameId/answer/:playerId", (request, response) -> {
            Game.SetAnswer answer = gson.fromJson(request.body(),Game.SetAnswer.class);
            return game.setAnswer(null,null,null);
        },new JsonTransformer());
        get("/game/:id", (request,response) -> {
            return game.get(Long.parseLong(request.params(":id")));
        },new JsonTransformer());
    }
}
