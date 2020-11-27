package com.example.sportstats.broker;

/**
 * A factory for creating the needed broker.
 *
 * @author Claes Andersson
 */
public class BrokerFactory {

    /**
     * Creates a new sport broker and returns it.
     *
     * @return a new sport broker
     */
    public SportBroker getSportBroker() {

        return new SportBroker();
    }

    /**
     * Creates a new leagues broker and returns it.
     *
     * @return a new league broker
     */
    public LeagueBroker getLeagueBroker() {

        return new LeagueBroker();
    }

    /**
     * Creates a new season broker and returns it.
     *
     * @return a new season broker
     */
    public SeasonBroker getSeasonBroker() {

        return new SeasonBroker();
    }

    /**
     * Creates a new arena broker and returns it.
     *
     * @return a new arena broker
     */
    public ArenaBroker getArenaBroker() {

        return new ArenaBroker();
    }

    /**
     * Creates a new team broker and returns it.
     *
     * @return a new team broker
     */
    public TeamBroker getTeamBroker() {

        return new TeamBroker();
    }

    /**
     * Creates a new match broker and returns it.
     *
     * @return a new match broker
     */
    public MatchBroker getMatchBroker() {

        return new MatchBroker();
    }

    /**
     * Creates a new seasonsteams broker and returns it.
     *
     * @return a new seasonsteams broker
     */
    public SeasonTeamBroker getSeasonsTeamsBroker() {

        return new SeasonTeamBroker();
    }

    /**
     * Creates a new round broker and returns it.
     *
     * @return a new round broker
     */
    public RoundBroker getRoundBroker() {

        return new RoundBroker();
    }
}
