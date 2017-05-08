// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.ui.player.PlayerFragment;

class ActionIdErrorDescriptorFactory
{
    protected static final String TAG = "ErrorManager";
    
    static ErrorDescriptor getHandlerForActionIdError(final PlayerFragment playerFragment, final NccpActionId nccpActionId) {
        Log.d("ErrorManager", "NccpError " + nccpActionId);
        switch (nccpActionId.getActionId()) {
            default: {
                Log.w("ErrorManager", "default, try to find correct error");
                return UknownErrorDescriptor.build(playerFragment, "");
            }
            case 1: {
                return ActionId1ErrorDescriptor.build(playerFragment, nccpActionId, "");
            }
            case 2: {
                return ActionId2ErrorDescriptor.build(playerFragment, nccpActionId, "");
            }
            case 3: {
                return ActionId3ErrorDescriptor.build(playerFragment, nccpActionId, "");
            }
            case 4: {
                return ActionId4ErrorDescriptor.build(playerFragment, nccpActionId, "");
            }
            case 5: {
                return ActionId5ErrorDescriptor.build(playerFragment, nccpActionId, "");
            }
            case 6: {
                return ActionId6ErrorDescriptor.build(playerFragment, nccpActionId, "");
            }
            case 7: {
                return ActionId7ErrorDescriptor.build(playerFragment, nccpActionId, "");
            }
            case 8: {
                return ActionId8ErrorDescriptor.build(playerFragment, nccpActionId, "");
            }
            case 9: {
                return ActionId9ErrorDescriptor.build(playerFragment, nccpActionId, "");
            }
            case 10: {
                return ActionId10ErrorDescriptor.build(playerFragment, nccpActionId, "");
            }
            case 11: {
                return ActionId11ErrorDescriptor.build(playerFragment, nccpActionId, "");
            }
            case 12: {
                return ActionId12ErrorDescriptor.build(playerFragment, nccpActionId, "");
            }
        }
    }
}
