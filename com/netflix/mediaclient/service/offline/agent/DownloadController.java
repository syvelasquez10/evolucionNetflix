// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.service.offline.utils.OfflineUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.Log;
import java.util.Random;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import android.os.Looper;
import android.os.Handler;
import java.util.Map;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable;
import java.util.List;
import com.netflix.mediaclient.service.job.NetflixJobScheduler;
import com.netflix.mediaclient.service.job.NetflixJob;
import android.content.Context;
import com.netflix.mediaclient.util.ConnectivityUtils$NetType;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

class DownloadController
{
    private static final long[] AttemptToBackOffMilliseconds;
    private static final long[] DL_WINDOW_BACK_OFF_TIMES;
    private static final boolean DOWNLOAD_ONE_ITEM_AT_A_TIME = true;
    private static final boolean MAINTENANCE_JOB_REQUIRES_UNMETERED_NETWORK = false;
    private static final int MAX_NETWORK_ERRORS_BEFORE_SELECTING_NEXT_PLAYABLE;
    private static final int MAX_NETWORK_ERRORS_IN_DL_WINDOW;
    private static final int MIN_NETWORK_ERRORS_IN_DL_WINDOW = 2;
    private static final int RANDOM_DELAY_TO_BACK_OFF_TIME_PERCENTAGE = 30;
    private static final String TAG = "nf_downloadController";
    private final long FIRST_TIME_NETWORK_CHANGE_RUNNABLE_DELAY;
    private final long MAINTENANCE_JOB_CANCEL_DELAY;
    private final Runnable mBackOffRunnable;
    private Runnable mCancelMtJobRunnable;
    private boolean mCancelMtJobRunnableScheduled;
    private int mCompletedCount;
    private final ServiceAgent$ConfigurationAgentInterface mConfigurationAgent;
    private ConnectivityUtils$NetType mConnectedNetType;
    private final Context mContext;
    private boolean mDestroyed;
    private final DownloadController$DownloadControllerListener mDownloadContinueReceiver;
    private NetflixJob mDownloadMaintenanceJob;
    private NetflixJob mDownloadResumeJob;
    private boolean mDownloadsAreStoppedByUser;
    private boolean mFirstTimeNetworkChangeReceived;
    private final Runnable mFirstTimeNetworkRunnable;
    private int mInProgressCount;
    private int mIncompleteItems;
    private int mIndexOfDlWindowBackOffTime;
    private int mIndexOfNextPlayable;
    private boolean mIsStreaming;
    private final NetflixJobScheduler mNetflixJobScheduler;
    private final DownloadController$NetworkChangeReceiver mNetworkChangeReceiver;
    private final Runnable mNetworkChangeRunnable;
    private int mNumberOfNetworkErrorsInCurrentDLWindow;
    private final List<OfflinePlayable> mOfflinePlayableList;
    private final Map<String, Integer> mPlayableNetworkErrorCountMap;
    private final Handler mWorkHandler;
    
    static {
        AttemptToBackOffMilliseconds = new long[] { 60000L };
        MAX_NETWORK_ERRORS_BEFORE_SELECTING_NEXT_PLAYABLE = DownloadController.AttemptToBackOffMilliseconds.length;
        MAX_NETWORK_ERRORS_IN_DL_WINDOW = (DownloadController.MAX_NETWORK_ERRORS_BEFORE_SELECTING_NEXT_PLAYABLE + 1) * 3 - 1;
        DL_WINDOW_BACK_OFF_TIMES = new long[] { 3600000L, 14400000L };
    }
    
