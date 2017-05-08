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
import com.netflix.mediaclient.service.configuration.esn.EsnProviderRegistry;
import com.netflix.mediaclient.service.configuration.drm.DrmManager$DrmReadyCallback;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.service.error.crypto.CryptoErrorManager;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
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
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Locale;
import java.io.IOException;
import com.netflix.mediaclient.service.configuration.volley.FetchConfigDataRequest;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.api.Api19Util;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.service.NetflixService;
import java.util.ArrayList;
import android.os.Handler;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import java.util.List;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;

class ConfigurationAgent$FetchConfigDataTask$1 extends SimpleConfigurationAgentWebCallback
{
    final /* synthetic */ ConfigurationAgent$FetchConfigDataTask this$1;
    
    ConfigurationAgent$FetchConfigDataTask$1(final ConfigurationAgent$FetchConfigDataTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onConfigDataFetched(final ConfigData configData, final Status status) {
        if (Log.isLoggable()) {
            Log.d("nf_configurationagent", String.format("onConfigDataFetched statusCode=%d", status.getStatusCode().getValue()));
        }
        this.this$1.this$0.mConfigRefreshStatus = status;
        if (status.isSucces() && configData != null) {
            this.this$1.this$0.persistConfigOverride(configData);
        }
        this.this$1.this$0.mIsConfigRefreshInBackground = false;
        this.this$1.this$0.refreshHandler.postDelayed(this.this$1.this$0.refreshRunnable, 28800000L);
        this.this$1.this$0.notifyConfigRefreshedAndClearListeners();
        if (this.this$1.getCallback() != null) {
            this.this$1.getCallback().onConfigDataFetched(configData, this.this$1.this$0.mConfigRefreshStatus);
        }
    }
}
