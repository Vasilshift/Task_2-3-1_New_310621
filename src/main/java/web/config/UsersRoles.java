package web.config;

import com.google.common.collect.*;
import java.util.Set;

import static web.config.UserPermission.*;

public enum UsersRoles {

    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE));

    //USER(Sets.newHashSet());


    private final Set<UserPermission> permissions;

    UsersRoles(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }
}
