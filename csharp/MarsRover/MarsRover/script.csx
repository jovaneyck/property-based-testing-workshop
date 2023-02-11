#r "C:\Users\Jo.VanEyck\Desktop\code\property-based-testing-workshop\csharp\MarsRover\MarsRover\bin\Debug\net6.0\FsCheck.dll"
using FsCheck;
using System.Linq;

enum State
{
	Draft, Proposed, Confirmed, Archived
}
//Generators
Gen.Constant(1).Sample(100, 3)
Arb.From<State>().Generator.Sample(3, 3)
Gen.ListOf(Arb.From<State>().Generator).Sample(3,3)
Arb.Generate<int>().Sample(3,3)
var nonFinalStates = Gen.OneOf(Gen.Constant(State.Draft), Gen.Constant(State.Proposed));

//Properties
var p = (State i) => i == State.Confirmed;
var prop = Prop.ForAll(nonFinalStates.ToArbitrary(), p);

//Replaying a specific counter-example
var replay = new Configuration { Replay = FsCheck.Random.StdGen.NewStdGen(757431419, 297145223) };
prop.Check(replay)
