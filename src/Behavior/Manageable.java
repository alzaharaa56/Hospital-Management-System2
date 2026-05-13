package Behavior;

import java.util.List;

public interface Manageable {

    // Add a new entity (Doctor, Patient, Nurse, etc.)

    void add(Object entity);

    // Remove an entity using its ID

    void remove(String id);

    // Get all stored entities

    List<Object> getAll();

}
