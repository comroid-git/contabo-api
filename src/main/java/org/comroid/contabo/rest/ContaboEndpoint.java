package org.comroid.contabo.rest;

import org.comroid.contabo.ContaboAPI;
import org.comroid.restless.endpoint.AccessibleEndpoint;
import org.comroid.util.RegExpUtil;
import org.intellij.lang.annotations.Language;

import java.util.regex.Pattern;

public enum ContaboEndpoint implements AccessibleEndpoint {
    // region Instances Endpoints
    // instances
    LIST_INSTANCES("/compute/instances"),
    SPECIFIC_INSTANCE("/compute/instances/%s", RegExpUtil.INTEGER_PATTERN),

    // instance actions
    CANCEL_INSTANCE("/compute/instances/%s/cancel", RegExpUtil.INTEGER_PATTERN),
    START_INSTANCE("/compute/instances/%s/actions/start", RegExpUtil.INTEGER_PATTERN),
    RESTART_INSTANCE("/compute/instances/%s/actions/restart", RegExpUtil.INTEGER_PATTERN),
    STOP_INSTANCE("/compute/instances/%s/actions/stop", RegExpUtil.INTEGER_PATTERN),

    // instance snapshots
    INSTANCE_LIST_SNAPSHOTS("/compute/instances/%s/snapshots", RegExpUtil.INTEGER_PATTERN),
    INSTANCE_SPECIFIC_SNAPSHOT("/compute/instances/%s/snapshots/%s", RegExpUtil.INTEGER_PATTERN, RegExpUtil.ANYTHING_PATTERN),
    INSTANCE_SNAPSHOT_ROLLBACK("/compute/instances/%s/snapshots/%s/rollback", RegExpUtil.INTEGER_PATTERN, RegExpUtil.ANYTHING_PATTERN),

    // instance audits
    INSTANCE_AUDITS("/compute/instances/audits"),
    INSTANCE_ACTION_AUDITS("/compute/instances/actions/audits"),
    INSTANCE_SNAPSHOT_AUDITS("/compute/instances/snapshots/audits"),
    // endregion

    // region Images Endpoints
    // images
    LIST_IMAGES("/compute/images"),
    SPECIFIC_IMAGE("/compute/images/%s", RegExpUtil.ANYTHING_PATTERN),
    IMAGE_STATISTICS("/compute/images/stats"),

    // image audits,
    IMAGE_AUDITS("/compute/images/audits"),
    // endregion

    // region Tags Endpoints
    // tags
    LIST_TAGS("/tags"),
    SPECIFIC_TAG("/tags/%s", RegExpUtil.INTEGER_PATTERN),

    // tag assignments,
    TAG_LIST_ASSIGNMENTS("/tags/%s/assignments", RegExpUtil.INTEGER_PATTERN),
    TAG_SPECIFIC_ASSIGNMENT("/tags/%s/assignments/%s/%s", RegExpUtil.INTEGER_PATTERN, RegExpUtil.ANYTHING_PATTERN, RegExpUtil.ANYTHING_PATTERN),

    // tag audits
    TAG_AUDITS("/tags/audits"),
    TAG_ASSIGNMENT_AUDITS("/tags/assignments/audits"),
    // endregion

    // region Users Endpoints
    // users
    LIST_USERS("/users"),
    SPECIFIC_USER("/users/%s", RegExpUtil.UUID4_PATTERN),

    USER_RESET_PASSWORD("/users/%s/reset-password", RegExpUtil.UUID4_PATTERN),
    USER_RESEND_EMAIL_VERIFICATION("/users/%s/resend-email-verification", RegExpUtil.UUID4_PATTERN),

    USERS_IDM_CLIENT("/users/client"),
    USERS_IDM_CLIENT_SECRET("/users/client/secret"),

    // roles,
    LIST_ROLES("/roles/%s", RegExpUtil.ANYTHING_PATTERN),
    SPECIFIC_ROLE("/roles/%s/%s", RegExpUtil.ANYTHING_PATTERN, RegExpUtil.INTEGER_PATTERN),

    ROLES_API_PERMISSIONS("/roles/api-permissions"),

    // user audits
    USER_AUDITS("/users/audits"),
    ROLE_AUDITS("/roles/audits"),
    // endregion

    // region Secrets Endpoints
    // secrets
    LIST_SECRETS("/secrets"),
    SPECIFIC_SECRET("/secrets/%s", RegExpUtil.INTEGER_PATTERN),

    // secret audits
    SECRET_AUDITS("/secrets/audits"),
    // endregion
    ;

    public static final String URL_BASE = "https://api.contabo.com/v" + ContaboAPI.VERSION;
    private final String extension;
    private final String[] regExp;
    private final Pattern pattern;

    @Override
    public String getUrlBase() {
        return URL_BASE;
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }

    @Override
    public String getUrlExtension() {
        return extension;
    }

    @Override
    public String[] getRegExpGroups() {
        return regExp;
    }

    ContaboEndpoint(String extension, @Language("RegExp") String... regExp) {
        this.extension = extension;
        this.regExp = regExp;
        this.pattern = buildUrlPattern();
    }
}
