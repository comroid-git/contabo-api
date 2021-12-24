package org.comroid.contabo.model.user.role;

import org.comroid.api.ContextualProvider;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.comroid.varbind.container.DataContainerBase;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public final class ApiPermission extends DataContainerBase<ApiPermission> {
    public static final GroupBind<ApiPermission> Type = new GroupBind<>("apiPermission");
    public static final VarBind<ApiPermission, String, String, String> API_NAME // todo better parsing
            = Type.createBind("apiName")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<ApiPermission, String, String, HashSet<String>> ACTIONS // todo better parsing
            = Type.createBind("actions")
            .extractAsArray(StandardValueType.STRING)
            .asIdentities()
            .intoCollection(HashSet<String>::new)
            .build();
    public final Ref<String> apiName = getComputedReference(API_NAME);
    public final Ref<HashSet<String>> actions = getComputedReference(ACTIONS);

    public ApiPermission(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
