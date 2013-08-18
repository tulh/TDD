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
//        board = new ArrayList<Cell>()
//        for (int row = 1; row <= 3; row++)
//        {
//            for (int col = 1; col <= 3; col++)
//            {
//                Cell cell = new Cell(row: row, col: col, value: null)
//                board.add(cell)
//            }
//        }
        activePlayer = (params.player == 'x') ? X : O
        render(params.player + " move first")
    }

    Cell find(int row, int col)
    {
        for (Cell cell : board)
        {
            if (cell.row == row && cell.col == col)
            {
                return cell
            }
        }
    }

    def stopGame = {
        render("Stopped")
    }

    def move = {
        // find & update cell's value
        Cell cell = boardService.findCellByRowAndCol(Integer.parseInt(params.row), Integer.parseInt(params.col), board)
        boardService.updateCell(cell, activePlayer)
//        Cell cell = find(Integer.parseInt(params.row), Integer.parseInt(params.col))
//        cell.setValue(activePlayer)
        //Cell cell = new Cell(row: params.row, col: params.col, value: activePlayer)
        render params.row + "," + params.col
        if (isWinner(activePlayer))
        {
            println(activePlayer + " win")
            render(activePlayer + " win")
            stopGame()
        }
        else if (isBoardFull())
        {
            println("full!")
            render("full!")
            stopGame()
        }
        else
        {
            changeActivePlayer()
        }
    }

    def changeActivePlayer()
    {
        activePlayer = (activePlayer == X) ? O : X
    }

    boolean isWinner(String player)
    {
        //todo loop cellHistories to check win or not
        List<Cell> movesHistory = new ArrayList<Cell>()
        for (Cell cell : board)
        {
            if(cell.value==player)
            {
                movesHistory.add(cell)
            }
        }
        if(movesHistory.size()==3)
        {
            // check for 4 cases: horizontal line, vertical line, diagonal line, 2 diagonals
            println "found 3 moves"
            return true
        }

        return false
    }

    boolean isBoardFull()
    {
        for (Cell cell : board)
        {
            if (cell.value == null)
            {
                return false
            }
        }
        return true
    }
}
