package com.example.roverkata.models

import com.example.roverkata.exceptions.ObstacleInPathException
import com.example.roverkata.models.Rover.Command.Companion.getCommand

class Rover {

    enum class Command(val command: Char) {
        LEFT('l'),
        RIGHT('r'),
        FORWARD('f');

        companion object {
            fun getCommand(value: Char): Command {
                return when (value) {
                    'l' -> LEFT
                    'r' -> RIGHT
                    else -> FORWARD
                }
            }
        }
    }

    companion object {
        const val X_MAIN = 5
        const val Y_MAIN = 5
    }

    var direction: CardinalPoints = CardinalPoints.NORTH
    var position = Position(x = X_MAIN, y = Y_MAIN)
    var obstacles = emptyList<Position>()

    fun execute(commands: String) {
        commands.forEach { commandValue ->
            val command = getCommand(value = commandValue)
            when (command) {
                Command.LEFT -> turnLeft()
                Command.RIGHT -> turnRight()
                Command.FORWARD -> goForward()
            }
        }
    }

    private fun goForward() {
        val newPosition = position.copy()
        when (direction) {
            CardinalPoints.NORTH -> newPosition.incrementY()
            CardinalPoints.EAST -> newPosition.incrementX()
            CardinalPoints.SOUTH -> newPosition.decrementY()
            CardinalPoints.WEST -> newPosition.decrementX()
        }
        if (obstacles.contains(newPosition)) {
            throw ObstacleInPathException(newPosition)
        } else {
            position = newPosition
        }
    }

    private fun turnLeft() {
        direction = when (direction) {
            CardinalPoints.NORTH -> CardinalPoints.WEST
            CardinalPoints.WEST -> CardinalPoints.SOUTH
            CardinalPoints.SOUTH -> CardinalPoints.EAST
            CardinalPoints.EAST -> CardinalPoints.NORTH
        }
    }

    private fun turnRight() {
        direction = when (direction) {
            CardinalPoints.NORTH -> CardinalPoints.EAST
            CardinalPoints.EAST -> CardinalPoints.SOUTH
            CardinalPoints.SOUTH -> CardinalPoints.WEST
            CardinalPoints.WEST -> CardinalPoints.NORTH
        }
    }

    override fun toString(): String {
        return "Direction: ${direction.value}, Position: (${position.x}, ${position.y})"
    }
}