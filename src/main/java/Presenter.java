public class Presenter {
    /*
    This is a class that retrieves the results from the use cases, and then called by
    the console to return the output.
     */

    public Presenter(UserAnalyzer analyzer){ this.userAnalyzer = analyzer; }

    private final UserAnalyzer userAnalyzer;

    /**
     * This method retrieves the output of one use case and instantiates it into one of the
     * private variable.
     */
    public String retrieveOutput(){
        return userAnalyzer.getResult();
    }
}
