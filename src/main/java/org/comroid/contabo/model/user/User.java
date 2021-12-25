package org.comroid.contabo.model.user;

import org.comroid.api.ContextualProvider;
import org.comroid.api.EMailAddress;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.contabo.model.user.role.Role;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.UUID;

public final class User extends ContaboModel {
    public static final GroupBind<User> Type = ContaboModel.Type.subGroup("user");
    public static final VarBind<User, String, UUID, UUID> ID
            = Type.createBind("userId")
            .extractAs(StandardValueType.STRING)
            .andRemap(UUID::fromString)
            .build();
    public static final VarBind<User, String, String, String> FIRST_NAME
            = Type.createBind("firstName")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<User, String, String, String> LAST_NAME
            = Type.createBind("lastName")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<User, String, EMailAddress, EMailAddress> EMAIL_ADDRESS
            = Type.createBind("email")
            .extractAs(StandardValueType.STRING)
            .andRemap(EMailAddress::parse)
            .build();
    public static final VarBind<User, Boolean, Boolean, Boolean> EMAIL_VERIFIED
            = Type.createBind("emailVerified")
            .extractAs(StandardValueType.BOOLEAN)
            .build();
    public static final VarBind<User, Boolean, Boolean, Boolean> ENABLED
            = Type.createBind("enabled")
            .extractAs(StandardValueType.BOOLEAN)
            .build();
    public static final VarBind<User, Boolean, Boolean, Boolean> TOTP
            = Type.createBind("totp")
            .extractAs(StandardValueType.BOOLEAN)
            .build();
    public static final VarBind<User, Boolean, Boolean, Boolean> ADMIN
            = Type.createBind("admin")
            .extractAs(StandardValueType.BOOLEAN)
            .build();
    public static final VarBind<User, Boolean, Boolean, Boolean> ACCESS_ALL_RESOURCES
            = Type.createBind("accessAllResources")
            .extractAs(StandardValueType.BOOLEAN)
            .build();
    public static final VarBind<User, String, String, String> LOCALE // todo better parsing
            = Type.createBind("locale")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<User, UniObjectNode, Role, HashSet<Role>> ROLES
            = Type.createBind("roles")
            .extractAsArray()
            .andResolve(ContaboModel::resolveRole)
            .intoCollection(HashSet<Role>::new)
            .build();
    public final Ref<UUID> id = getComputedReference(ID);
    public final Ref<String> firstName = getComputedReference(FIRST_NAME);
    public final Ref<String> lastName = getComputedReference(LAST_NAME);
    public final Ref<EMailAddress> emailAddress = getComputedReference(EMAIL_ADDRESS);
    public final Ref<Boolean> emailVerified = getComputedReference(EMAIL_VERIFIED);
    public final Ref<Boolean> enabled = getComputedReference(ENABLED);
    public final Ref<Boolean> enable = getComputedReference(TOTP);
    public final Ref<Boolean> admin = getComputedReference(ADMIN);
    public final Ref<Boolean> accessAllResources = getComputedReference(ACCESS_ALL_RESOURCES);
    public final Ref<String> locale = getComputedReference(LOCALE);
    public final Ref<HashSet<Role>> roles = getComputedReference(ROLES);

    @Override
    public String getName() {
        return firstName.combine(lastName, (first, last) -> first + ' ' + last)
                .orElseGet(emailAddress.map(EMailAddress::toString));
    }

    @Override
    public String getAlternateName() {
        return emailAddress.map(EMailAddress::toString).assertion();
    }

    public User(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
