package org.comroid.contabo.model.instance;

import org.comroid.api.ContextualProvider;
import org.comroid.common.info.Described;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.contabo.model.image.Image;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.UUID;

public final class Snapshot extends ContaboModel implements Described {
    public static final GroupBind<Snapshot> Type = ContaboModel.Type.subGroup("snapshot");
    public static final VarBind<Snapshot, String, String, String> SNAPSHOT_ID
            = Type.createBind("snapshotId")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<Snapshot, String, String, String> DESCRIPTION
            = Type.createBind("description")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<Snapshot, Long, Instance, Instance> INSTANCE
            = Type.createBind("instanceId")
            .extractAs(StandardValueType.LONG)
            .andResolveRef(ContaboModel::resolveInstance)
            .build();
    public static final VarBind<Snapshot, String, Instant, Instant> CREATED_DATE
            = Type.createBind("createdDate")
            .extractAs(StandardValueType.STRING)
            .andRemap(Instant::parse)
            .build();
    public static final VarBind<Snapshot, String, Instant, Instant> AUTO_DELETE_DATE
            = Type.createBind("autoDeleteDate")
            .extractAs(StandardValueType.STRING)
            .andRemap(Instant::parse)
            .build();
    public static final VarBind<Snapshot, String, Image, Image> IMAGE
            = Type.createBind("imageId")
            .extractAs(StandardValueType.STRING)
            .andResolveRef((it, img) -> ContaboModel.resolveImage(it, UUID.fromString(img)))
            .build();
    public static final VarBind<Snapshot, String, String, String> IMAGE_NAME
            = Type.createBind("imageName")
            .extractAs(StandardValueType.STRING)
            .build();
    public final Ref<String> snapshotId = getComputedReference(SNAPSHOT_ID);
    public final Ref<String> description = getComputedReference(DESCRIPTION);
    public final Ref<Instance> instance = getComputedReference(INSTANCE);
    public final Ref<Instant> createdDate = getComputedReference(CREATED_DATE);
    public final Ref<Instant> autoDeleteDate = getComputedReference(AUTO_DELETE_DATE);
    public final Ref<Image> image = getComputedReference(IMAGE);
    public final Ref<String> imageName = getComputedReference(IMAGE_NAME);

    @Override
    public String getDescription() {
        return description.orElse("<no description provided>");
    }

    public Snapshot(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
