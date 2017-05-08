// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import java.net.URLEncoder;
import java.util.Locale;
import android.net.Uri;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import java.util.ArrayList;
import android.content.Intent;
import java.util.List;

public class Payload
{
    private static final String ACTION_HOME = "action=home&source=pn";
    private static final String BROWSE = "nflx://www.netflix.com/Browse?q=";
    public static final String DEFAULT_INFO_ACTION = "INFO";
    public static final String DEFAULT_SOCIAL_ACTION = "SOCIAL";
    public static final String DEFAULT_sound_KEY = "default";
    private static final String PARAM_ActionInfoType = "type";
    public static final String PARAM_GUID = "guid";
    public static final String PARAM_MESSAGE_GUID = "messageGuid";
    public static final String PARAM_ORIGINATOR = "originator";
    public static final String PARAM_RENO_CAUSE = "cause";
    public static final String PARAM_RENO_CREATION_TS = "creationTS";
    public static final String PARAM_RENO_MESSAGE_GUID = "messageGUID";
    public static final String PARAM_RENO_PAYLOAD = "invisiblePayload";
    private static final String PARAM_actionIcon = "actionIcon.";
    private static final String PARAM_actionKey = "actionKey.";
    private static final String PARAM_actionPayload = "actionPayload.";
    private static final String PARAM_actionText = "actionText.";
    private static final String PARAM_bigViewPicture = "bigViewPicture";
    private static final String PARAM_bigViewSummary = "bigViewSummary";
    private static final String PARAM_bigViewText = "bigViewText";
    private static final String PARAM_bigViewTitle = "bigViewTitle";
    private static final String PARAM_defaultActionKey = "defaultActionKey";
    private static final String PARAM_defaultActionPayload = "defaultActionPayload";
    private static final String PARAM_info = "info";
    private static final String PARAM_largeIcon = "largeIcon";
    private static final String PARAM_ledColor = "ledColor";
    private static final String PARAM_profileGuid = "profileId";
    private static final String PARAM_smallIcon = "smallIcon";
    private static final String PARAM_sound = "sound";
    private static final String PARAM_subtext = "subtext";
    private static final String PARAM_text = "text";
    private static final String PARAM_ticker = "ticker";
    private static final String PARAM_title = "title";
    private static final String PARAM_vibrate = "vibrate";
    private static final String PARAM_when = "when";
    private static final String PROTOCOL_HTTP = "http://";
    private static final String PROTOCOL_HTTPS = "https://";
    private static final String PROTOCOL_NFLX = "nflx://";
    private static final String TAG = "nf_push";
    private static final String TARGET_URL = "target_url=";
    public String actionInfoType;
    protected List<Payload$Action> actions;
    public String bigViewPicture;
    public String bigViewSummary;
    public String bigViewText;
    public String bigViewTitle;
    public String defaultActionKey;
    private String defaultActionPayload;
    public String guid;
    public String info;
    public String largeIcon;
    public int ledColor;
    public String messageGuid;
    public String originator;
    public String profileGuid;
    public String renoCause;
    public long renoCreationTimestamp;
    public String renoMessageGuid;
    public String smallIcon;
    public String sound;
    public String subtext;
    public String text;
    private String ticker;
    private String title;
    public String vibrate;
    private long when;
    
