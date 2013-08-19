package tictactoe

import grails.converters.JSON

class GameController
{
    def boardService
    static List<Cell> board = new ArrayList<Cell>()
    static String activePlayer

    static String firstPlayer=""
    static String winnerPlayer=""
    static String moves=""

    final static String X = "x"
    final static String O = "o"

    def startGame = {
        // init board
        board = boardService.initAllCell(board)

        activePlayer = (params.player == 'x') ? X : O
        firstPlayer=activePlayer
        render(params.player + " move first")
    }

    def stopGame = {
        board = new ArrayList<Cell>()
        firstPlayer=""
        winnerPlayer=""
        moves=""
        render("Stopped")
    }

    def move = {
        // find & update cell's value
        Cell cell = boardService.findCellByRowAndCol(Integer.parseInt(params.row), Integer.parseInt(params.col), board)
        boardService.updateCell(cell, activePlayer)
        moves += params.row + "_" + params.col + ", "
        render params.row + "," + params.col
        println activePlayer
        def winner = boardService.getWinner(board)
        def fullBoard = boardService.isBoardFull(board)
        if(winner != null)
        {
            winnerPlayer = winner
            showGameHistory()
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

    def showGameHistory() {
        def game = boardService.saveGame(firstPlayer, winnerPlayer, moves)
        render "(${[game : game] as JSON});"
    }

}
