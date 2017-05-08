// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class ActionId3ErrorDescriptor extends PlaybackErrorDescriptor
{
    ActionId3ErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static ErrorDescriptor build(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        if (nccpActionId.getReasonCode() == 102) {
            return ConcurentStreamUpgradeErrorDescriptor.build(playerFragment, nccpActionId, s);
        }
        return createGeneric(playerFragment, nccpActionId, s);
    }
    
    private static ActionId3ErrorDescriptor createGeneric(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        final String message = nccpActionId.getMessage();
        final ExitPlayerAction exitPlayerAction = new ExitPlayerAction(playerFragment.getActivity());
        String string;
        if (StringUtils.isEmpty(message)) {
            string = playerFragment.getString(2131230779);
            Log.e("nf_play_error", "ActionID 3 NFErr_MC_NCCP_CustomError: Error message expected, but not received, displaying generic error");
        }
        else {
            string = message;
            if (Log.isLoggable()) {
                Log.e("nf_play_error", "ActionID 3 NFErr_MC_NCCP_CustomError: " + message);
                string = message;
            }
        }
        return new ActionId3ErrorDescriptor(new AlertDialogFactory$AlertDialogDescriptor(s, string, null, exitPlayerAction));
    }
}
