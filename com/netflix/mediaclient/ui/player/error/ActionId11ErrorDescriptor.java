// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class ActionId11ErrorDescriptor extends PlaybackErrorDescriptor
{
    ActionId11ErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static ActionId11ErrorDescriptor build(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.d("nf_play_error", "ActionID 11 NFErr_MC_AbortPlayback");
        String s2;
        if (StringUtils.isEmpty(s2 = nccpActionId.getMessage())) {
            s2 = playerFragment.getString(2131230778);
        }
        return new ActionId11ErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, s2, null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231077), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
}
