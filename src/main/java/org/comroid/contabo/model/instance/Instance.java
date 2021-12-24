package org.comroid.contabo.model.instance;

import org.comroid.api.ContextualProvider;
import org.comroid.api.MacAddress;
import org.comroid.api.os.OS;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.contabo.model.image.Image;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashSet;
import java.util.UUID;

public final class Instance extends ContaboModel {
    public static final GroupBind<Instance> Type = ContaboModel.Type.subGroup("instance");
    public static final VarBind<Instance, Long, Long, Long> INSTANCE_ID
            = Type.createBind("instanceId")
            .extractAs(StandardValueType.LONG)
            .build();
    public static final VarBind<Instance, String, String, String> REGION // todo better Parsing
            = Type.createBind("region")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<Instance, String, String, String> PRODUCT_ID
            = Type.createBind("productId")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<Instance, String, Image, Image> IMAGE
            = Type.createBind("imageId")
            .extractAs(StandardValueType.STRING)
            .andResolveRef((it, img) -> ContaboModel.resolveImage(it, UUID.fromString(img)))
            .build();
    public static final VarBind<Instance, UniObjectNode, IpConfig, IpConfig> IP_CONFIG
            = Type.createBind("ipConfig")
            .extractAsObject()
            .andConstruct(IpConfig.Type)
            .build();
    public static final VarBind<Instance, String, MacAddress, MacAddress> MAC_ADDRESS
            = Type.createBind("macAddress")
            .extractAs(StandardValueType.STRING)
            .andRemap(MacAddress::new)
            .build();
    public static final VarBind<Instance, Long, Long, Long> RAM_MB
            = Type.createBind("ramMb")
            .extractAs(StandardValueType.LONG)
            .build();
    public static final VarBind<Instance, Integer, Integer, Integer> CPU_CORES
            = Type.createBind("cpuCores")
            .extractAs(StandardValueType.INTEGER)
            .build();
    public static final VarBind<Instance, String, OS, OS> OPERATING_SYSTEM
            = Type.createBind("osType")
            .extractAs(StandardValueType.STRING)
            .andRemap(OS::findByName)
            .build();
    public static final VarBind<Instance, Long, Long, Long> DISK_MB
            = Type.createBind("diskMb")
            .extractAs(StandardValueType.LONG)
            .build();
    public static final VarBind<Instance, String, String, String> SSH_KEYS // todo better Parsing
            = Type.createBind("sshKeys")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<Instance, String, Instant, Instant> CREATED_DATE
            = Type.createBind("createdDate")
            .extractAs(StandardValueType.STRING)
            .andRemap(Instant::parse)
            .build();
    public static final VarBind<Instance, String, TemporalAccessor, TemporalAccessor> CANCEL_DATE
            = Type.createBind("cancelDate")
            .extractAs(StandardValueType.STRING)
            .andRemap(DateTimeFormatter.ISO_DATE::parse)
            .build();
    public static final VarBind<Instance, String, String, String> STATUS // todo better Parsing
            = Type.createBind("status")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<Instance, Long, Long, Long> VHOST_ID
            = Type.createBind("vHostId")
            .extractAs(StandardValueType.LONG)
            .build();
    public static final VarBind<Instance, UniObjectNode, AddonInfo, HashSet<AddonInfo>> ADDONS
            = Type.createBind("addons")
            .extractAsArray()
            .andConstruct(AddonInfo.Type)
            .intoCollection(HashSet<AddonInfo>::new)
            .build();
    public static final VarBind<Instance, String, String, String> PRODUCT_TYPE // todo better Parsing
            = Type.createBind("productType")
            .extractAs(StandardValueType.STRING)
            .build();
    public final Ref<Long> instanceId = getComputedReference(INSTANCE_ID);
    public final Ref<String> region = getComputedReference(REGION);
    public final Ref<String> productId = getComputedReference(PRODUCT_ID);
    public final Ref<Image> image = getComputedReference(IMAGE);
    public final Ref<IpConfig> ipConfig = getComputedReference(IP_CONFIG);
    public final Ref<MacAddress> macAddress = getComputedReference(MAC_ADDRESS);
    public final Ref<Long> ramMb = getComputedReference(RAM_MB);
    public final Ref<Integer> cpuCores = getComputedReference(CPU_CORES);
    public final Ref<OS> osType = getComputedReference(OPERATING_SYSTEM);
    public final Ref<Long> diskMb = getComputedReference(DISK_MB);
    public final Ref<String> sshKeys = getComputedReference(SSH_KEYS);
    public final Ref<Instant> createdDate = getComputedReference(CREATED_DATE);
    public final Ref<TemporalAccessor> cancelDate = getComputedReference(CANCEL_DATE);
    public final Ref<String> status = getComputedReference(STATUS);
    public final Ref<Long> vHostId = getComputedReference(VHOST_ID);
    public final Ref<HashSet<AddonInfo>> addons = getComputedReference(ADDONS);
    public final Ref<String> productType = getComputedReference(PRODUCT_TYPE);

    public Instance(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }
}
