package tictactoe

import grails.test.ControllerUnitTestCase

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

    void testMove() {
        gameController.startGame()
        gameController.move(1,1)
        assertEquals "Stopped", gameController.response.contentAsString

    }
}
