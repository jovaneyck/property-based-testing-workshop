#r "nuget: fscheck"
#r "nuget: Unquote"

open System
open FsCheck
open Swensen.Unquote

printfn "Hello World from F#!"

type Direction =
    | N
    | E
    | S
    | W

type Location = int * int

type Rover =
    { Location: Location
      Direction: Direction }

type Command =
    | F
    | B
    | L
    | R

let moveForward r =
    let x, y = r.Location

    match r.Direction with
    | N -> { r with Location = (x + 1, y) }
    | S -> { r with Location = (x - 1, y) }
    | E -> { r with Location = (x, y + 1) }
    | W -> { r with Location = (x, y - 1) }

let moveBackward r =
    let x, y = r.Location

    match r.Direction with
    | N -> { r with Location = (x - 1, y) }
    | S -> { r with Location = (x + 1, y) }
    | E -> { r with Location = (x, y - 1) }
    | W -> { r with Location = (x, y + 1) }

let rotateLeft (r: Rover) =
    match r.Direction with
    | N -> { r with Direction = W }
    | E -> { r with Direction = N }
    | S -> { r with Direction = E }
    | W -> { r with Direction = S }

let rotateRight (r: Rover) =
    match r.Direction with
    | N -> { r with Direction = E }
    | E -> { r with Direction = S }
    | S -> { r with Direction = W }
    | W -> { r with Direction = N }

let execute (r: Rover) (c: Command) =
    match c with
    | F -> moveForward r
    | B -> moveBackward r
    | L -> rotateLeft r
    | R -> rotateRight r

let run (r: Rover) (cs: Command list) = cs |> List.fold execute r

(*Example of a failing property:

A command always changes a rover-Falsifiable, after 1 test (0 shrinks) (StdGen (1772415496, 297107466)):
Original:
{ Location = (0, 0)
  Direction = W }
F
*)
Check.Quick("A command always changes a rover", (fun r c -> execute r c <> r)) //TODO FIXME
