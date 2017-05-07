// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.text.TextUtils;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public final class ClientNotifier implements NotifierInterface
{
    private static final String TAG = "nf_mdx";
    private final Context mContext;
    private final Map<String, MdxSharedState> mSharedStateMap;
    
    ClientNotifier(final Context mContext) {
        this.mSharedStateMap = new HashMap<String, MdxSharedState>();
        this.mContext = mContext;
        Log.v("nf_mdx", "Creating client notifier");
    }
    
    private void addSharedState(final String s) {
        if (!this.mSharedStateMap.containsKey(s)) {
            this.mSharedStateMap.put(s, new MdxSharedState(s));
        }
    }
    
    @Override
    public void abortPinVerification(final String s, final boolean b) {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.PIN_VERIFICATION_NOT_REQUIRED").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s).putExtra("isPinVerified", b));
    }
    
    @Override
    public void audiosub(final String s, final String s2) {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_AUDIOSUB").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s).putExtra("stringBlob", s2));
        Log.v("nf_mdx", "Intent MDXUPDATE_AUDIOSUB sent");
    }
    
    @Override
    public void capability(final String s, final String s2) {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_CAPABILITY").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s).putExtra("stringBlob", s2));
        Log.v("nf_mdx", "Intent MDXUPDATE_CAPABILITY sent");
    }
    
    public void commandPlayReceived(final String s) {
        synchronized (this.mSharedStateMap) {
            this.addSharedState(s);
            this.mSharedStateMap.get(s).notifyPlayCommandReceived();
        }
    }
    
    @Override
    public void dialogCancel(final String s, final String s2) {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_DIALOGCANCEL").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s).putExtra("stringBlob", s2));
        Log.v("nf_mdx", "Intent MDXUPDATE_DIALOGCANCEL sent");
    }
    
    @Override
    public void dialogShow(final String s, final String s2) {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_DIALOGSHOW").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s).putExtra("stringBlob", s2));
        Log.v("nf_mdx", "Intent MDXUPDATE_DIALOGSHOW sent");
    }
    
    @Override
    public void error(final String s, final int n, final String s2) {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_ERROR").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s).putExtra("errorCode", n).putExtra("errorDesc", s2));
        Log.v("nf_mdx", "Intent MDXUPDATE_ERROR sent");
    }
    
    public MdxSharedState getSharedState(final String s) {
        synchronized (this.mSharedStateMap) {
            return this.mSharedStateMap.get(s);
        }
    }
    
    @Override
    public void metaData(final String s, final String s2) {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_METADATA").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s).putExtra("stringBlob", s2));
        Log.v("nf_mdx", "Intent MDXUPDATE_METADATA sent");
    }
    
    @Override
    public void movieMetaData(final String s, final String s2, final String s3, final int n) {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_MOVIEMETADA").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s).putExtra("catalogId", s2).putExtra("episodeId", s3).putExtra("duration", n));
        Log.v("nf_mdx", "Intent MDXUPDATE_MOVIEDATA sent");
    }
    
    @Override
    public void movieMetaDataAvailable(final String s) {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_MOVIEMETADATA_AVAILABLE").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s));
        Log.v("nf_mdx", "Intent MOVIEMETADATA_AVAILABLE sent");
    }
    
    @Override
    public void notready() {
        this.mSharedStateMap.clear();
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_NOTREADY").addCategory("com.netflix.mediaclient.intent.category.MDX"));
        Log.v("nf_mdx", "Intent NOTREADY sent");
    }
    
    @Override
    public void playbackEnd(final String s, final String s2) {
        synchronized (this.mSharedStateMap) {
            if (this.mSharedStateMap.get(s) != null) {
                this.mSharedStateMap.get(s).notifyPlaybackEnd();
            }
            // monitorexit(this.mSharedStateMap)
            final Intent putExtra = new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKEND").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s);
            if (!TextUtils.isEmpty((CharSequence)s2)) {
                putExtra.putExtra("postplayState", s2);
            }
            this.mContext.sendBroadcast(putExtra);
            Log.v("nf_mdx", "Intent MDXUPDATE_PLAYBACKEND sent");
        }
    }
    
    @Override
    public void playbackStart(final String s) {
        synchronized (this.mSharedStateMap) {
            this.addSharedState(s);
            this.mSharedStateMap.get(s).notifyPlaybackStart();
            // monitorexit(this.mSharedStateMap)
            this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKSTART").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s));
            Log.v("nf_mdx", "Intent MDXUPDATE_PLAYBACKSTART sent");
        }
    }
    
    @Override
    public void postplayState(final String s, final String s2) {
        if (this.mSharedStateMap.get(s) != null) {
            this.mSharedStateMap.get(s).notifyPostplayState(s2);
        }
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_POSTPLAY").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s).putExtra("postplayState", s2));
    }
    
    @Override
    public void ready() {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_READY").addCategory("com.netflix.mediaclient.intent.category.MDX"));
        Log.v("nf_mdx", "Intent READY sent");
    }
    
    @Override
    public void requestPinVerification(final String s, final String s2, final int n, final int n2, final String s3) {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.PIN_VERIFICATION_SHOW").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s).putExtra("title", s2).putExtra("ancestorVideoType", s3).putExtra("videoId", n).putExtra("ancestorVideoId", n2));
    }
    
    @Override
    public void simplePlaybackState(final String s, final boolean b, final boolean b2, final String s2) {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_SIMPLE_PLAYBACKSTATE").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s).putExtra("paused", b).putExtra("transitioning", b2).putExtra("postplayState", s2));
    }
    
    @Override
    public void state(final String s, final String s2, final int n, final int n2) {
        synchronized (this.mSharedStateMap) {
            if (this.mSharedStateMap.get(s) != null) {
                this.mSharedStateMap.get(s).notifyPlaybackState(s2, n, n2);
            }
            // monitorexit(this.mSharedStateMap)
            this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_STATE").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", s).putExtra("currentState", s2).putExtra("time", n).putExtra("volume", n2));
            Log.v("nf_mdx", "Intent MDXUPDATE_STATE sent");
        }
    }
    
    @Override
    public void targetList() {
        this.mContext.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDXUPDATE_TARGETLIST").addCategory("com.netflix.mediaclient.intent.category.MDX"));
        Log.v("nf_mdx", "Intent MDXUPDATE_TARGETLISTY sent");
    }
}
