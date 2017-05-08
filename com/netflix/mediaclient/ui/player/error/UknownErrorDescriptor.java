// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class UknownErrorDescriptor extends PlaybackErrorDescriptor
{
    UknownErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static UknownErrorDescriptor build(final PlayerFragment playerFragment, final String s) {
        Log.w("nf_play_error", "Uknown error");
        return new UknownErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230787), null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231077), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
}
