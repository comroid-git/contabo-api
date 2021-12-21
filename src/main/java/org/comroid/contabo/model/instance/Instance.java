package org.comroid.contabo.model.instance;

import org.comroid.api.ContextualProvider;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.uniform.node.UniObjectNode;
import org.jetbrains.annotations.Nullable;

public class Instance extends ContaboModel {
    public Instance(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
