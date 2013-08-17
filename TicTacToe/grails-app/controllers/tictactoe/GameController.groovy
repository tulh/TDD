package tictactoe

class GameController {
    def BoardService boardService
    def Board board
    def Character player1
    def Character player2

    def startGame = {
        if(params.player=='x')
        {
            player1 = Character.X
            player2 = Character.O
        }
        else
        {
            player1 = Character.O
            player2 = Character.X
        }
        board = boardService.initAllCell()
        render(params.player + " move first")
    }

    def stopGame = {
        render("Stopped")
    }

    def move = {
        Cell cell = boardService.findCellByRowAndCol(params.row, params.col)
        boardService.updateCell(cell, "")
        render params.row + "," + params.col;
    }

    def waitsForMove = {

    }
}
