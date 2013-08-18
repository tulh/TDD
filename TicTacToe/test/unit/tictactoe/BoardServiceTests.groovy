package tictactoe

import grails.test.*

class BoardServiceTests extends GrailsUnitTestCase {
    BoardService boardService

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testInitBoard() {
        List<Cell> board = new ArrayList<Cell>()
        boardService = new BoardService()
        board = boardService.initAllCell(board)

        assertTrue board.size() == 9
    }

    void testFindCellByRowAndCol() {
        List<Cell> board = new ArrayList<Cell>()
        boardService = new BoardService()
        board = boardService.initAllCell(board)
        Cell cell = boardService.findCellByRowAndCol(1,2,board)
        assertNotNull cell
        assertEquals 1, cell.getRow()
        assertEquals 2, cell.getCol()
        assertEquals null, cell.getValue()
    }

    void testUpdateACell(){
        List<Cell> board = new ArrayList<Cell>()
        boardService = new BoardService()
        board = boardService.initAllCell(board)
        Cell cell = boardService.findCellByRowAndCol(1,2,board)
        assertEquals null, cell.getValue()
        boardService.updateCell(cell, "x")
        assertEquals "x", cell.getValue()

    }

}
