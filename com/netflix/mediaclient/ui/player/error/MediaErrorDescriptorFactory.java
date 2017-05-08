// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.ui.player.PlayerFragment;

class MediaErrorDescriptorFactory
{
    protected static final String TAG = "ErrorManager";
    
    static ErrorDescriptor getHandlerForMediaError(final PlayerFragment playerFragment, final Error error) {
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "MediaError " + error);
            Log.d("ErrorManager", "checkForOpenDeviceFailureInStack : " + error.checkForOpenDeviceFailureInStack());
        }
        final ServiceAgent$ConfigurationAgentInterface configuration = playerFragment.getNetflixActivity().getServiceManager().getConfiguration();
        if (OpenDeviceFailureErrorDescriptor.isValid(error)) {
            return OpenDeviceFailureErrorDescriptor.build(playerFragment, error);
        }
        if (OpenDeviceFailureRestartErrorDescriptor.isValid(error)) {
            return OpenDeviceFailureRestartErrorDescriptor.build(playerFragment, error);
        }
        if (AuthFailureErrorDescriptor.isValid(error)) {
            return AuthFailureErrorDescriptor.build(playerFragment, error);
        }
        if (StreamingFailureHttp420ErrorDescriptor.isValid(error)) {
            return StreamingFailureHttp420ErrorDescriptor.build(playerFragment, error);
        }
        if (JPlayerRestartErrorDescriptor.isValid(error, configuration)) {
            return JPlayerRestartErrorDescriptor.build(playerFragment, error);
        }
        return DefaultMediaErrorDescriptor.build(playerFragment, error);
    }
}
