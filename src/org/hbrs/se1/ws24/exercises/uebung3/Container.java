package org.hbrs.se1.ws24.exercises.uebung3;

import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Container implements Serializable {
    private List<Member> memberList;
    private PersistenceStrategy<Member> persistance;
    private static Container container; // Singleton

    private Container() {
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
     * @return list of current members
     */
    public List<Member> getCurrentList() {
        return memberList;
    }

    /**
     * @return amount of members in list
     */
    public int size() {
        return memberList.size();
    }

    /**
     * set the strategy to store/load Member objects
     * @param strategy the strategy for persistant storage to use
     */
    public void setPersistanceStrategy(PersistenceStrategy<Member> strategy) {
        persistance = strategy;
    }

    /**
     * Stores the Member objects of container persistant, uses a PersistanceStrategy for that
     * @throws PersistenceException
    */
    public void store() throws PersistenceException {
        if (persistance == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy set");
        }
        try {
            persistance.save(memberList);
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,
                    "Strategy not implemented yet");
        }
    }

    /**
     * loads Member objects using a PersistanceStrategy,
     * current Members in Container will get overwritten
     * @throws PersistenceException
    */
    public void load() throws PersistenceException {
        if (persistance == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy set");
        }
        try {
            memberList = persistance.load();
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,
                    "Strategy not implemented yet");
        }
    }

    /*@Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(memberList);
        out.writeObject(persistance);
    }
    @Serial
    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        memberList = (List<Member>) in.readObject();
        persistance = (PersistenceStrategy<Member>) in.readObject();
    }*/

    /**
     * @return instance of Singleton Container
     */
    public static Container getInstance() {
        if (container == null) {
            container = new Container();
        }
        return container;
    }

    /* Only use for JUnit Tests instead of Singleton because else every test breaks */
    public static Container createNotSingleton() {
        return new Container();
    }
}

