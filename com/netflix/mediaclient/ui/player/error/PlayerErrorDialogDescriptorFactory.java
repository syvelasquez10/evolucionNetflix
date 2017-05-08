// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.servicemgr.IPlayer$PlaybackError;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.NccpNetworkingError;
import com.netflix.mediaclient.event.network.NetworkError;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.event.nrdp.media.MediaEvent;
import com.netflix.mediaclient.ui.player.PlayerFragment;

public final class PlayerErrorDialogDescriptorFactory
{
    protected static final String TAG = "ErrorManager";
    
    public static ErrorDescriptor getHandler(final PlayerFragment playerFragment, final MediaEvent mediaEvent) {
        if (!playerFragment.isActivityValid()) {
            Log.i("ErrorManager", "Fragment was already detached from the activity - skipping error...");
            return null;
        }
        if (mediaEvent instanceof NccpActionId) {
            return ActionIdErrorDescriptorFactory.getHandlerForActionIdError(playerFragment, (NccpActionId)mediaEvent);
        }
        if (mediaEvent instanceof NetworkError) {
            return NetworkErrorDescriptor.build(playerFragment, (NetworkError)mediaEvent);
        }
        if (mediaEvent instanceof NccpNetworkingError) {
            return NccpNetworkingErrorDescriptor.build(playerFragment, (NccpNetworkingError)mediaEvent);
        }
        if (mediaEvent instanceof Error) {
            return MediaErrorDescriptorFactory.getHandlerForMediaError(playerFragment, (Error)mediaEvent);
        }
        Log.e("ErrorManager", "Ukwnown NCCP error, display generic error!");
        return UknownErrorDescriptor.build(playerFragment, "");
    }
    
    public static ErrorDescriptor getHandlerForPlaybackError(final PlayerFragment playerFragment, final IPlayer$PlaybackError player$PlaybackError) {
        return new UknownErrorDescriptor(new AlertDialogFactory$AlertDialogDescriptor("", playerFragment.getString(2131296409, new Object[] { "(" + player$PlaybackError.getUiDisplayErrorCode() + ")" }), null, new ExitPlayerAction(playerFragment.getActivity())));
    }
}
