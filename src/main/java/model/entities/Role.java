package model.entities;

public enum Role {
    ADMIN("admin"),
    SPEAKER("speaker"),
    LISTENER("listener"),
    ERROR("error");

    private String value;

    public String getValue() {return value;}

    Role(String value) {
        this.value = value;
    }

    public static Role stringToEnum(String role) {
        Role result = null;

        if(role == null) {
            return ERROR;
        }
        switch (role) {
            case "admin" : result = ADMIN; break;
            case "speaker" : result = SPEAKER; break;
            case "listener" : result = LISTENER; break;
        }
        return result;
    }
}
