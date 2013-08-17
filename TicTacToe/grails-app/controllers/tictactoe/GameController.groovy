package tictactoe

class GameController {
    def BoardService boardService = new BoardService()
    def Board board = new Board()
    def Character player1
    def Character player2
    def Character activePlayer
    boolean allCellFilled

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
        //boardService = new BoardService()
        board = boardService.initAllCell()
        render(params.player + " move first")
    }

    def stopGame = {
        render("Stopped")
    }

    def move = {
        println("BOARD IS " + board)
        Cell cell = boardService.findCellByRowAndCol(Integer.parseInt(params.row), Integer.parseInt(params.col),board)
        boardService.updateCell(cell, activePlayer.toString())
        checkGameStatus()
        render params.row + "," + params.col;
    }

    def changeActivePlayer() {
        activePlayer = (activePlayer == player1) ? player2 : player1
    }

    String checkGameStatus() {
        //check if user win
        if(isWinner(activePlayer))
        {
            return "winner is: " + activePlayer.toString()
        }
        else if (isAllFieldFilled()) {
            //game draw
            return "draw"
        }
        else return "continue"

    }

    boolean isWinner(Character character) {
        return false
    }

    boolean isAllFieldFilled() {
        for (Cell cell : board.getAllCell()) {
            if (cell.getValue() == null)
                return false;
        }
        return true
    }
}
