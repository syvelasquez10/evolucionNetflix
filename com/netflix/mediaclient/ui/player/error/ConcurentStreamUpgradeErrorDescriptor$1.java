// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.event.nrdp.device.ReasonCode;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.ui.player.PlayerFragment;

final class ConcurentStreamUpgradeErrorDescriptor$1 implements Runnable
{
    final /* synthetic */ PlayerFragment val$fragment;
    final /* synthetic */ JSONObject val$json;
    
    ConcurentStreamUpgradeErrorDescriptor$1(final PlayerFragment val$fragment, final JSONObject val$json) {
        this.val$fragment = val$fragment;
        this.val$json = val$json;
    }
    
    @Override
    public void run() {
        Log.d("nf_play_error", "Publish NCCP reason code event to UI");
        this.val$fragment.getNetflixActivity().getNetflixApplication().publishEvent(new ReasonCode(this.val$json));
        Log.d("nf_play_error", "Exit from playback after UI is alerted to handle");
        this.val$fragment.getActivity().finish();
    }
}
