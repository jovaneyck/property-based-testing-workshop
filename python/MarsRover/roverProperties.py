from hypothesis import given, strategies as st
from rover import Rover, Location, Direction, Command
from unittest import TestCase
from copy import deepcopy


class TestRover(TestCase):

    # generate random rovers and print them
    @given(st.builds(Rover, st.builds(Location, st.integers(), st.integers()), st.sampled_from(Direction)))
    def test_print_rover(self, rover: Rover):
        print(rover)

    @given(st.lists(st.sampled_from(Command), min_size=1))
    def test_print_command(self, command: Command):
        print(command)

    @given(st.builds(Rover, st.builds(Location, st.integers(), st.integers()), st.sampled_from(Direction)),
           st.sampled_from(Command))
    def test_any_command_changes_the_rover(self, rover: Rover, command: Command):
        og_rover: Rover = deepcopy(rover)
        self.assertNotEqual(rover.execute_singular(command), og_rover)
