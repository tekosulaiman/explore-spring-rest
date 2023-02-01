package co.id.service;

import java.util.Set;

import co.id.persistence.model.Privilege;
import co.id.persistence.model.Role;
import co.id.persistence.model.User;
import co.id.util.Um;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

@Component
public class SecuritySetup implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LoggerFactory.getLogger(SecuritySetup.class);

    private boolean setupDone;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPrivilegeService privilegeService;

    public SecuritySetup() {
        super();
    }

    @Override
    public final void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!setupDone) {
            logger.info("Executing Setup");

            createPrivileges();
            createRoles();
            createUserss();

            setupDone = true;
            logger.info("Setup Done");
        }
    }

    // Privilege
    private void createPrivileges() {
        createPrivilegeIfNotExisting(Um.Privileges.CAN_PRIVILEGE_READ);
        createPrivilegeIfNotExisting(Um.Privileges.CAN_PRIVILEGE_WRITE);

        createPrivilegeIfNotExisting(Um.Privileges.CAN_ROLE_READ);
        createPrivilegeIfNotExisting(Um.Privileges.CAN_ROLE_WRITE);

        createPrivilegeIfNotExisting(Um.Privileges.CAN_USER_READ);
        createPrivilegeIfNotExisting(Um.Privileges.CAN_USER_WRITE);
    }

    final void createPrivilegeIfNotExisting(final String name) {
        final Privilege entityByName = privilegeService.findByName(name);
        if (entityByName == null) {
            final Privilege entity = new Privilege(name);
            privilegeService.create(entity);
        }
    }

    // Role
    private void createRoles() {
        final Privilege canPrivilegeRead = privilegeService.findByName(Um.Privileges.CAN_PRIVILEGE_READ);
        final Privilege canPrivilegeWrite = privilegeService.findByName(Um.Privileges.CAN_PRIVILEGE_WRITE);
        final Privilege canRoleRead = privilegeService.findByName(Um.Privileges.CAN_ROLE_READ);
        final Privilege canRoleWrite = privilegeService.findByName(Um.Privileges.CAN_ROLE_WRITE);
        final Privilege canUserRead = privilegeService.findByName(Um.Privileges.CAN_USER_READ);
        final Privilege canUserWrite = privilegeService.findByName(Um.Privileges.CAN_USER_WRITE);

        Preconditions.checkNotNull(canPrivilegeRead);
        Preconditions.checkNotNull(canPrivilegeWrite);
        Preconditions.checkNotNull(canRoleRead);
        Preconditions.checkNotNull(canRoleWrite);
        Preconditions.checkNotNull(canUserRead);
        Preconditions.checkNotNull(canUserWrite);

        createRoleIfNotExisting(Um.Roles.ROLE_ADMIN, Sets.<Privilege> newHashSet(canUserRead, canUserWrite, canRoleRead, canRoleWrite, canPrivilegeRead, canPrivilegeWrite));
    }

    final void createRoleIfNotExisting(final String name, final Set<Privilege> privileges) {
        final Role entityByName = roleService.findByName(name);
        if (entityByName == null) {
            final Role entity = new Role(name);
            entity.setPrivileges(privileges);
            roleService.create(entity);
        }
    }

    // User/User
    final void createUserss() {
        final Role roleAdmin = roleService.findByName(Um.Roles.ROLE_ADMIN);

        // createUserIfNotExisting(SecurityConstants.ADMIN_USERNAME, SecurityConstants.ADMIN_PASS, Sets.<Role> newHashSet(roleAdmin));
        createUserIfNotExisting(Um.ADMIN_EMAIL, Um.ADMIN_PASS, Sets.<Role> newHashSet(roleAdmin));
    }

    final void createUserIfNotExisting(final String loginName, final String pass, final Set<Role> roles) {
        final User entityByName = userService.findByName(loginName);
        if (entityByName == null) {
            final User entity = new User(loginName, pass, roles);
            userService.create(entity);
        }
    }
}