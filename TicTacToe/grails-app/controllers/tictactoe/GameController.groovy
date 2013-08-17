package tictactoe

class GameController {
    def BoardService boardService
    def Board board
    def Character player1
    def Character player2
    def Character activePlayer

    def startGame = {
        if (params.player == 'x') {
            player1 = Character.X
            player2 = Character.O
        } else {
            player1 = Character.O
            player2 = Character.X
        }
        //set player1 active
        activePlayer = player1
        board = boardService.initAllCell()
        render(params.player + " move first")
    }

    def stopGame = {
        render("Stopped")
    }

    def move = {
        Cell cell = boardService.findCellByRowAndCol(Integer.parseInt(params.row), Integer.parseInt(params.col))
        boardService.updateCell(cell, activePlayer.toString())
        changeActivePlayer()
        render params.row + "," + params.col;
    }

    def changeActivePlayer() {
        activePlayer = (activePlayer == player1) ? player2 : player1
    }

    def waitsForMove = {

    }
}
