package org.comroid.contabo.model.secret;

import org.comroid.api.ContextualProvider;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.uniform.node.UniObjectNode;
import org.jetbrains.annotations.Nullable;

public class Secret extends ContaboModel {
    public Secret(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
