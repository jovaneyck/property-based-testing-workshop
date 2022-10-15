namespace MarsRover;

public enum Direction { N, E, S, W }

public record Location(int X, int Y)
{
    public static Location Origin => new(0, 0);
}

public enum Command { F, B, L, R }

public record Rover(Direction Direction, Location Location)
{
    public static Rover Initial => new(Direction.N, Location.Origin);

    public Rover Execute(Command[] commands) =>
        commands.Aggregate(
            this,
            (r, c) => r.Execute(c));

    public Rover Execute(Command c) =>
        c switch
        {
            Command.L => TurnLeft(),
            Command.R => TurnRight(),
            Command.F => MoveForward(),
            Command.B => MoveBackward(),
            _ => throw new ArgumentOutOfRangeException(nameof(c), c, null)
        };

    private Rover TurnLeft() =>
        Direction switch
        {
            Direction.N => this with { Direction = Direction.W },
            Direction.E => this with { Direction = Direction.N },
            Direction.S => this with { Direction = Direction.E },
            Direction.W => this with { Direction = Direction.S },
            _ => throw new ArgumentOutOfRangeException()
        };

    private Rover TurnRight() =>
        Direction switch
        {
            Direction.N => this with { Direction = Direction.E },
            Direction.E => this with { Direction = Direction.S },
            Direction.S => this with { Direction = Direction.W },
            Direction.W => this with { Direction = Direction.N },
            _ => throw new ArgumentOutOfRangeException()
        };

    private Rover MoveForward()
    {
        var (x, y) = this.Location;
        return Direction switch
        {
            Direction.N => this with { Location = new Location(x + 1, y) },
            Direction.E => this with { Location = new Location(x, y + 1) },
            Direction.S => this with { Location = new Location(x - 1, y) },
            Direction.W => this with { Location = new Location(x, y - 1) },
            _ => throw new ArgumentOutOfRangeException()
        };
    }

    private Rover MoveBackward()
    {
        var (x, y) = this.Location;
        return Direction switch
        {
            Direction.N => this with { Location = new Location(x - 1, y) },
            Direction.E => this with { Location = new Location(x, y - 1) },
            Direction.S => this with { Location = new Location(x + 1, y) },
            Direction.W => this with { Location = new Location(x, y + 1) },
            _ => throw new ArgumentOutOfRangeException()
        };
    }
}