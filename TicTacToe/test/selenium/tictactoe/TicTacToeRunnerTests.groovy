package tictactoe

import grails.plugins.selenium.*
import org.junit.*
import static org.junit.Assert.*
import static org.hamcrest.Matchers.*

@Mixin(SeleniumAware)
class TicTacToeRunnerTests extends GroovyTestCase {
    @Before void setUp() {
    }

    @After void tearDown() {
        super.tearDown()
    }

    void startGame(){
        selenium.open("http://localhost:8080/TicTacToe/game/startGame")
    }

    void stopGame(){
        selenium.open("http://localhost:8080/TicTacToe/game/stopGame")
    }

    void gameHasStarted(){
        assertTrue selenium.isTextPresent("Started")
    }

    void gameHasStopped(){
        assertTrue selenium.isTextPresent("Stopped")
    }
    @Test void testUserStartAGameAndThenStop() {
        startGame()
        gameHasStarted()
        stopGame()
        gameHasStopped()
    }
}
