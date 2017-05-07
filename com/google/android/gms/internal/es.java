// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.cast.MediaInfo;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import android.os.SystemClock;
import org.json.JSONObject;
import android.os.Looper;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.cast.MediaStatus;
import android.os.Handler;

public class es extends em
{
    private static final String NAMESPACE;
    private static final long zG;
    private static final long zH;
    private static final long zI;
    private static final long zJ;
    private final Handler mHandler;
    private long zK;
    private MediaStatus zL;
    private final ev zM;
    private final ev zN;
    private final ev zO;
    private final ev zP;
    private final ev zQ;
    private final ev zR;
    private final ev zS;
    private final ev zT;
    private final Runnable zU;
    private boolean zV;
    
    static {
        NAMESPACE = eo.X("com.google.cast.media");
        zG = TimeUnit.HOURS.toMillis(24L);
        zH = TimeUnit.HOURS.toMillis(24L);
        zI = TimeUnit.HOURS.toMillis(24L);
        zJ = TimeUnit.SECONDS.toMillis(1L);
    }
    
    public es() {
        this(null);
    }
    
    public es(final String s) {
        super(es.NAMESPACE, "MediaControlChannel", s);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.zU = new a();
        this.zM = new ev(es.zH);
        this.zN = new ev(es.zG);
        this.zO = new ev(es.zG);
        this.zP = new ev(es.zG);
        this.zQ = new ev(es.zI);
        this.zR = new ev(es.zG);
        this.zS = new ev(es.zG);
        this.zT = new ev(es.zG);
        this.dS();
    }
    
    private void a(final long n, final JSONObject jsonObject) throws JSONException {
        final boolean b = true;
        final boolean n2 = this.zM.n(n);
        int n3;
        if (this.zQ.dU() && !this.zQ.n(n)) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        boolean b2 = false;
        Label_0087: {
            if (this.zR.dU()) {
                b2 = b;
                if (!this.zR.n(n)) {
                    break Label_0087;
                }
            }
            b2 = (this.zS.dU() && !this.zS.n(n) && b);
        }
        int n4;
        if (n3 != 0) {
            n4 = 2;
        }
        else {
            n4 = 0;
        }
        int n5 = n4;
        if (b2) {
            n5 = (n4 | 0x1);
        }
        int a;
        if (n2 || this.zL == null) {
            this.zL = new MediaStatus(jsonObject);
            this.zK = SystemClock.elapsedRealtime();
            a = 7;
        }
        else {
            a = this.zL.a(jsonObject, n5);
        }
        if ((a & 0x1) != 0x0) {
            this.zK = SystemClock.elapsedRealtime();
            this.onStatusUpdated();
        }
        if ((a & 0x2) != 0x0) {
            this.zK = SystemClock.elapsedRealtime();
            this.onStatusUpdated();
        }
        if ((a & 0x4) != 0x0) {
            this.onMetadataUpdated();
        }
        this.zM.c(n, 0);
        this.zN.c(n, 0);
        this.zO.c(n, 0);
        this.zP.c(n, 0);
        this.zQ.c(n, 0);
        this.zR.c(n, 0);
        this.zS.c(n, 0);
        this.zT.c(n, 0);
    }
    
    private void dS() {
        this.w(false);
        this.zK = 0L;
        this.zL = null;
        this.zM.clear();
        this.zQ.clear();
        this.zR.clear();
    }
    
    private void w(final boolean zv) {
        if (this.zV != zv) {
            this.zV = zv;
            if (!zv) {
                this.mHandler.removeCallbacks(this.zU);
                return;
            }
            this.mHandler.postDelayed(this.zU, es.zJ);
        }
    }
    
