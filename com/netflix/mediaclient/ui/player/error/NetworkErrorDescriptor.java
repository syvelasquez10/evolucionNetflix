// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.network.NetworkError;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class NetworkErrorDescriptor extends PlaybackErrorDescriptor
{
    NetworkErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static NetworkErrorDescriptor build(final PlayerFragment playerFragment, final NetworkError networkError) {
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "NetworkError " + networkError);
        }
        return new NetworkErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", playerFragment.getString(2131231261), null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231077), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
}
