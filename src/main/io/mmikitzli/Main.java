package io.mmikitzli;

import io.mmikitzli.reshuffle.ConstrainedAssignment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        List<Set<String>> teams = Arrays.asList(
                new HashSet<>(Arrays.asList("1A,1B,1C,1D".split(","))),
                new HashSet<>(Arrays.asList("2A,2B,2C,2D".split(","))),
                new HashSet<>(Arrays.asList("3A,3B,3C,3D".split(","))),
                new HashSet<>(Arrays.asList("4A,4B,4C,4D".split(","))),
                new HashSet<>(Arrays.asList("5A,5B,5C,5D".split(","))),
                new HashSet<>(Arrays.asList("6A,6B,6C,6D".split(","))),
                new HashSet<>(Arrays.asList("7A,7B,7C,7D".split(",")))
        );

        ConstrainedAssignment<String> assignment = new ConstrainedAssignment<>(
                3,4, teams
        );

        System.out.println(assignment.reshuffle().get().toString());
    }

}
