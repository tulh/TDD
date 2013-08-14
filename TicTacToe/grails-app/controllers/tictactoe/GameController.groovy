package tictactoe

class GameController {
    public static final String X = "x"
    public static final String O = "o"

    def startGame(String player){
        render("Started")
    }

    def stopGame = {
        render("Stopped")
    }

    def move = {
        render params.i;
    }

    def waitsForMove(String player) {

    }
}
