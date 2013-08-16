package tictactoe

class GameController {
    def startGame = {
        render(params.player + " move first")
    }

    def stopGame = {
        render("Stopped")
    }

    def move = {
        render params.i;
    }

    def waitsForMove = {

    }
}
