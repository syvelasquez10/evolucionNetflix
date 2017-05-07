// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import org.json.JSONArray;
import com.google.android.gms.internal.ik;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaStatus
{
    public static final long COMMAND_PAUSE = 1L;
    public static final long COMMAND_SEEK = 2L;
    public static final long COMMAND_SET_VOLUME = 4L;
    public static final long COMMAND_SKIP_BACKWARD = 32L;
    public static final long COMMAND_SKIP_FORWARD = 16L;
    public static final long COMMAND_TOGGLE_MUTE = 8L;
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_ERROR = 4;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_NONE = 0;
    public static final int PLAYER_STATE_BUFFERING = 4;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int PLAYER_STATE_UNKNOWN = 0;
    private double FA;
    private boolean FB;
    private long[] FC;
    private JSONObject Fl;
    private MediaInfo Fm;
    private long Fu;
    private double Fv;
    private int Fw;
    private int Fx;
    private long Fy;
    private long Fz;
    
    public MediaStatus(final JSONObject jsonObject) throws JSONException {
        this.a(jsonObject, 0);
    }
    
    public int a(JSONObject jsonObject, int i) throws JSONException {
        final int n = 2;
        final boolean b = false;
        final int n2 = 1;
        final long long1 = jsonObject.getLong("mediaSessionId");
        boolean b2;
        if (long1 != this.Fu) {
            this.Fu = long1;
            b2 = true;
        }
        else {
            b2 = false;
        }
        int n3 = b2 ? 1 : 0;
        if (jsonObject.has("playerState")) {
            final String string = jsonObject.getString("playerState");
            int fw;
            if (string.equals("IDLE")) {
                fw = 1;
            }
            else if (string.equals("PLAYING")) {
                fw = 2;
            }
            else if (string.equals("PAUSED")) {
                fw = 3;
            }
            else if (string.equals("BUFFERING")) {
                fw = 4;
            }
            else {
                fw = 0;
            }
            int n4 = b2 ? 1 : 0;
            if (fw != this.Fw) {
                this.Fw = fw;
                n4 = ((b2 ? 1 : 0) | 0x2);
            }
            n3 = n4;
            if (fw == 1) {
                n3 = n4;
                if (jsonObject.has("idleReason")) {
                    final String string2 = jsonObject.getString("idleReason");
                    int fx;
                    if (string2.equals("CANCELLED")) {
                        fx = n;
                    }
                    else if (string2.equals("INTERRUPTED")) {
                        fx = 3;
                    }
                    else if (string2.equals("FINISHED")) {
                        fx = 1;
                    }
                    else if (string2.equals("ERROR")) {
                        fx = 4;
                    }
                    else {
                        fx = 0;
                    }
                    n3 = n4;
                    if (fx != this.Fx) {
                        this.Fx = fx;
                        n3 = (n4 | 0x2);
                    }
                }
            }
        }
        int n5 = n3;
        if (jsonObject.has("playbackRate")) {
            final double double1 = jsonObject.getDouble("playbackRate");
            n5 = n3;
            if (this.Fv != double1) {
                this.Fv = double1;
                n5 = (n3 | 0x2);
            }
        }
        int n6 = n5;
        if (jsonObject.has("currentTime")) {
            n6 = n5;
            if ((i & 0x2) == 0x0) {
                final long b3 = ik.b(jsonObject.getDouble("currentTime"));
                n6 = n5;
                if (b3 != this.Fy) {
                    this.Fy = b3;
                    n6 = (n5 | 0x2);
                }
            }
        }
        int n7 = n6;
        if (jsonObject.has("supportedMediaCommands")) {
            final long long2 = jsonObject.getLong("supportedMediaCommands");
            n7 = n6;
            if (long2 != this.Fz) {
                this.Fz = long2;
                n7 = (n6 | 0x2);
            }
        }
        int n8 = n7;
        if (jsonObject.has("volume")) {
            n8 = n7;
            if ((i & 0x1) == 0x0) {
                final JSONObject jsonObject2 = jsonObject.getJSONObject("volume");
                final double double2 = jsonObject2.getDouble("level");
                i = n7;
                if (double2 != this.FA) {
                    this.FA = double2;
                    i = (n7 | 0x2);
                }
                final boolean boolean1 = jsonObject2.getBoolean("muted");
                n8 = i;
                if (boolean1 != this.FB) {
                    this.FB = boolean1;
                    n8 = (i | 0x2);
                }
            }
        }
        long[] array;
        int n9;
        if (jsonObject.has("activeTrackIds")) {
            final JSONArray jsonArray = jsonObject.getJSONArray("activeTrackIds");
            final int length = jsonArray.length();
            array = new long[length];
            for (i = 0; i < length; ++i) {
                array[i] = jsonArray.getLong(i);
            }
            Label_0570: {
                if (this.FC == null) {
                    i = n2;
                }
                else {
                    i = n2;
                    if (this.FC.length == length) {
                        for (int j = 0; j < length; ++j) {
                            i = n2;
                            if (this.FC[j] != array[j]) {
                                break Label_0570;
                            }
                        }
                        i = 0;
                    }
                }
            }
            if (i != 0) {
                this.FC = array;
            }
            n9 = i;
        }
        else if (this.FC != null) {
            n9 = 1;
            array = null;
        }
        else {
            array = null;
            n9 = (b ? 1 : 0);
        }
        i = n8;
        if (n9 != 0) {
            this.FC = array;
            i = (n8 | 0x2);
        }
        int n10 = i;
        if (jsonObject.has("customData")) {
            this.Fl = jsonObject.getJSONObject("customData");
            n10 = (i | 0x2);
        }
        i = n10;
        if (jsonObject.has("media")) {
            jsonObject = jsonObject.getJSONObject("media");
            this.Fm = new MediaInfo(jsonObject);
            final int n11 = i = (n10 | 0x2);
            if (jsonObject.has("metadata")) {
                i = (n11 | 0x4);
            }
        }
        return i;
    }
    
    public long fx() {
        return this.Fu;
    }
    
    public long[] getActiveTrackIds() {
        return this.FC;
    }
    
    public JSONObject getCustomData() {
        return this.Fl;
    }
    
    public int getIdleReason() {
        return this.Fx;
    }
    
    public MediaInfo getMediaInfo() {
        return this.Fm;
    }
    
    public double getPlaybackRate() {
        return this.Fv;
    }
    
    public int getPlayerState() {
        return this.Fw;
    }
    
    public long getStreamPosition() {
        return this.Fy;
    }
    
    public double getStreamVolume() {
        return this.FA;
    }
    
    public boolean isMediaCommandSupported(final long n) {
        return (this.Fz & n) != 0x0L;
    }
    
    public boolean isMute() {
        return this.FB;
    }
}
