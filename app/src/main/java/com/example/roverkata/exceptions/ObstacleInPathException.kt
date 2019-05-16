package com.example.roverkata.exceptions

import com.example.roverkata.models.Position

class ObstacleInPathException(val positionWithObstacle: Position) : Exception()