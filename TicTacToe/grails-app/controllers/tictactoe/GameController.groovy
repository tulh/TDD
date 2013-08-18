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
        render("Stopped")
    }

    def move = {
        // find & update cell's value
        Cell cell = boardService.findCellByRowAndCol(Integer.parseInt(params.row), Integer.parseInt(params.col), board)
        boardService.updateCell(cell, activePlayer)
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
        if(movesHistory.size()>=3)
        {
            // check for 4 cases: horizontal line, vertical line, diagonal line, 2 diagonals
            println "found 3 moves"
            movesHistory.sort{x,y->
                x.col <=> y.col
            }
            for(Cell cell: movesHistory)
            {
                println cell.row+ " " + cell.col
            }
            Cell cell1 = movesHistory.get(0)
            Cell cell2 = movesHistory.get(1)
            Cell cell3 = movesHistory.get(2)
//            println(cell1.col + " " + cell1.row)
//            println(cell2.col + " " + cell2.row)
//            println(cell3.col + " " + cell3.row)
            if((cell1.col==cell2.col && cell2.col==cell3.col) || (cell1.row==cell2.row && cell2.row==cell3.row)
            || (cell1.col==1 && cell1.row==1&&cell2.col==2&&cell2.row==2&&cell3.col==3&&cell3.row==3)
                    || (cell1.col==1&&cell1.row==3&&cell2.col==2 && cell2.row==2&&cell3.col==3&&cell3.row==1))
            {
                return true
            }
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

    def save = {
        Cell cell = new Cell(row: 1, col: 1, value: "x")
        cell.save(flush: true)
        render cell.getId()
    }
}
