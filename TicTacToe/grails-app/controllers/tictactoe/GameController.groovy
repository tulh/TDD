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
        if (getWinner()!=null)
        {
            println getWinner() + " win"
            render(getWinner() + " win")
            stopGame()
        }
        else if (isBoardFull())
        {
            println("full!")
            render("full!")
            stopGame()
        }

        changeActivePlayer()
    }

    def changeActivePlayer()
    {
        activePlayer = (activePlayer == X) ? O : X
    }

    static boolean isBoardFull()
    {
        for (Cell cell : board)
        {
            if (cell.value == null)
            {
                return false
            }
        }
        println "FULL!!!"
        return true
    }

    String getWinner() {
        Cell cell1 = boardService.findCellByRowAndCol(1,1,board)
        Cell cell2 = boardService.findCellByRowAndCol(1,2,board)
        Cell cell3 = boardService.findCellByRowAndCol(1,3,board)
        Cell cell4 = boardService.findCellByRowAndCol(2,1,board)
        Cell cell5 = boardService.findCellByRowAndCol(2,2,board)
        Cell cell6 = boardService.findCellByRowAndCol(2,3,board)
        Cell cell7 = boardService.findCellByRowAndCol(3,1,board)
        Cell cell8 = boardService.findCellByRowAndCol(3,2,board)
        Cell cell9 = boardService.findCellByRowAndCol(3,3,board)
        // horizontal line
        if(isAllLike(cell1, cell2, cell3)) return cell1.value
        if(isAllLike(cell4, cell5, cell6)) return cell4.value
        if(isAllLike(cell7, cell8, cell9)) return cell7.value
        // vertical line
        if(isAllLike(cell1, cell4, cell7)) return cell1.value
        if(isAllLike(cell2, cell5, cell8)) return cell2.value
        if(isAllLike(cell3, cell6, cell9)) return cell3.value
        // left diagonal line
        if(isAllLike(cell1, cell5, cell9)) return cell1.value
        // right diagonal line
        if(isAllLike(cell7, cell5, cell3)) return cell7.value
        return null
    }

    private boolean isAllLike(Cell cell1, Cell cell2, Cell cell3)
    {
        return (cell1.value!=null)&&(cell2.value!=null)&&(cell3.value!=null)&&
                (cell1.value == cell2.value)&&(cell1.value==cell3.value)&&(cell2.value==cell3.value)
    }
    def save = {
        Cell cell = new Cell(row: 1, col: 1, value: "x")
        cell.save(flush: true)
        render cell.getId()
    }
}
