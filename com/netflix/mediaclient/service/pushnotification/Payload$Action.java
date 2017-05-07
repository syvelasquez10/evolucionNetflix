// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import java.net.URLEncoder;
import java.util.Locale;
import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import java.util.ArrayList;
import android.content.Intent;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.net.Uri;

public class Payload$Action
{
    public static final String ADD2QUEUE = "ADD2QUEUE";
    public static final String CUSTOM = "CUSTOM";
    public static final String MDP = "MDP";
    public static final String PLAY = "PLAY";
    public String guid;
    public String icon;
    public String key;
    public String payload;
    public String text;
    
    public Payload$Action(final String guid) {
        this.guid = guid;
    }
    
    public static boolean isSupportedActionKey(final String s) {
        return s != null && ("MDP".equals(s) || "PLAY".equals(s) || "ADD2QUEUE".equals(s) || "CUSTOM".equals(s));
    }
    
    public int getIcon() {
        return 2130837746;
    }
    
    public Uri getPayload() {
        try {
            return parsePayload(this.payload, this.key);
        }
        catch (Throwable t) {
            Log.e("nf_push", "Action.Payload URI is wrong: " + this.payload, t);
            return null;
        }
    }
    
    @Override
    public String toString() {
        return "Action [key=" + this.key + ", text=" + this.text + ", payload=" + this.payload + ", icon=" + this.icon + "]";
    }
}
