package org.comroid.contabo;

import org.comroid.api.ContextualProvider;
import org.comroid.api.Polyfill;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.contabo.model.image.Image;
import org.comroid.contabo.model.instance.Instance;
import org.comroid.contabo.model.instance.Snapshot;
import org.comroid.contabo.model.secret.Secret;
import org.comroid.contabo.model.tag.Tag;
import org.comroid.contabo.model.user.User;
import org.comroid.contabo.model.user.role.Role;
import org.comroid.contabo.model.user.role.RoleType;
import org.comroid.contabo.rest.ContaboEndpoint;
import org.comroid.restless.CommonHeaderNames;
import org.comroid.restless.REST;
import org.comroid.restless.body.BodyBuilderType;
import org.comroid.uniform.model.Serializable;
import org.comroid.uniform.node.UniNode;
import org.comroid.varbind.DataContainerCache;
import org.comroid.varbind.bind.GroupBind;
import org.jetbrains.annotations.ApiStatus.Internal;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public final class ContaboConnection implements ContextualProvider.Underlying {
    private final ContextualProvider context;
    private final REST rest;
    private final String bearerToken;

    private final DataContainerCache<UUID, User> userCache = new DataContainerCache<>(User.ID);
    private final DataContainerCache<Long, Role> roleCache = new DataContainerCache<>(Role.ID);
    private final DataContainerCache<Long, Tag> tagCache = new DataContainerCache<>(Tag.ID);
    private final DataContainerCache<UUID, Image> imageCache = new DataContainerCache<>(Image.ID);
    private final DataContainerCache<Long, Instance> instanceCache = new DataContainerCache<>(Instance.ID);
    private final DataContainerCache<String, Snapshot> snapshotCache = new DataContainerCache<>(Snapshot.ID);
    private final DataContainerCache<Long, Secret> secretCache = new DataContainerCache<>(Secret.ID);

    @Override
    public ContextualProvider getUnderlyingContextualProvider() {
        return context;
    }

    public ContaboConnection(String accessToken) {
        this(null, accessToken);
    }

    public ContaboConnection(ContextualProvider context, String accessToken) {
        this.context = Polyfill.notnullOr(context, ContextualProvider.getRoot()).plus("ContaboConnection", this);
        this.context.addToContext(this.rest = new REST(this.context));
        this.bearerToken = "Bearer " + accessToken;
    }

    public CompletableFuture<Set<Instance>> requestInstances() {
        return request(Instance.Type, ContaboEndpoint.LIST_INSTANCES)
                .buildBody(BodyBuilderType.OBJECT, obj -> obj.put("size", Long.MAX_VALUE))
                .execute$body()
                .thenApply(Serializable::toUniNode)
                .thenApply(obj -> {
                    Set<Instance> yields = new HashSet<>();
                    for (UniNode each : obj.get("data"))
                        instanceCache.autoUpdate(each.asObjectNode()).into(yields::add);
                    return yields;
                });
    }

    public CompletableFuture<Instance> requestInstance(long id) {
        return request(Instance.Type, ContaboEndpoint.SPECIFIC_INSTANCE, id)
                .execute$autoCache(instanceCache, "data")
                .thenApply(refs -> refs.get(0));
    }

    public CompletableFuture<Set<Snapshot>> requestSnapshots(Instance instance) {
        return request(Snapshot.Type, ContaboEndpoint.INSTANCE_LIST_SNAPSHOTS, instance.getID())
                .buildBody(BodyBuilderType.OBJECT, obj -> obj.put("size", Long.MAX_VALUE))
                .execute$body()
                .thenApply(Serializable::toUniNode)
                .thenApply(obj -> {
                    Set<Snapshot> yields = new HashSet<>();
                    for (UniNode each : obj.get("data"))
                        snapshotCache.autoUpdate(each.asObjectNode()).into(yields::add);
                    return yields;
                });
    }

    public CompletableFuture<Snapshot> requestSnapshot(Instance instance, String id) {
        return requestSnapshot(instance.getID(), id);
    }

    public CompletableFuture<Snapshot> requestSnapshot(long instanceId, String id) {
        return request(Snapshot.Type, ContaboEndpoint.INSTANCE_SPECIFIC_SNAPSHOT, instanceId, id)
                .execute$autoCache(snapshotCache, "data")
                .thenApply(refs -> refs.get(0));
    }

    public CompletableFuture<Set<Image>> requestImages() {
        return request(Image.Type, ContaboEndpoint.LIST_IMAGES)
                .buildBody(BodyBuilderType.OBJECT, obj -> obj.put("size", Long.MAX_VALUE))
                .execute$body()
                .thenApply(Serializable::toUniNode)
                .thenApply(obj -> {
                    Set<Image> yields = new HashSet<>();
                    for (UniNode each : obj.get("data"))
                        imageCache.autoUpdate(each.asObjectNode()).into(yields::add);
                    return yields;
                });
    }

    public CompletableFuture<Image> requestImage(UUID id) {
        return request(Image.Type, ContaboEndpoint.SPECIFIC_IMAGE, id)
                .execute$autoCache(imageCache, "data")
                .thenApply(refs -> refs.get(0));
    }

    public CompletableFuture<Set<Tag>> requestTags() {
        return request(Tag.Type, ContaboEndpoint.LIST_TAGS)
                .buildBody(BodyBuilderType.OBJECT, obj -> obj.put("size", Long.MAX_VALUE))
                .execute$body()
                .thenApply(Serializable::toUniNode)
                .thenApply(obj -> {
                    Set<Tag> yields = new HashSet<>();
                    for (UniNode each : obj.get("data"))
                        tagCache.autoUpdate(each.asObjectNode()).into(yields::add);
                    return yields;
                });
    }

    public CompletableFuture<Tag> requestTag(long id) {
        return request(Tag.Type, ContaboEndpoint.SPECIFIC_TAG, id)
                .execute$autoCache(tagCache, "data")
                .thenApply(refs -> refs.get(0));
    }

    public CompletableFuture<Set<User>> requestUsers() {
        return request(User.Type, ContaboEndpoint.LIST_USERS)
                .buildBody(BodyBuilderType.OBJECT, obj -> obj.put("size", Long.MAX_VALUE))
                .execute$body()
                .thenApply(Serializable::toUniNode)
                .thenApply(obj -> {
                    Set<User> yields = new HashSet<>();
                    for (UniNode each : obj.get("data"))
                        userCache.autoUpdate(each.asObjectNode()).into(yields::add);
                    return yields;
                });
    }

    public CompletableFuture<User> requestUser(UUID id) {
        return request(User.Type, ContaboEndpoint.SPECIFIC_USER, id)
                .execute$autoCache(userCache, "data")
                .thenApply(refs -> refs.get(0));
    }

    public CompletableFuture<Set<Role>> requestRoles(RoleType type) {
        return request(Role.Type, ContaboEndpoint.LIST_ROLES, type.name())
                .buildBody(BodyBuilderType.OBJECT, obj -> obj.put("size", Long.MAX_VALUE))
                .execute$body()
                .thenApply(Serializable::toUniNode)
                .thenApply(obj -> {
                    Set<Role> yields = new HashSet<>();
                    for (UniNode each : obj.get("data"))
                        roleCache.autoUpdate(each.asObjectNode()).into(yields::add);
                    return yields;
                });
    }

    public CompletableFuture<Role> requestRole(RoleType type, long id) {
        return request(Role.Type, ContaboEndpoint.SPECIFIC_ROLE, type.name(), id)
                .execute$autoCache(roleCache, "data")
                .thenApply(refs -> refs.get(0));
    }

    public CompletableFuture<Set<Secret>> requestSecrets() {
        return request(Secret.Type, ContaboEndpoint.LIST_SECRETS)
                .buildBody(BodyBuilderType.OBJECT, obj -> obj.put("size", Long.MAX_VALUE))
                .execute$body()
                .thenApply(Serializable::toUniNode)
                .thenApply(obj -> {
                    Set<Secret> yields = new HashSet<>();
                    for (UniNode each : obj.get("data"))
                        secretCache.autoUpdate(each.asObjectNode()).into(yields::add);
                    return yields;
                });
    }

    public CompletableFuture<Secret> requestSecret(long id) {
        return request(Secret.Type, ContaboEndpoint.SPECIFIC_SECRET, id)
                .execute$autoCache(secretCache, "data")
                .thenApply(refs -> refs.get(0));
    }

    @Internal
    public REST.Request<UniNode> request(ContaboEndpoint endpoint, Object... args) {
        return request(UUID.randomUUID(), endpoint, args);
    }

    @Internal
    public REST.Request<UniNode> request(UUID requestId, ContaboEndpoint endpoint, Object... args) {
        return rest.request()
                .endpoint(endpoint, args)
                .addHeader(CommonHeaderNames.AUTHORIZATION, bearerToken)
                .addHeader("x-trace-id", requestId.toString());
    }

    @Internal
    public <T extends ContaboModel> REST.Request<T> request(GroupBind<T> type, ContaboEndpoint endpoint, Object... args) {
        return request(type, UUID.randomUUID(), endpoint, args);
    }

    @Internal
    public <T extends ContaboModel> REST.Request<T> request(GroupBind<T> type, UUID requestId, ContaboEndpoint endpoint, Object... args) {
        return rest.request(type)
                .endpoint(endpoint, args)
                .addHeader(CommonHeaderNames.AUTHORIZATION, bearerToken)
                .addHeader("x-trace-id", requestId.toString());
    }

    public void deleteSnapshot(String id) {
        snapshotCache.remove(id);
    }

    public void deleteImage(UUID id) {
        imageCache.remove(id);
    }

    public void deleteTag(long id) {
        tagCache.remove(id);
    }
}
