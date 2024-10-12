package org.hbrs.se1.ws24.exercises.uebung2;

public class ConcreteMember implements Member{
    private final int ID;
    public ConcreteMember(int ID) {
        this.ID = ID;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Member (ID = " + getID() + ")";
    }
}
