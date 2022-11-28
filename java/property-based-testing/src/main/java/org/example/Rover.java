package org.example;

import java.util.Objects;

public class Rover {
    private Location location;
    private Direction direction;

    private Rover(Builder builder) {
        this.location = builder.location;
        this.direction = builder.direction;
    }

    public static Builder roverBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Location location = new Location(0, 0);
        private Direction direction = Direction.NORTH;

        public Builder withStartingLocation(Location location) {
            this.location = location;
            return this;
        }

        public Builder withStartingDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        public Rover build() {
            return new Rover(this);
        }
    }

    public Location getLocation() {
        return location;
    }

    public Direction getDirection() {
        return direction;
    }


    public Rover moveForward() {
        switch (direction) {
            case NORTH -> this.location = location.stepToNorth();
            case EAST -> this.location = location.stepToEast();
            case SOUTH -> this.location = location.stepToSouth();
            case WEST -> this.location = location.stepToWest();
        }
        return this;
    }

    public Rover moveBackward() {
        switch (direction) {
            case NORTH -> this.location = location.stepToSouth();
            case EAST -> this.location = location.stepToWest();
            case SOUTH -> this.location = location.stepToNorth();
            case WEST -> this.location = location.stepToEast();
        }
        return this;
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