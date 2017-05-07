// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.error;

public abstract class NFError
{
    public static final String[] ACTION_IDS;
    private static final long[] NFERRS;
    private static final String[] NFERRS_labels;
    private static final long NFERR_CATEGORY_DEVICEACCOUNT_VAL = 4026728448L;
    private static final long NFERR_CATEGORY_GENERICERROR_VAL = 4026531840L;
    private static final long NFERR_CATEGORY_MASK = 4294901760L;
    private static final long NFERR_CATEGORY_MCERROR_VAL = 4026597376L;
    private static final long NFERR_CATEGORY_NCCPERROR_VAL = 4026662912L;
    private static final long NFERR_CATEGORY_SUCCESS_VAL = 0L;
    public static final long NFErr_ASFDataError = 4026531863L;
    public static final long NFErr_ASFSkipData = 4026531864L;
    public static final long NFErr_ArrayOutofBoundary = 4026531852L;
    public static final long NFErr_Bad = 4026531841L;
    public static final long NFErr_BadParameter = 4026531845L;
    public static final long NFErr_CMDWhileWaitingEOS = 4026531865L;
    public static final long NFErr_Category_GenericError = 4026531840L;
    public static final long NFErr_Category_McError = 4026597376L;
    public static final long NFErr_Category_NccpError = 4026662912L;
    public static final long NFErr_Category_Success = 0L;
    public static final long NFErr_ConnRefused = 4026531883L;
    public static final long NFErr_ConnReset = 4026531884L;
    public static final long NFErr_DNSTimedOut = 4026531882L;
    public static final long NFErr_DRMFailed = 4026531868L;
    public static final long NFErr_DeadLock = 4026531847L;
    public static final long NFErr_DeviceAccount_InvalidKey = 4026728449L;
    public static final long NFErr_DeviceAccount_NoCurrentKey = 4026728450L;
    public static final long NFErr_DeviceAccount_TooManyAccounts = 4026728451L;
    public static final long NFErr_DuplicateEntry = 4026531876L;
    public static final long NFErr_EmptyList = 4026531856L;
    public static final long NFErr_EndOfFile = 4026531857L;
    public static final long NFErr_EndOfStream = 4026531862L;
    public static final long NFErr_FileNotOpened = 4026531846L;
    public static final long NFErr_IOError = 4026531858L;
    public static final long NFErr_Internal = 4026531885L;
    public static final long NFErr_Interrupted = 4026531853L;
    public static final long NFErr_InvalidAccess = 4026531849L;
    public static final long NFErr_InvalidBookmark = 4026531878L;
    public static final long NFErr_InvalidStateTransition = 4026531869L;
    public static final long NFErr_MC_AcquireLicenseFailure = 4026597382L;
    public static final long NFErr_MC_AcquireLicenseFailureNCCP = 4026597383L;
    public static final long NFErr_MC_AuthFailure = 4026597377L;
    public static final long NFErr_MC_AuthFailureNCCP = 4026597378L;
    public static final long NFErr_MC_ConnectionFailure = 4026597387L;
    public static final long NFErr_MC_ContentNotAvailable = 4026597390L;
    public static final long NFErr_MC_DeviceDecryptionError = 4026597386L;
    public static final long NFErr_MC_DevicePlaybackError = 4026597385L;
    public static final long NFErr_MC_HeartbeatFailure = 4026597392L;
    public static final long NFErr_MC_HttpClientError = 4026597389L;
    public static final long NFErr_MC_HttpServerError = 4026597388L;
    public static final long NFErr_MC_InitStreamFailure = 4026597379L;
    public static final long NFErr_MC_NCCP_AbortPlayback = 4026662923L;
    public static final long NFErr_MC_NCCP_CTicketRenewalRequired = 4026662917L;
    public static final long NFErr_MC_NCCP_CustomError = 4026662915L;
    public static final long NFErr_MC_NCCP_Exception = 4026662925L;
    public static final long NFErr_MC_NCCP_GetNewCredentials = 4026662920L;
    public static final long NFErr_MC_NCCP_ImpossibleImpossibility = 4026662919L;
    public static final long NFErr_MC_NCCP_MTicketRenewalRequired = 4026662918L;
    public static final long NFErr_MC_NCCP_NonRecoverableError = 4026662913L;
    public static final long NFErr_MC_NCCP_PotentiallyRecoverableError = 4026662914L;
    public static final long NFErr_MC_NCCP_RegistrationRequired = 4026662916L;
    public static final long NFErr_MC_NCCP_SecondaryCredentialsRenewalRequired = 4026662922L;
    public static final long NFErr_MC_NCCP_StaleCredentials = 4026662924L;
    public static final long NFErr_MC_NCCP_UnsupportedVersion = 4026662921L;
    public static final long NFErr_MC_OpenDeviceFailure = 4026597381L;
    public static final long NFErr_MC_RefuseToPlayNonDrmed = 4026597384L;
    public static final long NFErr_MC_StreamSetIncomplete = 4026597380L;
    public static final long NFErr_MC_SubtitleFailure = 4026597391L;
    public static final long NFErr_MalformedData = 4026531870L;
    public static final long NFErr_MessageQueueEmpty = 4026531860L;
    public static final long NFErr_MessageQueueFull = 4026531859L;
    public static final long NFErr_NCCPInvalidRedirect = 4026728451L;
    public static final long NFErr_NCCPResponseFailed = 4026531872L;
    public static final long NFErr_NetworkError = 4026531881L;
    public static final long NFErr_NoFilmInProgress = 4026531867L;
    public static final long NFErr_NoMemory = 4026531842L;
    public static final long NFErr_NoReplyPort = 4026531861L;
    public static final long NFErr_NotActive = 4026531880L;
    public static final long NFErr_NotAllowed = 4026531874L;
    public static final long NFErr_NotFound = 4026531844L;
    public static final long NFErr_NotImplemented = 4026531843L;
    public static final long NFErr_NotReady = 4026531892L;
    public static final long NFErr_OK = 1L;
    public static final long NFErr_OtherFilmInProgress = 4026531866L;
    public static final long NFErr_Pending = 4026531877L;
    public static final long NFErr_Repeat = 4026531871L;
    public static final long NFErr_SSLCrlOcsp = 4026531890L;
    public static final long NFErr_SSLExpired = 4026531888L;
    public static final long NFErr_SSLFailure = 4026531886L;
    public static final long NFErr_SSLNoCipher = 4026531889L;
    public static final long NFErr_SSLUntrusted = 4026531887L;
    public static final long NFErr_Skip = 4026531875L;
    public static final long NFErr_SkipToNextKeyFrame = 4026531879L;
    public static final long NFErr_Stop = 4026531873L;
    public static final long NFErr_Thread_NotFound = 4026531851L;
    public static final long NFErr_Thread_NotStarted = 4026531850L;
    public static final long NFErr_TimedOut = 4026531854L;
    public static final long NFErr_Uninitialized = 4026531891L;
    public static final long NFErr_Uninitialized_Mutex = 4026531848L;
    public static final long NFErr_WaitingSelf = 4026531855L;
    
