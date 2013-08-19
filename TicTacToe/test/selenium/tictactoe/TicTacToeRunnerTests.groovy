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

    void save()
    {
        selenium.open("http://localhost:8080/TicTacToe/game/save")
    }

    void gameHasStarted()
    {
        assertNotNull(selenium.getBodyText())
    }

    void gameHasStopped()
    {
        assertTrue selenium.isTextPresent("Stopped")
    }

    void move(int row, int col)
    {
        selenium.open("http://localhost:8080/TicTacToe/game/move?row=$row&col=$col")
    }

    void playerHasMovedTo(int row, int col)
    {
        assertTrue selenium.isTextPresent(row.toString() + "," + col.toString())
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

    void gameDisplaysTheWinner(String player)
    {
        assertTrue selenium.isTextPresent(player + " win")
    }

    void gameDisplaysBoardFull()
    {
        println selenium.bodyText
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
        gameDisplaysTheWinner("x")
        gameHasStopped()

    }

    @Test
    void testSave() {
        save()
        assertNotNull(selenium.getBodyText())
    }

    @Test
    void testPlayerStartGameAndPlayUntilBoardIsFullAndNoOneWin()
    {
        startGame("x")
        gameDisplaysThePlayerMoveFirst("x")
        move(1, 1) //x
        playerHasMovedTo(1, 1)
        move(3, 1) //o
        playerHasMovedTo(3, 1)
        move(1, 2) //x
        playerHasMovedTo(1, 2)
        move(2, 1)  //o
        playerHasMovedTo(2, 1)
        move(2, 3)  //x
        playerHasMovedTo(2, 3)
        move(2, 2)  //o
        playerHasMovedTo(2, 2)
        move(3, 1)  //x
        playerHasMovedTo(3, 1)
        move(3, 2)  //o
        playerHasMovedTo(3, 2)
        move(3, 3)  //x
        playerHasMovedTo(3, 3)
        gameDisplaysBoardFull()
        gameHasStopped()
    }
}
