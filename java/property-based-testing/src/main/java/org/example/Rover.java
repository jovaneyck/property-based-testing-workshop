package org.example;

import java.util.Objects;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rover rover = (Rover) o;

        return (Objects.equals(location, rover.getLocation())) &&
                (Objects.equals(direction, rover.getDirection()));
    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}