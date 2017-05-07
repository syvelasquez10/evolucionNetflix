// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.internal.dh;
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
    private JSONObject kM;
    private MediaInfo kN;
    private long kV;
    private double kW;
    private int kX;
    private int kY;
    private long kZ;
    private long la;
    private double lb;
    private boolean lc;
    
    public MediaStatus(final JSONObject jsonObject) throws JSONException {
        this.a(jsonObject, 0);
    }
    
    public int a(JSONObject jsonObject, int n) throws JSONException {
        final int n2 = 2;
        final long long1 = jsonObject.getLong("mediaSessionId");
        boolean b;
        if (long1 != this.kV) {
            this.kV = long1;
            b = true;
        }
        else {
            b = false;
        }
        int n3 = b ? 1 : 0;
        if (jsonObject.has("playerState")) {
            final String string = jsonObject.getString("playerState");
            int kx;
            if (string.equals("IDLE")) {
                kx = 1;
            }
            else if (string.equals("PLAYING")) {
                kx = 2;
            }
            else if (string.equals("PAUSED")) {
                kx = 3;
            }
            else if (string.equals("BUFFERING")) {
                kx = 4;
            }
            else {
                kx = 0;
            }
            int n4 = b ? 1 : 0;
            if (kx != this.kX) {
                this.kX = kx;
                n4 = ((b ? 1 : 0) | 0x2);
            }
            n3 = n4;
            if (kx == 1) {
                n3 = n4;
                if (jsonObject.has("idleReason")) {
                    final String string2 = jsonObject.getString("idleReason");
                    int ky;
                    if (string2.equals("CANCELLED")) {
                        ky = n2;
                    }
                    else if (string2.equals("INTERRUPTED")) {
                        ky = 3;
                    }
                    else if (string2.equals("FINISHED")) {
                        ky = 1;
                    }
                    else if (string2.equals("ERROR")) {
                        ky = 4;
                    }
                    else {
                        ky = 0;
                    }
                    n3 = n4;
                    if (ky != this.kY) {
                        this.kY = ky;
                        n3 = (n4 | 0x2);
                    }
                }
            }
        }
        int n5 = n3;
        if (jsonObject.has("playbackRate")) {
            final double double1 = jsonObject.getDouble("playbackRate");
            n5 = n3;
            if (this.kW != double1) {
                this.kW = double1;
                n5 = (n3 | 0x2);
            }
        }
        int n6 = n5;
        if (jsonObject.has("currentTime")) {
            n6 = n5;
            if ((n & 0x2) == 0x0) {
                final long b2 = dh.b(jsonObject.getDouble("currentTime"));
                n6 = n5;
                if (b2 != this.kZ) {
                    this.kZ = b2;
                    n6 = (n5 | 0x2);
                }
            }
        }
        int n7 = n6;
        if (jsonObject.has("supportedMediaCommands")) {
            final long long2 = jsonObject.getLong("supportedMediaCommands");
            n7 = n6;
            if (long2 != this.la) {
                this.la = long2;
                n7 = (n6 | 0x2);
            }
        }
        int n8 = n7;
        if (jsonObject.has("volume")) {
            n8 = n7;
            if ((n & 0x1) == 0x0) {
                final JSONObject jsonObject2 = jsonObject.getJSONObject("volume");
                final double double2 = jsonObject2.getDouble("level");
                n = n7;
                if (double2 != this.lb) {
                    this.lb = double2;
                    n = (n7 | 0x2);
                }
                final boolean boolean1 = jsonObject2.getBoolean("muted");
                n8 = n;
                if (boolean1 != this.lc) {
                    this.lc = boolean1;
                    n8 = (n | 0x2);
                }
            }
        }
        n = n8;
        if (jsonObject.has("customData")) {
            this.kM = jsonObject.getJSONObject("customData");
            n = (n8 | 0x2);
        }
        int n9 = n;
        if (jsonObject.has("media")) {
            jsonObject = jsonObject.getJSONObject("media");
            this.kN = new MediaInfo(jsonObject);
            n = (n9 = (n | 0x2));
            if (jsonObject.has("metadata")) {
                n9 = (n | 0x4);
            }
        }
        return n9;
    }
    
    public long aQ() {
        return this.kV;
    }
    
    public JSONObject getCustomData() {
        return this.kM;
    }
    
    public int getIdleReason() {
        return this.kY;
    }
    
    public MediaInfo getMediaInfo() {
        return this.kN;
    }
    
    public double getPlaybackRate() {
        return this.kW;
    }
    
    public int getPlayerState() {
        return this.kX;
    }
    
    public long getStreamPosition() {
        return this.kZ;
    }
    
    public double getStreamVolume() {
        return this.lb;
    }
    
    public boolean isMediaCommandSupported(final long n) {
        return (this.la & n) != 0x0L;
    }
    
    public boolean isMute() {
        return this.lc;
    }
}
