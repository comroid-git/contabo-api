package org.comroid.contabo;

import org.comroid.api.ContextualProvider;
import org.comroid.api.Polyfill;
import org.comroid.contabo.model.ContaboModel;
import org.comroid.contabo.rest.ContaboEndpoint;
import org.comroid.restless.REST;
import org.comroid.varbind.bind.GroupBind;

public final class ContaboConnection implements ContextualProvider.Underlying {
    private final ContextualProvider context;
    private final REST rest;
    private final String bearerToken;

    public ContaboConnection(String accessToken) {
        this(null, accessToken);
    }

    public ContaboConnection(ContextualProvider context, String accessToken) {
        this.context = Polyfill.notnullOr(context, ContextualProvider.getRoot());
        this.rest = new REST(this.context);

        this.bearerToken = "Bearer " + accessToken;
    }

    private <T, C extends ContaboModel> REST.Request<T> request(Class<C> type, ContaboEndpoint endpoint, Object... args) {
        return rest.request(GroupBind.find(type))
    }

    @Override
    public ContextualProvider getUnderlyingContextualProvider() {
        return context;
    }
}
