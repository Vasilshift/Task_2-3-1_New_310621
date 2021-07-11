package web.config;

public enum UserPermission {

    USER_READ("user:read"),
    USER_WRITE("user:write");


    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
