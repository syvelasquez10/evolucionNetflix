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

public class dl extends df
{
    private static final long me;
    private static final long mf;
    private static final long mg;
    private static final long mh;
    private final Handler mHandler;
    private long mi;
    private MediaStatus mj;
    private final do mk;
    private final do ml;
    private final do mm;
    private final do mn;
    private final do mo;
    private final Runnable mp;
    private boolean mq;
    
    static {
        me = TimeUnit.SECONDS.toMillis(3L);
        mf = TimeUnit.HOURS.toMillis(24L);
        mg = TimeUnit.SECONDS.toMillis(5L);
        mh = TimeUnit.SECONDS.toMillis(1L);
    }
    
    public dl() {
        super("urn:x-cast:com.google.cast.media", "MediaControlChannel");
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mp = new a();
        this.mk = new do(dl.mf);
        this.ml = new do(dl.mg);
        this.mm = new do(dl.me);
        this.mn = new do(dl.me);
        this.mo = new do(dl.me);
        this.bd();
    }
    
    private void a(final long n, final JSONObject jsonObject) throws JSONException {
        final boolean b = true;
        final boolean i = this.mk.i(n);
        int n2;
        if (this.ml.bf() && !this.ml.i(n)) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        boolean b2 = false;
        Label_0087: {
            if (this.mm.bf()) {
                b2 = b;
                if (!this.mm.i(n)) {
                    break Label_0087;
                }
            }
            b2 = (this.mn.bf() && !this.mn.i(n) && b);
        }
        int n3;
        if (n2 != 0) {
            n3 = 2;
        }
        else {
            n3 = 0;
        }
        int n4 = n3;
        if (b2) {
            n4 = (n3 | 0x1);
        }
        int a;
        if (i || this.mj == null) {
            this.mj = new MediaStatus(jsonObject);
            this.mi = SystemClock.elapsedRealtime();
            a = 7;
        }
        else {
            a = this.mj.a(jsonObject, n4);
        }
        if ((a & 0x1) != 0x0) {
            this.mi = SystemClock.elapsedRealtime();
            this.onStatusUpdated();
        }
        if ((a & 0x2) != 0x0) {
            this.mi = SystemClock.elapsedRealtime();
            this.onStatusUpdated();
        }
        if ((a & 0x4) != 0x0) {
            this.onMetadataUpdated();
        }
        this.mk.c(n, 0);
        this.ml.c(n, 0);
        this.mm.c(n, 0);
        this.mn.c(n, 0);
        this.mo.c(n, 0);
    }
    
    private void bd() {
        this.o(false);
        this.mi = 0L;
        this.mj = null;
        this.mk.clear();
        this.ml.clear();
        this.mm.clear();
    }
    
    private void o(final boolean mq) {
        if (this.mq != mq) {
            this.mq = mq;
            if (!mq) {
                this.mHandler.removeCallbacks(this.mp);
                return;
            }
            this.mHandler.postDelayed(this.mp, dl.mh);
        }
    }
    
