package tictactoe

class GameController {
    def startGame = {
        render(params.player + " move first")
    }

    def stopGame = {
        render("Stopped")
    }

    def move = {
        render params.i + "," + params.j;
    }

    def waitsForMove = {

    }
}
