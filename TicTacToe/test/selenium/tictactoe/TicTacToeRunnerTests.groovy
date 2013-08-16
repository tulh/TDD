package tictactoe

import grails.plugins.selenium.SeleniumAware
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

@Mixin(SeleniumAware)
class TicTacToeRunnerTests extends GroovyTestCase
{

    @Before
    void setUp()
    {
    }

    @After
    void tearDown()
    {
        super.tearDown()
    }

    void startGame(String player)
    {
        selenium.open("http://localhost:8080/TicTacToe/game/startGame?player=$player")
    }

    void stopGame()
    {
        selenium.open("http://localhost:8080/TicTacToe/game/stopGame")
    }

    void gameHasStarted()
    {
        assertNotNull(selenium.getBodyText())
    }

    void gameHasStopped()
    {
        assertTrue selenium.isTextPresent("Stopped")
    }

    void move(int i, int j)
    {
        selenium.open("http://localhost:8080/TicTacToe/game/move?i=$i&j=$j")
    }

    void playerHasMovedTo(int i, int j)
    {
        assertTrue selenium.isTextPresent(i.toString() + "," + j.toString())
    }

    void gameDisplaysThePlayerMoveFirst(String player)
    {
        assertTrue selenium.isTextPresent(player + " move first")
    }

    @Test
    void testPlayerStartAGameAndThenStop()
    {
        startGame("x");
        gameHasStarted()
        stopGame()
        gameHasStopped()
    }

    @Test
    void testPlayerStartAGameAndMovesThenStop()
    {
        startGame("x")
        gameDisplaysThePlayerMoveFirst("x")
        move(1, 1)
        playerHasMovedTo(1, 1)
        move(1, 2)
        playerHasMovedTo(1, 2)
        stopGame()
    }

    void gameDisplaysTheWinner()
    {

    }

    @Test
    void testPlayerStartAGameAndPlayUntilHeWin()
    {
        startGame("x")
        gameDisplaysThePlayerMoveFirst("x")
        move(1, 1)
        playerHasMovedTo(1, 1)
        move(1, 2)
        playerHasMovedTo(1, 2)
        move(2, 2)
        playerHasMovedTo(2, 2)
        move(1, 3)
        playerHasMovedTo(1, 3)
        move(3, 3)
        playerHasMovedTo(3, 3)
        gameDisplaysTheWinner()
        gameHasStopped()

    }
}
