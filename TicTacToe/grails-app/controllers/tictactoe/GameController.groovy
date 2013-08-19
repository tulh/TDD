package tictactoe

class GameController
{
    def boardService
    static List<Cell> board = new ArrayList<Cell>()
    static String activePlayer

    final static String X = "x"
    final static String O = "o"

    def startGame = {
        // init board
        board = boardService.initAllCell(board)

        activePlayer = (params.player == 'x') ? X : O
        render(params.player + " move first")
    }

    def stopGame = {
        board = new ArrayList<Cell>()
        render("Stopped")
    }

    def move = {
        // find & update cell's value
        Cell cell = boardService.findCellByRowAndCol(Integer.parseInt(params.row), Integer.parseInt(params.col), board)
        boardService.updateCell(cell, activePlayer)
        render params.row + "," + params.col
        println activePlayer
        def winner = boardService.getWinner(board)
        def fullBoard = boardService.isBoardFull(board)
        if(winner != null)
        {
            println winner + " win"
            render(winner + " win")
            stopGame()
        }
        if(fullBoard)
        {
            println "fullBoard: " + fullBoard
            render("full!")
            stopGame()
        }

        changeActivePlayer()
    }

    def changeActivePlayer()
    {
        activePlayer = (activePlayer == X) ? O : X
    }

    def save = {
        Cell cell = new Cell(row: 1, col: 1, value: "x")
        cell.save(flush: true)
        render cell.getId()
    }
}
