package controller.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("register", new RegisterCommand());
        commands.put("login", new LoginCommand());
        commands.put("logOut", new LogoutCommand());
    }

    public static Command getCommandByName(String name) {
        return commands.get(name);
    }
}
