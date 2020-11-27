package com.example.sportstats.domain;

import com.example.sportstats.record.SportRecord;

/**
 * Domain model representing a sport.
 *
 * @author Claes Andersson
 */
public class Sport {

    private SportRecord theRecord;

    public Sport() {

        this(new SportRecord());
    }

    /**
     * Constructor for inserting a record manually.
     *
     * @param record a sportrecord.
     */
    public Sport(SportRecord record) {

        theRecord = record;
    }

    /**
     * Get the id of this sport.
     *
     * @return id of this sport.
     */
    public Integer getId() {

        return theRecord.getInteger("id");
    }

    /**
     * Set the name of this sport.
     *
     * @param name the name of this sport.
     */
    public void setName(String name) {

        theRecord.setString("name", name);
    }

    /**
     * Get the name of this sport.
     *
     * @return name of this sport.
     */
    public String getName() {

        return theRecord.getString("name");
    }

    /**
     * Get the points for a win of this sport.
     *
     * @return points for a win of this sport.
     */
    public Integer getPointsForWin() {

        return theRecord.getInteger("win");
    }

    /**
     * Set the points for a win of this sport.
     *
     * @param points amount of points for a win of this sport.
     */
    public void setPointsForWin(Integer points) {

        theRecord.setInteger("win", points);
    }

    /**
     * Get the points for an overtime win of this sport.
     *
     * @return points for an overtime win of this sport.
     */
    public Integer getPointsForOvertimeWin() {

        return theRecord.getInteger("winot");
    }

    /**
     * Set the points for an overtime win of this sport.
     *
     * @param points amount of points for an overtime win of this sport.
     */
    public void setPointsForOvertimeWin(Integer points) {

        theRecord.setInteger("winot", points);
    }

    /**
     * Get the points for a draw of this sport.
     *
     * @return points for a draw of this sport.
     */
    public Integer getPointsForDraw() {

        return theRecord.getInteger("draw");
    }

    /**
     * Set the points for a draw of this sport.
     *
     * @param points amount of points for a draw of this sport.
     */
    public void setPointsForDraw(Integer points) {

        theRecord.setInteger("draw", points);
    }

    /**
     * Get the points for a loss of this sport.
     *
     * @return points for a loss of this sport.
     */
    public Integer getPointsForLoss() {

        return theRecord.getInteger("loss");
    }

    /**
     * Set the points for a loss of this sport.
     *
     * @param points amount of points for a loss of this sport.
     */
    public void setPointsForLoss(Integer points) {

        theRecord.setInteger("loss", points);
    }

    /**
     * Get the points for an overtime loss of this sport.
     *
     * @return points for an overtime loss of this sport.
     */
    public Integer getPointsForOvertimeLoss() {

        return theRecord.getInteger("lossot");
    }

    /**
     * Get the points for an overtime loss of this sport.
     *
     * @param points amount of points for an overtime loss of this sport.
     */
    public void setPointsForOvertimeLoss(Integer points) {

        theRecord.setInteger("lossot", points);
    }

    /**
     * Saves this sport to the database.
     */
    public void save() {

        theRecord.saveIt();
    }

    /**
     * Deletes this sport from the database.
     */
    public void delete() {

        theRecord.delete();
    }
}
