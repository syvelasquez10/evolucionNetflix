// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class ActionId1ErrorDescriptor extends PlaybackErrorDescriptor
{
    ActionId1ErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static ActionId1ErrorDescriptor build(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        if (BlacklistedWidevinePluginErrorDescriptor.canHandle(nccpActionId)) {
            return BlacklistedWidevinePluginErrorDescriptor.build(playerFragment, nccpActionId, s);
        }
        return createGeneric(playerFragment, nccpActionId, s);
    }
    
    private static ActionId1ErrorDescriptor createGeneric(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.d("nf_play_error", "actionID 1 NFErr_MC_NCCP_NonRecoverableError");
        return new ActionId1ErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230777), null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231061), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
}
