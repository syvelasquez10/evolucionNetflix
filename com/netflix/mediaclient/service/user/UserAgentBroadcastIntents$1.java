// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import java.util.HashSet;

final class UserAgentBroadcastIntents$1 extends HashSet<String>
{
    UserAgentBroadcastIntents$1() {
        this.add("com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_NOT_LOGGED_IN");
        this.add("com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_ACTIVE");
        this.add("com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_DEACTIVE");
        this.add("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_ACTIVE");
        this.add("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_DEACTIVE");
        this.add("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_READY_TO_SELECT");
        this.add("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_SELECTION_RESULT");
        this.add("com.netflix.mediaclient.intent.action.NOTIFY_PROFILES_LIST_UPDATED");
        this.add("com.netflix.mediaclient.intent.action.NOTIFY_CURRENT_PROFILE_INVALID");
    }
}
