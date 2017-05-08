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

class ActionId5ErrorDescriptor extends PlaybackErrorDescriptor
{
    ActionId5ErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static ActionId5ErrorDescriptor build(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.w("nf_play_error", "ActionID 5 NFErr_MC_NCCP_CTicketRenewalRequired, AUTHENTICATION_RENEW_REQUIRE");
        return new ActionId5ErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230783), null, new UnregisterAction(playerFragment.getActivity()), playerFragment.getString(2131231077), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
}
