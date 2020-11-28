package com.example.sportstats.service;

import com.example.sportstats.domain.Sport;
import org.springframework.http.HttpStatus;

/**
 * Service that edits an existing sport.
 *
 * @author Claes Andersson
 */
public class EditSportService extends BaseService<Sport> {

    private final Integer id;
    private final String name;
    private final Integer win;
    private final Integer winot;
    private final Integer draw;
    private final Integer loss;
    private final Integer lossot;

    public EditSportService(Integer id, String name, Integer win, Integer winot, Integer draw, Integer loss, Integer lossot) {

        if (name != null && (name.isEmpty() || name.isBlank())) {
            throw new SportstatsServiceException("Sport name can't be empty", HttpStatus.BAD_REQUEST);
        }
        this.id = id;
        this.name = name;
        this.win = win;
        this.winot = winot;
        this.draw = draw;
        this.loss = loss;
        this.lossot = lossot;
    }

    @Override
    public Sport execute() {

        Sport sport = getBrokerFactory().getSportBroker().findById(id);
        if (sport == null) {
            throw new SportstatsServiceException("There are no sport with id: " + id, HttpStatus.NOT_FOUND);
        }
        if (name != null) {
            sport.setName(name);
        }
        if (win != null) {
            sport.setPointsForWin(win);
        }
        if (winot != null) {
            sport.setPointsForOvertimeWin(winot);
        }
        if (draw != null) {
            sport.setPointsForDraw(draw);
        }
        if (loss != null) {
            sport.setPointsForLoss(loss);
        }
        if (lossot != null) {
            sport.setPointsForOvertimeLoss(lossot);
        }
        sport.save();

        return sport;
    }
}
