package com.example.sportstats.service;

import com.example.sportstats.domain.Sport;
import java.util.HashMap;
import java.util.Map;

/**
 * Service that deletes sports.
 *
 * @author Claes Andersson
 */
public class DeleteSportService extends BaseService<Map> {

    private final Integer id;

    public DeleteSportService(Integer id) {

        this.id = id;
    }

    @Override
    public Map execute() {

        Sport sport = getBrokerFactory().getSportBroker().findById(id);
        if (sport == null) {
            throw new SportstatsServiceException("There are no sport with id: " + id);
        }
        Map deletedSport = new HashMap();
        deletedSport.put("id", sport.getId());
        deletedSport.put("name", sport.getName());
        deletedSport.put("pointsForWin", sport.getPointsForWin());
        deletedSport.put("pointsForOvertimeWin", sport.getPointsForOvertimeWin());
        deletedSport.put("pointsForDraw", sport.getPointsForDraw());
        deletedSport.put("pointsForLoss", sport.getPointsForLoss());
        deletedSport.put("pointsForOvertimeLoss", sport.getPointsForOvertimeLoss());
        sport.delete();

        return deletedSport;
    }
}
