package org.hbrs.se1.ws24.exercises.uebung2;

import java.util.ArrayList;

public class Container {
    private ArrayList<Member> memberList;
    public Container() {
        memberList = new ArrayList<>();
    }

    /**
     * @param member to add to list of members
     * @throws ContainerException if member is already in list of members
     */
    public void addMember(Member member) throws ContainerException {
        for (Member m : memberList) {
            if (m.getID().equals(member.getID())) {
                throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() +  " ist bereits vorhanden!");
            }
        }
        // member noch nicht in memberList
        memberList.add(member);
    }

    /**
     * Nachteile bei dieses Design: es ist nicht sofort ersichtlich dass Fehler auftreten können,
     * Control Flow geht weiter
     * @param id the ID of the member to delete from list of members
     * @return Error message if member is not in list, "success" on success
     */
    public String deleteMember(Integer id) {
        boolean removeSuccess = memberList.removeIf(m -> m.getID().equals(id));
        if (!removeSuccess) {
            return "Kein Member-Objekt mit der ID " + id + " ist vorhanden";
        }
        return "success";
    }

    /**
     * prints all Members to stdout
     */
    public void dump() {
        memberList.forEach(System.out::println);
    }

    /**
     * @return amount of members in list
     */
    public int size() {
        return memberList.size();
    }
}
