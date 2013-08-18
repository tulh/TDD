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
        gameController = new GameController()
        gameController.params.player='x'
        gameController.startGame
        gameController.params.col=1
        gameController.params.row=1
        gameController.move

        assertEquals "Stopped", gameController.response.contentAsString

    }
}
