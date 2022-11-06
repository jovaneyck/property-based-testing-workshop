import { describe, expect, it, xit, fit } from "@jest/globals"
import { contains } from "./examples";

import { Command, Direction, executeCommand, executeCommands, Rover, initial, equal } from "./rover"
import * as fc from "fast-check";
import { Arbitrary } from "fast-check";

describe("Rover", () => {
  it("can process basic F command", () => {
    let r = executeCommand(initial, Command.F)
    expect(r.direction).toBe(initial.direction)
    expect(r.location).toStrictEqual([1, 0])
  })

  it("can process basic B command", () => {
    let r = executeCommand(initial, Command.B)
    expect(r.direction).toBe(initial.direction)
    expect(r.location).toStrictEqual([-1, 0])
  })

  it("can process basic L command", () => {
    let r = executeCommand(initial, Command.L)
    expect(r.direction).toBe(Direction.W)
    expect(r.location).toStrictEqual(initial.location)
  })

  it("can process basic R command", () => {
    let r = executeCommand(initial, Command.R)
    expect(r.direction).toBe(Direction.E)
    expect(r.location).toStrictEqual(initial.location)
  })

  it("can process a full scenario", () => {
    let r = executeCommands(initial, [
      Command.F,
      Command.L,
      Command.F,
      Command.L,
      Command.B,
      Command.R,
      Command.B
    ])
    expect(r.direction).toBe(Direction.W)
    expect(r.location).toStrictEqual([2, 0])
  })
})


describe("Example properties for string.contains", () => {
  it("a string should always contain itself", () => {
    fc.assert(fc.property(fc.string(), (text) => contains(text, text)));
  });
  it("a string should always contain its substrings", () => {
    fc.assert(
      fc.property(fc.string(), fc.string(), fc.string(), (a, b, c) =>
        contains(a + b + c, b)
      )
    );
  });
});

describe("Rover properties", () => {
  let commandArb = fc.constantFrom(Command.L, Command.R, Command.F, Command.B);
  let directionArb = fc.constantFrom(
    Direction.N,
    Direction.E,
    Direction.S,
    Direction.W
  );
  let locationArb: Arbitrary<[number, number]> = fc.tuple(fc.nat(), fc.nat());
  let roverArb: Arbitrary<Rover> = fc.record({
    direction: directionArb,
    location: locationArb,
  });

  /*
   Example of a failing property:
   Rover › A command always changes the rover

    Property failed after 1 tests
    { seed: -279917664, path: "0:0:0:0", endOnFailure: true }
    Counterexample: [{"direction":0,"location":[0,0]},2]
    Shrunk 3 time(s)
    Got error: Property failed by returning false
  */
  it("Executing a single command always changes the rover", () =>
    fc.assert(
      fc.property(roverArb, commandArb, (r, c) => {
        const actual = executeCommand(r, c);
        return equal(actual, r); //TODO FIXME
      })
    ));
});