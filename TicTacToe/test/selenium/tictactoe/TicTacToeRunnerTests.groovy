package tictactoe

import grails.plugins.selenium.SeleniumAware
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

@Mixin(SeleniumAware)
class TicTacToeRunnerTests extends GroovyTestCase {

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
        assertNotNull(selenium.getBodyText())
    }

    void gameHasStopped() {
        assertTrue selenium.isTextPresent("Stopped")
    }

    void move(int i) {
        selenium.open("http://localhost:8080/TicTacToe/game/move?i=$i")
    }

    void playerHasMovedTo(int i){
        assertTrue selenium.isTextPresent(i.toString())
    }

    void gameDisplaysThePlayerMoveFirst(String player) {
        assertTrue selenium.isTextPresent(player + " move first")
    }

    @Test
    void testUserStartAGameAndThenStop() {
        startGame("x");
        gameHasStarted()
        stopGame()
        gameHasStopped()
    }

    @Test
    void testPlayerStartAGameAndMovesThenStop() {
        startGame("x")
        gameDisplaysThePlayerMoveFirst("x")
        move(1)
        playerHasMovedTo(1)
        move(2)
        playerHasMovedTo(2)
        stopGame()
    }
}
