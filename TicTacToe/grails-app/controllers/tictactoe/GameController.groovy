package tictactoe

class GameController {
    final static String X = "x"
    final static String O = "o"

    def startGame = {
    render("Started")
    }

    def stopGame = {
    render("Stopped")
    }

    def move = {
        render params.i;
    }


}
