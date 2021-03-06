package org.comroid.contabo.model.audit;

import org.comroid.api.ContextualProvider;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.uniform.node.UniObjectNode;
import org.jetbrains.annotations.Nullable;

public class BaseAudit extends ContaboModel {
    public BaseAudit(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
