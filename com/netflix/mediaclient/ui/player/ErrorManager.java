// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.android.widget.UpdateDialog$Builder;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.NccpNetworkingError;
import com.netflix.mediaclient.event.network.NetworkError;
import org.json.JSONObject;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.MediaEvent;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.os.Handler;
import java.util.Locale;

public class ErrorManager
{
    public static final String[] ACTION_IDS;
    private static final String MEDIA_ERROR_CODE = "Media Error";
    public static final String MEDIA_ERR_DECODE = "MEDIA_ERR_DECODE";
    public static final String MEDIA_ERR_NETWORK = "MEDIA_ERR_NETWORK";
    public static final String MEDIA_ERR_SRC_NOT_SUPPORTED = "MEDIA_ERR_SRC_NOT_SUPPORTED";
    private static final long PLAYREADY_CERT_REVOKED = 113L;
    private static final String TAG = "ErrorManager";
    private static final Locale US_LOCALE;
    private static int appRestartCountForJPlayer = 0;
    private static final String kbHelpUrl = "https://help.netflix.com/en/node/14384";
    private PlayerActivity context;
    private boolean destroyed;
    private boolean errorPosted;
    private Runnable exit;
    private final Runnable forceExit;
    private Handler handler;
    private Runnable launchHelpInBrowser;
    private IClientLogging mClientLogging;
    private ServiceAgent$ConfigurationAgentInterface mConfig;
    private MediaEvent reportedError;
    private Runnable resetApp;
    private Runnable restartApp;
    private final Runnable unregister;
    
    static {
        US_LOCALE = Locale.US;
        ACTION_IDS = new String[] { "NFErr_MC_NCCP_NonRecoverableError", "NFErr_MC_NCCP_PotentiallyRecoverableError", "NFErr_MC_NCCP_CustomError", "NFErr_MC_NCCP_RegistrationRequired", "NFErr_MC_NCCP_CTicketRenewalRequired", "NFErr_MC_NCCP_MTicketRenewalRequired", "NFErr_MC_NCCP_ImpossibleImpossibility", "NFErr_MC_NCCP_GetNewCredentials", "NFErr_MC_NCCP_UnsupportedVersion", "NFErr_MC_NCCP_SecondaryCredentialsRenewalRequired", "NFErr_MC_NCCP_AbortPlayback", "NFErr_MC_NCCP_StaleCredentials" };
        ErrorManager.appRestartCountForJPlayer = 0;
    }
    
    public ErrorManager(final Handler handler, final PlayerActivity context, final ServiceAgent$ConfigurationAgentInterface mConfig, final IClientLogging mClientLogging) {
        this.errorPosted = false;
        this.destroyed = false;
        this.exit = new ErrorManager$4(this);
        this.restartApp = new ErrorManager$5(this);
        this.launchHelpInBrowser = new ErrorManager$6(this);
        this.resetApp = new ErrorManager$7(this);
        this.unregister = new ErrorManager$8(this);
        this.forceExit = new ErrorManager$9(this);
        this.handler = handler;
        this.context = context;
        this.mConfig = mConfig;
        this.mClientLogging = mClientLogging;
    }
    
