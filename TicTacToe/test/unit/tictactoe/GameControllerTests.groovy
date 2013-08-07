package tictactoe

import grails.test.*

class GameControllerTests extends ControllerUnitTestCase {
    def gameController;

    protected void setUp() {
        super.setUp()
        gameController = new GameController()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testStartGame() {
        gameController.startGame()
        assertEquals "Started", gameController.response.contentAsString

    }

    void testStopGame() {
        gameController.stopGame()
        assertEquals "Stopped", gameController.response.contentAsString

    }

    // 1st acceptance test
    void testUserStartAndThenStopGame(){
        gameController.startGame()

        gameController.stopGame()

    }
}
