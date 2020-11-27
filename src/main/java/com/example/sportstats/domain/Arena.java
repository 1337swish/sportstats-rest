package com.example.sportstats.domain;

import com.example.sportstats.record.ArenaRecord;

/**
 * Domain model representing an arena.
 *
 * @author Claes Andersson
 */
public class Arena {

    private ArenaRecord theRecord;

    public Arena() {

        this(new ArenaRecord());
    }

    /**
     * Constructor for inserting a record manually.
     *
     * @param record an arenarecord.
     */
    public Arena(ArenaRecord record) {

        theRecord = record;
    }

    /**
     * Get the id of this arena.
     *
     * @return id of this arena.
     */
    public Integer getId() {

        return theRecord.getInteger("id");
    }

    /**
     * Set the name of this arena.
     *
     * @param name the name of this arena.
     */
    public void setName(String name) {

        theRecord.setString("name", name);
    }

    /**
     * Get the name of this arena.
     *
     * @return name of this arena.
     */
    public String getName() {

        return theRecord.getString("name");
    }

    /**
     * Set the full address of this arena.
     *
     * @param address the address of this arena.
     */
    public void setAddress(String address) {

        theRecord.setString("address", address);
    }

    /**
     * Get the full address of this arena.
     *
     * @return address of this arena.
     */
    public String getAddress() {

        return theRecord.getString("address");
    }

    /**
     * Set the capacity of this arena.
     *
     * @param capacity the maximum amount of spectators this arena can allow.
     */
    public void setCapacity(Integer capacity) {

        theRecord.setInteger("capacity", capacity);
    }

    /**
     * Get the capacity of this arena.
     *
     * @return the maximum amount of spectators this arena allows.
     */
    public Integer getCapacity() {

        return theRecord.getInteger("capacity");
    }
    
    /**
     * Saves this arena to the database.
     */
    public void save() {

        theRecord.saveIt();
    }

    /**
     * Deletes this arena from the database.
     */
    public void delete() {

        theRecord.delete();
    }
}
