// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import android.content.Intent;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.offline.utils.OfflineUtils;
import com.android.volley.Network;
import com.android.volley.Cache;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.NoCache;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable$PlayableMaintenanceCallBack;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.log.OfflineLogUtils;
import com.netflix.mediaclient.util.NetflixTransactionIdGenerator;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import android.media.UnsupportedSchemeException;
import android.media.NotProvisionedException;
import com.netflix.mediaclient.service.offline.registry.ChecksumException;
import com.netflix.mediaclient.service.offline.license.OfflineLicenseManagerImpl;
import com.netflix.mediaclient.service.offline.manifest.OfflineManifestManagerImpl;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.offline.download.OfflinePlayableImpl;
import com.netflix.mediaclient.service.offline.utils.OfflinePathUtils;
import com.netflix.mediaclient.service.offline.download.PlayableProgressInfo;
import com.netflix.mediaclient.service.resfetcher.volley.ResourceHttpStack;
import com.android.volley.toolbox.HttpStack;
import android.os.Looper;
import java.util.Iterator;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;
import com.netflix.mediaclient.service.offline.registry.RegistryData;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import android.content.Context;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.android.app.Status;
import java.util.HashMap;
import java.util.ArrayList;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.user.UserAgent;
import com.android.volley.RequestQueue;
import io.realm.Realm;
import com.netflix.mediaclient.service.offline.registry.OfflineRegistry;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiListImpl;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable;
import java.util.List;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$ManifestCallback;
import com.netflix.mediaclient.service.offline.manifest.OfflineManifestManager;
import com.netflix.mediaclient.service.offline.license.OfflineLicenseManager;
import com.netflix.mediaclient.service.offline.download.OfflinePlayableListener;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.os.HandlerThread;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import com.netflix.mediaclient.service.IntentCommandHandler;
import com.netflix.mediaclient.service.ServiceAgent;
import java.util.Map;

class OfflineAgent$3 implements DownloadGeoPlayabilityHelper$GeoPlayabilityCallBack
{
    final /* synthetic */ OfflineAgent this$0;
    
    OfflineAgent$3(final OfflineAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onGeoPlayabilityResponse(final Map<String, Boolean> map) {
        this.this$0.onGeoPlayabilityUpdated(map);
    }
}
