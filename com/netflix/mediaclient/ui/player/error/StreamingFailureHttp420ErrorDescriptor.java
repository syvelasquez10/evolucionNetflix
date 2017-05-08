// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class StreamingFailureHttp420ErrorDescriptor extends PlaybackErrorDescriptor
{
    private static final int NFErr_HTTPErrorCode = -268435423;
    
    StreamingFailureHttp420ErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static StreamingFailureHttp420ErrorDescriptor build(final PlayerFragment playerFragment, final Error error) {
        return new StreamingFailureHttp420ErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", playerFragment.getString(2131231275), null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231061), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://netflix.com/proxy")));
    }
    
    static boolean isValid(final Error error) {
        return error.getError() == -268435423 && error.checkForStreamingFailureHttp420();
    }
}
