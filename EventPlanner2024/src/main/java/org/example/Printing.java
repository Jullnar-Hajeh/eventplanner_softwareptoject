package org.example;
import java.util.logging.*;

public class Printing {
    private static final Logger LOGGER = Logger.getLogger(Printing.class.getName());

    static {
        // Create a console handler with a custom formatter that only prints the message
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord logRecord) {
                String message = super.formatMessage(logRecord);
               String ansiRed = ANSI_CYAN;

               String ansiReset = ANSI_RESET;

                return ansiRed + message + ansiReset;
            }
        });

        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(consoleHandler);
    }

    public void printSomething( String msg ) {
        LOGGER.log(Level.INFO, msg);
    }
    
    
    
    
    
    
/////////////////////////////////////////////////    haneen  new code    /////////////////////////////////////
    public void printInColor(String msg, String color) {
        String formattedMsg = color + msg + ANSI_RESET;
        LOGGER.log(Level.INFO, formattedMsg);
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public String getColoredString(String input, String color) {
        return color + input + ANSI_RESET;
    }
 // Additional color constants
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_MAGENTA = "\u001B[35m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GRAY = "\u001B[90m";
    // Custom color constants
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";
    public static final String ANSI_PINK = "\u001B[38;5;205m";
    public static final String ANSI_LIME = "\u001B[38;5;154m";
    
    


public static String colorText(String text, String color) {
    return color + text + ANSI_RESET;
}

}
