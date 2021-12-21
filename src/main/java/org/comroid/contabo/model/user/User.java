package org.comroid.contabo.model.user;

import org.comroid.api.ContextualProvider;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.uniform.node.UniObjectNode;
import org.jetbrains.annotations.Nullable;

public class User extends ContaboModel {
    public User(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
