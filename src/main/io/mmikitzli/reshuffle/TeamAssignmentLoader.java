package io.mmikitzli.reshuffle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TeamAssignmentLoader {

    public static ConstrainedAssignment<String> readFromFile(String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File(fileName));
        List<Set<String>> teams = new ArrayList<>();
        Set<String> team = new HashSet<>();
        teams.add(team);
        while (s.hasNext()) {
            String nextLine = s.next();
            if (nextLine.contentEquals("#")) {
                team = new HashSet<>();
                teams.add(team);
            } else {
                team.add(nextLine);
            }
        }
        s.close();
        return new ConstrainedAssignment<>(3,4, teams);
    }

}
