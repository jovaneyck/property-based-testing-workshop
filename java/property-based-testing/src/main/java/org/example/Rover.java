package org.example;

public class Rover {
    private Location location;
    private Direction direction;

    public Rover() {
        this.location = new Location(0, 0);
        this.direction = Direction.NORTH;
    }

    public Location getLocation() {
        return location;
    }

    public Direction getDirection() {
        return direction;
    }
}