from rover import Rover, Command, Location, Direction
from unittest import TestCase


class TestRover(TestCase):

    def test_basic_commands(self):
        self.assertEqual(Rover.initial().execute([Command.F]).location, Location(1, 0))
        self.assertEqual(Rover.initial().execute([Command.B]).location, Location(-1, 0))
        self.assertEqual(Rover.initial().execute([Command.L]).direction, Direction.W)
        self.assertEqual(Rover.initial().execute([Command.R]).direction, Direction.E)

    def test_fullExample(self):
        self.assertEqual(Rover.initial().execute([Command.F,
                                                  Command.L,
                                                  Command.F,
                                                  Command.L,
                                                  Command.B,
                                                  Command.R,
                                                  Command.B]).location, Location(2, 0))