    @Override
    public final void U(final String s) {
        this.yY.b("message received: %s", s);
        JSONObject jsonObject;
        String string;
        long optLong;
        try {
            jsonObject = new JSONObject(s);
            string = jsonObject.getString("type");
            optLong = jsonObject.optLong("requestId", -1L);
            if (string.equals("MEDIA_STATUS")) {
                final JSONArray jsonArray = jsonObject.getJSONArray("status");
                if (jsonArray.length() > 0) {
                    this.a(optLong, jsonArray.getJSONObject(0));
                    return;
                }
                this.zL = null;
                this.onStatusUpdated();
                this.onMetadataUpdated();
                this.zT.c(optLong, 0);
                return;
            }
        }
        catch (JSONException ex) {
            this.yY.d("Message is malformed (%s); ignoring: %s", ex.getMessage(), s);
            return;
        }
        if (string.equals("INVALID_PLAYER_STATE")) {
            this.yY.d("received unexpected error: Invalid Player State.", new Object[0]);
            final JSONObject optJSONObject = jsonObject.optJSONObject("customData");
            this.zM.b(optLong, 1, optJSONObject);
            this.zN.b(optLong, 1, optJSONObject);
            this.zO.b(optLong, 1, optJSONObject);
            this.zP.b(optLong, 1, optJSONObject);
            this.zQ.b(optLong, 1, optJSONObject);
            this.zR.b(optLong, 1, optJSONObject);
            this.zS.b(optLong, 1, optJSONObject);
            this.zT.b(optLong, 1, optJSONObject);
            return;
        }
        if (string.equals("LOAD_FAILED")) {
            this.zM.b(optLong, 1, jsonObject.optJSONObject("customData"));
            return;
        }
        if (string.equals("LOAD_CANCELLED")) {
            this.zM.b(optLong, 2, jsonObject.optJSONObject("customData"));
            return;
        }
        if (string.equals("INVALID_REQUEST")) {
            this.yY.d("received unexpected error: Invalid Request.", new Object[0]);
            final JSONObject optJSONObject2 = jsonObject.optJSONObject("customData");
            this.zM.b(optLong, 1, optJSONObject2);
            this.zN.b(optLong, 1, optJSONObject2);
            this.zO.b(optLong, 1, optJSONObject2);
            this.zP.b(optLong, 1, optJSONObject2);
            this.zQ.b(optLong, 1, optJSONObject2);
            this.zR.b(optLong, 1, optJSONObject2);
            this.zS.b(optLong, 1, optJSONObject2);
            this.zT.b(optLong, 1, optJSONObject2);
        }
    }
    
