using FluentAssertions;
using FsCheck.Xunit;

namespace MarsRover;

public class RoverProperties
{
    //This is where you can describe new properties for the Rover
    [Property] //Note the property attribute
    public void example_property(Rover r, Command c) => //All input arguments you declare for your properties will be randomly generated
        r.Execute(c).Should().Be(r, "FIXME"); //you can use assertions in a property
    /*
    Example output of a failing property:

    FsCheck.Xunit.PropertyFailedException

    Falsifiable, after 1 test (0 shrinks) (StdGen (117936488,297098571)):
    Original:
    (Rover { Direction = E, Location = Location { X = 0, Y = 0 } }, F)
    */
}