    public DownloadController(final Context mContext, final NetflixJobScheduler mNetflixJobScheduler, final List<OfflinePlayable> mOfflinePlayableList, final Looper looper, final DownloadController$DownloadControllerListener mDownloadContinueReceiver, final boolean mDownloadsAreStoppedByUser, final ServiceAgent$ConfigurationAgentInterface mConfigurationAgent) {
        int n = 1;
        this.mNetworkChangeReceiver = new DownloadController$NetworkChangeReceiver(this, null);
        this.mPlayableNetworkErrorCountMap = new HashMap<String, Integer>();
        this.mIndexOfNextPlayable = 0;
        this.mFirstTimeNetworkChangeReceived = true;
        this.FIRST_TIME_NETWORK_CHANGE_RUNNABLE_DELAY = TimeUnit.SECONDS.toMillis(30L);
        this.MAINTENANCE_JOB_CANCEL_DELAY = TimeUnit.SECONDS.toMillis(30L);
        this.mCancelMtJobRunnableScheduled = false;
        this.mCancelMtJobRunnable = new DownloadController$1(this);
        this.mBackOffRunnable = new DownloadController$4(this);
        this.mFirstTimeNetworkRunnable = new DownloadController$5(this);
        this.mNetworkChangeRunnable = new DownloadController$6(this);
        this.mContext = mContext;
        this.mNetflixJobScheduler = mNetflixJobScheduler;
        this.mWorkHandler = new Handler(looper);
        this.mDownloadContinueReceiver = mDownloadContinueReceiver;
        this.mOfflinePlayableList = mOfflinePlayableList;
        this.mConfigurationAgent = mConfigurationAgent;
        this.mDownloadResumeJob = NetflixJob.buildDownloadResumeJob(this.requiresUnmeteredConnectionForDownload());
        final long mtJobPeriodInMsFromOfflineConfig = this.getMtJobPeriodInMsFromOfflineConfig();
        while (true) {
            Label_0252: {
                if (mtJobPeriodInMsFromOfflineConfig <= 0L) {
                    break Label_0252;
                }
                this.mDownloadMaintenanceJob = NetflixJob.buildDownloadMaintenanceJob(false, mtJobPeriodInMsFromOfflineConfig);
                if (mOfflinePlayableList.size() <= 0) {
                    break Label_0252;
                }
                if (n != 0) {
                    this.scheduleMaintenanceJobIfRequired();
                }
                else {
                    this.cancelMaintenanceJobIfScheduled();
                }
                this.mDestroyed = false;
                this.mDownloadsAreStoppedByUser = mDownloadsAreStoppedByUser;
                this.mContext.registerReceiver((BroadcastReceiver)this.mNetworkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                return;
            }
            n = 0;
            continue;
        }
    }
    
    private long addRandomDelayToBackOffTime(final long n) {
        return new Random().nextInt(30) * n / 100L + n;
    }
    
    private void cancelDownloadResumeJob() {
        if (this.mNetflixJobScheduler.isJobScheduled(this.mDownloadResumeJob.getNetflixJobId())) {
            this.mNetflixJobScheduler.cancelJob(this.mDownloadResumeJob.getNetflixJobId());
            Log.i("nf_downloadController", "cancelDownloadResumeJob cancelled");
        }
    }
    
    private void cancelMaintenanceJobIfScheduled() {
        if (this.mNetflixJobScheduler.isJobScheduled(NetflixJob$NetflixJobId.DOWNLOAD_MAINTENANCE)) {
            this.mNetflixJobScheduler.cancelJob(NetflixJob$NetflixJobId.DOWNLOAD_MAINTENANCE);
            Log.i("nf_downloadController", "DownloadMaintenanceJob cancelled");
        }
    }
    
    private Integer getSafeNetworkErrorCount(final String s) {
        Integer value;
        if ((value = this.mPlayableNetworkErrorCountMap.get(s)) == null) {
            value = 0;
        }
        return value;
    }
    
    private void handleNetworkChanged() {
        this.updateConnectedNetworkType();
        if (this.mConnectedNetType == null) {
            this.mDownloadContinueReceiver.stopDownloadsNoNetwork();
            Log.i("nf_downloadController", "mNetworkChangeRunnable, no network.");
            return;
        }
        if (this.mDownloadResumeJob.canExecute(this.mContext)) {
            Log.i("nf_downloadController", "handleNetworkChanged, retry the download.");
            this.mDownloadContinueReceiver.continueDownloadOnNetworkChanged();
            return;
        }
        if (!this.mDownloadResumeJob.canExecuteByNetwork(this.mContext)) {
            this.mDownloadContinueReceiver.stopDownloadsNotAllowedByNetwork();
            Log.i("nf_downloadController", "handleNetworkChanged, can't execute the job, stop downloads.");
            return;
        }
        Log.e("nf_downloadController", "handleNetworkChanged, this shouldn't happen.");
    }
    
    private void handleStartDownloadOnStreamingStopped() {
        this.mDownloadContinueReceiver.continueDownloadOnStreamingStopped();
    }
    
    private void handleStopDownloadOnStreamingStarted() {
        this.mDownloadContinueReceiver.stopDownloadOnStreamingStarted();
    }
    
    private void handleUserNetworkSettingsChanged() {
        this.handleNetworkChanged();
    }
    
    private int incrementNetworkErrorCount(final String s) {
        final Integer value = this.getSafeNetworkErrorCount(s) + 1;
        this.mPlayableNetworkErrorCountMap.put(s, value);
        return value;
    }
    
    private void onCancelMtJobRunnable() {
        Log.i("nf_downloadController", "onCancelMtJobRunnable");
        this.cancelMaintenanceJobIfScheduled();
        this.mCancelMtJobRunnableScheduled = false;
    }
    
    private void onNetworkChangeRunnable() {
        if (this.mFirstTimeNetworkChangeReceived) {
            this.mFirstTimeNetworkChangeReceived = false;
            Log.i("nf_downloadController", "mNetworkChangeRunnable delaying the first network change event by " + TimeUnit.MILLISECONDS.toSeconds(this.FIRST_TIME_NETWORK_CHANGE_RUNNABLE_DELAY));
            this.mWorkHandler.removeCallbacks(this.mFirstTimeNetworkRunnable);
            this.mWorkHandler.postDelayed(this.mFirstTimeNetworkRunnable, this.FIRST_TIME_NETWORK_CHANGE_RUNNABLE_DELAY);
            return;
        }
        this.handleNetworkChanged();
    }
    
    private void resetDLWindow() {
        Log.i("nf_downloadController", "resetDLWindow");
        this.mNumberOfNetworkErrorsInCurrentDLWindow = 0;
        this.resetDownloadResumeJobBackOffTime();
    }
    
    private void resetDownloadResumeJobBackOffTime() {
        Log.i("nf_downloadController", "resetDownloadResumeJobBackOffTime");
        this.mIndexOfDlWindowBackOffTime = 0;
        PreferenceUtils.putIntPref(this.mContext, "download_back_off_window_index", 0);
    }
    
    private void resetFirstLevelBackOff() {
        this.mIndexOfNextPlayable = 0;
        this.resetPlayableNetworkErrorCounts();
    }
    
    private void resetPlayableNetworkErrorCounts() {
        final Iterator<Map.Entry<String, Integer>> iterator = this.mPlayableNetworkErrorCountMap.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().setValue(0);
        }
    }
    
