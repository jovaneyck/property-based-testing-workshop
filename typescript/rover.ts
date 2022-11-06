export enum Command {
  F,
  B,
  L,
  R,
}
export enum Direction {
  N,
  E,
  S,
  W,
}
export interface Rover {
  location: [number, number];
  direction: Direction;
}

export const initial: Rover = { location: [0, 0], direction: Direction.N };

function moveForward(r: Rover): Rover {
  let [x, y] = r.location
  switch (r.direction) {
    case Direction.N: return { direction: r.direction, location: [x + 1, y] }
    case Direction.E: return { direction: r.direction, location: [x, y + 1] }
    case Direction.S: return { direction: r.direction, location: [x - 1, y] }
    case Direction.W: return { direction: r.direction, location: [x, y - 1] }
  }
}

function moveBackward(r: Rover): Rover {
  let [x, y] = r.location
  switch (r.direction) {
    case Direction.N: return { direction: r.direction, location: [x - 1, y] }
    case Direction.E: return { direction: r.direction, location: [x, y - 1] }
    case Direction.S: return { direction: r.direction, location: [x + 1, y] }
    case Direction.W: return { direction: r.direction, location: [x, y + 1] }
  }
}

function turnLeft(r: Rover): Rover {
  switch (r.direction) {
    case Direction.N: return { direction: Direction.W, location: r.location }
    case Direction.E: return { direction: Direction.N, location: r.location }
    case Direction.S: return { direction: Direction.E, location: r.location }
    case Direction.W: return { direction: Direction.S, location: r.location }
  }
}

function turnRight(r: Rover): Rover {
  switch (r.direction) {
    case Direction.N: return { direction: Direction.E, location: r.location }
    case Direction.E: return { direction: Direction.S, location: r.location }
    case Direction.S: return { direction: Direction.W, location: r.location }
    case Direction.W: return { direction: Direction.N, location: r.location }
  }
}

export function executeCommand(r: Rover, c: Command): Rover {
  switch (c) {
    case Command.F: return moveForward(r)
    case Command.B: return moveBackward(r)
    case Command.L: return turnLeft(r)
    case Command.R: return turnRight(r)
  }
}

export function executeCommands(r: Rover, c: Command[]): Rover {
  return c.reduce(executeCommand, r);
}

export function equal(r1: Rover, r2: Rover) {
  return r1.direction === r2.direction && r1.location === r2.location;
}
