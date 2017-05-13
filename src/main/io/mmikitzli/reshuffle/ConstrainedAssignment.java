package io.mmikitzli.reshuffle;

import java.util.*;

public class ConstrainedAssignment<T> extends Assignment<T> {

    private final int minTeamSize;
    private final int maxTeamSize;

    public ConstrainedAssignment(int minTeamSize, int maxTeamSize, List<Set<T>> assignment) {
        super(assignment);
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
    }

    public ConstrainedAssignment(int minTeamSize, int maxTeamSize, int numGroups) {
        super(numGroups);
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
    }

    private Optional<Integer> findGroup(T member, Assignment<T> newAssignment, Set<Integer> exclude) {
        Set<T> previousGroup = this.groupOf(member);

        for (int i = 0; i < newAssignment.getNumGroups(); i++) {
            Set<T> group = newAssignment.getGroup(i);
            if ((group.size() < maxTeamSize) &&
                    !exclude.contains(i) &&
                    previousGroup.stream().noneMatch(group::contains)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    public boolean isValid() {
        return getGroups().stream()
                .allMatch(group -> (group.size() >= minTeamSize && group.size() <= maxTeamSize));
    }

    public Optional<ConstrainedAssignment<T>> reshuffle() {
        ConstrainedAssignment<T> reshuffled = new ConstrainedAssignment<>(minTeamSize, maxTeamSize, getNumGroups());

        Map<T, Set<Integer>> checked = new HashMap<>();

        int currentMember = 0;
        List<T> membersToAssign = getMemberList();
        membersToAssign.forEach(member -> checked.put(member, new HashSet<>()));

        while (currentMember < membersToAssign.size()) {

            T member = membersToAssign.get(currentMember);
            reshuffled.remove(member);
            Set<Integer> exclude = checked.get(member);

            Optional<Integer> group = findGroup(member, reshuffled, exclude);

            if (group.isPresent()) {
                reshuffled.add(group.get(), member);
                checked.get(member).add(group.get());
                currentMember++;
            } else {
                checked.get(member).clear();
                currentMember--;
            }

            if (currentMember < 0) {
                return Optional.empty();
            }

            if ((currentMember >= membersToAssign.size()) && (!isValid())) {
                currentMember--;
            }
        }

        return Optional.of(reshuffled);
    }
}
