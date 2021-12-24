package org.comroid.contabo.model.instance;

import org.comroid.api.ContextualProvider;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.comroid.varbind.container.DataContainerBase;
import org.jetbrains.annotations.Nullable;

public final class AddonInfo extends DataContainerBase<AddonInfo> {
    public static final GroupBind<AddonInfo> Type = new GroupBind<>("addonInfo");
    public static final VarBind<AddonInfo, Integer, Integer, Integer> ID
            = Type.createBind("id")
            .extractAs(StandardValueType.INTEGER)
            .build();
    public static final VarBind<AddonInfo, Integer, Integer, Integer> QUANTITY
            = Type.createBind("quantity")
            .extractAs(StandardValueType.INTEGER)
            .build();
    public final Ref<Integer> id = getComputedReference(ID);
    public final Ref<Integer> quantity = getComputedReference(QUANTITY);

    public AddonInfo(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