    private void scheduleBackOffTimer(long addRandomDelayToBackOffTime) {
        this.mWorkHandler.removeCallbacks(this.mBackOffRunnable);
        addRandomDelayToBackOffTime = this.addRandomDelayToBackOffTime(addRandomDelayToBackOffTime);
        Log.e("nf_downloadController", "scheduleBackOffTimer for seconds=" + TimeUnit.MILLISECONDS.toSeconds(addRandomDelayToBackOffTime));
        this.mWorkHandler.postDelayed(this.mBackOffRunnable, addRandomDelayToBackOffTime);
    }
    
    private void scheduleDownloadResumeJob(final long minimumDelay) {
        if (this.mNetflixJobScheduler.isJobScheduled(this.mDownloadResumeJob.getNetflixJobId())) {
            this.mNetflixJobScheduler.cancelJob(this.mDownloadResumeJob.getNetflixJobId());
        }
        this.mDownloadResumeJob.setMinimumDelay(minimumDelay);
        this.mNetflixJobScheduler.scheduleJob(this.mDownloadResumeJob);
        Log.i("nf_downloadController", "DownloadResumeJob scheduled minimumDelay=" + TimeUnit.MILLISECONDS.toSeconds(minimumDelay));
    }
    
    private void scheduleDownloadResumeJobNoDelay() {
        this.scheduleDownloadResumeJob(0L);
    }
    
    private void scheduleMaintenanceJobIfRequired() {
        if (this.mDownloadMaintenanceJob == null) {
            return;
        }
        if (!this.mNetflixJobScheduler.isJobScheduled(NetflixJob$NetflixJobId.DOWNLOAD_MAINTENANCE)) {
            Log.i("nf_downloadController", "DownloadMaintenanceJob scheduled");
            this.mNetflixJobScheduler.scheduleJob(this.mDownloadMaintenanceJob);
            OfflineUtils.setMaintenanceJobPeriodMsToPref(this.mContext, this.mDownloadMaintenanceJob.getRepeatingPeriodInMs());
            return;
        }
        Log.i("nf_downloadController", "DownloadMaintenanceJob already scheduled");
    }
    
