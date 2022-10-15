using FluentAssertions;
using Xunit;

namespace MarsRover;

public class RoverTests
{
    [Fact]
    public void BasicCommands()
    {
        Rover.Initial
            .Execute(Command.F).Should()
            .Be(Rover.Initial with { Location = Location.Origin with { X = 1 } });
        Rover.Initial
            .Execute(Command.B).Should()
            .Be(Rover.Initial with { Location = Location.Origin with { X = -1 } });
        Rover.Initial
            .Execute(Command.L).Should()
            .Be(Rover.Initial with { Direction = Direction.W });
        Rover.Initial
            .Execute(Command.R).Should()
            .Be(Rover.Initial with { Direction = Direction.E });
    }

    [Fact]
    public void FullExample()
    {
        var rover = Rover.Initial;
        var moved =
            rover.Execute(new[]
            {
                Command.F,
                Command.L,
                Command.F,
                Command.L,
                Command.B,
                Command.R,
                Command.B,
            });
        moved.Should().Be(new Rover(Direction.W, new Location(2, 0)));
    }
}