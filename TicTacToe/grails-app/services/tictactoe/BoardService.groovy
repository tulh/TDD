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

    List<Cell> initAllCell(List<Cell> board) {
        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 3; col++) {
                Cell cell = new Cell(row: row, col: col, value: null)
                board.add(cell)
            }
        }
        return board;
    }
}