    private ErrorManager$LinkTag extractLink(final String s, final StringBuilder sb) {
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "Trimmed message: " + s);
        }
        final String lowerCase = s.toLowerCase(ErrorManager.US_LOCALE);
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "Lower case test message: " + lowerCase);
        }
        final int index = lowerCase.indexOf("<a href=\"");
        if (index < 0) {
            Log.d("ErrorManager", "Not a concurrent stream upgrade message");
            return null;
        }
        final int index2 = lowerCase.indexOf("\"", index + 10);
        if (index2 < 0) {
            Log.d("ErrorManager", "Not a concurrent stream upgrade message");
            return null;
        }
        final ErrorManager$LinkTag errorManager$LinkTag = new ErrorManager$LinkTag(null);
        errorManager$LinkTag.href = s.substring(index + 9, index2);
        final int index3 = lowerCase.indexOf(">", index2);
        final String substring = lowerCase.substring(index3 + 1);
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "HREF extracted: " + errorManager$LinkTag.href);
            Log.d("ErrorManager", "Rest of message: " + substring);
        }
        final int index4 = lowerCase.indexOf("</a>");
        if (index > index4) {
            Log.e("ErrorManager", "Closing </a> found before <a>!" + index4 + " < " + index);
            return null;
        }
        errorManager$LinkTag.text = s.substring(index3 + 1, index4);
        String substring2;
        if (index4 + 5 >= s.length()) {
            substring2 = "";
        }
        else {
            substring2 = s.substring(index4 + 4);
        }
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "TEXT extracted: " + errorManager$LinkTag.text);
            Log.d("ErrorManager", "Rest of message: " + substring2);
        }
        sb.append(s.substring(0, index));
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "User message (part before link): " + sb.toString());
        }
        sb.append(" ").append(substring2);
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "User message (part after link): " + sb.toString());
        }
        return errorManager$LinkTag;
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForActionId1(final NccpActionId nccpActionId, final String s) {
        Log.d("ErrorManager", "actionID 1 NFErr_MC_NCCP_NonRecoverableError");
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, this.context.getString(2131493009), null, this.exit, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForActionId10(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 10 NFErr_MC_NCCP_CustomerCredentialsRenewalRequired");
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, this.context.getString(2131493015), null, this.unregister, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForActionId11(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 11 ");
        final String message = nccpActionId.getMessage();
        if (message != null) {
            final String string = message;
            if (!"".equals(message.trim())) {
                return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, string, null, this.exit, this.context.getString(2131493337), this.launchHelpInBrowser);
            }
        }
        final String string = this.context.getString(2131493016);
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, string, null, this.exit, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForActionId12(final NccpActionId nccpActionId, final String s) {
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, this.context.getString(2131493017), null, this.exit, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForActionId2(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 2 NFErr_MC_NCCP_PotentiallyRecoverableError");
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, this.context.getString(2131493010), null, this.exit, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForActionId3(final NccpActionId nccpActionId, final String s) {
        final String message = nccpActionId.getMessage();
        final Runnable exit = this.exit;
        String string;
        if (message == null) {
            string = this.context.getString(2131493011);
            Log.e("ErrorManager", "ActionID 3 NFErr_MC_NCCP_CustomError: Error message expected, but not received, displaying generic error");
        }
        else {
            if (Log.isLoggable()) {
                Log.e("ErrorManager", "ActionID 3 NFErr_MC_NCCP_CustomError: " + message);
            }
            final AlertDialogFactory$AlertDialogDescriptor errorDescriptorForConcurrentStreamUpgrade = this.getErrorDescriptorForConcurrentStreamUpgrade(nccpActionId, s);
            string = message;
            if (errorDescriptorForConcurrentStreamUpgrade != null) {
                Log.d("ErrorManager", "ActionID 3: Concurrent stream upgrade message");
                return errorDescriptorForConcurrentStreamUpgrade;
            }
        }
        return new AlertDialogFactory$AlertDialogDescriptor(s, string, null, exit);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForActionId4(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 4 NFErr_MC_NCCP_RegistrationRequired");
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, this.context.getString(2131493099), null, this.unregister, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForActionId5(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 5 NFErr_MC_NCCP_CTicketRenewalRequired, AUTHENTICATION_RENEW_REQUIRE");
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, this.context.getString(2131493012), null, this.unregister, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForActionId6(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 6 NFErr_MC_NCCP_MTicketRenewalRequired, AUTHORIZATION_RENEW_REQUIRED");
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, this.context.getString(2131493101), null, this.unregister, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForActionId7(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 7 NFErr_MC_NCCP_ImpossibleImpossibility, logout");
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, this.context.getString(2131493102), null, this.exit, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForActionId8(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 8 NFErr_MC_NCCP_GetNewCredentials");
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, this.context.getString(2131493013), null, this.unregister, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForActionId9(final NccpActionId nccpActionId, final String s) {
        final String message = nccpActionId.getMessage();
        String string;
        if (message == null) {
            string = this.context.getString(2131493014);
            Log.e("ErrorManager", "ActionID 9 NFErr_MC_NCCP_UnsupportedVersion: force exit app, generic message");
        }
        else {
            string = message;
            if (Log.isLoggable()) {
                Log.e("ErrorManager", "ActionID 9 NFErr_MC_NCCP_UnsupportedVersion: force exit app, with custom message: " + message);
                string = message;
            }
        }
        return new AlertDialogFactory$AlertDialogDescriptor(s, string, null, this.forceExit);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForConcurrentStreamUpgrade(final NccpActionId nccpActionId, final String s) {
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "Original message: " + nccpActionId.getMessage());
        }
        if (nccpActionId.getReasonCode() != 102) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        final String trimWhiteSpace = StringUtils.trimWhiteSpace(nccpActionId.getMessage());
        final ErrorManager$LinkTag link = this.extractLink(trimWhiteSpace, sb);
        if (link == null) {
            this.context.setMaxStreamsReachedDialogId(this.context.reportUiModelessViewSessionStart(IClientLogging$ModalView.maxStreamsReached));
            return new AlertDialogFactory$AlertDialogDescriptor(s, trimWhiteSpace, null, this.exit);
        }
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "Link found: href=" + link.href + ", text=" + link.text);
        }
        final String text = link.text;
        String string = null;
        Label_0194: {
            if (text != null) {
                string = text;
                if (!"".equals(text.trim())) {
                    break Label_0194;
                }
            }
            string = this.context.getString(2131493116);
        }
        final String string2 = this.context.getString(2131493117);
        Log.d("ErrorManager", "Check if link contains NCCP reason code");
        if (link.href.startsWith("RC:")) {
            Log.d("ErrorManager", "NCCP reason code found");
            try {
                return this.getErrorDescriptorForConcurrentStreamUpgradeWithNccpReasonCode(link, s, sb.toString(), string, string2);
            }
            catch (JSONException ex) {
                Log.e("ErrorManager", "Failed to process upgrade with NCCP reason code, return generic action id 3 message", (Throwable)ex);
                return null;
            }
        }
        Log.d("ErrorManager", "NCCP reason code NOT found, treat it as path of URL");
        this.context.setMaxStreamsReachedDialogId(this.context.reportUiModelessViewSessionStart(IClientLogging$ModalView.maxStreamsReached));
        return this.getErrorDescriptorForConcurrentStreamUpgradeRegular(link, s, sb.toString(), string, string2);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForConcurrentStreamUpgradeRegular(final ErrorManager$LinkTag errorManager$LinkTag, final String s, final String s2, final String s3, final String s4) {
        final ErrorManager$3 errorManager$3 = new ErrorManager$3(this, errorManager$LinkTag);
        this.context.setMaxStreamsReachedDialogId(this.context.reportUiModelessViewSessionStart(IClientLogging$ModalView.maxStreamsReached));
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, s2, s3, errorManager$3, s4, this.exit);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getErrorDescriptorForConcurrentStreamUpgradeWithNccpReasonCode(final ErrorManager$LinkTag errorManager$LinkTag, final String s, final String s2, final String s3, final String s4) {
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "Link found: href=" + errorManager$LinkTag.href + ", text=" + errorManager$LinkTag.text);
        }
        final String[] tokens = StringUtils.extractTokens(errorManager$LinkTag.href, ";");
        final JSONObject jsonObject = new JSONObject();
        if (tokens.length != 2) {
            Log.e("ErrorManager", "Problem with RC format! ; is missing!");
            return null;
        }
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "HREF token: " + tokens[0] + ", " + tokens[1]);
        }
        final String[] tokens2 = StringUtils.extractTokens(tokens[0], ":");
        if (tokens2.length != 2) {
            Log.e("ErrorManager", "Problem with RC format!");
            return null;
        }
        if (!"RC".equalsIgnoreCase(tokens2[0])) {
            Log.e("ErrorManager", "RC is NOT first element, but: " + tokens2[0]);
            return null;
        }
        jsonObject.put("RC", (Object)tokens2[1]);
        final String[] tokens3 = StringUtils.extractTokens(tokens[1], "&");
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "# of parameters found in NCCP reason code: " + tokens3.length);
        }
        for (int i = 0; i < tokens3.length; ++i) {
            final String[] tokens4 = StringUtils.extractTokens(tokens3[i], "=");
            if (tokens4.length == 2) {
                jsonObject.put(tokens4[0], (Object)tokens4[1]);
            }
            else if (Log.isLoggable()) {
                Log.w("ErrorManager", "Parameter " + i + " does not have proper format: " + tokens3[i] + ". Skipping.");
            }
        }
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "JSON: " + jsonObject);
        }
        final ErrorManager$2 errorManager$2 = new ErrorManager$2(this, jsonObject);
        this.context.setMaxStreamsReachedDialogId(this.context.reportUiModelessViewSessionStart(IClientLogging$ModalView.maxStreamsReached));
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, s2, s3, errorManager$2, s4, this.exit);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getHandler(final MediaEvent mediaEvent) {
        if (mediaEvent instanceof NccpActionId) {
            return this.getHandlerForActionIdError((NccpActionId)mediaEvent);
        }
        if (mediaEvent instanceof NetworkError) {
            return this.getHandlerForNetworkError((NetworkError)mediaEvent);
        }
        if (mediaEvent instanceof NccpNetworkingError) {
            return this.getHandlerForNetworkingError((NccpNetworkingError)mediaEvent);
        }
        if (mediaEvent instanceof Error) {
            return this.getHandlerForMediaError((Error)mediaEvent);
        }
        Log.e("ErrorManager", "Ukwnown NCCP error, display generic error!");
        return this.getUknownErrorDescriptor(mediaEvent, "");
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getHandlerForActionIdError(final NccpActionId nccpActionId) {
        Log.d("ErrorManager", "NccpError " + nccpActionId);
        switch (nccpActionId.getActionId()) {
            default: {
                Log.w("ErrorManager", "default, try to find correct error");
                return this.getUknownErrorDescriptor(nccpActionId, "");
            }
            case 1: {
                return this.getErrorDescriptorForActionId1(nccpActionId, "");
            }
            case 2: {
                return this.getErrorDescriptorForActionId2(nccpActionId, "");
            }
            case 3: {
                return this.getErrorDescriptorForActionId3(nccpActionId, "");
            }
            case 4: {
                return this.getErrorDescriptorForActionId4(nccpActionId, "");
            }
            case 5: {
                return this.getErrorDescriptorForActionId5(nccpActionId, "");
            }
            case 6: {
                return this.getErrorDescriptorForActionId6(nccpActionId, "");
            }
            case 7: {
                return this.getErrorDescriptorForActionId7(nccpActionId, "");
            }
            case 8: {
                return this.getErrorDescriptorForActionId8(nccpActionId, "");
            }
            case 9: {
                return this.getErrorDescriptorForActionId9(nccpActionId, "");
            }
            case 10: {
                return this.getErrorDescriptorForActionId10(nccpActionId, "");
            }
            case 11: {
                Log.d("ErrorManager", "ActionID 11 NFErr_MC_AbortPlayback");
                return this.getErrorDescriptorForActionId11(nccpActionId, "");
            }
            case 12: {
                Log.d("ErrorManager", "ActionID 12 NFErr_MC_StaleCredentials");
                return this.getErrorDescriptorForActionId12(nccpActionId, "");
            }
        }
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getHandlerForMediaError(final Error error) {
        final boolean checkForOpenDeviceFailureInStack = error.checkForOpenDeviceFailureInStack();
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "MediaError " + error);
            Log.d("ErrorManager", "checkForOpenDeviceFailureInStack : " + checkForOpenDeviceFailureInStack);
        }
        if (error.getError() == -268369916) {
            return new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", this.context.getString(2131493094), null, this.restartApp, this.context.getString(2131493337), this.launchHelpInBrowser);
        }
        if (error.getError() == -268369915 && checkForOpenDeviceFailureInStack) {
            final String string = this.context.getString(2131493094);
            if (this.mConfig.getCurrentPlayerType() == PlayerType.device12 && this.mConfig.isCurrentDrmWidevine()) {
                return new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", string, null, this.restartApp, this.context.getString(2131493337), this.launchHelpInBrowser);
            }
            return new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", string, null, this.resetApp, this.context.getString(2131493337), this.launchHelpInBrowser);
        }
        else {
            if (error.getError() != -268369919) {
                if (error.getError() == -268369911 && this.mConfig.getCurrentPlayerType() == PlayerType.device10) {
                    final int appRestartCountForJPlayer = ErrorManager.appRestartCountForJPlayer;
                    ErrorManager.appRestartCountForJPlayer = appRestartCountForJPlayer + 1;
                    if (appRestartCountForJPlayer >= this.mConfig.getJPlayerStreamErrorRestartCount()) {
                        if (Log.isLoggable()) {
                            Log.d("ErrorManager", "JPlayer app restart count exceded: " + this.mConfig.getJPlayerStreamErrorRestartCount());
                        }
                        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", this.context.getString(2131493038), null, this.restartApp, this.context.getString(2131493337), this.launchHelpInBrowser);
                    }
                }
                return new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", this.context.getString(2131493038), null, this.exit, this.context.getString(2131493337), this.launchHelpInBrowser);
            }
            final String string2 = this.context.getString(2131493038);
            if (error.checkForAuthFailureRegistrationRequired()) {
                this.mClientLogging.getErrorLogging().logHandledException("AuthFailure, RegistrationRequired");
                return new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", string2, null, this.resetApp, this.context.getString(2131493337), this.launchHelpInBrowser);
            }
            return new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", string2, null, this.exit, this.context.getString(2131493337), this.launchHelpInBrowser);
        }
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getHandlerForNetworkError(final NetworkError networkError) {
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "NetworkError " + networkError);
        }
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", this.context.getString(2131493132), null, this.exit, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getHandlerForNetworkingError(final NccpNetworkingError nccpNetworkingError) {
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "NccpNetworkingError " + nccpNetworkingError);
        }
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", this.context.getString(2131493066), null, this.exit, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private static JSONObject getJSonSafely(final MediaEvent mediaEvent) {
        try {
            return mediaEvent.getData();
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    private AlertDialogFactory$AlertDialogDescriptor getUknownErrorDescriptor(final MediaEvent mediaEvent, final String s) {
        Log.w("ErrorManager", "Uknown error");
        return new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, this.context.getString(2131493008), null, this.exit, this.context.getString(2131493337), this.launchHelpInBrowser);
    }
    
    private Runnable handle(final MediaEvent mediaEvent) {
        Log.d("ErrorManager", "Handle error " + mediaEvent);
        return new ErrorManager$1(this, mediaEvent);
    }
    
    private void handleError(final MediaEvent mediaEvent) {
        while (true) {
            Label_0059: {
                synchronized (this) {
                    if (this.destroyed) {
                        Log.w("ErrorManager", "ErrorManager is already destroyed, ignoring...");
                    }
                    else {
                        if (!this.handlePlayReadyRevocation(mediaEvent)) {
                            break Label_0059;
                        }
                        this.reportStartPlayEnded(RootCause.clientFailure, ActionOnUIError.handledSilently, null, null, mediaEvent);
                        Log.d("ErrorManager", "Playready certificate revocated, do not display error!");
                    }
                    return;
                }
            }
            final MediaEvent mediaEvent2;
            final AlertDialogFactory$AlertDialogDescriptor handler = this.getHandler(mediaEvent2);
            if (handler == null) {
                Log.w("ErrorManager", "We decided to ignore " + mediaEvent2);
                this.errorPosted = false;
                return;
            }
            this.reportStartPlayEnded(RootCause.clientFailure, ActionOnUIError.displayedError, handler.getMessage(), null, mediaEvent2);
            final UpdateDialog$Builder dialog = AlertDialogFactory.createDialog((Context)this.context, this.handler, handler);
            if (dialog != null) {
                dialog.show();
                this.context.setErrorDialogId(this.context.reportUiModelessViewSessionStart(IClientLogging$ModalView.errorDialog));
            }
        }
    }
    
    private boolean handlePlayReadyRevocation(final MediaEvent mediaEvent) {
        if (!(mediaEvent instanceof NccpActionId)) {
            Log.d("ErrorManager", "Not action ID error");
            return false;
        }
        if (((NccpActionId)mediaEvent).getReasonCode() == 113L) {
            Log.e("ErrorManager", "Playready certificate revocated, we do not have backup player, report an error!");
            return false;
        }
        Log.e("ErrorManager", "Regular error, handle as popup");
        return false;
    }
    
    private void reportStartPlayEnded(final RootCause rootCause, final ActionOnUIError actionOnUIError, final String s, final Integer n, final MediaEvent mediaEvent) {
        if (this.context.getState().playStartInProgress.getAndSet(false)) {
            UserActionLogUtils.reportPlayActionEnded((Context)this.context, IClientLogging$CompletionReason.failed, StatusUtils.createUiError("Media Error", actionOnUIError, s, true, rootCause, ConsolidatedLoggingUtils.createDebug(null, getJSonSafely(mediaEvent))), n, this.context.getServiceManager().getConfiguration().getCurrentPlayerType());
        }
    }
    
    public boolean addError(final NccpError nccpError) {
        while (true) {
            boolean b = true;
            Label_0050: {
                synchronized (this) {
                    if (this.destroyed) {
                        Log.w("ErrorManager", "ErrorManager is already destroyed, ignoring...");
                        b = false;
                    }
                    else {
                        if (!this.errorPosted) {
                            break Label_0050;
                        }
                        Log.w("ErrorManager", "ErrorManager already posted an error message, ignoring...");
                    }
                    return b;
                }
            }
            this.errorPosted = true;
            final MediaEvent reportedError;
            this.reportedError = reportedError;
            this.handler.post(this.handle(reportedError));
            return b;
        }
    }
    
    public boolean addMediaError(final Error error) {
        synchronized (this) {
            if (this.destroyed) {
                Log.w("ErrorManager", "ErrorManager is already destroyed, ignoring...");
                return false;
            }
            if (this.errorPosted) {
                Log.w("ErrorManager", "ErrorManager already posted an error message, ignoring...");
                return true;
            }
        }
        this.errorPosted = true;
        final MediaEvent reportedError;
        this.reportedError = reportedError;
        this.handler.post(this.handle(reportedError));
        // monitorexit(this)
        return true;
    }
    
    public void destroy() {
        synchronized (this) {
            this.destroyed = true;
            this.context = null;
            this.handler = null;
            this.exit = null;
        }
    }
    
    public MediaEvent getReportedError() {
        return this.reportedError;
    }
    
    public final boolean isErrorReported() {
        return this.errorPosted;
    }
    
    public void reset() {
        synchronized (this) {
            this.errorPosted = false;
        }
    }
}