    public long a(final eu eu) throws IOException {
        final JSONObject jsonObject = new JSONObject();
        final long de = this.dE();
        this.zT.a(de, eu);
        this.w(true);
        while (true) {
            try {
                jsonObject.put("requestId", de);
                jsonObject.put("type", (Object)"GET_STATUS");
                if (this.zL != null) {
                    jsonObject.put("mediaSessionId", this.zL.dC());
                }
                this.a(jsonObject.toString(), de, null);
                return de;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final eu eu, final double n, final JSONObject jsonObject) throws IOException, IllegalStateException, IllegalArgumentException {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            throw new IllegalArgumentException("Volume cannot be " + n);
        }
        final JSONObject jsonObject2 = new JSONObject();
        final long de = this.dE();
        this.zR.a(de, eu);
        this.w(true);
        while (true) {
            try {
                jsonObject2.put("requestId", de);
                jsonObject2.put("type", (Object)"SET_VOLUME");
                jsonObject2.put("mediaSessionId", this.dC());
                final JSONObject jsonObject3 = new JSONObject();
                jsonObject3.put("level", n);
                jsonObject2.put("volume", (Object)jsonObject3);
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), de, null);
                return de;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final eu eu, final long n, final int n2, final JSONObject jsonObject) throws IOException, IllegalStateException {
        final JSONObject jsonObject2 = new JSONObject();
        final long de = this.dE();
        this.zQ.a(de, eu);
        this.w(true);
        while (true) {
            try {
                jsonObject2.put("requestId", de);
                jsonObject2.put("type", (Object)"SEEK");
                jsonObject2.put("mediaSessionId", this.dC());
                jsonObject2.put("currentTime", eo.m(n));
                if (n2 == 1) {
                    jsonObject2.put("resumeState", (Object)"PLAYBACK_START");
                }
                else if (n2 == 2) {
                    jsonObject2.put("resumeState", (Object)"PLAYBACK_PAUSE");
                }
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), de, null);
                return de;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final eu eu, final MediaInfo mediaInfo, final boolean b, final long n, final JSONObject jsonObject) throws IOException {
        final JSONObject jsonObject2 = new JSONObject();
        final long de = this.dE();
        this.zM.a(de, eu);
        this.w(true);
        while (true) {
            try {
                jsonObject2.put("requestId", de);
                jsonObject2.put("type", (Object)"LOAD");
                jsonObject2.put("media", (Object)mediaInfo.dB());
                jsonObject2.put("autoplay", b);
                jsonObject2.put("currentTime", eo.m(n));
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), de, null);
                return de;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final eu eu, final JSONObject jsonObject) throws IOException {
        final JSONObject jsonObject2 = new JSONObject();
        final long de = this.dE();
        this.zN.a(de, eu);
        this.w(true);
        while (true) {
            try {
                jsonObject2.put("requestId", de);
                jsonObject2.put("type", (Object)"PAUSE");
                jsonObject2.put("mediaSessionId", this.dC());
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), de, null);
                return de;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final eu eu, final boolean b, final JSONObject jsonObject) throws IOException, IllegalStateException {
        final JSONObject jsonObject2 = new JSONObject();
        final long de = this.dE();
        this.zS.a(de, eu);
        this.w(true);
        while (true) {
            try {
                jsonObject2.put("requestId", de);
                jsonObject2.put("type", (Object)"SET_VOLUME");
                jsonObject2.put("mediaSessionId", this.dC());
                final JSONObject jsonObject3 = new JSONObject();
                jsonObject3.put("muted", b);
                jsonObject2.put("volume", (Object)jsonObject3);
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), de, null);
                return de;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public void a(final long n, final int n2) {
        this.zM.c(n, n2);
        this.zN.c(n, n2);
        this.zO.c(n, n2);
        this.zP.c(n, n2);
        this.zQ.c(n, n2);
        this.zR.c(n, n2);
        this.zS.c(n, n2);
        this.zT.c(n, n2);
    }
    
    public long b(final eu eu, final JSONObject jsonObject) throws IOException {
        final JSONObject jsonObject2 = new JSONObject();
        final long de = this.dE();
        this.zP.a(de, eu);
        this.w(true);
        while (true) {
            try {
                jsonObject2.put("requestId", de);
                jsonObject2.put("type", (Object)"STOP");
                jsonObject2.put("mediaSessionId", this.dC());
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), de, null);
                return de;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long c(final eu eu, final JSONObject jsonObject) throws IOException, IllegalStateException {
        final JSONObject jsonObject2 = new JSONObject();
        final long de = this.dE();
        this.zO.a(de, eu);
        this.w(true);
        while (true) {
            try {
                jsonObject2.put("requestId", de);
                jsonObject2.put("type", (Object)"PLAY");
                jsonObject2.put("mediaSessionId", this.dC());
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), de, null);
                return de;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long dC() throws IllegalStateException {
        if (this.zL == null) {
            throw new IllegalStateException("No current media session");
        }
        return this.zL.dC();
    }
    
    @Override
    public void dF() {
        this.dS();
    }
    
    public long getApproximateStreamPosition() {
        final MediaInfo mediaInfo = this.getMediaInfo();
        if (mediaInfo == null || this.zK == 0L) {
            return 0L;
        }
        final double playbackRate = this.zL.getPlaybackRate();
        final long streamPosition = this.zL.getStreamPosition();
        final int playerState = this.zL.getPlayerState();
        if (playbackRate == 0.0 || playerState != 2) {
            return streamPosition;
        }
        long n = SystemClock.elapsedRealtime() - this.zK;
        if (n < 0L) {
            n = 0L;
        }
        if (n == 0L) {
            return streamPosition;
        }
        final long streamDuration = mediaInfo.getStreamDuration();
        long n2 = streamPosition + (long)(n * playbackRate);
        if (n2 > streamDuration) {
            n2 = streamDuration;
        }
        else if (n2 < 0L) {
            n2 = 0L;
        }
        return n2;
    }
    
    public MediaInfo getMediaInfo() {
        if (this.zL == null) {
            return null;
        }
        return this.zL.getMediaInfo();
    }
    
    public MediaStatus getMediaStatus() {
        return this.zL;
    }
    
    public long getStreamDuration() {
        final MediaInfo mediaInfo = this.getMediaInfo();
        if (mediaInfo != null) {
            return mediaInfo.getStreamDuration();
        }
        return 0L;
    }
    
    protected void onMetadataUpdated() {
    }
    
    protected void onStatusUpdated() {
    }
    
    private class a implements Runnable
    {
        @Override
        public void run() {
            while (true) {
                boolean b = false;
                es.this.zV = false;
                final long elapsedRealtime = SystemClock.elapsedRealtime();
                es.this.zM.d(elapsedRealtime, 3);
                es.this.zN.d(elapsedRealtime, 3);
                es.this.zO.d(elapsedRealtime, 3);
                es.this.zP.d(elapsedRealtime, 3);
                es.this.zQ.d(elapsedRealtime, 3);
                es.this.zR.d(elapsedRealtime, 3);
                es.this.zS.d(elapsedRealtime, 3);
                es.this.zT.d(elapsedRealtime, 3);
                while (true) {
                    synchronized (ev.Ab) {
                        if (!es.this.zM.dU() && !es.this.zQ.dU() && !es.this.zR.dU() && !es.this.zS.dU()) {
                            if (!es.this.zT.dU()) {
                                // monitorexit(ev.Ab)
                                es.this.w(b);
                                return;
                            }
                        }
                    }
                    b = true;
                    continue;
                }
            }
        }
    }
}
