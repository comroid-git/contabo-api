package org.comroid.contabo.model.secret;

import org.comroid.api.ContextualProvider;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;

public final class Secret extends ContaboModel {
    public static final GroupBind<Secret> Type = ContaboModel.Type.subGroup("secret");
    public static final VarBind<Secret, Long, Long, Long> SECRET_ID
            = Type.createBind("secretId")
            .extractAs(StandardValueType.LONG)
            .build();
    public static final VarBind<Secret, String, String, String> TYPE // todo better parsing
            = Type.createBind("type")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<Secret, String, String, String> VALUE
            = Type.createBind("value")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<Secret, String, Instant, Instant> CREATED_DATE
            = Type.createBind("createdAt")
            .extractAs(StandardValueType.STRING)
            .andRemap(Instant::parse)
            .build();
    public static final VarBind<Secret, String, Instant, Instant> UPDATED_DATE
            = Type.createBind("updatedAt")
            .extractAs(StandardValueType.STRING)
            .andRemap(Instant::parse)
            .build();
    public final Ref<Long> id = getComputedReference(SECRET_ID);
    public final Ref<String> type = getComputedReference(TYPE);
    public final Ref<String> value = getComputedReference(VALUE);
    public final Ref<Instant> createdDate = getComputedReference(CREATED_DATE);
    public final Ref<Instant> updatedDate = getComputedReference(UPDATED_DATE);

    public Secret(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
