package tictactoe

class GameController {
    def List<Cell> cellHistories
    def Character activePlayer

    def startGame = {
        cellHistories = new ArrayList<Cell>()
        activePlayer = (params.player == 'x') ? Character.X : Character.O
        render(params.player + " move first")
    }

    def stopGame = {
        render("Stopped")
    }

    def move = {
        Cell cell = new Cell(row: params.row, col: params.col, value: activePlayer)
        render params.row + "," + params.col
        cellHistories.add(cell)
        if (isWinner(activePlayer)) {
            println(activePlayer + " win")
            render(activePlayer + " win")
        } else if (isBoardFull()) {
            println("full!")
            render("full!")
            stopGame()
        } else {
            changeActivePlayer()
        }
    }

    def changeActivePlayer() {
        activePlayer = (activePlayer == Character.X) ? Character.O : Character.X
    }

    boolean isWinner(Character character) {
        //todo loop cellHistories to check win or not
        return false
    }

    boolean isBoardFull() {
        return (cellHistories.size() == 9)
    }
}
