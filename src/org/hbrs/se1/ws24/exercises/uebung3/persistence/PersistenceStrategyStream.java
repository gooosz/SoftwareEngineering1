package org.hbrs.se1.ws24.exercises.uebung3.persistence;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     * Look-up in Google for further help! Good source:
     * https://www.digitalocean.com/community/tutorials/objectoutputstream-java-write-object-file
     * (Last Access: Oct, 15th 2024)
     */
    public void save(List<E> member) throws PersistenceException  {
        try (FileOutputStream outputStream = new FileOutputStream(location);
             ObjectOutputStream objectOut = new ObjectOutputStream(outputStream);) {
            objectOut.writeObject(member);
	} catch (IOException e) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Couldn't write Objects to file " + location);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException  {
        // Some Coding hints ;-)
        List<E> readObjects = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(location);
             ObjectInputStream objectIn = new ObjectInputStream(inputStream);) {
            Object obj = objectIn.readObject();
            if (obj instanceof List<?>) {
                readObjects = (List<E>) obj;
            } else {
                throw new PersistenceException(
                        PersistenceException.ExceptionType.DataCorrupted,
                        "Couldn't read Objects as a specific Type from file " + location);
            }
	} catch (IOException | ClassNotFoundException e) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Couldn't read Objects from file + " + location);
	}
        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );

        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams
        return readObjects;
    }
}
