package io.mmikitzli.reshuffle;

import java.util.*;

public class Assignment<T> {

    private final List<Set<T>> assignment;
    private final Map<T, Integer> memberToGroup;
    private final List<T> members;

    public Assignment(int numGroups) {
        members = new ArrayList<>();
        memberToGroup = new HashMap<>();
        assignment = new ArrayList<>(numGroups);
        for (int i = 0; i < numGroups; i++) {
            assignment.add(new HashSet<>());
        }
    }

    public Assignment(List<Set<T>> assignment) {
        this.members = new ArrayList<>();
        this.assignment = assignment;
        this.memberToGroup = new HashMap<>();
        for (int i = 0; i < assignment.size(); i++) {
            for (T member : assignment.get(i)) {
                memberToGroup.put(member, i);
                members.add(member);
            }
        }
    }

    public int getNumGroups() {
        return assignment.size();
    }

    public void add(int group, T member) {
        assignment.get(group).add(member);
        memberToGroup.put(member, group);
        members.add(member);
    }

    public Set<T> getGroup(T member) {
        return assignment.get(memberToGroup.get(member));
    }

    public List<T> getMemberList() {
        return members;
    }

    public List<Set<T>> getGroups() {
        return assignment;
    }

}