    private void scheduleNextDLWindow() {
        this.resetFirstLevelBackOff();
        this.mNumberOfNetworkErrorsInCurrentDLWindow = 0;
        this.mIndexOfDlWindowBackOffTime = PreferenceUtils.getIntPref(this.mContext, "download_back_off_window_index", 0);
        if (this.mIndexOfDlWindowBackOffTime < DownloadController.DL_WINDOW_BACK_OFF_TIMES.length) {
            this.scheduleDownloadResumeJob(this.addRandomDelayToBackOffTime(DownloadController.DL_WINDOW_BACK_OFF_TIMES[this.mIndexOfDlWindowBackOffTime]));
            ++this.mIndexOfDlWindowBackOffTime;
            PreferenceUtils.putIntPref(this.mContext, "download_back_off_window_index", this.mIndexOfDlWindowBackOffTime);
            return;
        }
        Log.e("nf_downloadController", "scheduleNextDLWindow exhausted all DL windows mIndexOfDlWindowBackOffTime=" + this.mIndexOfDlWindowBackOffTime);
    }
    
    private void updateConnectedNetworkType() {
        if (ConnectivityUtils.isConnectedOrConnecting(this.mContext)) {
            this.mConnectedNetType = ConnectivityUtils.getCurrentNetType(this.mContext);
            return;
        }
        this.mConnectedNetType = null;
    }
    
    private void updateItemCounts() {
        this.mCompletedCount = 0;
        this.mInProgressCount = 0;
        this.mIncompleteItems = 0;
        for (final OfflinePlayable offlinePlayable : this.mOfflinePlayableList) {
            if (offlinePlayable.getDownloadState() == DownloadState.Complete) {
                ++this.mCompletedCount;
            }
            else {
                if (offlinePlayable.getDownloadState() != DownloadState.InProgress) {
                    continue;
                }
                ++this.mInProgressCount;
            }
        }
        this.mIncompleteItems = this.mOfflinePlayableList.size() - this.mCompletedCount;
    }
    
    public boolean canThisPlayableBeResumedByUser(final OfflinePlayable offlinePlayable) {
        if (offlinePlayable.getDownloadState() != DownloadState.Stopped) {
            Log.i("nf_downloadController", "canThisPlayableBeResumedByUser no, state=" + offlinePlayable.getDownloadState());
            return false;
        }
        this.updateItemCounts();
        if (this.mInProgressCount > 0) {
            Log.i("nf_downloadController", "canThisPlayableBeResumedByUser no, mInProgressCount=" + this.mInProgressCount);
            return false;
        }
        if (!this.mDownloadResumeJob.canExecute(this.mContext)) {
            Log.i("nf_downloadController", "canThisPlayableBeResumedByUser no, downloadResumeJob says no.");
            if (!this.mDownloadResumeJob.canExecuteByNetwork(this.mContext)) {
                offlinePlayable.getOfflineViewablePersistentData().setDownloadStateStopped(StopReason.NotAllowedOnCurrentNetwork);
            }
            this.scheduleDownloadResumeJobNoDelay();
            return false;
        }
        return true;
    }
    
    public void destroy() {
        if (!this.mDestroyed) {
            this.mContext.unregisterReceiver((BroadcastReceiver)this.mNetworkChangeReceiver);
            this.mWorkHandler.removeCallbacksAndMessages((Object)null);
            this.mDestroyed = true;
        }
    }
    
    public long getMtJobPeriodInMsFromOfflineConfig() {
        return TimeUnit.HOURS.toMillis(this.mConfigurationAgent.getOfflineConfig().getMaintenanceJobPeriodInHrs());
    }
    
