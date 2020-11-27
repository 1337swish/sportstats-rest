package com.example.sportstats.service;

import com.example.sportstats.domain.Sport;

/**
 * Service that adds a new sport.
 *
 * @author Claes Andersson
 */
public class AddSportService extends BaseService<Sport> {

    private final String sportName;
    private final Integer win;
    private final Integer winot;
    private final Integer draw;
    private final Integer loss;
    private final Integer lossot;

    public AddSportService(String sportName, Integer win, Integer winot, Integer draw, Integer loss, Integer lossot) {

        if (sportName.isEmpty() || sportName.isBlank()) {
            throw new SportstatsServiceException("Sport name can't be empty");
        }
        this.sportName = sportName;
        this.win = win;
        this.winot = winot;
        this.draw = draw;
        this.loss = loss;
        this.lossot = lossot;
    }

    @Override
    public Sport execute() {

        if (getBrokerFactory().getSportBroker().exists(sportName)) {
            throw new SportstatsServiceException("Sport already exists");
        }
        Sport sport = getBrokerFactory().getSportBroker().create();
        sport.setName(sportName);
        sport.setPointsForWin(win);
        sport.setPointsForOvertimeWin(winot);
        sport.setPointsForDraw(draw);
        sport.setPointsForLoss(loss);
        sport.setPointsForOvertimeLoss(lossot);
        sport.save();

        return sport;
    }
}
