// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.error.action.RestartApplicationAction;
import com.netflix.mediaclient.service.error.action.ResetApplicationAction;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.NccpNetworkingError;
import com.netflix.mediaclient.event.network.NetworkError;
import com.netflix.mediaclient.event.nrdp.media.MediaEvent;
import org.json.JSONObject;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.error.action.ForceExitAction;
import com.netflix.mediaclient.service.error.action.UnregisterAction;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.Log;
import java.util.Locale;

public final class PlayerErrorDialogDescriptorFactory
{
    private static final int NFErr_HTTPErrorCode = -268435423;
    private static final int NFErr_Interrupted = -268369911;
    private static final int NFErr_MC_AcquireLicenseFailure = -268369915;
    private static final int NFErr_MC_AuthFailure = -268369919;
    private static final int NFErr_MC_OpenDeviceFailure = -268369916;
    protected static final String TAG = "ErrorManager";
    private static final Locale US_LOCALE;
    private static final String kbHelpUrl = "https://help.netflix.com/en/node/14384";
    private static final String kbProxyUrl = "https://netflix.com/proxy";
    private static int sAppRestartCountForJPlayer;
    
    static {
        US_LOCALE = Locale.US;
        PlayerErrorDialogDescriptorFactory.sAppRestartCountForJPlayer = 0;
    }
    