    static {
        ACTION_IDS = new String[] { "NFErr_MC_NCCP_NonRecoverableError", "NFErr_MC_NCCP_PotentiallyRecoverableError", "NFErr_MC_NCCP_CustomError", "NFErr_MC_NCCP_RegistrationRequired", "NFErr_MC_NCCP_CTicketRenewalRequired", "NFErr_MC_NCCP_MTicketRenewalRequired", "NFErr_MC_NCCP_ImpossibleImpossibility", "NFErr_MC_NCCP_GetNewCredentials", "NFErr_MC_NCCP_UnsupportedVersion", "NFErr_MC_NCCP_SecondaryCredentialsRenewalRequired", "NFErr_MC_NCCP_AbortPlayback", "NFErr_MC_NCCP_StaleCredentials" };
        NFERRS = new long[] { 0L, 1L, 4026531840L, 4026531841L, 4026531842L, 4026531843L, 4026531844L, 4026531845L, 4026531846L, 4026531847L, 4026531848L, 4026531849L, 4026531850L, 4026531851L, 4026531852L, 4026531853L, 4026531854L, 4026531855L, 4026531856L, 4026531857L, 4026531858L, 4026531859L, 4026531860L, 4026531861L, 4026531862L, 4026531863L, 4026531864L, 4026531865L, 4026531866L, 4026531867L, 4026531868L, 4026531869L, 4026531870L, 4026531871L, 4026531872L, 4026531873L, 4026531874L, 4026531875L, 4026531876L, 4026531877L, 4026531878L, 4026531879L, 4026531880L, 4026531881L, 4026531882L, 4026531883L, 4026531884L, 4026531885L, 4026531886L, 4026531887L, 4026531888L, 4026531889L, 4026531890L, 4026531891L, 4026531892L, 4026597376L, 4026597377L, 4026597378L, 4026597379L, 4026597380L, 4026597381L, 4026597382L, 4026597383L, 4026597384L, 4026597385L, 4026597386L, 4026597387L, 4026597388L, 4026597389L, 4026597390L, 4026597391L, 4026597392L, 4026662912L, 4026662913L, 4026662914L, 4026662915L, 4026662916L, 4026662917L, 4026662918L, 4026662919L, 4026662920L, 4026662921L, 4026662922L, 4026662923L, 4026662924L, 4026662925L, 4026728449L, 4026728450L, 4026728451L, 4026728451L };
        NFERRS_labels = new String[] { "NFErr_Category_Success", "NFErr_OK", "NFErr_Category_GenericError", "NFErr_Bad", "NFErr_NoMemory", "NFErr_NotImplemented", "NFErr_NotFound", "NFErr_BadParameter", "NFErr_FileNotOpened", "NFErr_DeadLock", "NFErr_Uninitialized_Mutex", "NFErr_InvalidAccess", "NFErr_Thread_NotStarted", "NFErr_Thread_NotFound", "NFErr_ArrayOutofBoundary", "NFErr_Interrupted", "NFErr_TimedOut", "NFErr_WaitingSelf", "NFErr_EmptyList", "NFErr_EndOfFile", "NFErr_IOError", "NFErr_MessageQueueFull", "NFErr_MessageQueueEmpty", "NFErr_NoReplyPort", "NFErr_EndOfStream", "NFErr_ASFDataError", "NFErr_ASFSkipData", "NFErr_CMDWhileWaitingEOS", "NFErr_OtherFilmInProgress", "NFErr_NoFilmInProgress", "NFErr_DRMFailed", "NFErr_InvalidStateTransition", "NFErr_MalformedData", "NFErr_Repeat", "NFErr_NCCPResponseFailed", "NFErr_Stop", "NFErr_NotAllowed", "NFErr_Skip", "NFErr_DuplicateEntry", "NFErr_Pending", "NFErr_InvalidBookmark", "NFErr_SkipToNextKeyFrame", "NFErr_NotActive", "NFErr_NetworkError", "NFErr_DNSTimedOut", "NFErr_ConnRefused", "NFErr_ConnReset", "NFErr_Internal", "NFErr_SSLFailure", "NFErr_SSLUntrusted", "NFErr_SSLExpired", "NFErr_SSLNoCipher", "NFErr_SSLNoCipher", "NFErr_SSLCrlOcsp", "NFErr_Uninitialized", "NFErr_NotReady", "NFErr_Category_McError", "NFErr_MC_AuthFailure", "NFErr_MC_AuthFailureNCCP", "NFErr_MC_InitStreamFailure", "NFErr_MC_StreamSetIncomplete", "NFErr_MC_OpenDeviceFailure", "NFErr_MC_AcquireLicenseFailure", "NFErr_MC_AcquireLicenseFailureNCCP", "NFErr_MC_RefuseToPlayNonDrmed", "NFErr_MC_DevicePlaybackError", "NFErr_MC_DeviceDecryptionError", "NFErr_MC_ConnectionFailure", "NFErr_MC_HttpServerError", "NFErr_MC_HttpClientError", "NFErr_MC_ContentNotAvailable", "NFErr_MC_SubtitleFailure", "NFErr_MC_HeartbeatFailure", "NFErr_MC_HeartbeatFailure", "NFErr_Category_NccpError", "NFErr_MC_NCCP_NonRecoverableError", "NFErr_MC_NCCP_PotentiallyRecoverableError", "NFErr_MC_NCCP_CustomError", "NFErr_MC_NCCP_RegistrationRequired", "NFErr_MC_NCCP_CTicketRenewalRequired", "NFErr_MC_NCCP_MTicketRenewalRequired", "NFErr_MC_NCCP_ImpossibleImpossibility", "NFErr_MC_NCCP_GetNewCredentials", "NFErr_MC_NCCP_UnsupportedVersion", "NFErr_MC_NCCP_SecondaryCredentialsRenewalRequired", "NFErr_MC_NCCP_CannotObtainNpTicket", "NFErr_MC_NCCP_Exception", "NFErr_DeviceAccount_InvalidKey", "NFErr_DeviceAccount_NoCurrentKey", "NFErr_DeviceAccount_TooManyAccounts", "NFErr_NCCPInvalidRedirect" };
    }
    
