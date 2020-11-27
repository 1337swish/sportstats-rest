package com.example.sportstats.service;

import com.example.sportstats.domain.Sport;
import java.util.List;

/**
 *
 * @author Claes Andersson
 */
public class GetSportsService extends BaseService<List<Sport>> {

    @Override
    public List<Sport> execute() {

        List<Sport> sports = getBrokerFactory().getSportBroker().findAll();
        return sports;
    }
}
