// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.cast.TextTrackStyle;
import org.json.JSONArray;
import com.google.android.gms.cast.MediaInfo;
import java.io.IOException;
import org.json.JSONException;
import java.util.Iterator;
import android.os.SystemClock;
import org.json.JSONObject;
import java.util.ArrayList;
import android.os.Looper;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import java.util.List;
import com.google.android.gms.cast.MediaStatus;

public class iq extends ii
{
    private static final long Hb;
    private static final long Hc;
    private static final long Hd;
    private static final long He;
    private static final String NAMESPACE;
    private long Hf;
    private MediaStatus Hg;
    private final it Hh;
    private final it Hi;
    private final it Hj;
    private final it Hk;
    private final it Hl;
    private final it Hm;
    private final it Hn;
    private final it Ho;
    private final it Hp;
    private final it Hq;
    private final List<it> Hr;
    private final Runnable Hs;
    private boolean Ht;
    private final Handler mHandler;
    
    static {
        NAMESPACE = ik.aG("com.google.cast.media");
        Hb = TimeUnit.HOURS.toMillis(24L);
        Hc = TimeUnit.HOURS.toMillis(24L);
        Hd = TimeUnit.HOURS.toMillis(24L);
        He = TimeUnit.SECONDS.toMillis(1L);
    }
    
    public iq() {
        this(null);
    }
    
    public iq(final String s) {
        super(iq.NAMESPACE, "MediaControlChannel", s);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.Hs = new a();
        this.Hr = new ArrayList<it>();
        this.Hh = new it(iq.Hc);
        this.Hr.add(this.Hh);
        this.Hi = new it(iq.Hb);
        this.Hr.add(this.Hi);
        this.Hj = new it(iq.Hb);
        this.Hr.add(this.Hj);
        this.Hk = new it(iq.Hb);
        this.Hr.add(this.Hk);
        this.Hl = new it(iq.Hd);
        this.Hr.add(this.Hl);
        this.Hm = new it(iq.Hb);
        this.Hr.add(this.Hm);
        this.Hn = new it(iq.Hb);
        this.Hr.add(this.Hn);
        this.Ho = new it(iq.Hb);
        this.Hr.add(this.Ho);
        this.Hp = new it(iq.Hb);
        this.Hr.add(this.Hp);
        this.Hq = new it(iq.Hb);
        this.Hr.add(this.Hq);
        this.fU();
    }
    
    private void H(final boolean ht) {
        if (this.Ht != ht) {
            this.Ht = ht;
            if (!ht) {
                this.mHandler.removeCallbacks(this.Hs);
                return;
            }
            this.mHandler.postDelayed(this.Hs, iq.He);
        }
    }
    
