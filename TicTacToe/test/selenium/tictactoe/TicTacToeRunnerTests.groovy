package tictactoe

import grails.plugins.selenium.*
import org.junit.*
import static org.junit.Assert.*
import static org.hamcrest.Matchers.*

@Mixin(SeleniumAware)
class TicTacToeRunnerTests extends GroovyTestCase {

    def GameController gameController
    @Before
    void setUp() {
    }

    @After
    void tearDown() {
        super.tearDown()
    }

    void startGame() {
        selenium.open("http://localhost:8080/TicTacToe/game/startGame")
    }

    void stopGame() {
        selenium.open("http://localhost:8080/TicTacToe/game/stopGame")
    }

    void gameHasStarted() {
        assertTrue selenium.isTextPresent("Started")
    }

    void gameHasStopped() {
        assertTrue selenium.isTextPresent("Stopped")
    }

    void userHasMovedTo(int i){
        assertTrue selenium.isTextPresent(i)
    }

    @Test
    void testUserStartAGameAndThenStop() {
        startGame()
        gameHasStarted()
        stopGame()
        gameHasStopped()
    }

    @Test
    void testUserStartAGameAndMovesThenStop() {
        startGame(gameController.X)
        gameController.waitsForMove(gameController.X)
        gameController.move(1)
        userHasMovedTo(1)
        gameController.waitsForMove(gameController.O)
        gameController.move(2)
        userHasMovedTo(2)
        stopGame()
    }
}