    private static PlayerErrorDialogDescriptorFactory$LinkTag extractLink(final String s, final StringBuilder sb) {
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "Trimmed message: " + s);
        }
        final String lowerCase = s.toLowerCase(PlayerErrorDialogDescriptorFactory.US_LOCALE);
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
        final PlayerErrorDialogDescriptorFactory$LinkTag playerErrorDialogDescriptorFactory$LinkTag = new PlayerErrorDialogDescriptorFactory$LinkTag(null);
        playerErrorDialogDescriptorFactory$LinkTag.href = s.substring(index + 9, index2);
        final int index3 = lowerCase.indexOf(">", index2);
        final String substring = lowerCase.substring(index3 + 1);
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "HREF extracted: " + playerErrorDialogDescriptorFactory$LinkTag.href);
            Log.d("ErrorManager", "Rest of message: " + substring);
        }
        final int index4 = lowerCase.indexOf("</a>");
        if (index > index4) {
            Log.e("ErrorManager", "Closing </a> found before <a>!" + index4 + " < " + index);
            return null;
        }
        playerErrorDialogDescriptorFactory$LinkTag.text = s.substring(index3 + 1, index4);
        String substring2;
        if (index4 + 5 >= s.length()) {
            substring2 = "";
        }
        else {
            substring2 = s.substring(index4 + 4);
        }
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "TEXT extracted: " + playerErrorDialogDescriptorFactory$LinkTag.text);
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
        return playerErrorDialogDescriptorFactory$LinkTag;
    }
    
    private static ErrorDescriptor getErrorDescriptorForActionId1(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.d("ErrorManager", "actionID 1 NFErr_MC_NCCP_NonRecoverableError");
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230777), null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    private static ErrorDescriptor getErrorDescriptorForActionId10(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 10 NFErr_MC_NCCP_CustomerCredentialsRenewalRequired");
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230774), null, new UnregisterAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    private static ErrorDescriptor getErrorDescriptorForActionId11(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 11 ");
        final String message = nccpActionId.getMessage();
        if (message != null) {
            final String string = message;
            if (!"".equals(message.trim())) {
                return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, string, null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
            }
        }
        final String string = playerFragment.getString(2131230775);
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, string, null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    private static ErrorDescriptor getErrorDescriptorForActionId12(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230776), null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    private static ErrorDescriptor getErrorDescriptorForActionId2(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 2 NFErr_MC_NCCP_PotentiallyRecoverableError");
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230778), null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    private static ErrorDescriptor getErrorDescriptorForActionId3(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        final String message = nccpActionId.getMessage();
        final ExitPlayerAction exitPlayerAction = new ExitPlayerAction(playerFragment.getActivity());
        String string;
        if (message == null) {
            string = playerFragment.getString(2131230779);
            Log.e("ErrorManager", "ActionID 3 NFErr_MC_NCCP_CustomError: Error message expected, but not received, displaying generic error");
        }
        else {
            if (Log.isLoggable()) {
                Log.e("ErrorManager", "ActionID 3 NFErr_MC_NCCP_CustomError: " + message);
            }
            final ErrorDescriptor errorDescriptorForConcurrentStreamUpgrade = getErrorDescriptorForConcurrentStreamUpgrade(playerFragment, nccpActionId, s);
            string = message;
            if (errorDescriptorForConcurrentStreamUpgrade != null) {
                Log.d("ErrorManager", "ActionID 3: Concurrent stream upgrade message");
                return errorDescriptorForConcurrentStreamUpgrade;
            }
        }
        return new PlaybackErrorDescriptor(new AlertDialogFactory$AlertDialogDescriptor(s, string, null, exitPlayerAction));
    }
    
    private static ErrorDescriptor getErrorDescriptorForActionId4(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 4 NFErr_MC_NCCP_RegistrationRequired");
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230833), null, new UnregisterAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    private static ErrorDescriptor getErrorDescriptorForActionId5(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 5 NFErr_MC_NCCP_CTicketRenewalRequired, AUTHENTICATION_RENEW_REQUIRE");
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230780), null, new UnregisterAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    private static ErrorDescriptor getErrorDescriptorForActionId6(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 6 NFErr_MC_NCCP_MTicketRenewalRequired, AUTHORIZATION_RENEW_REQUIRED");
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230830), null, new UnregisterAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    private static ErrorDescriptor getErrorDescriptorForActionId7(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 7 NFErr_MC_NCCP_ImpossibleImpossibility, logout");
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230829), null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    private static ErrorDescriptor getErrorDescriptorForActionId8(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 8 NFErr_MC_NCCP_GetNewCredentials");
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230781), null, new UnregisterAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    private static ErrorDescriptor getErrorDescriptorForActionId9(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        final String message = nccpActionId.getMessage();
        String string;
        if (message == null) {
            string = playerFragment.getString(2131230782);
            Log.e("ErrorManager", "ActionID 9 NFErr_MC_NCCP_UnsupportedVersion: force exit app, generic message");
        }
        else {
            string = message;
            if (Log.isLoggable()) {
                Log.e("ErrorManager", "ActionID 9 NFErr_MC_NCCP_UnsupportedVersion: force exit app, with custom message: " + message);
                string = message;
            }
        }
        return new PlaybackErrorDescriptor(new AlertDialogFactory$AlertDialogDescriptor(s, string, null, new ForceExitAction(playerFragment.getActivity())));
    }
    
    private static ErrorDescriptor getErrorDescriptorForConcurrentStreamUpgrade(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "Original message: " + nccpActionId.getMessage());
        }
        if (nccpActionId.getReasonCode() != 102) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        final String trimWhiteSpace = StringUtils.trimWhiteSpace(nccpActionId.getMessage());
        final PlayerErrorDialogDescriptorFactory$LinkTag link = extractLink(trimWhiteSpace, sb);
        if (link == null) {
            final ExitPlayerAction exitPlayerAction = new ExitPlayerAction(playerFragment.getActivity());
            playerFragment.setMaxStreamsReachedDialogId(playerFragment.getNetflixActivity().reportUiModelessViewSessionStart(IClientLogging$ModalView.maxStreamsReached));
            return new PlaybackErrorDescriptor(new AlertDialogFactory$AlertDialogDescriptor(s, trimWhiteSpace, null, exitPlayerAction));
        }
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "Link found: href=" + link.href + ", text=" + link.text);
        }
        final String text = link.text;
        String string = null;
        Label_0203: {
            if (text != null) {
                string = text;
                if (!"".equals(text.trim())) {
                    break Label_0203;
                }
            }
            string = playerFragment.getString(2131230997);
        }
        final String string2 = playerFragment.getString(2131230996);
        Log.d("ErrorManager", "Check if link contains NCCP reason code");
        if (link.href.startsWith("RC:")) {
            Log.d("ErrorManager", "NCCP reason code found");
            try {
                return getErrorDescriptorForConcurrentStreamUpgradeWithNccpReasonCode(playerFragment, link, s, sb.toString(), string, string2);
            }
            catch (JSONException ex) {
                Log.e("ErrorManager", "Failed to process upgrade with NCCP reason code, return generic action id 3 message", (Throwable)ex);
                return null;
            }
        }
        Log.d("ErrorManager", "NCCP reason code NOT found, treat it as path of URL");
        playerFragment.setMaxStreamsReachedDialogId(playerFragment.getNetflixActivity().reportUiModelessViewSessionStart(IClientLogging$ModalView.maxStreamsReached));
        return getErrorDescriptorForConcurrentStreamUpgradeRegular(playerFragment, link, s, sb.toString(), string, string2);
    }
    
    private static ErrorDescriptor getErrorDescriptorForConcurrentStreamUpgradeRegular(final PlayerFragment playerFragment, final PlayerErrorDialogDescriptorFactory$LinkTag playerErrorDialogDescriptorFactory$LinkTag, final String s, final String s2, final String s3, final String s4) {
        final PlayerErrorDialogDescriptorFactory$2 playerErrorDialogDescriptorFactory$2 = new PlayerErrorDialogDescriptorFactory$2(playerErrorDialogDescriptorFactory$LinkTag, playerFragment);
        playerFragment.setMaxStreamsReachedDialogId(playerFragment.getNetflixActivity().reportUiModelessViewSessionStart(IClientLogging$ModalView.maxStreamsReached));
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, s2, s3, playerErrorDialogDescriptorFactory$2, s4, new ExitPlayerAction(playerFragment.getActivity())));
    }
    
    private static ErrorDescriptor getErrorDescriptorForConcurrentStreamUpgradeWithNccpReasonCode(final PlayerFragment playerFragment, final PlayerErrorDialogDescriptorFactory$LinkTag playerErrorDialogDescriptorFactory$LinkTag, final String s, final String s2, final String s3, final String s4) {
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "Link found: href=" + playerErrorDialogDescriptorFactory$LinkTag.href + ", text=" + playerErrorDialogDescriptorFactory$LinkTag.text);
        }
        final String[] tokens = StringUtils.extractTokens(playerErrorDialogDescriptorFactory$LinkTag.href, ";");
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
        final PlayerErrorDialogDescriptorFactory$1 playerErrorDialogDescriptorFactory$1 = new PlayerErrorDialogDescriptorFactory$1(playerFragment, jsonObject);
        playerFragment.setMaxStreamsReachedDialogId(playerFragment.getNetflixActivity().reportUiModelessViewSessionStart(IClientLogging$ModalView.maxStreamsReached));
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, s2, s3, playerErrorDialogDescriptorFactory$1, s4, new ExitPlayerAction(playerFragment.getActivity())));
    }
    
    public static ErrorDescriptor getHandler(final PlayerFragment playerFragment, final MediaEvent mediaEvent) {
        if (!playerFragment.isActivityValid()) {
            Log.i("ErrorManager", "Fragment was already detached from the activity - skipping error...");
            return null;
        }
        if (mediaEvent instanceof NccpActionId) {
            return getHandlerForActionIdError(playerFragment, (NccpActionId)mediaEvent);
        }
        if (mediaEvent instanceof NetworkError) {
            return getHandlerForNetworkError(playerFragment, (NetworkError)mediaEvent);
        }
        if (mediaEvent instanceof NccpNetworkingError) {
            return getHandlerForNetworkingError(playerFragment, (NccpNetworkingError)mediaEvent);
        }
        if (mediaEvent instanceof Error) {
            return getHandlerForMediaError(playerFragment, (Error)mediaEvent);
        }
        Log.e("ErrorManager", "Ukwnown NCCP error, display generic error!");
        return getUknownErrorDescriptor(playerFragment, mediaEvent, "");
    }
    
    private static ErrorDescriptor getHandlerForActionIdError(final PlayerFragment playerFragment, final NccpActionId nccpActionId) {
        Log.d("ErrorManager", "NccpError " + nccpActionId);
        switch (nccpActionId.getActionId()) {
            default: {
                Log.w("ErrorManager", "default, try to find correct error");
                return getUknownErrorDescriptor(playerFragment, nccpActionId, "");
            }
            case 1: {
                return getErrorDescriptorForActionId1(playerFragment, nccpActionId, "");
            }
            case 2: {
                return getErrorDescriptorForActionId2(playerFragment, nccpActionId, "");
            }
            case 3: {
                return getErrorDescriptorForActionId3(playerFragment, nccpActionId, "");
            }
            case 4: {
                return getErrorDescriptorForActionId4(playerFragment, nccpActionId, "");
            }
            case 5: {
                return getErrorDescriptorForActionId5(playerFragment, nccpActionId, "");
            }
            case 6: {
                return getErrorDescriptorForActionId6(playerFragment, nccpActionId, "");
            }
            case 7: {
                return getErrorDescriptorForActionId7(playerFragment, nccpActionId, "");
            }
            case 8: {
                return getErrorDescriptorForActionId8(playerFragment, nccpActionId, "");
            }
            case 9: {
                return getErrorDescriptorForActionId9(playerFragment, nccpActionId, "");
            }
            case 10: {
                return getErrorDescriptorForActionId10(playerFragment, nccpActionId, "");
            }
            case 11: {
                Log.d("ErrorManager", "ActionID 11 NFErr_MC_AbortPlayback");
                return getErrorDescriptorForActionId11(playerFragment, nccpActionId, "");
            }
            case 12: {
                Log.d("ErrorManager", "ActionID 12 NFErr_MC_StaleCredentials");
                return getErrorDescriptorForActionId12(playerFragment, nccpActionId, "");
            }
        }
    }
    
    private static ErrorDescriptor getHandlerForMediaError(final PlayerFragment playerFragment, final Error error) {
        final ServiceAgent$ConfigurationAgentInterface configuration = playerFragment.getNetflixActivity().getServiceManager().getConfiguration();
        final boolean checkForOpenDeviceFailureInStack = error.checkForOpenDeviceFailureInStack();
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "MediaError " + error);
            Log.d("ErrorManager", "checkForOpenDeviceFailureInStack : " + checkForOpenDeviceFailureInStack);
        }
        final ExitPlayerAction exitPlayerAction = new ExitPlayerAction(playerFragment.getActivity());
        final ResetApplicationAction resetApplicationAction = new ResetApplicationAction(playerFragment.getActivity());
        AlertDialogFactory$TwoButtonAlertDialogDescriptor alertDialogFactory$TwoButtonAlertDialogDescriptor;
        if (error.getError() == -268369916) {
            alertDialogFactory$TwoButtonAlertDialogDescriptor = new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", playerFragment.getString(2131230838), null, new RestartApplicationAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384"));
        }
        else if (error.getError() == -268369915 && checkForOpenDeviceFailureInStack) {
            final String string = playerFragment.getString(2131230838);
            final LaunchHelpInBrowserAction launchHelpInBrowserAction = new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384");
            final RestartApplicationAction restartApplicationAction = new RestartApplicationAction(playerFragment.getActivity());
            if (configuration.getCurrentPlayerType() == PlayerType.device12 && configuration.isCurrentDrmWidevine()) {
                alertDialogFactory$TwoButtonAlertDialogDescriptor = new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", string, null, restartApplicationAction, playerFragment.getString(2131231058), launchHelpInBrowserAction);
            }
            else {
                alertDialogFactory$TwoButtonAlertDialogDescriptor = new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", string, null, resetApplicationAction, playerFragment.getString(2131231058), launchHelpInBrowserAction);
            }
        }
        else if (error.getError() == -268369919) {
            final String string2 = playerFragment.getString(2131230808);
            final LaunchHelpInBrowserAction launchHelpInBrowserAction2 = new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384");
            if (error.checkForAuthFailureRegistrationRequired()) {
                playerFragment.getNetflixActivity().getServiceManager().getClientLogging().getErrorLogging().logHandledException("AuthFailure, RegistrationRequired");
                alertDialogFactory$TwoButtonAlertDialogDescriptor = new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", string2, null, resetApplicationAction, playerFragment.getString(2131231058), launchHelpInBrowserAction2);
            }
            else {
                alertDialogFactory$TwoButtonAlertDialogDescriptor = new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", string2, null, exitPlayerAction, playerFragment.getString(2131231058), launchHelpInBrowserAction2);
            }
        }
        else if (error.getError() == -268435423 && error.checkForStreamingFailureHttp420()) {
            alertDialogFactory$TwoButtonAlertDialogDescriptor = new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", playerFragment.getString(2131231268), null, exitPlayerAction, playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://netflix.com/proxy"));
        }
        else {
            if (error.getError() == -268369911 && configuration.getCurrentPlayerType() == PlayerType.device10) {
                final int sAppRestartCountForJPlayer = PlayerErrorDialogDescriptorFactory.sAppRestartCountForJPlayer;
                PlayerErrorDialogDescriptorFactory.sAppRestartCountForJPlayer = sAppRestartCountForJPlayer + 1;
                if (sAppRestartCountForJPlayer >= configuration.getJPlayerStreamErrorRestartCount()) {
                    if (Log.isLoggable()) {
                        Log.d("ErrorManager", "JPlayer app restart count exceded: " + configuration.getJPlayerStreamErrorRestartCount());
                    }
                    alertDialogFactory$TwoButtonAlertDialogDescriptor = new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", playerFragment.getString(2131230808), null, new RestartApplicationAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384"));
                    return new PlaybackErrorDescriptor(alertDialogFactory$TwoButtonAlertDialogDescriptor);
                }
            }
            alertDialogFactory$TwoButtonAlertDialogDescriptor = new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", playerFragment.getString(2131230808), null, exitPlayerAction, playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384"));
        }
        return new PlaybackErrorDescriptor(alertDialogFactory$TwoButtonAlertDialogDescriptor);
    }
    
    private static ErrorDescriptor getHandlerForNetworkError(final PlayerFragment playerFragment, final NetworkError networkError) {
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "NetworkError " + networkError);
        }
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", playerFragment.getString(2131231202), null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    private static ErrorDescriptor getHandlerForNetworkingError(final PlayerFragment playerFragment, final NccpNetworkingError nccpNetworkingError) {
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "NccpNetworkingError " + nccpNetworkingError);
        }
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", playerFragment.getString(2131230847), null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
    
    private static ErrorDescriptor getUknownErrorDescriptor(final PlayerFragment playerFragment, final MediaEvent mediaEvent, final String s) {
        Log.w("ErrorManager", "Uknown error");
        return new PlaybackErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, playerFragment.getString(2131230784), null, new ExitPlayerAction(playerFragment.getActivity()), playerFragment.getString(2131231058), new LaunchHelpInBrowserAction(playerFragment.getActivity(), "https://help.netflix.com/en/node/14384")));
    }
}