    public OfflinePlayable getNextPlayableForDownload() {
        Log.i("nf_downloadController", "getNextPlayableForDownload mIndexOfNextPlayable=" + this.mIndexOfNextPlayable);
        if (this.mDownloadsAreStoppedByUser) {
            Log.i("nf_downloadController", "getNextPlayableForDownload mDownloadsAreStoppedByUser=true");
            return null;
        }
        if (this.mIsStreaming) {
            Log.i("nf_downloadController", "getNextPlayableForDownload mIsStreaming=true");
            return null;
        }
        if (this.mOfflinePlayableList.size() <= 0) {
            Log.i("nf_downloadController", "getNextPlayableForDownload all done. mOfflinePlayableList.size=" + this.mOfflinePlayableList.size());
            return null;
        }
        this.updateItemCounts();
        if (this.mCompletedCount == this.mOfflinePlayableList.size()) {
            Log.i("nf_downloadController", "getNextPlayableForDownload all downloaded, mCompletedCount=" + this.mCompletedCount);
            return null;
        }
        if (this.mInProgressCount > 0) {
            Log.i("nf_downloadController", "getNextPlayableForDownload already downloading, mInProgressCount=" + this.mInProgressCount);
            return null;
        }
        if (!this.mDownloadResumeJob.canExecute(this.mContext)) {
            Log.i("nf_downloadController", "getNextPlayableForDownload can't execute, downloadResumeJob says no.");
            this.scheduleDownloadResumeJobNoDelay();
            return null;
        }
        for (int i = this.mOfflinePlayableList.size(); i > 0; --i) {
            if (this.mIndexOfNextPlayable >= this.mOfflinePlayableList.size()) {
                this.mIndexOfNextPlayable = 0;
            }
            if (OfflineUtils.canStartDownload(this.mOfflinePlayableList.get(this.mIndexOfNextPlayable))) {
                final OfflinePlayable offlinePlayable = this.mOfflinePlayableList.get(this.mIndexOfNextPlayable);
                Log.i("nf_downloadController", "getNextPlayableForDownload found with errorCount=" + this.getSafeNetworkErrorCount(offlinePlayable.getPlayableId()) + " playableId=" + offlinePlayable.getPlayableId());
                return offlinePlayable;
            }
            ++this.mIndexOfNextPlayable;
        }
        return null;
    }
    
    public void notifyStreamingStarted() {
        this.mIsStreaming = true;
        this.mWorkHandler.removeCallbacksAndMessages((Object)null);
        this.mWorkHandler.post((Runnable)new DownloadController$2(this));
    }
    
    public void notifyStreamingStopped() {
        this.mIsStreaming = false;
        this.mWorkHandler.removeCallbacksAndMessages((Object)null);
        this.mWorkHandler.post((Runnable)new DownloadController$3(this));
    }
    
    public void onAllPlayableDeleted() {
        this.mPlayableNetworkErrorCountMap.clear();
        this.cancelDownloadResumeJob();
        this.cancelMaintenanceJobIfScheduled();
    }
    
    public void onDeleted(final String s) {
        this.mPlayableNetworkErrorCountMap.remove(s);
        if (this.mOfflinePlayableList.size() == 0) {
            this.cancelDownloadResumeJob();
            this.cancelMaintenanceJobIfScheduled();
        }
    }
    
    public void onDownloadResumeJobDone() {
        this.mNetflixJobScheduler.onJobFinished(NetflixJob$NetflixJobId.DOWNLOAD_RESUME, false);
    }
    
    public void onDownloadedSuccessfully(final String s) {
        this.mPlayableNetworkErrorCountMap.remove(s);
        this.resetFirstLevelBackOff();
        this.resetDLWindow();
        this.cancelDownloadResumeJob();
        this.scheduleMaintenanceJobIfRequired();
        this.mWorkHandler.removeCallbacks(this.mBackOffRunnable);
    }
    
    public void onMaintenanceJobDone() {
        final long maintenanceJobPeriodMsFromPref = OfflineUtils.getMaintenanceJobPeriodMsFromPref(this.mContext, -1L);
        final long mtJobPeriodInMsFromOfflineConfig = this.getMtJobPeriodInMsFromOfflineConfig();
        Log.i("nf_downloadController", "jobPeriodFromPref=" + maintenanceJobPeriodMsFromPref + " jobPeriodFromConfigurationAgent=" + mtJobPeriodInMsFromOfflineConfig);
        if (mtJobPeriodInMsFromOfflineConfig <= 0L) {
            this.cancelMaintenanceJobIfScheduled();
            return;
        }
        if (maintenanceJobPeriodMsFromPref != mtJobPeriodInMsFromOfflineConfig) {
            this.cancelMaintenanceJobIfScheduled();
            this.mDownloadMaintenanceJob = NetflixJob.buildDownloadMaintenanceJob(false, mtJobPeriodInMsFromOfflineConfig);
            this.scheduleMaintenanceJobIfRequired();
            return;
        }
        this.mNetflixJobScheduler.onJobFinished(NetflixJob$NetflixJobId.DOWNLOAD_MAINTENANCE, false);
    }
    
    public void onMaintenanceJobRunningTooEarly() {
        if (!this.mCancelMtJobRunnableScheduled) {
            this.mCancelMtJobRunnableScheduled = true;
            this.mWorkHandler.postDelayed(this.mCancelMtJobRunnable, this.MAINTENANCE_JOB_CANCEL_DELAY);
        }
    }
    