    @Override
    public final void B(final String s) {
        this.lx.b("message received: %s", s);
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
                this.mj = null;
                this.onStatusUpdated();
                this.onMetadataUpdated();
                this.mo.c(optLong, 0);
                return;
            }
        }
        catch (JSONException ex) {
            this.lx.d("Message is malformed (%s); ignoring: %s", ex.getMessage(), s);
            return;
        }
        if (string.equals("INVALID_PLAYER_STATE")) {
            this.lx.d("received unexpected error: Invalid Player State.", new Object[0]);
            final JSONObject optJSONObject = jsonObject.optJSONObject("customData");
            this.mk.b(optLong, 1, optJSONObject);
            this.ml.b(optLong, 1, optJSONObject);
            this.mm.b(optLong, 1, optJSONObject);
            this.mn.b(optLong, 1, optJSONObject);
            this.mo.b(optLong, 1, optJSONObject);
            return;
        }
        if (string.equals("LOAD_FAILED")) {
            this.mk.b(optLong, 1, jsonObject.optJSONObject("customData"));
            return;
        }
        if (string.equals("LOAD_CANCELLED")) {
            this.mk.b(optLong, 2, jsonObject.optJSONObject("customData"));
            return;
        }
        if (string.equals("INVALID_REQUEST")) {
            this.lx.d("received unexpected error: Invalid Request.", new Object[0]);
            final JSONObject optJSONObject2 = jsonObject.optJSONObject("customData");
            this.mk.b(optLong, 1, optJSONObject2);
            this.ml.b(optLong, 1, optJSONObject2);
            this.mm.b(optLong, 1, optJSONObject2);
            this.mn.b(optLong, 1, optJSONObject2);
            this.mo.b(optLong, 1, optJSONObject2);
        }
    }
    
    public long a(final dn dn) throws IOException {
        final JSONObject jsonObject = new JSONObject();
        final long as = this.aS();
        this.mo.a(as, dn);
        this.o(true);
        while (true) {
            try {
                jsonObject.put("requestId", as);
                jsonObject.put("type", (Object)"GET_STATUS");
                if (this.mj != null) {
                    jsonObject.put("mediaSessionId", this.mj.aQ());
                }
                this.a(jsonObject.toString(), as, null);
                return as;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final dn dn, final double n, final JSONObject jsonObject) throws IOException, IllegalStateException, IllegalArgumentException {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            throw new IllegalArgumentException("Volume cannot be " + n);
        }
        final JSONObject jsonObject2 = new JSONObject();
        final long as = this.aS();
        this.mm.a(as, dn);
        this.o(true);
        while (true) {
            try {
                jsonObject2.put("requestId", as);
                jsonObject2.put("type", (Object)"SET_VOLUME");
                jsonObject2.put("mediaSessionId", this.aQ());
                final JSONObject jsonObject3 = new JSONObject();
                jsonObject3.put("level", n);
                jsonObject2.put("volume", (Object)jsonObject3);
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), as, null);
                return as;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final dn dn, final long n, final int n2, final JSONObject jsonObject) throws IOException, IllegalStateException {
        final JSONObject jsonObject2 = new JSONObject();
        final long as = this.aS();
        this.ml.a(as, dn);
        this.o(true);
        while (true) {
            try {
                jsonObject2.put("requestId", as);
                jsonObject2.put("type", (Object)"SEEK");
                jsonObject2.put("mediaSessionId", this.aQ());
                jsonObject2.put("currentTime", dh.h(n));
                if (n2 == 1) {
                    jsonObject2.put("resumeState", (Object)"PLAYBACK_START");
                }
                else if (n2 == 2) {
                    jsonObject2.put("resumeState", (Object)"PLAYBACK_PAUSE");
                }
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), as, null);
                return as;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final dn dn, final MediaInfo mediaInfo, final boolean b, final long n, final JSONObject jsonObject) throws IOException {
        final JSONObject jsonObject2 = new JSONObject();
        final long as = this.aS();
        this.mk.a(as, dn);
        this.o(true);
        while (true) {
            try {
                jsonObject2.put("requestId", as);
                jsonObject2.put("type", (Object)"LOAD");
                jsonObject2.put("media", (Object)mediaInfo.aP());
                jsonObject2.put("autoplay", b);
                jsonObject2.put("currentTime", dh.h(n));
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), as, null);
                return as;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final dn dn, final boolean b, final JSONObject jsonObject) throws IOException, IllegalStateException {
        final JSONObject jsonObject2 = new JSONObject();
        final long as = this.aS();
        this.mn.a(as, dn);
        this.o(true);
        while (true) {
            try {
                jsonObject2.put("requestId", as);
                jsonObject2.put("type", (Object)"SET_VOLUME");
                jsonObject2.put("mediaSessionId", this.aQ());
                final JSONObject jsonObject3 = new JSONObject();
                jsonObject3.put("muted", b);
                jsonObject2.put("volume", (Object)jsonObject3);
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), as, null);
                return as;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public void a(final long n, final int n2) {
        this.mk.c(n, n2);
        this.ml.c(n, n2);
        this.mm.c(n, n2);
        this.mn.c(n, n2);
        this.mo.c(n, n2);
    }
    
    public long aQ() throws IllegalStateException {
        if (this.mj == null) {
            throw new IllegalStateException("No current media session");
        }
        return this.mj.aQ();
    }
    
    @Override
    public void aT() {
        this.bd();
    }
    
    public void c(final JSONObject jsonObject) throws IOException {
        final JSONObject jsonObject2 = new JSONObject();
        final long as = this.aS();
        while (true) {
            try {
                jsonObject2.put("requestId", as);
                jsonObject2.put("type", (Object)"PAUSE");
                jsonObject2.put("mediaSessionId", this.aQ());
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), as, null);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public void d(final JSONObject jsonObject) throws IOException {
        final JSONObject jsonObject2 = new JSONObject();
        final long as = this.aS();
        while (true) {
            try {
                jsonObject2.put("requestId", as);
                jsonObject2.put("type", (Object)"STOP");
                jsonObject2.put("mediaSessionId", this.aQ());
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), as, null);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public void e(final JSONObject jsonObject) throws IOException, IllegalStateException {
        final JSONObject jsonObject2 = new JSONObject();
        final long as = this.aS();
        while (true) {
            try {
                jsonObject2.put("requestId", as);
                jsonObject2.put("type", (Object)"PLAY");
                jsonObject2.put("mediaSessionId", this.aQ());
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), as, null);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long getApproximateStreamPosition() {
        final MediaInfo mediaInfo = this.getMediaInfo();
        if (mediaInfo == null || this.mi == 0L) {
            return 0L;
        }
        final double playbackRate = this.mj.getPlaybackRate();
        final long streamPosition = this.mj.getStreamPosition();
        final int playerState = this.mj.getPlayerState();
        if (playbackRate == 0.0 || playerState != 2) {
            return streamPosition;
        }
        long n = SystemClock.elapsedRealtime() - this.mi;
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
        if (this.mj == null) {
            return null;
        }
        return this.mj.getMediaInfo();
    }
    
    public MediaStatus getMediaStatus() {
        return this.mj;
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
                dl.this.mq = false;
                final long elapsedRealtime = SystemClock.elapsedRealtime();
                dl.this.mk.d(elapsedRealtime, 3);
                dl.this.ml.d(elapsedRealtime, 3);
                dl.this.mm.d(elapsedRealtime, 3);
                dl.this.mn.d(elapsedRealtime, 3);
                dl.this.mo.d(elapsedRealtime, 3);
                while (true) {
                    synchronized (do.mw) {
                        if (!dl.this.mk.bf() && !dl.this.ml.bf() && !dl.this.mm.bf() && !dl.this.mn.bf()) {
                            if (!dl.this.mo.bf()) {
                                // monitorexit(do.mw)
                                dl.this.o(b);
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