    public static Response getErrorResponse(final long n) {
        final Response response = new Response();
        if (n == 4026531852L) {
            response.labelId = 2131493029;
            return response;
        }
        if (n == 4026531863L) {
            response.labelId = 2131493040;
            return response;
        }
        if (n == 4026531864L) {
            response.labelId = 2131493041;
            return response;
        }
        if (n == 4026531841L) {
            response.labelId = 2131493018;
            return response;
        }
        if (n == 4026531845L) {
            response.labelId = 2131493022;
            return response;
        }
        if (n == 4026531840L) {
            response.labelId = 2131493017;
            return response;
        }
        if (n == 4026597376L) {
            response.labelId = 2131493017;
            return response;
        }
        if (n == 4026662912L) {
            response.labelId = 2131493088;
            return response;
        }
        if (n == 0L) {
            response.labelId = 2131493015;
            response.action = 2;
            return response;
        }
        if (n == 4026531865L) {
            response.labelId = 2131493042;
            return response;
        }
        if (n == 4026531883L) {
            response.labelId = 2131493060;
            return response;
        }
        if (n == 4026531884L) {
            response.labelId = 2131493061;
            return response;
        }
        if (n == 4026531847L) {
            response.labelId = 2131493024;
            return response;
        }
        if (n == 4026531882L) {
            response.labelId = 2131493059;
            return response;
        }
        if (n == 4026531868L) {
            response.labelId = 2131493045;
            return response;
        }
        if (n == 4026531876L) {
            response.labelId = 2131493053;
            return response;
        }
        if (n == 4026531856L) {
            response.labelId = 2131493033;
            return response;
        }
        if (n == 4026531857L) {
            response.labelId = 2131493034;
            return response;
        }
        if (n == 4026531862L) {
            response.labelId = 2131493039;
            return response;
        }
        if (n == 4026531846L) {
            response.labelId = 2131493023;
            return response;
        }
        if (n == 4026531885L) {
            response.labelId = 2131493062;
            return response;
        }
        if (n == 4026531853L) {
            response.labelId = 2131493030;
            return response;
        }
        if (n == 4026531849L) {
            response.labelId = 2131493026;
            return response;
        }
        if (n == 4026531878L) {
            response.labelId = 2131493055;
            return response;
        }
        if (n == 4026531869L) {
            response.labelId = 2131493046;
            return response;
        }
        if (n == 4026531858L) {
            response.labelId = 2131493035;
            return response;
        }
        if (n == 4026531870L) {
            response.labelId = 2131493047;
            return response;
        }
        if (n == 4026597382L) {
            response.labelId = 2131493075;
            return response;
        }
        if (n == 4026597383L) {
            response.labelId = 2131493076;
            return response;
        }
        if (n == 4026597377L) {
            response.labelId = 2131493070;
            return response;
        }
        if (n == 4026597378L) {
            response.labelId = 2131493071;
            return response;
        }
        if (n == 4026597387L) {
            response.labelId = 2131493080;
            return response;
        }
        if (n == 4026597390L) {
            response.labelId = 2131493083;
            return response;
        }
        if (n == 4026597386L) {
            response.labelId = 2131493079;
            return response;
        }
        if (n == 4026597385L) {
            response.labelId = 2131493078;
            return response;
        }
        if (n == 4026597389L) {
            response.labelId = 2131493082;
            return response;
        }
        if (n == 4026597388L) {
            response.labelId = 2131493081;
            return response;
        }
        if (n == 4026597379L) {
            response.labelId = 2131493072;
            return response;
        }
        if (n == 4026728449L) {
            response.labelId = 2131493102;
            return response;
        }
        if (n == 4026728450L) {
            response.labelId = 2131493103;
            return response;
        }
        if (n == 4026728451L) {
            response.labelId = 2131493104;
            return response;
        }
        if (n == 4026728451L) {
            response.labelId = 2131493105;
            return response;
        }
        if (n == 4026662917L) {
            response.labelId = 2131493093;
            return response;
        }
        if (n == 4026662922L) {
            response.labelId = 2131493098;
            return response;
        }
        if (n == 4026662923L) {
            response.labelId = 2131493099;
            return response;
        }
        if (n == 4026662924L) {
            response.labelId = 2131493100;
            return response;
        }
        if (n == 4026662915L) {
            response.labelId = 2131493091;
            return response;
        }
        if (n == 4026662925L) {
            response.labelId = 2131493101;
            return response;
        }
        if (n == 4026662920L) {
            response.labelId = 2131493096;
            return response;
        }
        if (n == 4026662919L) {
            response.labelId = 2131493095;
            return response;
        }
        if (n == 4026662918L) {
            response.labelId = 2131493094;
            return response;
        }
        if (n == 4026662913L) {
            response.labelId = 2131493089;
            return response;
        }
        if (n == 4026662914L) {
            response.labelId = 2131493090;
            return response;
        }
        if (n == 4026662916L) {
            response.labelId = 2131493092;
            return response;
        }
        if (n == 4026662921L) {
            response.labelId = 2131493097;
            return response;
        }
        if (n == 4026597381L) {
            response.labelId = 2131493074;
            return response;
        }
        if (n == 4026597384L) {
            response.labelId = 2131493077;
            return response;
        }
        if (n == 4026597380L) {
            response.labelId = 2131493073;
            return response;
        }
        if (n == 4026597391L) {
            response.labelId = 2131493084;
            response.action = 1;
            return response;
        }
        if (n == 4026597392L) {
            response.labelId = 2131493085;
            response.action = 1;
            return response;
        }
        if (n == 4026531860L) {
            response.labelId = 2131493037;
            return response;
        }
        if (n == 4026531859L) {
            response.labelId = 2131493036;
            return response;
        }
        if (n == 4026531872L) {
            response.labelId = 2131493049;
            return response;
        }
        if (n == 4026531881L) {
            response.labelId = 2131493058;
            return response;
        }
        if (n == 4026531867L) {
            response.labelId = 2131493044;
            return response;
        }
        if (n == 4026531842L) {
            response.labelId = 2131493019;
            return response;
        }
        if (n == 4026531861L) {
            response.labelId = 2131493038;
            return response;
        }
        if (n == 4026531880L) {
            response.labelId = 2131493057;
            return response;
        }
        if (n == 4026531874L) {
            response.labelId = 2131493051;
            return response;
        }
        if (n == 4026531844L) {
            response.labelId = 2131493021;
            return response;
        }
        if (n == 4026531843L) {
            response.labelId = 2131493020;
            return response;
        }
        if (n == 4026531892L) {
            response.labelId = 2131493069;
            return response;
        }
        if (n == 1L) {
            response.labelId = 2131493016;
            response.action = 2;
            return response;
        }
        if (n == 4026531866L) {
            response.labelId = 2131493043;
            return response;
        }
        if (n == 4026531877L) {
            response.labelId = 2131493054;
            return response;
        }
        if (n == 4026531871L) {
            response.labelId = 2131493048;
            return response;
        }
        if (n == 4026531875L) {
            response.labelId = 2131493052;
            return response;
        }
        if (n == 4026531879L) {
            response.labelId = 2131493056;
            return response;
        }
        if (n == 4026531888L) {
            response.labelId = 2131493065;
            return response;
        }
        if (n == 4026531886L) {
            response.labelId = 2131493063;
            return response;
        }
        if (n == 4026531889L) {
            response.labelId = 2131493066;
            return response;
        }
        if (n == 4026531890L) {
            response.labelId = 2131493067;
            return response;
        }
        if (n == 4026531887L) {
            response.labelId = 2131493064;
            return response;
        }
        if (n == 4026531873L) {
            response.labelId = 2131493050;
            return response;
        }
        if (n == 4026531851L) {
            response.labelId = 2131493028;
            return response;
        }
        if (n == 4026531850L) {
            response.labelId = 2131493027;
            return response;
        }
        if (n == 4026531854L) {
            response.labelId = 2131493031;
            return response;
        }
        if (n == 4026531891L) {
            response.labelId = 2131493068;
            return response;
        }
        if (n == 4026531848L) {
            response.labelId = 2131493025;
            return response;
        }
        if (n == 4026531855L) {
            response.labelId = 2131493032;
            return response;
        }
        return null;
    }
    
    public static String getLabel(final long n) {
        for (int i = 0; i < NFError.NFERRS.length; ++i) {
            if (n == NFError.NFERRS[i]) {
                return NFError.NFERRS_labels[i];
            }
        }
        return null;
    }
    
    public static boolean isGenericError(final int n) {
        return (n & 0xFFFF0000L) == 0xF0000000L;
    }
    
    public static boolean isKnown(final long n) {
        for (int i = 0; i < NFError.NFERRS.length; ++i) {
            if (n == NFError.NFERRS[i]) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isMediaControlError(final int n) {
        return (n & 0xFFFF0000L) == 0xF0010000L;
    }
    
    public static boolean isNccpError(final int n) {
        return (n & 0xFFFF0000L) == 0xF0020000L;
    }
    
    public static boolean isSuccessful(final long n) {
        return (0xFFFF0000L & n) == 0x0L;
    }
    
    public static class Response
    {
        public static final int CONTINUE_PLAY = 1;
        public static final int IGNORE = 2;
        public static final int TERMINATE_PLAY = 0;
        public int action;
        public int labelId;
        
        public Response() {
            this.labelId = 2131492990;
            this.action = 0;
        }
    }
}
