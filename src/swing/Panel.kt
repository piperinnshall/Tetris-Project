package swing

import draw.Background
import draw.Grid
import input.KeyInput
import input.MouseInput
import gameloop.EventHandler
import gameloop.UpdateHandler

import java.awt.Graphics
import javax.swing.JPanel
class Panel : JPanel() {
    companion object {
        const val BOARD_WIDTH = 800
        const val BOARD_HEIGHT = 600
    }
    val updateHandler = UpdateHandler(this)
    val eventHandler = EventHandler(this)
    init {
        isFocusable = true
        addKeyListener(KeyInput(this))
        addMouseListener(MouseInput(this))
    }
    fun update() {

    }
    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        Background(g)
        Grid(g)
    }
}