    public void onNetworkError(final String s) {
        final int n = 2;
        this.updateConnectedNetworkType();
        if (this.mConnectedNetType == null) {
            Log.i("nf_downloadController", "onNetworkError networkConnected=no, scheduling download resume job");
            this.scheduleDownloadResumeJobNoDelay();
            return;
        }
        ++this.mNumberOfNetworkErrorsInCurrentDLWindow;
        Log.i("nf_downloadController", "onNetworkError mNumberOfNetworkErrorsInCurrentDLWindow=" + this.mNumberOfNetworkErrorsInCurrentDLWindow);
        this.updateItemCounts();
        int max_NETWORK_ERRORS_IN_DL_WINDOW;
        if ((max_NETWORK_ERRORS_IN_DL_WINDOW = this.mIncompleteItems * (DownloadController.MAX_NETWORK_ERRORS_BEFORE_SELECTING_NEXT_PLAYABLE + 1) - 1) > DownloadController.MAX_NETWORK_ERRORS_IN_DL_WINDOW) {
            max_NETWORK_ERRORS_IN_DL_WINDOW = DownloadController.MAX_NETWORK_ERRORS_IN_DL_WINDOW;
        }
        if (max_NETWORK_ERRORS_IN_DL_WINDOW < 2) {
            max_NETWORK_ERRORS_IN_DL_WINDOW = n;
        }
        Log.i("nf_downloadController", "maxErrorsInDlWindow=" + max_NETWORK_ERRORS_IN_DL_WINDOW);
        if (this.mNumberOfNetworkErrorsInCurrentDLWindow <= max_NETWORK_ERRORS_IN_DL_WINDOW) {
            int incrementNetworkErrorCount;
            if ((incrementNetworkErrorCount = this.incrementNetworkErrorCount(s)) > DownloadController.MAX_NETWORK_ERRORS_BEFORE_SELECTING_NEXT_PLAYABLE) {
                ++this.mIndexOfNextPlayable;
                this.mPlayableNetworkErrorCountMap.put(s, 1);
                incrementNetworkErrorCount = 1;
            }
            this.scheduleBackOffTimer(DownloadController.AttemptToBackOffMilliseconds[(incrementNetworkErrorCount - 1) % DownloadController.AttemptToBackOffMilliseconds.length]);
            return;
        }
        this.scheduleNextDLWindow();
    }
    
    public void onStorageError() {
        Log.i("nf_downloadController", "onStorageError removing the back-off timer");
        this.mWorkHandler.removeCallbacks(this.mBackOffRunnable);
    }
    
    public void onThreadException() {
        this.cancelDownloadResumeJob();
        this.cancelMaintenanceJobIfScheduled();
    }
    
    public void onUnRecoverableError(final String s, final Status status) {
        Log.i("nf_downloadController", "onUnRecoverableError playableId=" + s + " status=" + status);
    }
    
    public boolean requiresUnmeteredConnectionForDownload() {
        return PreferenceUtils.getBooleanPref(this.mContext, "download_requires_unmetered_network", true);
    }
    
    public void setDownloadsAreStoppedByUser(final boolean mDownloadsAreStoppedByUser) {
        if (!(this.mDownloadsAreStoppedByUser = mDownloadsAreStoppedByUser)) {
            this.mIsStreaming = false;
        }
    }
    
    public void setRequiresUnmeteredNetwork(final boolean b) {
        final boolean requiresUnmeteredConnectionForDownload = this.requiresUnmeteredConnectionForDownload();
        if (Log.isLoggable()) {
            Log.i("nf_downloadController", "setRequiresUnmeteredNetwork oldValue=" + requiresUnmeteredConnectionForDownload + " newValue=" + b);
        }
        if (requiresUnmeteredConnectionForDownload != b) {
            PreferenceUtils.putBooleanPref(this.mContext, "download_requires_unmetered_network", b);
            this.mDownloadResumeJob = NetflixJob.buildDownloadResumeJob(b);
            if (this.mNetflixJobScheduler.isJobScheduled(this.mDownloadResumeJob.getNetflixJobId())) {
                this.mNetflixJobScheduler.cancelJob(this.mDownloadResumeJob.getNetflixJobId());
                this.scheduleDownloadResumeJobNoDelay();
            }
            this.handleUserNetworkSettingsChanged();
        }
    }
}
