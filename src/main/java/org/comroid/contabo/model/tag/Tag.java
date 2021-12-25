package org.comroid.contabo.model.tag;

import org.comroid.api.ContextualProvider;
import org.comroid.api.Polyfill;
import org.comroid.contabo.ContaboConnection;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.contabo.rest.ContaboEndpoint;
import org.comroid.mutatio.model.Ref;
import org.comroid.restless.HTTPStatusCodes;
import org.comroid.restless.REST;
import org.comroid.restless.body.BodyBuilderType;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.concurrent.CompletableFuture;

public final class Tag extends ContaboModel {
    public static final GroupBind<Tag> Type = ContaboModel.Type.subGroup("tag");
    public static final VarBind<Tag, Long, Long, Long> ID
            = Type.createBind("tagId")
            .extractAs(StandardValueType.LONG)
            .build();
    public static final VarBind<Tag, String, Color, Color> COLOR
            = Type.createBind("color")
            .extractAs(StandardValueType.STRING)
            .andRemap(Polyfill::parseHexColor)
            .build();
    public final Ref<Long> id = getComputedReference(ID);
    public final Ref<Color> color = getComputedReference(COLOR);

    public long getID() {
        return id.assertion();
    }

    public Color getColor() {
        return color.orElse(Color.WHITE);
    }

    public Tag(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }

    public CompletableFuture<Tag> update(String name, Color color) {
        ContaboConnection connection = requireFromContext(ContaboConnection.class);
        return connection.request(ContaboEndpoint.SPECIFIC_TAG, getID())
                .method(REST.Method.PATCH)
                .buildBody(BodyBuilderType.OBJECT, obj -> {
                    obj.put("name", name);
                    obj.put("color", Polyfill.hexString(color));
                })
                .execute()
                .thenCompose(resp -> connection.requestTag(getID()));
    }

    public CompletableFuture<Void> delete() {
        ContaboConnection connection = requireFromContext(ContaboConnection.class);
        return connection.request(ContaboEndpoint.SPECIFIC_TAG, getID())
                .method(REST.Method.DELETE)
                .expect(HTTPStatusCodes.NO_CONTENT)
                .execute()
                .thenAccept(resp -> connection.deleteTag(getID()));
    }
}
