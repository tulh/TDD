package tictactoe

class GameController {
    public static final String X = "x"
    public static final String O = "o"

    def startGame = {
        render(params.player)
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
