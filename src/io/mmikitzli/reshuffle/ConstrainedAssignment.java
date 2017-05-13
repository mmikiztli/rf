package io.mmikitzli.reshuffle;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ConstrainedAssignment<T> {

    private final int minTeamSize;
    private final int maxTeamSize;
    private final Assignment<T> assignment;

    public ConstrainedAssignment(int minTeamSize, int maxTeamSize, Assignment<T> assignment) {
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
        this.assignment = assignment;
    }

    private Optional<Integer> findGroup(T member, Assignment<T> newAssignment) {
        Set<T> previousGroup = assignment.getGroup(member);

        for (int i = 0; i < newAssignment.getNumGroups(); i++) {
            Set<T> group = newAssignment.getGroups().get(i);
            if ((group.size() < maxTeamSize) && previousGroup.stream().noneMatch(group::contains)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    public Assignment<T> reshuffle() {
        Assignment<T> reshuffled = new Assignment<>(assignment.getNumGroups());

        List<T> membersToAssign = assignment.getMemberList();

        while (membersToAssign.size() > 0) {

            T member = membersToAssign.get(membersToAssign.size() - 1);

            Optional<Integer> group = findGroup(member, reshuffled);

            if (group.isPresent()) {
                reshuffled.add(group.get(), member);
                membersToAssign.remove(member);
            } else {

            }

        }

        return reshuffled;
    }
}
