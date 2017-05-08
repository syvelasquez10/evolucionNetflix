// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import android.app.Activity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.service.error.action.RestartApplicationAction;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

public class JPlayerRestartErrorDescriptor extends PlaybackErrorDescriptor
{
    private static final int NFErr_Interrupted = -268369911;
    private static int sAppRestartCountForJPlayer;
    
    static {
        JPlayerRestartErrorDescriptor.sAppRestartCountForJPlayer = 0;
    }
    
    JPlayerRestartErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static JPlayerRestartErrorDescriptor build(final PlayerFragment playerFragment, final Error error) {
        return new JPlayerRestartErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", playerFragment.getString(2131230808), null, new RestartApplicationAction(playerFragment.getActivity()), playerFragment.getString(2131231059), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    static boolean isValid(final Error error, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        while (true) {
            Label_0085: {
                if (error.getError() != -268369911 || serviceAgent$ConfigurationAgentInterface.getCurrentPlayerType() != PlayerType.device10) {
                    break Label_0085;
                }
                final int sAppRestartCountForJPlayer = JPlayerRestartErrorDescriptor.sAppRestartCountForJPlayer;
                JPlayerRestartErrorDescriptor.sAppRestartCountForJPlayer = sAppRestartCountForJPlayer + 1;
                if (sAppRestartCountForJPlayer < serviceAgent$ConfigurationAgentInterface.getJPlayerStreamErrorRestartCount()) {
                    break Label_0085;
                }
                final boolean b = true;
                if (b && Log.isLoggable()) {
                    Log.d("nf_play_error", "JPlayer app restart count exceded: " + serviceAgent$ConfigurationAgentInterface.getJPlayerStreamErrorRestartCount());
                }
                return b;
            }
            final boolean b = false;
            continue;
        }
    }
}
