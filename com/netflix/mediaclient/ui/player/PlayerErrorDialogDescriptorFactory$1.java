// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.event.nrdp.device.ReasonCode;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;

final class PlayerErrorDialogDescriptorFactory$1 implements Runnable
{
    final /* synthetic */ PlayerFragment val$fragment;
    final /* synthetic */ JSONObject val$json;
    
    PlayerErrorDialogDescriptorFactory$1(final PlayerFragment val$fragment, final JSONObject val$json) {
        this.val$fragment = val$fragment;
        this.val$json = val$json;
    }
    
    @Override
    public void run() {
        Log.d("ErrorManager", "Publish NCCP reason code event to UI");
        this.val$fragment.getNetflixActivity().getNetflixApplication().publishEvent(new ReasonCode(this.val$json));
        Log.d("ErrorManager", "Exit from playback after UI is alerted to handle");
        this.val$fragment.getActivity().finish();
    }
}