    public Payload(final Intent intent) {
        this.actions = new ArrayList<Payload$Action>();
        if (intent.hasExtra("bigViewPicture")) {
            this.bigViewPicture = intent.getStringExtra("bigViewPicture");
        }
        if (intent.hasExtra("bigViewSummary")) {
            this.bigViewSummary = intent.getStringExtra("bigViewSummary");
        }
        if (intent.hasExtra("bigViewText")) {
            this.bigViewText = intent.getStringExtra("bigViewText");
        }
        if (intent.hasExtra("bigViewTitle")) {
            this.bigViewTitle = intent.getStringExtra("bigViewTitle");
        }
        if (intent.hasExtra("defaultActionKey")) {
            this.defaultActionKey = intent.getStringExtra("defaultActionKey");
        }
        if (intent.hasExtra("defaultActionPayload")) {
            this.defaultActionPayload = intent.getStringExtra("defaultActionPayload");
        }
        if (intent.hasExtra("info")) {
            this.info = intent.getStringExtra("info");
        }
        if (intent.hasExtra("largeIcon")) {
            this.largeIcon = intent.getStringExtra("largeIcon");
        }
        if (intent.hasExtra("smallIcon")) {
            this.smallIcon = intent.getStringExtra("smallIcon");
        }
    Label_0522:
        while (true) {
        Label_0783_Outer:
            while (true) {
                Label_0372: {
                    long long1 = 0L;
                    Label_0367: {
                        Object o;
                        while (true) {
                            Label_0222: {
                                if (!intent.hasExtra("ledColor")) {
                                    break Label_0222;
                                }
                                o = intent.getStringExtra("ledColor");
                                int int1 = 0;
                                Label_0217: {
                                    if (StringUtils.isNumeric((String)o)) {
                                        int1 = Integer.parseInt((String)o);
                                        break Label_0217;
                                    }
                                    Label_0778: {
                                        break Label_0778;
                                        while (true) {
                                            while (true) {
                                                Label_0831: {
                                                    Label_0816: {
                                                        try {
                                                            final JSONObject jsonObject = new JSONObject((String)o);
                                                            this.renoCause = JsonUtils.getString(jsonObject, "cause", (String)null);
                                                            this.renoMessageGuid = JsonUtils.getString(jsonObject, "messageGUID", (String)null);
                                                            this.renoCreationTimestamp = JsonUtils.getLong(jsonObject, "creationTS", System.currentTimeMillis());
                                                            this.originator = extractOriginator(this.defaultActionPayload);
                                                            int i = 0;
                                                            while (i > -1) {
                                                                final String string = "actionKey." + i;
                                                                if (!intent.hasExtra(string)) {
                                                                    break Label_0831;
                                                                }
                                                                o = new Payload$Action(this.guid);
                                                                ((Payload$Action)o).key = intent.getStringExtra(string);
                                                                final String string2 = "actionIcon." + i;
                                                                if (intent.hasExtra(string2)) {
                                                                    ((Payload$Action)o).icon = intent.getStringExtra(string2);
                                                                }
                                                                final String string3 = "actionPayload." + i;
                                                                if (intent.hasExtra(string3)) {
                                                                    ((Payload$Action)o).payload = intent.getStringExtra(string3);
                                                                }
                                                                final String string4 = "actionText." + i;
                                                                if (intent.hasExtra(string4)) {
                                                                    ((Payload$Action)o).text = intent.getStringExtra(string4);
                                                                }
                                                                ++i;
                                                                if (((Payload$Action)o).payload != null && ((Payload$Action)o).text != null && Payload$Action.isSupportedActionKey(((Payload$Action)o).key)) {
                                                                    break Label_0816;
                                                                }
                                                                Log.e("nf_push", "Invalid action: " + o);
                                                            }
                                                            break;
                                                            long1 = 0L;
                                                            break Label_0367;
                                                            int1 = 0;
                                                            break Label_0217;
                                                        }
                                                        catch (JSONException ex) {
                                                            Log.e("nf_push", String.format("invalid renoPayload %s", o), (Throwable)ex);
                                                            continue Label_0522;
                                                        }
                                                    }
                                                    this.actions.add((Payload$Action)o);
                                                    continue Label_0783_Outer;
                                                }
                                                int i = -1;
                                                continue Label_0783_Outer;
                                            }
                                        }
                                    }
                                    return;
                                }
                                this.ledColor = int1;
                            }
                            if (intent.hasExtra("sound")) {
                                this.sound = intent.getStringExtra("sound");
                            }
                            if (intent.hasExtra("subtext")) {
                                this.subtext = intent.getStringExtra("subtext");
                            }
                            if (intent.hasExtra("text")) {
                                this.text = intent.getStringExtra("text");
                            }
                            if (intent.hasExtra("ticker")) {
                                this.ticker = intent.getStringExtra("ticker");
                            }
                            if (intent.hasExtra("title")) {
                                this.title = intent.getStringExtra("title");
                            }
                            if (intent.hasExtra("vibrate")) {
                                this.vibrate = intent.getStringExtra("vibrate");
                            }
                            if (!intent.hasExtra("when")) {
                                break Label_0372;
                            }
                            o = intent.getStringExtra("when");
                            if (!StringUtils.isNumeric((String)o)) {
                                continue;
                            }
                            break;
                        }
                        long1 = Long.parseLong((String)o);
                    }
                    this.when = long1;
                }
                if (intent.hasExtra("guid")) {
                    this.guid = intent.getStringExtra("guid");
                }
                if (intent.hasExtra("messageGuid")) {
                    this.messageGuid = intent.getStringExtra("messageGuid");
                }
                if (intent.hasExtra("profileId")) {
                    this.profileGuid = intent.getStringExtra("profileId");
                }
                if (intent.hasExtra("type")) {
                    this.actionInfoType = intent.getStringExtra("type");
                }
                if (!intent.hasExtra("invisiblePayload")) {
                    continue Label_0522;
                }
                Object o = intent.getStringExtra("invisiblePayload");
                if (StringUtils.isNotEmpty((String)o)) {
                    continue Label_0783_Outer;
                }
                break;
            }
            continue Label_0522;
        }
    }
    
