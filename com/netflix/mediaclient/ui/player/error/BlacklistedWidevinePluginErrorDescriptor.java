// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import android.app.Activity;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

public class BlacklistedWidevinePluginErrorDescriptor extends ActionId1ErrorDescriptor
{
    private static final int BLACKLISTED_WIDEVINE_PLUGIN = 15003;
    protected static final String TAG = "nf_user_error";
    
    BlacklistedWidevinePluginErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static BlacklistedWidevinePluginErrorDescriptor build(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.d("nf_user_error", "actionID 1 15003, Widevine blacklisted...");
        return new BlacklistedWidevinePluginErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", playerFragment.getString(2131230878, new Object[] { 15003 }), null, new FailbackToLegacyCryptoAction(playerFragment.getActivity()), playerFragment.getString(2131231077), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    static boolean canHandle(final NccpActionId nccpActionId) {
        return nccpActionId.getCode() == 15003;
    }
}
