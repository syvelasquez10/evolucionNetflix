// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.service.webclient.model.leafs.NrmLanguagesData;
import com.netflix.mediaclient.media.JPlayer.DolbyDigitalHelper;
import com.netflix.mediaclient.service.webclient.model.leafs.VoipConfiguration;
import android.view.Display;
import android.util.DisplayMetrics;
import android.hardware.display.DisplayManager;
import com.netflix.mediaclient.media.VideoResolutionRange;
import com.netflix.mediaclient.service.webclient.model.leafs.SubtitleDownloadRetryPolicy;
import com.netflix.mediaclient.service.webclient.model.leafs.SignInConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.OfflineConfig;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmConfigData;
import org.json.JSONObject;
import com.netflix.mediaclient.service.net.IpConnectivityPolicy;
import com.netflix.mediaclient.service.webclient.model.leafs.ErrorLoggingSpecification;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.webclient.model.leafs.ConsolidatedLoggingSessionSpecification;
import java.util.Arrays;
import com.netflix.mediaclient.util.Base64;
import android.util.Pair;
import com.netflix.mediaclient.service.webclient.model.leafs.BreadcrumbLoggingSpecification;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData;
import android.media.UnsupportedSchemeException;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.error.crypto.ErrorSource;
import com.netflix.mediaclient.service.error.crypto.CryptoErrorManager;
import com.netflix.mediaclient.util.MediaDrmUtils;
import com.netflix.mediaclient.service.configuration.esn.EsnProviderRegistry;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.PreviewContentConfigData;
import com.netflix.mediaclient.ui.experience.PersistentExperience;
import com.netflix.mediaclient.javabridge.transport.NativeTransport;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.content.pm.PackageManager;
import com.netflix.mediaclient.service.voip.VoipAuthorizationTokensUpdater;
import java.util.Locale;
import java.io.IOException;
import com.netflix.mediaclient.service.configuration.volley.FetchConfigDataRequest;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.api.Api19Util;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;
import com.netflix.mediaclient.service.NetflixService;
import java.util.ArrayList;
import android.os.Handler;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import java.util.List;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.configuration.drm.DrmManager$DrmReadyCallback;

class ConfigurationAgent$1 implements DrmManager$DrmReadyCallback
{
    final /* synthetic */ ConfigurationAgent this$0;
    
    ConfigurationAgent$1(final ConfigurationAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void drmError(final Status status) {
        PerformanceProfiler.getInstance().endSession(Sessions.DRM_LOADED);
        if (Log.isLoggable()) {
            Log.e("nf_configurationagent", "DRM failed to initialize, Error code: " + status.getStatusCode());
        }
        this.this$0.initCompleted(status);
    }
    
    @Override
    public void drmReady() {
        Log.d("nf_configurationagent", "DRM manager is ready");
        PerformanceProfiler.getInstance().endSession(Sessions.DRM_LOADED);
        if (DrmManagerRegistry.hasEsnChanged(this.this$0.mESN.getEsn())) {
            this.this$0.mNeedEsMigration = true;
        }
        Log.d("nf_configurationagent", "EsnMigration needed:" + this.this$0.mNeedEsMigration);
        this.this$0.initCompleted(CommonStatus.OK);
    }
}
