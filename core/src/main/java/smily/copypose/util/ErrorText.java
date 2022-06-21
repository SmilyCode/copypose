package smily.copypose.util;

public class ErrorText {
    public static final String START = "Failed to start: ";
    public static final String STOP = "Failed to stop: ";
    public static final String ACTIVITY= "Cannot be done: ";
    public static final String WRONG_FORMAT = "Error at format: ";
    public static final String INVALID_COMMAND = "Invalid command: ";
    public static final String TOO_MANY_ARGS = INVALID_COMMAND + "too many arguments";
    public static final String MISSING_ARGS = INVALID_COMMAND + "missing argument";
    public static final String NULL_SELECTED = "Cannot perform any operation while nothing is selected";

    public static class Resolve{
        public static final String ACTIVITY = "Try to: ";
        public static final String LOOK_FOWARD = "Look into: ";
        public static final String FORMAT = "Accepted format: ";
    }
}
