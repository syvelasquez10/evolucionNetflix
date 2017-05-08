// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.NccpNetworkingError;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class NccpNetworkingErrorDescriptor extends PlaybackErrorDescriptor
{
    NccpNetworkingErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static NccpNetworkingErrorDescriptor build(final PlayerFragment playerFragment, final NccpNetworkingError nccpNetworkingError) {
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "NccpNetworkingError " + nccpNetworkingError);
        }
        return new NccpNetworkingErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", playerFragment.getString(2131230847), null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231059), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
}
