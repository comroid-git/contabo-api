package org.comroid.contabo.model;

import org.comroid.api.ContextualProvider;
import org.comroid.api.Named;
import org.comroid.api.Rewrapper;
import org.comroid.contabo.model.image.Image;
import org.comroid.contabo.model.instance.Instance;
import org.comroid.contabo.model.tag.Tag;
import org.comroid.contabo.model.user.role.Role;
import org.comroid.mutatio.model.Ref;
import org.comroid.uniform.node.UniObjectNode;
import org.comroid.util.StandardValueType;
import org.comroid.varbind.bind.GroupBind;
import org.comroid.varbind.bind.VarBind;
import org.comroid.varbind.container.DataContainerBase;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.UUID;

public abstract class ContaboModel extends DataContainerBase<ContaboModel> implements Named {
    public static final GroupBind<ContaboModel> Type = new GroupBind<>("com.contabo");
    public static final VarBind<ContaboModel, String, String, String> TENANT_ID // todo better Parsing
            = Type.createBind("tenantId")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<ContaboModel, Integer, Integer, Integer> CUSTOMER_ID
            = Type.createBind("customerId")
            .extractAs(StandardValueType.INTEGER)
            .build();
    public static final VarBind<ContaboModel, String, String, String> NAME
            = Type.createBind("name")
            .extractAs(StandardValueType.STRING)
            .build();
    public static final VarBind<ContaboModel, String, String, String> ERROR_MESSAGE
            = Type.createBind("errorMessage")
            .extractAs(StandardValueType.STRING)
            .asIdentities()
            .onceEach()
            .setOptional()
            .build();
    public static final VarBind<ContaboModel, String, Instant, Instant> LAST_MODIFIED
            = Type.createBind("lastModifiedDate")
            .extractAs(StandardValueType.STRING)
            .andRemap(Instant::parse)
            .onceEach()
            .setOptional()
            .build();
    public final Ref<String> tenantID = getComputedReference(TENANT_ID);
    public final Ref<Integer> customerID = getComputedReference(CUSTOMER_ID);
    public final Ref<String> name = getComputedReference(NAME);
    public final Ref<String> errorMessage = getComputedReference(ERROR_MESSAGE);
    public final Ref<Instant> lastModified = getComputedReference(LAST_MODIFIED);

    public String getTenantID() {
        return tenantID.assertion();
    }

    public int getCustomerID() {
        return customerID.assertion();
    }

    public String getErrorMessage() {
        return errorMessage.orElse("<no error message>");
    }

    public Instant getLastModified() {
        return lastModified.orElseGet(Instant::now);
    }

    @Override
    public String getName() {
        // todo: can this ever be null?
        return name.orElse("<no name specified>");
    }

    protected ContaboModel(ContextualProvider context, @Nullable UniObjectNode initialData) {
        super(context, initialData);
    }

    public static Tag resolveTag(ContextualProvider ctx, UniObjectNode obj) {
        return null; // todo
    }

    public static Role resolveRole(ContextualProvider ctx, UniObjectNode obj) {
        return null; // todo
    }

    public static Rewrapper<Image> resolveImage(ContextualProvider ctx, UUID imageId) {
        return null; // todo
    }

    public static Rewrapper<Instance> resolveInstance(ContextualProvider ctx, long instanceId) {
        return null; // todo
    }

    public static Rewrapper<Tag> resolveTag(ContextualProvider ctx, long tagId) {
        return null; // todo
    }
}
