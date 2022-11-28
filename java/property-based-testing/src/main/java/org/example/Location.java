package org.example;

public class Location {
    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public Location stepToNorth() {
        return new Location(x + 1, y);
    }

    public Location stepToSouth() {
        return new Location(x - 1, y);
    }

    public Location stepToEast() {
        return new Location(x, y + 1);
    }

    public Location stepToWest() {
        return new Location(x, y - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return (x == location.getX()) &&
                (y == location.getY());
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
