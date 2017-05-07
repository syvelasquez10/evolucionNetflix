// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import java.util.Locale;
import java.net.URLEncoder;
import com.netflix.mediaclient.util.StringUtils;
import java.io.UnsupportedEncodingException;
import android.net.Uri;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import android.content.Intent;
import java.util.List;

public class Payload
{
    private static final String ACTION_HOME = "action=home&source=pn";
    private static final String BROWSE = "nflx://www.netflix.com/Browse?q=";
    public static final String DEFAULT_sound_KEY = "default";
    public static final String PARAM_GUID = "guid";
    public static final String PARAM_MESSAGE_GUID = "messageGuid";
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
    protected List<Action> actions;
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
    public String smallIcon;
    public String sound;
    public String subtext;
    public String text;
    private String ticker;
    private String title;
    public String vibrate;
    private long when;
    
    public Payload(final Intent intent) {
        this.actions = new ArrayList<Action>();
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
        if (intent.hasExtra("ledColor")) {
            this.ledColor = intent.getIntExtra("ledColor", 0);
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
        if (intent.hasExtra("when")) {
            this.when = intent.getLongExtra("when", 0L);
        }
        if (intent.hasExtra("guid")) {
            this.guid = intent.getStringExtra("guid");
        }
        if (intent.hasExtra("messageGuid")) {
            this.messageGuid = intent.getStringExtra("messageGuid");
        }
        int i = 0;
        while (i > -1) {
            final String string = "actionKey." + i;
            if (intent.hasExtra(string)) {
                final Action action = new Action(this.guid);
                action.key = intent.getStringExtra(string);
                final String string2 = "actionIcon." + i;
                if (intent.hasExtra(string2)) {
                    action.icon = intent.getStringExtra(string2);
                }
                final String string3 = "actionPayload." + i;
                if (intent.hasExtra(string3)) {
                    action.payload = intent.getStringExtra(string3);
                }
                final String string4 = "actionText." + i;
                if (intent.hasExtra(string4)) {
                    action.text = intent.getStringExtra(string4);
                }
                ++i;
                if (action.payload == null || action.text == null || !Action.isSupportedActionKey(action.key)) {
                    Log.e("nf_push", "Invalid action: " + action);
                }
                else {
                    this.actions.add(action);
                }
            }
            else {
                i = -1;
            }
        }
        this.validate();
    }
    
    private static Uri parsePayload(String s, final String s2) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(s)) {
            Log.d("nf_push", "Empty payload, return URI that will launch our application to HOME page");
            s = "nflx://www.netflix.com/Browse?q=" + URLEncoder.encode("action=home&source=pn", "UTF-8");
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "Payload for home page " + s);
            }
            return Uri.parse(s);
        }
        if (s2 != null && "CUSTOM".equalsIgnoreCase(s2.trim())) {
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "Custom action. Just pass through " + s);
            }
            return Uri.parse(s);
        }
        if (s.toLowerCase(Locale.US).startsWith("nflx://")) {
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "Payload using nflx. Just pass through: " + s);
            }
            s = "nflx://www.netflix.com/Browse?q=" + URLEncoder.encode("target_url=" + s, "UTF-8");
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "Payload for MDP page " + s);
            }
            return Uri.parse(s);
        }
        if (s.toLowerCase(Locale.US).startsWith("https://")) {
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "Payload using https : " + s + ". Just pass through.");
            }
            return Uri.parse(s);
        }
        if (s.toLowerCase(Locale.US).startsWith("http://")) {
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "Payload using http : " + s);
            }
            s = "nflx://" + s.substring("http://".length());
            s = "nflx://www.netflix.com/Browse?q=" + URLEncoder.encode("target_url=" + s, "UTF-8");
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "Payload for MDP page " + s);
            }
            return Uri.parse(s);
        }
        Log.d("nf_push", "Unsuported protocol, return URI that will launch our application to HOME page");
        s = "nflx://www.netflix.com/Browse?q=" + URLEncoder.encode("action=home&source=pn", "UTF-8");
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "Payload for home page " + s);
        }
        return Uri.parse(s);
    }
    
    private void validate() {
        if (this.title == null) {
            throw new IllegalArgumentException("Payload:: title is missing!");
        }
        if (this.text == null) {
            throw new IllegalArgumentException("Payload:: text is missing!");
        }
        if (this.defaultActionPayload == null) {
            throw new IllegalArgumentException("Payload:: defaultActionPayload is missing!");
        }
    }
    
    public Action[] getActions() {
        return this.actions.toArray(new Action[this.actions.size()]);
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
            if (Log.isLoggable("nf_push", 3)) {
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
    
    public static class Action
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
        
        public Action(final String guid) {
            this.guid = guid;
        }
        
        public static boolean isSupportedActionKey(final String s) {
            return s != null && ("MDP".equals(s) || "PLAY".equals(s) || "ADD2QUEUE".equals(s) || "CUSTOM".equals(s));
        }
        
        public int getIcon() {
            return 2130837603;
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
}
