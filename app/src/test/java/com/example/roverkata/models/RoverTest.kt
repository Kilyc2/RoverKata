package com.example.roverkata.models

import com.example.roverkata.models.Position.Companion.MAP_FIRST
import com.example.roverkata.models.Position.Companion.MAP_LIMIT
import com.example.roverkata.models.Rover.Companion.X_MAIN
import com.example.roverkata.models.Rover.Companion.Y_MAIN
import com.example.roverkata.exceptions.ObstacleInPathException
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RoverTest {

    private lateinit var sut: Rover

    @Before
    fun setUp() {
        sut = Rover()
    }

    //<editor-fold desc="Init">
    @Test
    fun sutIsNotNull() {
        assertNotNull(sut)
    }

    @Test
    fun sutInitialDirectionIsNorth() {
        assertEquals(CardinalPoints.NORTH, sut.direction)
    }

    @Test
    fun sutInitialCoordinatesAreCorrect() {
        assertEquals(Position(x = X_MAIN, y = Y_MAIN), sut.position)
    }

    //</editor-fold>

    //<editor-fold desc="Turn Right">
    @Test
    fun turnRightOnceChangesDirectionToEast() {
        sut.execute(commands = generateCommands(Rover.Command.RIGHT))

        assertEquals(CardinalPoints.EAST, sut.direction)
    }

    @Test
    fun turnRightTwiceChangesDirectionToSouth() {
        sut.execute(commands = generateCommands(Rover.Command.RIGHT, Rover.Command.RIGHT))

        assertEquals(CardinalPoints.SOUTH, sut.direction)
    }

    @Test
    fun turnRightThreeTimesChangesDirectionToWest() {
        sut.execute(
            commands = generateCommands(
                Rover.Command.RIGHT,
                Rover.Command.RIGHT,
                Rover.Command.RIGHT
            )
        )

        assertEquals(CardinalPoints.WEST, sut.direction)
    }

    @Test
    fun turnRightFourTimesChangesDirectionToNorth() {
        sut.execute(
            commands = generateCommands(
                Rover.Command.RIGHT,
                Rover.Command.RIGHT,
                Rover.Command.RIGHT,
                Rover.Command.RIGHT
            )
        )

        assertEquals(CardinalPoints.NORTH, sut.direction)
    }
    //</editor-fold>

    //<editor-fold desc="Turn Left">
    @Test
    fun turnLeftOnceChangesDirectionToWest() {
        sut.execute(commands = generateCommands(Rover.Command.LEFT))

        assertEquals(CardinalPoints.WEST, sut.direction)
    }

    @Test
    fun turnLeftTwiceChangesDirectionToSouth() {
        sut.execute(
            commands = generateCommands(Rover.Command.LEFT, Rover.Command.LEFT)
        )

        assertEquals(CardinalPoints.SOUTH, sut.direction)
    }

    @Test
    fun turnLeftThreeTimesChangesDirectionToEast() {
        sut.execute(
            commands = generateCommands(
                Rover.Command.LEFT,
                Rover.Command.LEFT,
                Rover.Command.LEFT
            )
        )

        assertEquals(CardinalPoints.EAST, sut.direction)
    }

    @Test
    fun turnLeftFourTimesChangesDirectionToNorth() {
        sut.execute(
            commands = generateCommands(
                Rover.Command.LEFT,
                Rover.Command.LEFT,
                Rover.Command.LEFT,
                Rover.Command.LEFT
            )
        )

        assertEquals(
            CardinalPoints.NORTH, sut.direction
        )
    }
    //</editor-fold>

    //<editor-fold desc="Move Forward">
    @Test
    fun moveForwardWithNorthDirectionIncreaseY() {
        sut.execute(commands = generateCommands(Rover.Command.FORWARD))

        assertEquals(Position(x = X_MAIN, y = 6), sut.position)
    }

    @Test
    fun moveForwardTwiceWithNorthDirectionIncreaseYTwice() {
        sut.execute(commands = generateCommands(Rover.Command.FORWARD, Rover.Command.FORWARD))

        assertEquals(Position(x = X_MAIN, y = 7), sut.position)
    }

    @Test
    fun moveForwardWithEastDirectionIncreaseX() {
        sut.direction = CardinalPoints.EAST
        sut.execute(commands = generateCommands(Rover.Command.FORWARD))

        assertEquals(Position(x = 6, y = Y_MAIN), sut.position)
    }

    @Test
    fun moveForwardTwiceWithEastDirectionIncreaseXTwice() {
        sut.direction = CardinalPoints.EAST
        sut.execute(commands = generateCommands(Rover.Command.FORWARD, Rover.Command.FORWARD))

        assertEquals(Position(x = 7, y = Y_MAIN), sut.position)
    }

    @Test
    fun moveForwardWithSouthDirectionDecreaseY() {
        sut.direction = CardinalPoints.SOUTH
        sut.execute(commands = generateCommands(Rover.Command.FORWARD))

        assertEquals(Position(x = X_MAIN, y = 4), sut.position)
    }

    @Test
    fun moveForwardTwiceWithSouthDirectionDecreaseYTwice() {
        sut.direction = CardinalPoints.SOUTH
        sut.execute(commands = generateCommands(Rover.Command.FORWARD, Rover.Command.FORWARD))

        assertEquals(Position(x = X_MAIN, y = 3), sut.position)
    }

    @Test
    fun moveForwardWithWestDirectionDecreaseX() {
        sut.direction = CardinalPoints.WEST
        sut.execute(commands = generateCommands(Rover.Command.FORWARD))

        assertEquals(Position(x = 4, y = Y_MAIN), sut.position)
    }

    @Test
    fun moveForwardTwiceWithWestDirectionDecreaseXTwice() {
        sut.direction = CardinalPoints.WEST
        sut.execute(commands = generateCommands(Rover.Command.FORWARD, Rover.Command.FORWARD))

        assertEquals(Position(x = 3, y = Y_MAIN), sut.position)
    }

    @Test
    fun moveForwardInNorthLimitOfTheMapAndGoBackToFirstPosition() {
        sut.position.y = MAP_LIMIT

        sut.execute(commands = generateCommands(Rover.Command.FORWARD))

        assertEquals(Position(x = X_MAIN, y = MAP_FIRST), sut.position)
    }

    @Test
    fun moveForwardInSouthLimitOfTheMapAndGoBackToLastPosition() {
        sut.position.y = MAP_FIRST
        sut.direction = CardinalPoints.SOUTH

        sut.execute(commands = generateCommands(Rover.Command.FORWARD))

        assertEquals(Position(x = X_MAIN, y = MAP_LIMIT), sut.position)
    }

    @Test
    fun moveForwardInEastLimitOfTheMapAndGoBackToFirstPosition() {
        sut.position.x = MAP_LIMIT
        sut.direction = CardinalPoints.EAST

        sut.execute(commands = generateCommands(Rover.Command.FORWARD))

        assertEquals(Position(x = MAP_FIRST, y = Y_MAIN), sut.position)
    }

    @Test
    fun moveForwardInWestLimitOfTheMapAndGoBackToFirstPosition() {
        sut.position.x = MAP_FIRST
        sut.direction = CardinalPoints.WEST

        sut.execute(commands = generateCommands(Rover.Command.FORWARD))

        assertEquals(Position(x = MAP_LIMIT, y = Y_MAIN), sut.position)
    }
    //</editor-fold>

    //<editor-fold desc="Obstacles">
    @Test
    fun moveForwardToNorthToAnObstacleAbortMovement() {
        val obstacles = listOf(Position(x = 5, y = 6))
        sut.obstacles = obstacles

        try {
            sut.execute(commands = generateCommands(Rover.Command.FORWARD))
        } catch (exception: Exception) {

        }

        assertEquals(Position(x = X_MAIN, y = Y_MAIN), sut.position)
    }

    @Test
    fun moveForwardToEastToAnObstacleAbortMovement() {
        val obstacles = listOf(Position(x = 6, y = 5))
        sut.obstacles = obstacles
        sut.direction = CardinalPoints.EAST

        try {
            sut.execute(commands = generateCommands(Rover.Command.FORWARD))
        } catch (exception: Exception) {

        }

        assertEquals(Position(x = X_MAIN, y = Y_MAIN), sut.position)
    }

    @Test
    fun moveForwardToSouthToAnObstacleAbortMovement() {
        val obstacles = listOf(Position(x = 5, y = 4))
        sut.obstacles = obstacles
        sut.direction = CardinalPoints.SOUTH

        try {
            sut.execute(commands = generateCommands(Rover.Command.FORWARD))
        } catch (exception: Exception) {

        }

        assertEquals(Position(x = X_MAIN, y = Y_MAIN), sut.position)
    }

    @Test
    fun moveForwardToWestToAnObstacleAbortMovement() {
        val obstacles = listOf(Position(x = 4, y = 5))
        sut.obstacles = obstacles
        sut.direction = CardinalPoints.WEST

        try {
            sut.execute(commands = generateCommands(Rover.Command.FORWARD))
        } catch (exception: Exception) {

        }

        assertEquals(Position(x = X_MAIN, y = Y_MAIN), sut.position)
    }

    @Test(expected = ObstacleInPathException::class)
    fun throwsExceptionWhenFindAnObstacleWhenMovesForward() {
        val obstacles = listOf(Position(x = 5, y = 6))
        sut.obstacles = obstacles

        sut.execute(commands = generateCommands(Rover.Command.FORWARD))
    }

    @Test
    fun catchExceptionWhenFindAnObstacleWhenMovesForwardAndVerifyPosition() {
        val obstacles = listOf(Position(x = 5, y = 6))
        sut.obstacles = obstacles

        try {
            sut.execute(commands = generateCommands(Rover.Command.FORWARD))
        } catch (exception: ObstacleInPathException) {
            assertTrue(obstacles.contains(exception.positionWithObstacle))
        }
    }

    @Test
    fun abortOperationsWhenFindAnObstacleWhenMovesForward() {
        val obstacles = listOf(Position(x = 5, y = 8))
        sut.obstacles = obstacles

        try {
            sut.execute(
                commands = generateCommands(
                    Rover.Command.FORWARD,
                    Rover.Command.FORWARD,
                    Rover.Command.FORWARD
                )
            )
        } catch (exception: ObstacleInPathException) {
            assertEquals(Position(x = X_MAIN, y = 7), sut.position)
        }
    }
    //</editor-fold>

    private fun generateCommands(vararg commands: Rover.Command): String {
        var generatedCommands = ""
        commands.forEach {
            generatedCommands += it.command
        }
        return generatedCommands
    }
}