// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import org.json.JSONObject;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import java.util.Locale;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.player.PlayerFragment;

final class ConcurentStreamUpgradeErrorDescriptor$2 implements Runnable
{
    final /* synthetic */ PlayerFragment val$fragment;
    final /* synthetic */ PlaybackErrorDescriptor$LinkTag val$link;
    
    ConcurentStreamUpgradeErrorDescriptor$2(final PlaybackErrorDescriptor$LinkTag val$link, final PlayerFragment val$fragment) {
        this.val$link = val$link;
        this.val$fragment = val$fragment;
    }
    
    @Override
    public void run() {
        String s;
        if (this.val$link.href.toLowerCase(ConcurentStreamUpgradeErrorDescriptor.US_LOCALE).trim().startsWith("http")) {
            s = this.val$link.href;
        }
        else {
            s = "http://www.netflix.com" + "/" + this.val$link.href;
        }
        Log.d("nf_play_error", "Launch browser");
        this.val$fragment.runOnUiThread(new ConcurentStreamUpgradeErrorDescriptor$2$1(this, s));
    }
}
