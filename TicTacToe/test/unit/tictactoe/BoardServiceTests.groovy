package tictactoe

import grails.test.*

class BoardServiceTests extends GrailsUnitTestCase {
    BoardService boardService
    Board board

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testInitBoard() {
        boardService = new BoardService()
        board = boardService.initAllCell()

        assertTrue board.getAllCell().size() == 9
    }

    void testFindCellByRowAndCol() {
        boardService = new BoardService()
        board = boardService.initAllCell()
        Cell cell = boardService.findCellByRowAndCol(1,2,board)
        assertNotNull cell
        assertEquals 1, cell.getRow()
        assertEquals 2, cell.getCol()
    }
}
