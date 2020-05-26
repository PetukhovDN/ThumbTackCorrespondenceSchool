package net.thumbtack.school.elections.enums;

public enum ResultsOfRequests {
    SUCCESSFUL_REQUEST("Success");

    private String resultText;

    ResultsOfRequests(String resultText) {
        this.resultText = resultText;
    }

    public String getErrorString() {
        return resultText;
    }
}
