from __future__ import annotations

from enum import Enum


class Direction(Enum):
    N = "North"
    E = "East"
    S = "South"
    W = "West"


class Command(Enum):
    F = "Forward"
    B = "Backward"
    L = "Left"
    R = "Right"


class Location:
    x: int
    y: int

    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y

    def __eq__(self, other: Location) -> bool:
        return self.x == other.x and self.y == other.y

    def __str__(self):
        return "["+str(self.x)+", "+str(self.y)+"]"

    @staticmethod
    def origin():
        return Location(0, 0)


class Rover:
    direction: Direction
    location: Location

    def __init__(self, location: Location, direction: Direction):
        self.direction = direction
        self.location = location

    def __eq__(self, other: Rover) -> bool:
        return self.location == other.location and self.direction == other.direction

    def __str__(self):
        return "Rover at "+str(self.location) + " facing " + str(self.direction)

    @staticmethod
    def initial() -> Rover:
        return Rover(Direction.N, Location.origin())

    def execute(self, commands: list[Command]) -> Rover:
        for c in commands:
            self.execute_singular(c)
        return self

    def execute_singular(self, command: Command) -> Rover:
        match command:
            case Command.F: self.move_forward()
            case Command.B: self.move_backward()
            case Command.L: self.turn_left()
            case Command.R: self.turn_right()
        return self

    def move_forward(self) -> Rover:
        match self.direction:
            case Direction.N: self.location = Location(self.location.x+1, self.location.y)
            case Direction.E: self.location = Location(self.location.x, self.location.y+1)
            case Direction.S: self.location = Location(self.location.x-1, self.location.y)
            case Direction.W: self.location = Location(self.location.x, self.location.y-1)
        return self

    def move_backward(self) -> Rover:
        match self.direction:
            case Direction.N: self.location = Location(self.location.x-1, self.location.y)
            case Direction.E: self.location = Location(self.location.x, self.location.y-1)
            case Direction.S: self.location = Location(self.location.x+1, self.location.y)
            case Direction.W: self.location = Location(self.location.x, self.location.y+1)
        return self

    def turn_left(self) -> Rover:
        match self.direction:
            case Direction.N: self.direction = Direction.W
            case Direction.E: self.direction = Direction.N
            case Direction.S: self.direction = Direction.E
            case Direction.W: self.direction = Direction.S
        return self

    def turn_right(self) -> Rover:
        match self.direction:
            case Direction.N: self.direction = Direction.E
            case Direction.E: self.direction = Direction.S
            case Direction.S: self.direction = Direction.W
            case Direction.W: self.direction = Direction.N
        return self



