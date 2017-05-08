// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.service.error.action.ForceExitAction;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class ActionId9ErrorDescriptor extends PlaybackErrorDescriptor
{
    ActionId9ErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static ActionId9ErrorDescriptor build(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        final String message = nccpActionId.getMessage();
        String string;
        if (message == null) {
            string = playerFragment.getString(2131230782);
            Log.e("nf_play_error", "ActionID 9 NFErr_MC_NCCP_UnsupportedVersion: force exit app, generic message");
        }
        else {
            string = message;
            if (Log.isLoggable()) {
                Log.e("nf_play_error", "ActionID 9 NFErr_MC_NCCP_UnsupportedVersion: force exit app, with custom message: " + message);
                string = message;
            }
        }
        return new ActionId9ErrorDescriptor(new AlertDialogFactory$AlertDialogDescriptor(s, string, null, new ForceExitAction(playerFragment.getActivity())));
    }
}
