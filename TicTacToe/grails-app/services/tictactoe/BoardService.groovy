package tictactoe

class BoardService {

    static transactional = true

    Cell findCellByRowAndCol(int row, int col, board) {
        for (Cell cell : board) {
            if ((cell.row == row) && (cell.col == col)) {
                return cell
            }
        }
        return null
    }

    def updateCell(Cell cell, String value) {
        cell.setValue(value)

    }

    boolean isBoardFull(board)
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

    String getWinner(def board) {
        Cell cell1 = findCellByRowAndCol(1,1,board)
        Cell cell2 = findCellByRowAndCol(1,2,board)
        Cell cell3 = findCellByRowAndCol(1,3,board)
        Cell cell4 = findCellByRowAndCol(2,1,board)
        Cell cell5 = findCellByRowAndCol(2,2,board)
        Cell cell6 = findCellByRowAndCol(2,3,board)
        Cell cell7 = findCellByRowAndCol(3,1,board)
        Cell cell8 = findCellByRowAndCol(3,2,board)
        Cell cell9 = findCellByRowAndCol(3,3,board)
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

    List<Cell> initAllCell(List<Cell> board) {
        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 3; col++) {
                Cell cell = new Cell(row: row, col: col, value: null)
                board.add(cell)
            }
        }
        return board;
    }

    Game saveGame(def firstPlayer, def winnerPlayer, def moves)
    {
        Game game = new Game(firstPlayer: firstPlayer, winner: winnerPlayer, moves: moves)
        game.save(flush: true)
        return game
    }
}
