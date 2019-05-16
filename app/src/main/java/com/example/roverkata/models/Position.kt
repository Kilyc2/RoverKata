package com.example.roverkata.models

data class Position(var x: Int, var y: Int) {

    companion object {
        const val MAP_LIMIT = 100
        const val MAP_FIRST = 0
    }

    fun incrementY() {
        y++
        if (y > MAP_LIMIT) {
            y = MAP_FIRST
        }
    }

    fun decrementY() {
        y--
        if (y < MAP_FIRST) {
            y = MAP_LIMIT
        }
    }

    fun incrementX() {
        x++
        if (x > MAP_LIMIT) {
            x = MAP_FIRST
        }
    }

    fun decrementX() {
        x--
        if (x < MAP_FIRST) {
            x = MAP_LIMIT
        }
    }
}