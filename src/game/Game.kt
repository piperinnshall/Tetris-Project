package game

import tetriminos.Generator.generate
import tetriminos.Tetrimino
import tetriminos.Tetrimino.Companion.setTetrimino

class Game {
    enum class GameState {
        NOT_PLAYING,
        GENERATING,
        DROPPING,
        GAME_OVER
    }
    private var state: GameState = GameState.NOT_PLAYING
    private var tetriminoList = mutableListOf<Tetrimino>()
    private lateinit var tetrimino: Tetrimino
    private var tetriminoY = 0
    private var timeExists: Double = 0.0
    private var timeSimulated: Double = 0.0
    private var gravity: Double = 0.2598
    init {
        repeat(3) {
            tetriminoList.add(generate())
        }
    }
    fun update(deltaUpdate: Double) {
        timeExists += deltaUpdate
        when (state) {
            GameState.NOT_PLAYING -> state = GameState.GENERATING
            GameState.GENERATING -> generating()
            GameState.DROPPING -> dropping()
            GameState.GAME_OVER -> print("")
        }
    }
    private fun generating() {
        tetrimino = tetriminoList.first()
        tetriminoList.removeAt(0)
        tetriminoList.add(generate())
        setTetrimino(tetrimino)
        state = GameState.DROPPING
    }
    private fun dropping() {
        if (timeExists > timeSimulated) {
            if (timeSimulated >= 1.0 / gravity) {
                tetriminoY++
            }
            timeSimulated += 1.0 / gravity
            tetrimino.setY(tetriminoY)
        }
    }
    fun setGravity(level: Int): Double {
        val gravityMap = mapOf(
            1 to 0.01667,
            2 to 0.021017,
            3 to 0.026977,
            4 to 0.035256,
            5 to 0.04693,
            6 to 0.06361,
            7 to 0.0879,
            8 to 0.1236,
            9 to 0.1775,
            10 to 0.2598,
            11 to 0.388,
            12 to 0.59,
            13 to 0.92,
            14 to 1.46,
            15 to 2.36
        )
        return gravityMap[level] ?: 1.0
    }
}