package org.comroid.contabo.model.user.role;

import org.comroid.api.ContextualProvider;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public final class Role extends ContaboModel {
    public static final GroupBind<Role> Type = ContaboModel.Type.subGroup("role");
    public static final VarBind<Role, Long, Long, Long> ID
            = Type.createBind("roleId")
            .extractAs(StandardValueType.LONG)
            .build();
    public static final VarBind<Role, String, String, String> TYPE
            = Type.createBind("roleType") // todo better parsing
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<Role, UniObjectNode, ApiPermission, HashSet<ApiPermission>> API_PERMISSIONS
            = Type.createBind("apiPermissions")
            .extractAsArray()
            .andConstruct(ApiPermission.Type)
            .intoCollection(HashSet<ApiPermission>::new)
            .build();
    public static final VarBind<Role, UniObjectNode, ResourcePermission, HashSet<ResourcePermission>> RESOURCE_PERMISSIONS
            = Type.createBind("resourcePermissions")
            .extractAsArray()
            .andConstruct(ResourcePermission.Type)
            .intoCollection(HashSet<ResourcePermission>::new)
            .build();
    public final Ref<Long> id = getComputedReference(ID);
    public final Ref<String> type = getComputedReference(TYPE);
    public final Ref<HashSet<ApiPermission>> apiPermissions = getComputedReference(API_PERMISSIONS);
    public final Ref<HashSet<ResourcePermission>> resourcePermissions = getComputedReference(RESOURCE_PERMISSIONS);

    public Role(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
