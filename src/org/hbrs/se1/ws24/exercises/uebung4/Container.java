package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// T must be a Member because of getID and Serializable
public class Container<T extends Member> implements Serializable {
    private List<T> memberList;
    private PersistenceStrategy<T> persistance;
    private static Container<?> container; // Singleton

    private Container() {
        memberList = new ArrayList<>();
    }

    /**
     * @param member to add to list of members
     * @throws ContainerException if member is already in list of members
     */
    public void addMember(T member) throws ContainerException {
        for (T m : memberList) {
            if (m.getID().equals(member.getID())) {
                throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() +  " ist bereits vorhanden!");
            }
        }
        // member noch nicht in memberList
        memberList.add(member);
    }

    /**
     * delete a member of given id from container
     * @param id the ID of the member to delete from list of members
     * @return false if member is not in list, true on success
     */
    public boolean deleteMember(Integer id) {
        boolean removeSuccess = memberList.removeIf(m -> m.getID().equals(id));
        if (!removeSuccess) {
            System.out.println("Kein Member-Objekt mit der ID " + id + " ist vorhanden");
            return false;
        }
        return true;
    }

    /**
     * @return list of current members
     */
    public List<T> getCurrentList() {
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
    public void setPersistanceStrategy(PersistenceStrategy<T> strategy) {
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
    public static Container<? extends Member> getInstance() {
        if (container == null) {
            container = new Container<>();
        }
        return container;
    }

    /* Only use for JUnit Tests instead of Singleton because else every test breaks */
    public static Container<? extends Member> createNotSingleton() {
        return new Container<>();
    }
}

