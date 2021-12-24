package org.comroid.contabo.model.image;

import org.comroid.api.ContextualProvider;
import org.comroid.api.Polyfill;
import org.comroid.api.UUIDContainer;
import org.comroid.api.os.OS;
import org.comroid.common.Version;
import org.comroid.common.info.Described;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.contabo.model.tag.Tag;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.UUID;

public final class Image extends ContaboModel implements UUIDContainer, Described {
    public static final GroupBind<Image> Type = ContaboModel.Type.subGroup("image");
    public static final VarBind<Image, String, UUID, UUID> ID
            = Type.createBind("imageId")
            .extractAs(StandardValueType.STRING)
            .andRemap(UUID::fromString)
            .build();
    public static final VarBind<Image, String, String, String> DESCRIPTION
            = Type.createBind("description")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<Image, String, java.net.URL, java.net.URL> URL
            = Type.createBind("url")
            .extractAs(StandardValueType.STRING)
            .andRemap(Polyfill::url)
            .build();
    public static final VarBind<Image, Integer, Integer, Integer> SIZE_MB
            = Type.createBind("sizeMb")
            .extractAs(StandardValueType.INTEGER)
            .build();
    public static final VarBind<Image, String, OS, OS> OPERATING_SYSTEM
            = Type.createBind("osType")
            .extractAs(StandardValueType.STRING)
            .andRemap(org.comroid.api.os.OS::findByName)
            .build();
    public static final VarBind<Image, String, Version, Version> OS_VERSION
            = Type.createBind("version")
            .extractAs(StandardValueType.STRING)
            .andRemap(Version::new)
            .build();
    public static final VarBind<Image, String, String, String> FORMAT // todo better Parsing
            = Type.createBind("format")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<Image, String, String, String> STATUS // todo better Parsing
            = Type.createBind("status")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<Image, Boolean, Boolean, Boolean> STANDARD_IMAGE
            = Type.createBind("standardImage")
            .extractAs(StandardValueType.BOOLEAN)
            .build();
    public static final VarBind<Image, UniObjectNode, Tag, HashSet<Tag>> TAGS
            = Type.createBind("tags")
            .extractAsArray()
            .andResolve(ContaboModel::resolveTag)
            .intoCollection(HashSet<Tag>::new)
            .build();
    public final Ref<UUID> id = getComputedReference(ID);
    public final Ref<String> description = getComputedReference(DESCRIPTION);
    public final Ref<java.net.URL> url = getComputedReference(URL);
    public final Ref<Integer> sizeMb = getComputedReference(SIZE_MB);
    public final Ref<OS> os = getComputedReference(OPERATING_SYSTEM);
    public final Ref<Version> osVersion = getComputedReference(OS_VERSION);
    public final Ref<String> format = getComputedReference(FORMAT);
    public final Ref<String> status = getComputedReference(STATUS);
    public final Ref<Boolean> isStandardImage = getComputedReference(STANDARD_IMAGE);
    public final Ref<HashSet<Tag>> tags = getComputedReference(TAGS);

    @Override
    public String getDescription() {
        return description.orElse("<no description provided>");
    }

    @Override
    public UUID getUUID() {
        return id.assertion();
    }

    public Image(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
