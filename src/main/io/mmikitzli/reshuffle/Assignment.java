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
        this.assignment = copy(assignment);
        this.memberToGroup = new HashMap<>();
        for (int i = 0; i < assignment.size(); i++) {
            for (T member : assignment.get(i)) {
                memberToGroup.put(member, i);
                members.add(member);
            }
        }
    }

    private List<Set<T>> copy(List<Set<T>> assignment) {
        List<Set<T>> result = new ArrayList<>(assignment.size());
        for (Set<T> group : assignment) {
            result.add(new HashSet<>(group));
        }
        return result;
    }

    public int getNumGroups() {
        return assignment.size();
    }

    public void add(int group, T member) {
        assignment.get(group).add(member);
        memberToGroup.put(member, group);
        members.add(member);
    }

    public void remove(T member) {
        if (memberToGroup.containsKey(member)) {
            int group = memberToGroup.get(member);
            assignment.get(group).remove(member);
            members.remove(member);
            memberToGroup.remove(member);
        }
    }

    public Set<T> groupOf(T member) {
        return new HashSet<>(assignment.get(memberToGroup.get(member)));
    }

    public Set<T> getGroup(int i) {
        return new HashSet<>(assignment.get(i));
    }

    public List<T> getMemberList() {
        return new ArrayList<>(members);
    }

    public List<Set<T>> getGroups() {
        return copy(assignment);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < assignment.size(); i++) {
            Set<T> group = assignment.get(i);
            output.append(String.format("%d: %s\n", i, group.toString()));
        }
        return output.toString();
    }
}
