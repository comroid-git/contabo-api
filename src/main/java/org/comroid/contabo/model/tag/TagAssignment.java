package org.comroid.contabo.model.tag;

import org.comroid.api.ContextualProvider;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.contabo.model.user.ResourceType;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.jetbrains.annotations.Nullable;

public final class TagAssignment extends ContaboModel {
    public static final GroupBind<TagAssignment> Type = ContaboModel.Type.subGroup("tag-assignment");
    public static final VarBind<TagAssignment, Integer, Tag, Tag> TAG
            = Type.createBind("tagId")
            .extractAs(StandardValueType.INTEGER)
            .andResolveRef(ContaboModel::resolveTag)
            .build();
    public static final VarBind<TagAssignment, String, String, String> TAG_NAME
            = Type.createBind("tagName")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<TagAssignment, String, ResourceType, ResourceType> RESOURCE_TYPE // todo better parsing
            = Type.createBind("resourceType")
            .extractAs(StandardValueType.STRING)
            .andRemap(ResourceType::valueOf)
            .build();
    public static final VarBind<TagAssignment, String, String, String> RESOURCE_NAME
            = Type.createBind("resourceName")
            .extractAs(StandardValueType.STRING)
            .build();
    public final Ref<Tag> tag = getComputedReference(TAG);
    public final Ref<String> tagName = getComputedReference(TAG_NAME);
    public final Ref<ResourceType> resourceType = getComputedReference(RESOURCE_TYPE);
    public final Ref<String> resourceName = getComputedReference(RESOURCE_NAME);

    public long getTagID() {
        return tag.map(Tag::getID).assertion();
    }

    public TagAssignment(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