    private void a(final long n, final JSONObject jsonObject) throws JSONException {
        final boolean b = true;
        final boolean p2 = this.Hh.p(n);
        int n2;
        if (this.Hl.fW() && !this.Hl.p(n)) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        boolean b2 = false;
        Label_0087: {
            if (this.Hm.fW()) {
                b2 = b;
                if (!this.Hm.p(n)) {
                    break Label_0087;
                }
            }
            b2 = (this.Hn.fW() && !this.Hn.p(n) && b);
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
        if (p2 || this.Hg == null) {
            this.Hg = new MediaStatus(jsonObject);
            this.Hf = SystemClock.elapsedRealtime();
            a = 7;
        }
        else {
            a = this.Hg.a(jsonObject, n4);
        }
        if ((a & 0x1) != 0x0) {
            this.Hf = SystemClock.elapsedRealtime();
            this.onStatusUpdated();
        }
        if ((a & 0x2) != 0x0) {
            this.Hf = SystemClock.elapsedRealtime();
            this.onStatusUpdated();
        }
        if ((a & 0x4) != 0x0) {
            this.onMetadataUpdated();
        }
        final Iterator<it> iterator = this.Hr.iterator();
        while (iterator.hasNext()) {
            iterator.next().d(n, 0);
        }
    }
    
    private void fU() {
        this.H(false);
        this.Hf = 0L;
        this.Hg = null;
        this.Hh.clear();
        this.Hl.clear();
        this.Hm.clear();
    }
    
    public long a(final is is) throws IOException {
        final JSONObject jsonObject = new JSONObject();
        final long fa = this.fA();
        this.Ho.a(fa, is);
        this.H(true);
        while (true) {
            try {
                jsonObject.put("requestId", fa);
                jsonObject.put("type", (Object)"GET_STATUS");
                if (this.Hg != null) {
                    jsonObject.put("mediaSessionId", this.Hg.fx());
                }
                this.a(jsonObject.toString(), fa, null);
                return fa;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final is is, final double n, final JSONObject jsonObject) throws IOException, IllegalStateException, IllegalArgumentException {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            throw new IllegalArgumentException("Volume cannot be " + n);
        }
        final JSONObject jsonObject2 = new JSONObject();
        final long fa = this.fA();
        this.Hm.a(fa, is);
        this.H(true);
        while (true) {
            try {
                jsonObject2.put("requestId", fa);
                jsonObject2.put("type", (Object)"SET_VOLUME");
                jsonObject2.put("mediaSessionId", this.fx());
                final JSONObject jsonObject3 = new JSONObject();
                jsonObject3.put("level", n);
                jsonObject2.put("volume", (Object)jsonObject3);
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), fa, null);
                return fa;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final is is, final long n, final int n2, final JSONObject jsonObject) throws IOException, IllegalStateException {
        final JSONObject jsonObject2 = new JSONObject();
        final long fa = this.fA();
        this.Hl.a(fa, is);
        this.H(true);
        while (true) {
            try {
                jsonObject2.put("requestId", fa);
                jsonObject2.put("type", (Object)"SEEK");
                jsonObject2.put("mediaSessionId", this.fx());
                jsonObject2.put("currentTime", ik.o(n));
                if (n2 == 1) {
                    jsonObject2.put("resumeState", (Object)"PLAYBACK_START");
                }
                else if (n2 == 2) {
                    jsonObject2.put("resumeState", (Object)"PLAYBACK_PAUSE");
                }
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), fa, null);
                return fa;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final is is, final MediaInfo mediaInfo, final boolean b, final long n, final long[] array, final JSONObject jsonObject) throws IOException {
        final JSONObject jsonObject2 = new JSONObject();
        final long fa = this.fA();
        this.Hh.a(fa, is);
        this.H(true);
        while (true) {
            try {
                jsonObject2.put("requestId", fa);
                jsonObject2.put("type", (Object)"LOAD");
                jsonObject2.put("media", (Object)mediaInfo.bL());
                jsonObject2.put("autoplay", b);
                jsonObject2.put("currentTime", ik.o(n));
                if (array != null && array.length > 0) {
                    final JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < array.length; ++i) {
                        jsonArray.put(i, array[i]);
                    }
                    jsonObject2.put("activeTrackIds", (Object)jsonArray);
                }
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), fa, null);
                return fa;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final is is, final TextTrackStyle textTrackStyle) throws IOException {
        final JSONObject jsonObject = new JSONObject();
        final long fa = this.fA();
        this.Hq.a(fa, is);
        this.H(true);
        while (true) {
            try {
                jsonObject.put("requestId", fa);
                jsonObject.put("type", (Object)"EDIT_TRACKS_INFO");
                if (textTrackStyle != null) {
                    jsonObject.put("textTrackStyle", (Object)textTrackStyle.bL());
                }
                jsonObject.put("mediaSessionId", this.fx());
                this.a(jsonObject.toString(), fa, null);
                return fa;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final is is, final JSONObject jsonObject) throws IOException {
        final JSONObject jsonObject2 = new JSONObject();
        final long fa = this.fA();
        this.Hi.a(fa, is);
        this.H(true);
        while (true) {
            try {
                jsonObject2.put("requestId", fa);
                jsonObject2.put("type", (Object)"PAUSE");
                jsonObject2.put("mediaSessionId", this.fx());
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), fa, null);
                return fa;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final is is, final boolean b, final JSONObject jsonObject) throws IOException, IllegalStateException {
        final JSONObject jsonObject2 = new JSONObject();
        final long fa = this.fA();
        this.Hn.a(fa, is);
        this.H(true);
        while (true) {
            try {
                jsonObject2.put("requestId", fa);
                jsonObject2.put("type", (Object)"SET_VOLUME");
                jsonObject2.put("mediaSessionId", this.fx());
                final JSONObject jsonObject3 = new JSONObject();
                jsonObject3.put("muted", b);
                jsonObject2.put("volume", (Object)jsonObject3);
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), fa, null);
                return fa;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long a(final is is, final long[] array) throws IOException {
        final JSONObject jsonObject = new JSONObject();
        final long fa = this.fA();
        this.Hp.a(fa, is);
        this.H(true);
        while (true) {
            try {
                jsonObject.put("requestId", fa);
                jsonObject.put("type", (Object)"EDIT_TRACKS_INFO");
                jsonObject.put("mediaSessionId", this.fx());
                final JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < array.length; ++i) {
                    jsonArray.put(i, array[i]);
                }
                jsonObject.put("activeTrackIds", (Object)jsonArray);
                this.a(jsonObject.toString(), fa, null);
                return fa;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public final void aD(final String s) {
        this.Go.b("message received: %s", s);
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
                this.Hg = null;
                this.onStatusUpdated();
                this.onMetadataUpdated();
                this.Ho.d(optLong, 0);
                return;
            }
        }
        catch (JSONException ex) {
            this.Go.d("Message is malformed (%s); ignoring: %s", ex.getMessage(), s);
            return;
        }
        if (string.equals("INVALID_PLAYER_STATE")) {
            this.Go.d("received unexpected error: Invalid Player State.", new Object[0]);
            final JSONObject optJSONObject = jsonObject.optJSONObject("customData");
            final Iterator<it> iterator = this.Hr.iterator();
            while (iterator.hasNext()) {
                iterator.next().b(optLong, 2100, optJSONObject);
            }
        }
        else {
            if (string.equals("LOAD_FAILED")) {
                this.Hh.b(optLong, 2100, jsonObject.optJSONObject("customData"));
                return;
            }
            if (string.equals("LOAD_CANCELLED")) {
                this.Hh.b(optLong, 2101, jsonObject.optJSONObject("customData"));
                return;
            }
            if (string.equals("INVALID_REQUEST")) {
                this.Go.d("received unexpected error: Invalid Request.", new Object[0]);
                final JSONObject optJSONObject2 = jsonObject.optJSONObject("customData");
                final Iterator<it> iterator2 = this.Hr.iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().b(optLong, 2100, optJSONObject2);
                }
            }
        }
    }
    
    public long b(final is is, final JSONObject jsonObject) throws IOException {
        final JSONObject jsonObject2 = new JSONObject();
        final long fa = this.fA();
        this.Hk.a(fa, is);
        this.H(true);
        while (true) {
            try {
                jsonObject2.put("requestId", fa);
                jsonObject2.put("type", (Object)"STOP");
                jsonObject2.put("mediaSessionId", this.fx());
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), fa, null);
                return fa;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public void b(final long n, final int n2) {
        final Iterator<it> iterator = this.Hr.iterator();
        while (iterator.hasNext()) {
            iterator.next().d(n, n2);
        }
    }
    
    public long c(final is is, final JSONObject jsonObject) throws IOException, IllegalStateException {
        final JSONObject jsonObject2 = new JSONObject();
        final long fa = this.fA();
        this.Hj.a(fa, is);
        this.H(true);
        while (true) {
            try {
                jsonObject2.put("requestId", fa);
                jsonObject2.put("type", (Object)"PLAY");
                jsonObject2.put("mediaSessionId", this.fx());
                if (jsonObject != null) {
                    jsonObject2.put("customData", (Object)jsonObject);
                }
                this.a(jsonObject2.toString(), fa, null);
                return fa;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public void fB() {
        this.fU();
    }
    
    public long fx() throws IllegalStateException {
        if (this.Hg == null) {
            throw new IllegalStateException("No current media session");
        }
        return this.Hg.fx();
    }
    
    public long getApproximateStreamPosition() {
        final MediaInfo mediaInfo = this.getMediaInfo();
        if (mediaInfo == null || this.Hf == 0L) {
            return 0L;
        }
        final double playbackRate = this.Hg.getPlaybackRate();
        final long streamPosition = this.Hg.getStreamPosition();
        final int playerState = this.Hg.getPlayerState();
        if (playbackRate == 0.0 || playerState != 2) {
            return streamPosition;
        }
        long n = SystemClock.elapsedRealtime() - this.Hf;
        if (n < 0L) {
            n = 0L;
        }
        if (n == 0L) {
            return streamPosition;
        }
        final long streamDuration = mediaInfo.getStreamDuration();
        long n2 = streamPosition + (long)(n * playbackRate);
        if (streamDuration > 0L && n2 > streamDuration) {
            n2 = streamDuration;
        }
        else if (n2 < 0L) {
            n2 = 0L;
        }
        return n2;
    }
    
    public MediaInfo getMediaInfo() {
        if (this.Hg == null) {
            return null;
        }
        return this.Hg.getMediaInfo();
    }
    
    public MediaStatus getMediaStatus() {
        return this.Hg;
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
            boolean b = false;
            iq.this.Ht = false;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final Iterator<it> iterator = (Iterator<it>)iq.this.Hr.iterator();
            while (iterator.hasNext()) {
                iterator.next().e(elapsedRealtime, 2102);
            }
            while (true) {
                while (true) {
                    Label_0133: {
                        synchronized (it.Hz) {
                            final Iterator iterator2 = iq.this.Hr.iterator();
                            if (!iterator2.hasNext()) {
                                // monitorexit(it.Hz)
                                iq.this.H(b);
                                return;
                            }
                            if (iterator2.next().fW()) {
                                b = true;
                                break Label_0133;
                            }
                            break Label_0133;
                        }
                    }
                    continue;
                }
            }
        }
    }
}
