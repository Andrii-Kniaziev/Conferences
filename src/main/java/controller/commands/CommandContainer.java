package controller.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("register", new RegisterCommand());
    }

    public static Command getCommandByName(String name) {
        return commands.get(name);
    }
}
