// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.service.error.action.ResetApplicationAction;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class AuthFailureErrorDescriptor extends PlaybackErrorDescriptor
{
    private static final int NFErr_MC_AuthFailure = -268369919;
    
    AuthFailureErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static AuthFailureErrorDescriptor build(final PlayerFragment playerFragment, final Error error) {
        final ExitPlayerAction exitPlayerAction = new ExitPlayerAction(playerFragment.getActivity());
        final ResetApplicationAction resetApplicationAction = new ResetApplicationAction(playerFragment.getActivity());
        final String string = playerFragment.getString(2131230808);
        final LaunchHelpInBrowserAction launchHelpInBrowserAction = new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384");
        AlertDialogFactory$TwoButtonAlertDialogDescriptor alertDialogFactory$TwoButtonAlertDialogDescriptor;
        if (error.checkForAuthFailureRegistrationRequired()) {
            playerFragment.getNetflixActivity().getServiceManager().getClientLogging().getErrorLogging().logHandledException("AuthFailure, RegistrationRequired");
            alertDialogFactory$TwoButtonAlertDialogDescriptor = new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", string, null, resetApplicationAction, playerFragment.getString(2131231059), launchHelpInBrowserAction);
        }
        else {
            alertDialogFactory$TwoButtonAlertDialogDescriptor = new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", string, null, exitPlayerAction, playerFragment.getString(2131231059), launchHelpInBrowserAction);
        }
        return new AuthFailureErrorDescriptor(alertDialogFactory$TwoButtonAlertDialogDescriptor);
    }
    
    static boolean isValid(final Error error) {
        return error.getError() == -268369919;
    }
}
