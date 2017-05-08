// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.service.error.action.RestartApplicationAction;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class OpenDeviceFailureErrorDescriptor extends PlaybackErrorDescriptor
{
    private static final int NFErr_MC_OpenDeviceFailure = -268369916;
    
    OpenDeviceFailureErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static OpenDeviceFailureErrorDescriptor build(final PlayerFragment playerFragment, final Error error) {
        return new OpenDeviceFailureErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", playerFragment.getString(2131296391), null, new RestartApplicationAction(playerFragment.getActivity()), playerFragment.getString(2131296633), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    static boolean isValid(final Error error) {
        return error.getError() == -268369916;
    }
}
