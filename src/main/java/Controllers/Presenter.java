package Controllers;

import UseCases.UserAnalyzer;

/**
 * This is a Presenter class that retrieves the results from the use cases, and then is called by
 * the console to return the result calculated from the different use cases
 */
public class Presenter {

    private final UserAnalyzer userAnalyzer;

    /**
     * Initiating a Presenter by declaring the type of analyzer it needs to retrieve results from
     *
     * @param analyzer an analyzer that is of type UserAnalyzer
     */
    public Presenter(UserAnalyzer analyzer) {
        this.userAnalyzer = analyzer;
    }


    /**
     * This method retrieves the output of one use case and instantiates it into one of the
     * private variable.
     *
     * @return returns the output calculated by the userAnalyzer
     */
    public String retrieveOutput() {
        return userAnalyzer.getResult();
    }
}
