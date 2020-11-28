package com.example.sportstats.service;

import com.example.sportstats.domain.Sport;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Claes Andersson
 */
public class GetSportsService extends BaseService<List<Sport>> {

    @Override
    public List<Sport> execute() {

        List<Sport> sports = getBrokerFactory().getSportBroker().findAll();
        
        if (sports.isEmpty()) {
            throw new SportstatsServiceException("There are no sports", HttpStatus.NOT_FOUND);
        }
        
        return sports;
    }
}