    private static String extractOriginator(String substring) {
        if (substring != null) {
            final int index = substring.toLowerCase(Locale.ENGLISH).indexOf("source=");
            if (index >= 0) {
                final int n = index + 7;
                if (n < substring.length()) {
                    final int n2 = index - 1;
                    if (n2 >= 0) {
                        final char char1 = substring.charAt(n2);
                        if (char1 == '?' || char1 == '&') {
                            substring = substring.substring(n);
                            final int index2 = substring.indexOf(38);
                            if (index2 < 0) {
                                return substring.trim();
                            }
                            return substring.substring(0, index2);
                        }
                        else {
                            final int n3 = index + 7;
                            if (n3 < substring.length()) {
                                return extractOriginator(substring.substring(n3));
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public static String isValid(final Payload payload) {
        String s = null;
        if ("INFO".equals(payload.defaultActionKey)) {
            if (StringUtils.isEmpty(payload.actionInfoType)) {
                s = "Payload:: missing fields in INFO !";
            }
        }
        else {
            if (StringUtils.isEmpty(payload.title)) {
                return "Payload:: title is missing!";
            }
            if (StringUtils.isEmpty(payload.text)) {
                return "Payload:: text is missing!";
            }
            if (StringUtils.isEmpty(payload.defaultActionKey)) {
                return "Payload:: defaultActionPayload is missing!";
            }
        }
        return s;
    }
    
    private static Uri parsePayload(String s, final String s2) {
        if (StringUtils.isEmpty(s)) {
            Log.d("nf_push", "Empty payload, return URI that will launch our application to HOME page");
            s = "nflx://www.netflix.com/Browse?q=" + URLEncoder.encode("action=home&source=pn", "UTF-8");
            if (Log.isLoggable()) {
                Log.d("nf_push", "Payload for home page " + s);
            }
            return Uri.parse(s);
        }
        if (s2 != null && "CUSTOM".equalsIgnoreCase(s2.trim())) {
            if (Log.isLoggable()) {
                Log.d("nf_push", "Custom action. Just pass through " + s);
            }
            return Uri.parse(s);
        }
        if (s.toLowerCase(Locale.US).startsWith("nflx://")) {
            if (Log.isLoggable()) {
                Log.d("nf_push", "Payload using nflx. Just pass through: " + s);
            }
            s = "nflx://www.netflix.com/Browse?q=" + URLEncoder.encode("target_url=" + s, "UTF-8");
            if (Log.isLoggable()) {
                Log.d("nf_push", "Payload for MDP page " + s);
            }
            return Uri.parse(s);
        }
        if (s.toLowerCase(Locale.US).startsWith("https://")) {
            if (Log.isLoggable()) {
                Log.d("nf_push", "Payload using https : " + s + ". Just pass through.");
            }
            return Uri.parse(s);
        }
        if (s.toLowerCase(Locale.US).startsWith("http://")) {
            if (Log.isLoggable()) {
                Log.d("nf_push", "Payload using http : " + s);
            }
            s = "nflx://" + s.substring("http://".length());
            s = "nflx://www.netflix.com/Browse?q=" + URLEncoder.encode("target_url=" + s, "UTF-8");
            if (Log.isLoggable()) {
                Log.d("nf_push", "Payload for MDP page " + s);
            }
            return Uri.parse(s);
        }
        Log.d("nf_push", "Unsuported protocol, return URI that will launch our application to HOME page");
        s = "nflx://www.netflix.com/Browse?q=" + URLEncoder.encode("action=home&source=pn", "UTF-8");
        if (Log.isLoggable()) {
            Log.d("nf_push", "Payload for home page " + s);
        }
        return Uri.parse(s);
    }
    
    public Payload$Action[] getActions() {
        return this.actions.toArray(new Payload$Action[this.actions.size()]);
    }
    
    public Uri getDefaultActionPayload() {
        try {
            return parsePayload(this.defaultActionPayload, this.defaultActionKey);
        }
        catch (Throwable t) {
            Log.e("nf_push", "defaultActionPayload URI is wrong: " + this.defaultActionPayload, t);
            return null;
        }
    }
    
    public String getTicker(String ticker) {
        if (this.ticker != null) {
            ticker = this.ticker;
        }
        return ticker;
    }
    
    public String getTitle(String title) {
        if (this.title != null) {
            title = this.title;
        }
        return title;
    }
    
    public long getWhen() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.when >= currentTimeMillis) {
            final long when = this.when;
            if (Log.isLoggable()) {
                Log.d("nf_push", "Post notification later: " + when);
            }
            return when;
        }
        Log.d("nf_push", "Post notification now!");
        return currentTimeMillis;
    }
    
    @Override
    public String toString() {
        return "Payload [title=" + this.title + ", text=" + this.text + ", subtext=" + this.subtext + ", info=" + this.info + ", ticker=" + this.ticker + ", ledColor=" + this.ledColor + ", sound=" + this.sound + ", vibrate=" + this.vibrate + ", when=" + this.when + ", smallIcon=" + this.smallIcon + ", largeIcon=" + this.largeIcon + ", bigViewText=" + this.bigViewText + ", bigViewPicture=" + this.bigViewPicture + ", bigViewTitle=" + this.bigViewTitle + ", bigViewSummary=" + this.bigViewSummary + ", defaultActionKey=" + this.defaultActionKey + ", defaultActionPayload=" + this.defaultActionPayload + ", actions=" + this.actions + ", guid=" + this.guid + ", messageGuid=" + this.messageGuid + "]";
    }
}
