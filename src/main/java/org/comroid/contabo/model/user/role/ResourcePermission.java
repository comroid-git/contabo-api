package org.comroid.contabo.model.user.role;

import org.comroid.api.ContextualProvider;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.contabo.model.tag.Tag;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.comroid.varbind.container.DataContainerBase;
import org.jetbrains.annotations.Nullable;

public final class ResourcePermission extends DataContainerBase<ResourcePermission> {
    public static final GroupBind<ResourcePermission> Type = new GroupBind<>("resourcePermission");
    public static final VarBind<ResourcePermission, Long, Tag, Tag> TAG
            = Type.createBind("tagId")
            .extractAs(StandardValueType.LONG)
            .andResolveRef(ContaboModel::resolveTag) // todo fixme
            .build();
    public static final VarBind<ResourcePermission, String, String, String> TAG_NAME
            = Type.createBind("tagName")
            .extractAs(StandardValueType.STRING)
            .build();
    public final Ref<Tag> tag = getComputedReference(TAG);
    public final Ref<String> tagName = getComputedReference(TAG_NAME);

    public ResourcePermission(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
