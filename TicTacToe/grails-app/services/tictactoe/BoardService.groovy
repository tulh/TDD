package tictactoe

class BoardService {

    Board board
    static transactional = true

    Cell findCellByRowAndCol(int row, int col, board) {
        for (Cell cell : board.getAllCell()) {
            if ((cell.row == row) && (cell.col == col)) {
                return cell
            }
        }
        return null
    }

    def updateCell(Cell cell, String value) {

    }

    Board initAllCell() {
        board = new Board()

        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 3; col++) {
                Cell cell = new Cell(row: row, col: col, value: null)
                board.getAllCell().add(cell)
            }
        }
        return board;
    }
}
