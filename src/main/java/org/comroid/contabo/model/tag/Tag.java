package org.comroid.contabo.model.tag;

import org.comroid.api.ContextualProvider;
import org.comroid.api.Polyfill;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public final class Tag extends ContaboModel {
    public static final GroupBind<Tag> Type = ContaboModel.Type.subGroup("tag");
    public static final VarBind<Tag, Integer, Integer, Integer> ID
            = Type.createBind("tagId")
            .extractAs(StandardValueType.INTEGER)
            .build();
    public static final VarBind<Tag, String, Color, Color> COLOR
            = Type.createBind("color")
            .extractAs(StandardValueType.STRING)
            .andRemap(Polyfill::parseHexColor)
            .build();
    public final Ref<Integer> id = getComputedReference(ID);
    public final Ref<Color> color = getComputedReference(COLOR);

    public int getId() {
        return id.assertion();
    }

    public Color getColor() {
        return color.orElse(Color.WHITE);
    }

    public Tag(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
