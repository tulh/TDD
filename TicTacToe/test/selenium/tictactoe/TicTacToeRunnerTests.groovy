package tictactoe

import grails.plugins.selenium.SeleniumAware
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertTrue

@Mixin(SeleniumAware)
class TicTacToeRunnerTests extends GroovyTestCase {

    GameController gameController = new GameController()
    @Before
    void setUp() {
    }

    @After
    void tearDown() {
        super.tearDown()
    }

    void startGame(String player) {
        selenium.open("http://localhost:8080/TicTacToe/game/startGame?player=$player")
    }

    void stopGame() {
        selenium.open("http://localhost:8080/TicTacToe/game/stopGame")
    }

    void gameHasStarted() {
        assertTrue selenium.isTextPresent(GameController.X)
    }

    void gameHasStopped() {
        assertTrue selenium.isTextPresent("Stopped")
    }

    void move(int i) {
        selenium.open("http://localhost:8080/TicTacToe/game/move?i=$i")
    }

    void userHasMovedTo(int i){
        assertTrue selenium.isTextPresent(i.toString())
    }

    @Test
    void testUserStartAGameAndThenStop() {
        startGame("x");
        gameHasStarted()
        stopGame()
        gameHasStopped()
    }

    @Test
    void testUserStartAGameAndMovesThenStop() {
        startGame("x")
        //gameController.waitsForMove(gameController.X)
        move(1)
        userHasMovedTo(1)
        //gameController.waitsForMove(gameController.O)
        move(2)
        userHasMovedTo(2)
        stopGame()
    }
}
