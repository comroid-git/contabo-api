package org.comroid.contabo.model.instance;

import org.comroid.api.ContextualProvider;
import org.comroid.api.Polyfill;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.comroid.varbind.container.DataContainerBase;
import org.jetbrains.annotations.Nullable;

import java.net.Inet4Address;
import java.net.Inet6Address;

public final class IpConfig extends DataContainerBase<IpConfig> {
    public static final GroupBind<IpConfig> Type = new GroupBind<>("ipConfig");
    public static final VarBind<IpConfig, UniObjectNode, V4Entry, V4Entry> IPv4
            = Type.createBind("v4")
            .extractAsObject()
            .andConstruct(V4Entry.Type)
            .build();
    public static final VarBind<IpConfig, UniObjectNode, V6Entry, V6Entry> IPv6
            = Type.createBind("v6")
            .extractAsObject()
            .andConstruct(V6Entry.Type)
            .build();
    public final Ref<V4Entry> ipv4 = getComputedReference(IPv4);
    public final Ref<V6Entry> ipv6 = getComputedReference(IPv6);

    public Inet4Address getIPv4() {
        return ipv4.flatMap(v4 -> v4.ip).assertion("No IPv4 address found");
    }

    public Inet6Address getIPv6() {
        return ipv6.flatMap(v6 -> v6.ip).assertion("No IPv6 address found");
    }

    public IpConfig(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }

    public static final class V4Entry extends DataContainerBase<V4Entry> {
        public static final GroupBind<V4Entry> Type = new GroupBind<>("ipv4");
        public static final VarBind<V4Entry, String, Inet4Address, Inet4Address> IP
                = Type.createBind("ip")
                .extractAs(StandardValueType.STRING)
                .andRemap(Polyfill::parseIPv4)
                .build();
        public static final VarBind<V4Entry, Integer, Integer, Integer> NETMASK_CIDR
                = Type.createBind("netmaskCidr")
                .extractAs(StandardValueType.INTEGER)
                .build();
        public static final VarBind<V4Entry, String, Inet4Address, Inet4Address> GATEWAY
                = Type.createBind("gateway")
                .extractAs(StandardValueType.STRING)
                .andRemap(Polyfill::parseIPv4)
                .build();
        public final Ref<Inet4Address> ip = getComputedReference(IP);
        public final Ref<Integer> netmaskCidr = getComputedReference(NETMASK_CIDR);
        public final Ref<Inet4Address> gateway = getComputedReference(GATEWAY);

        public V4Entry(ContextualProvider context, @Nullable UniObjectNode initialData) {
            super(context, initialData);
        }
    }

    public static final class V6Entry extends DataContainerBase<V6Entry> {
        public static final GroupBind<V6Entry> Type = new GroupBind<>("ipv6");
        public static final VarBind<V6Entry, String, Inet6Address, Inet6Address> IP
                = Type.createBind("ip")
                .extractAs(StandardValueType.STRING)
                .andRemap(Polyfill::parseIPv6)
                .build();
        public static final VarBind<V6Entry, Integer, Integer, Integer> NETMASK_CIDR
                = Type.createBind("netmaskCidr")
                .extractAs(StandardValueType.INTEGER)
                .build();
        public static final VarBind<V6Entry, String, Inet6Address, Inet6Address> GATEWAY
                = Type.createBind("gateway")
                .extractAs(StandardValueType.STRING)
                .andRemap(Polyfill::parseIPv6)
                .build();
        public final Ref<Inet6Address> ip = getComputedReference(IP);
        public final Ref<Integer> netmaskCidr = getComputedReference(NETMASK_CIDR);
        public final Ref<Inet6Address> gateway = getComputedReference(GATEWAY);

        public V6Entry(ContextualProvider context, @Nullable UniObjectNode initialData) {
            super(context, initialData);
        }
    }
}
