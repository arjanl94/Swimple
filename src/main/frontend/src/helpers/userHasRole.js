export function userHasRole(user, roleName) {
    if(user.roles.isEmpty) return false;

    const role = user.roles.find(role => {
        return role.name == roleName;
    });

    if(!role) return false;

    return true;
}

export function userIsAdmin(user) {
    return userHasRole(user, 'ADMIN_ROLE');
}