// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.service.error.action.UnregisterAction;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class ActionId4ErrorDescriptor extends PlaybackErrorDescriptor
{
    ActionId4ErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static ActionId4ErrorDescriptor build(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.w("nf_play_error", "ActionID 4 NFErr_MC_NCCP_RegistrationRequired");
        return new ActionId4ErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230833), null, new UnregisterAction(playerFragment.getActivity()), playerFragment.getString(2131231061), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
}
