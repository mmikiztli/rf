package io.mmikitzli;

import io.mmikitzli.reshuffle.ConstrainedAssignment;
import io.mmikitzli.reshuffle.TeamAssignmentLoader;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        ConstrainedAssignment<String> oldTeams = TeamAssignmentLoader.readFromFile("resources/teams_sample.txt");

        System.out.println(oldTeams.reshuffle().get().toString());
    }

}
