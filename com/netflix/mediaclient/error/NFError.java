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
    
    public static NFError$Response getErrorResponse(final long n) {
        final NFError$Response nfError$Response = new NFError$Response();
        if (n == 4026531852L) {
            nfError$Response.labelId = 2131165253;
            return nfError$Response;
        }
        if (n == 4026531863L) {
            nfError$Response.labelId = 2131165251;
            return nfError$Response;
        }
        if (n == 4026531864L) {
            nfError$Response.labelId = 2131165252;
            return nfError$Response;
        }
        if (n == 4026531841L) {
            nfError$Response.labelId = 2131165254;
            return nfError$Response;
        }
        if (n == 4026531845L) {
            nfError$Response.labelId = 2131165255;
            return nfError$Response;
        }
        if (n == 4026531840L) {
            nfError$Response.labelId = 2131165257;
            return nfError$Response;
        }
        if (n == 4026597376L) {
            nfError$Response.labelId = 2131165257;
            return nfError$Response;
        }
        if (n == 4026662912L) {
            nfError$Response.labelId = 2131165258;
            return nfError$Response;
        }
        if (n == 0L) {
            nfError$Response.labelId = 2131165259;
            nfError$Response.action = 2;
            return nfError$Response;
        }
        if (n == 4026531865L) {
            nfError$Response.labelId = 2131165256;
            return nfError$Response;
        }
        if (n == 4026531883L) {
            nfError$Response.labelId = 2131165260;
            return nfError$Response;
        }
        if (n == 4026531884L) {
            nfError$Response.labelId = 2131165261;
            return nfError$Response;
        }
        if (n == 4026531847L) {
            nfError$Response.labelId = 2131165264;
            return nfError$Response;
        }
        if (n == 4026531882L) {
            nfError$Response.labelId = 2131165262;
            return nfError$Response;
        }
        if (n == 4026531868L) {
            nfError$Response.labelId = 2131165263;
            return nfError$Response;
        }
        if (n == 4026531876L) {
            nfError$Response.labelId = 2131165268;
            return nfError$Response;
        }
        if (n == 4026531856L) {
            nfError$Response.labelId = 2131165269;
            return nfError$Response;
        }
        if (n == 4026531857L) {
            nfError$Response.labelId = 2131165270;
            return nfError$Response;
        }
        if (n == 4026531862L) {
            nfError$Response.labelId = 2131165271;
            return nfError$Response;
        }
        if (n == 4026531846L) {
            nfError$Response.labelId = 2131165272;
            return nfError$Response;
        }
        if (n == 4026531885L) {
            nfError$Response.labelId = 2131165274;
            return nfError$Response;
        }
        if (n == 4026531853L) {
            nfError$Response.labelId = 2131165275;
            return nfError$Response;
        }
        if (n == 4026531849L) {
            nfError$Response.labelId = 2131165276;
            return nfError$Response;
        }
        if (n == 4026531878L) {
            nfError$Response.labelId = 2131165277;
            return nfError$Response;
        }
        if (n == 4026531869L) {
            nfError$Response.labelId = 2131165278;
            return nfError$Response;
        }
        if (n == 4026531858L) {
            nfError$Response.labelId = 2131165273;
            return nfError$Response;
        }
        if (n == 4026531870L) {
            nfError$Response.labelId = 2131165309;
            return nfError$Response;
        }
        if (n == 4026597382L) {
            nfError$Response.labelId = 2131165279;
            return nfError$Response;
        }
        if (n == 4026597383L) {
            nfError$Response.labelId = 2131165280;
            return nfError$Response;
        }
        if (n == 4026597377L) {
            nfError$Response.labelId = 2131165281;
            return nfError$Response;
        }
        if (n == 4026597378L) {
            nfError$Response.labelId = 2131165282;
            return nfError$Response;
        }
        if (n == 4026597387L) {
            nfError$Response.labelId = 2131165283;
            return nfError$Response;
        }
        if (n == 4026597390L) {
            nfError$Response.labelId = 2131165284;
            return nfError$Response;
        }
        if (n == 4026597386L) {
            nfError$Response.labelId = 2131165285;
            return nfError$Response;
        }
        if (n == 4026597385L) {
            nfError$Response.labelId = 2131165286;
            return nfError$Response;
        }
        if (n == 4026597389L) {
            nfError$Response.labelId = 2131165288;
            return nfError$Response;
        }
        if (n == 4026597388L) {
            nfError$Response.labelId = 2131165289;
            return nfError$Response;
        }
        if (n == 4026597379L) {
            nfError$Response.labelId = 2131165290;
            return nfError$Response;
        }
        if (n == 4026728449L) {
            nfError$Response.labelId = 2131165265;
            return nfError$Response;
        }
        if (n == 4026728450L) {
            nfError$Response.labelId = 2131165266;
            return nfError$Response;
        }
        if (n == 4026728451L) {
            nfError$Response.labelId = 2131165267;
            return nfError$Response;
        }
        if (n == 4026728451L) {
            nfError$Response.labelId = 2131165312;
            return nfError$Response;
        }
        if (n == 4026662917L) {
            nfError$Response.labelId = 2131165292;
            return nfError$Response;
        }
        if (n == 4026662922L) {
            nfError$Response.labelId = 2131165301;
            return nfError$Response;
        }
        if (n == 4026662923L) {
            nfError$Response.labelId = 2131165291;
            return nfError$Response;
        }
        if (n == 4026662924L) {
            nfError$Response.labelId = 2131165302;
            return nfError$Response;
        }
        if (n == 4026662915L) {
            nfError$Response.labelId = 2131165293;
            return nfError$Response;
        }
        if (n == 4026662925L) {
            nfError$Response.labelId = 2131165294;
            return nfError$Response;
        }
        if (n == 4026662920L) {
            nfError$Response.labelId = 2131165295;
            return nfError$Response;
        }
        if (n == 4026662919L) {
            nfError$Response.labelId = 2131165296;
            return nfError$Response;
        }
        if (n == 4026662918L) {
            nfError$Response.labelId = 2131165297;
            return nfError$Response;
        }
        if (n == 4026662913L) {
            nfError$Response.labelId = 2131165298;
            return nfError$Response;
        }
        if (n == 4026662914L) {
            nfError$Response.labelId = 2131165299;
            return nfError$Response;
        }
        if (n == 4026662916L) {
            nfError$Response.labelId = 2131165300;
            return nfError$Response;
        }
        if (n == 4026662921L) {
            nfError$Response.labelId = 2131165303;
            return nfError$Response;
        }
        if (n == 4026597381L) {
            nfError$Response.labelId = 2131165304;
            return nfError$Response;
        }
        if (n == 4026597384L) {
            nfError$Response.labelId = 2131165306;
            return nfError$Response;
        }
        if (n == 4026597380L) {
            nfError$Response.labelId = 2131165307;
            return nfError$Response;
        }
        if (n == 4026597391L) {
            nfError$Response.labelId = 2131165308;
            nfError$Response.action = 1;
            return nfError$Response;
        }
        if (n == 4026597392L) {
            nfError$Response.labelId = 2131165287;
            nfError$Response.action = 1;
            return nfError$Response;
        }
        if (n == 4026531860L) {
            nfError$Response.labelId = 2131165310;
            return nfError$Response;
        }
        if (n == 4026531859L) {
            nfError$Response.labelId = 2131165311;
            return nfError$Response;
        }
        if (n == 4026531872L) {
            nfError$Response.labelId = 2131165313;
            return nfError$Response;
        }
        if (n == 4026531881L) {
            nfError$Response.labelId = 2131165314;
            return nfError$Response;
        }
        if (n == 4026531867L) {
            nfError$Response.labelId = 2131165315;
            return nfError$Response;
        }
        if (n == 4026531842L) {
            nfError$Response.labelId = 2131165316;
            return nfError$Response;
        }
        if (n == 4026531861L) {
            nfError$Response.labelId = 2131165317;
            return nfError$Response;
        }
        if (n == 4026531880L) {
            nfError$Response.labelId = 2131165318;
            return nfError$Response;
        }
        if (n == 4026531874L) {
            nfError$Response.labelId = 2131165319;
            return nfError$Response;
        }
        if (n == 4026531844L) {
            nfError$Response.labelId = 2131165320;
            return nfError$Response;
        }
        if (n == 4026531843L) {
            nfError$Response.labelId = 2131165321;
            return nfError$Response;
        }
        if (n == 4026531892L) {
            nfError$Response.labelId = 2131165322;
            return nfError$Response;
        }
        if (n == 1L) {
            nfError$Response.labelId = 2131165323;
            nfError$Response.action = 2;
            return nfError$Response;
        }
        if (n == 4026531866L) {
            nfError$Response.labelId = 2131165324;
            return nfError$Response;
        }
        if (n == 4026531877L) {
            nfError$Response.labelId = 2131165325;
            return nfError$Response;
        }
        if (n == 4026531871L) {
            nfError$Response.labelId = 2131165326;
            return nfError$Response;
        }
        if (n == 4026531875L) {
            nfError$Response.labelId = 2131165332;
            return nfError$Response;
        }
        if (n == 4026531879L) {
            nfError$Response.labelId = 2131165333;
            return nfError$Response;
        }
        if (n == 4026531888L) {
            nfError$Response.labelId = 2131165328;
            return nfError$Response;
        }
        if (n == 4026531886L) {
            nfError$Response.labelId = 2131165329;
            return nfError$Response;
        }
        if (n == 4026531889L) {
            nfError$Response.labelId = 2131165330;
            return nfError$Response;
        }
        if (n == 4026531890L) {
            nfError$Response.labelId = 2131165327;
            return nfError$Response;
        }
        if (n == 4026531887L) {
            nfError$Response.labelId = 2131165331;
            return nfError$Response;
        }
        if (n == 4026531873L) {
            nfError$Response.labelId = 2131165334;
            return nfError$Response;
        }
        if (n == 4026531851L) {
            nfError$Response.labelId = 2131165335;
            return nfError$Response;
        }
        if (n == 4026531850L) {
            nfError$Response.labelId = 2131165336;
            return nfError$Response;
        }
        if (n == 4026531854L) {
            nfError$Response.labelId = 2131165337;
            return nfError$Response;
        }
        if (n == 4026531891L) {
            nfError$Response.labelId = 2131165338;
            return nfError$Response;
        }
        if (n == 4026531848L) {
            nfError$Response.labelId = 2131165339;
            return nfError$Response;
        }
        if (n == 4026531855L) {
            nfError$Response.labelId = 2131165340;
            return nfError$Response;
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
        final boolean b = false;
        int n2 = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n2 >= NFError.NFERRS.length) {
                break;
            }
            if (n == NFError.NFERRS[n2]) {
                b2 = true;
                break;
            }
            ++n2;
        }
        return b2;
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
}
