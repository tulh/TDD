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

    void testIsBoardFull()
    {
        List<Cell> board = new ArrayList<Cell>()
        boardService = new BoardService()
        board = boardService.initAllCell(board)
        def full = boardService.isBoardFull(board)
        assertNotSame(true, full)
        boardService.updateCell(boardService.findCellByRowAndCol(1,1,board), "x")
        boardService.updateCell(boardService.findCellByRowAndCol(1,2,board), "x")
        boardService.updateCell(boardService.findCellByRowAndCol(1,3,board), "o")
        boardService.updateCell(boardService.findCellByRowAndCol(2,1,board), "o")
        boardService.updateCell(boardService.findCellByRowAndCol(2,2,board), "o")
        boardService.updateCell(boardService.findCellByRowAndCol(2,3,board), "x")
        boardService.updateCell(boardService.findCellByRowAndCol(3,1,board), "o")
        boardService.updateCell(boardService.findCellByRowAndCol(3,2,board), "x")
        boardService.updateCell(boardService.findCellByRowAndCol(3,3,board), "x")
        assertTrue(boardService.isBoardFull(board))
    }

    void testGetWinner()
    {
        List<Cell> board = new ArrayList<Cell>()
        boardService = new BoardService()
        board = boardService.initAllCell(board)

        boardService.updateCell(boardService.findCellByRowAndCol(1,1,board), "x")
        boardService.updateCell(boardService.findCellByRowAndCol(1,2,board), "x")
        boardService.updateCell(boardService.findCellByRowAndCol(1,3,board), "o")
        boardService.updateCell(boardService.findCellByRowAndCol(2,1,board), "o")
        boardService.updateCell(boardService.findCellByRowAndCol(2,2,board), "o")
        boardService.updateCell(boardService.findCellByRowAndCol(2,3,board), "x")
        boardService.updateCell(boardService.findCellByRowAndCol(3,1,board), "o")
        boardService.updateCell(boardService.findCellByRowAndCol(3,2,board), "x")
        boardService.updateCell(boardService.findCellByRowAndCol(3,3,board), "x")
        assertEquals("o", boardService.getWinner(board))
    }
}
