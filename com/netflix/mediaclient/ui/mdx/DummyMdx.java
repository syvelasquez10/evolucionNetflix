// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.WebApiUtils;
import android.util.Pair;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import java.nio.ByteBuffer;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import com.netflix.mediaclient.media.BifManager;
import com.netflix.mediaclient.servicemgr.IMdx;

public class DummyMdx implements IMdx
{
    private final BifManager bifMan;
    private final VideoDetails dummyDetails;
    
    public DummyMdx(final NetflixActivity netflixActivity) {
        this.bifMan = new BifManager((Context)netflixActivity, "http://tp.akam.nflximg.com/tpa2/799/1190182799.bif");
        this.dummyDetails = new DummyMdxVideoDetails();
    }
    
    @Override
    public ByteBuffer getBifFrame(final int n) {
        return this.bifMan.getIndexFrame(n);
    }
    
    @Override
    public String getCurrentTarget() {
        return "Dummy_UUID";
    }
    
    @Override
    public IMdxSharedState getSharedState() {
        return null;
    }
    
    @Override
    public Pair<String, String>[] getTargetList() {
        return (Pair<String, String>[])new Pair[0];
    }
    
    @Override
    public VideoDetails getVideoDetail() {
        return this.dummyDetails;
    }
    
    @Override
    public WebApiUtils.VideoIds getVideoIds() {
        return null;
    }
    
    @Override
    public boolean isBifReady() {
        return this.bifMan.isBifReady();
    }
    
    @Override
    public boolean isPaused() {
        return false;
    }
    
    @Override
    public boolean isReady() {
        return true;
    }
    
    @Override
    public boolean isTargetLaunchingOrLaunched() {
        return false;
    }
    
    @Override
    public void setCurrentTarget(final String s) {
    }
    
    @Override
    public boolean setDialUuidAsCurrentTarget(final String s) {
        return false;
    }
    
    @Override
    public void switchPlaybackFromTarget(final String s, final int n) {
    }
}
