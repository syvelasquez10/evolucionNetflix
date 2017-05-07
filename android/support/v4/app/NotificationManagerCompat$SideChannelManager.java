// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.app.Notification;
import android.provider.Settings$Secure;
import android.os.Build$VERSION;
import android.app.NotificationManager;
import android.os.Message;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import android.os.DeadObjectException;
import android.os.IBinder;
import java.util.Iterator;
import android.util.Log;
import android.content.Intent;
import java.util.HashSet;
import java.util.HashMap;
import android.content.ComponentName;
import java.util.Map;
import android.os.HandlerThread;
import android.os.Handler;
import android.content.Context;
import java.util.Set;
import android.os.Handler$Callback;
import android.content.ServiceConnection;

class NotificationManagerCompat$SideChannelManager implements ServiceConnection, Handler$Callback
{
    private static final String KEY_BINDER = "binder";
    private static final int MSG_QUEUE_TASK = 0;
    private static final int MSG_RETRY_LISTENER_QUEUE = 3;
    private static final int MSG_SERVICE_CONNECTED = 1;
    private static final int MSG_SERVICE_DISCONNECTED = 2;
    private Set<String> mCachedEnabledPackages;
    private final Context mContext;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private final Map<ComponentName, NotificationManagerCompat$SideChannelManager$ListenerRecord> mRecordMap;
    
    public NotificationManagerCompat$SideChannelManager(final Context mContext) {
        this.mRecordMap = new HashMap<ComponentName, NotificationManagerCompat$SideChannelManager$ListenerRecord>();
        this.mCachedEnabledPackages = new HashSet<String>();
        this.mContext = mContext;
        (this.mHandlerThread = new HandlerThread("NotificationManagerCompat")).start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper(), (Handler$Callback)this);
    }
    
    private boolean ensureServiceBound(final NotificationManagerCompat$SideChannelManager$ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord) {
        if (notificationManagerCompat$SideChannelManager$ListenerRecord.bound) {
            return true;
        }
        notificationManagerCompat$SideChannelManager$ListenerRecord.bound = this.mContext.bindService(new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(notificationManagerCompat$SideChannelManager$ListenerRecord.componentName), (ServiceConnection)this, NotificationManagerCompat.SIDE_CHANNEL_BIND_FLAGS);
        if (notificationManagerCompat$SideChannelManager$ListenerRecord.bound) {
            notificationManagerCompat$SideChannelManager$ListenerRecord.retryCount = 0;
        }
        else {
            Log.w("NotifManCompat", "Unable to bind to listener " + notificationManagerCompat$SideChannelManager$ListenerRecord.componentName);
            this.mContext.unbindService((ServiceConnection)this);
        }
        return notificationManagerCompat$SideChannelManager$ListenerRecord.bound;
    }
    
    private void ensureServiceUnbound(final NotificationManagerCompat$SideChannelManager$ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord) {
        if (notificationManagerCompat$SideChannelManager$ListenerRecord.bound) {
            this.mContext.unbindService((ServiceConnection)this);
            notificationManagerCompat$SideChannelManager$ListenerRecord.bound = false;
        }
        notificationManagerCompat$SideChannelManager$ListenerRecord.service = null;
    }
    
    private void handleQueueTask(final NotificationManagerCompat$Task notificationManagerCompat$Task) {
        this.updateListenerMap();
        for (final NotificationManagerCompat$SideChannelManager$ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord : this.mRecordMap.values()) {
            notificationManagerCompat$SideChannelManager$ListenerRecord.taskQueue.add(notificationManagerCompat$Task);
            this.processListenerQueue(notificationManagerCompat$SideChannelManager$ListenerRecord);
        }
    }
    
    private void handleRetryListenerQueue(final ComponentName componentName) {
        final NotificationManagerCompat$SideChannelManager$ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord = this.mRecordMap.get(componentName);
        if (notificationManagerCompat$SideChannelManager$ListenerRecord != null) {
            this.processListenerQueue(notificationManagerCompat$SideChannelManager$ListenerRecord);
        }
    }
    
    private void handleServiceConnected(final ComponentName componentName, final IBinder binder) {
        final NotificationManagerCompat$SideChannelManager$ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord = this.mRecordMap.get(componentName);
        if (notificationManagerCompat$SideChannelManager$ListenerRecord != null) {
            notificationManagerCompat$SideChannelManager$ListenerRecord.service = INotificationSideChannel$Stub.asInterface(binder);
            notificationManagerCompat$SideChannelManager$ListenerRecord.retryCount = 0;
            this.processListenerQueue(notificationManagerCompat$SideChannelManager$ListenerRecord);
        }
    }
    
    private void handleServiceDisconnected(final ComponentName componentName) {
        final NotificationManagerCompat$SideChannelManager$ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord = this.mRecordMap.get(componentName);
        if (notificationManagerCompat$SideChannelManager$ListenerRecord != null) {
            this.ensureServiceUnbound(notificationManagerCompat$SideChannelManager$ListenerRecord);
        }
    }
    
    private void processListenerQueue(final NotificationManagerCompat$SideChannelManager$ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Processing component " + notificationManagerCompat$SideChannelManager$ListenerRecord.componentName + ", " + notificationManagerCompat$SideChannelManager$ListenerRecord.taskQueue.size() + " queued tasks");
        }
        if (!notificationManagerCompat$SideChannelManager$ListenerRecord.taskQueue.isEmpty()) {
            if (!this.ensureServiceBound(notificationManagerCompat$SideChannelManager$ListenerRecord) || notificationManagerCompat$SideChannelManager$ListenerRecord.service == null) {
                this.scheduleListenerRetry(notificationManagerCompat$SideChannelManager$ListenerRecord);
                return;
            }
            Label_0141: {
                break Label_0141;
                while (true) {
                    try {
                        NotificationManagerCompat$Task notificationManagerCompat$Task = null;
                        do {
                            if (Log.isLoggable("NotifManCompat", 3)) {
                                Log.d("NotifManCompat", "Sending task " + notificationManagerCompat$Task);
                            }
                            notificationManagerCompat$Task.send(notificationManagerCompat$SideChannelManager$ListenerRecord.service);
                            notificationManagerCompat$SideChannelManager$ListenerRecord.taskQueue.remove();
                            notificationManagerCompat$Task = notificationManagerCompat$SideChannelManager$ListenerRecord.taskQueue.peek();
                        } while (notificationManagerCompat$Task != null);
                        if (!notificationManagerCompat$SideChannelManager$ListenerRecord.taskQueue.isEmpty()) {
                            this.scheduleListenerRetry(notificationManagerCompat$SideChannelManager$ListenerRecord);
                        }
                    }
                    catch (DeadObjectException ex2) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Remote service has died: " + notificationManagerCompat$SideChannelManager$ListenerRecord.componentName);
                        }
                        continue;
                    }
                    catch (RemoteException ex) {
                        Log.w("NotifManCompat", "RemoteException communicating with " + notificationManagerCompat$SideChannelManager$ListenerRecord.componentName, (Throwable)ex);
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void scheduleListenerRetry(final NotificationManagerCompat$SideChannelManager$ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord) {
        if (this.mHandler.hasMessages(3, (Object)notificationManagerCompat$SideChannelManager$ListenerRecord.componentName)) {
            return;
        }
        ++notificationManagerCompat$SideChannelManager$ListenerRecord.retryCount;
        if (notificationManagerCompat$SideChannelManager$ListenerRecord.retryCount > 6) {
            Log.w("NotifManCompat", "Giving up on delivering " + notificationManagerCompat$SideChannelManager$ListenerRecord.taskQueue.size() + " tasks to " + notificationManagerCompat$SideChannelManager$ListenerRecord.componentName + " after " + notificationManagerCompat$SideChannelManager$ListenerRecord.retryCount + " retries");
            notificationManagerCompat$SideChannelManager$ListenerRecord.taskQueue.clear();
            return;
        }
        final int n = (1 << notificationManagerCompat$SideChannelManager$ListenerRecord.retryCount - 1) * 1000;
        if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Scheduling retry for " + n + " ms");
        }
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(3, (Object)notificationManagerCompat$SideChannelManager$ListenerRecord.componentName), (long)n);
    }
    
    private void updateListenerMap() {
        final Set<String> enabledListenerPackages = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
        if (!enabledListenerPackages.equals(this.mCachedEnabledPackages)) {
            this.mCachedEnabledPackages = enabledListenerPackages;
            final List queryIntentServices = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 4);
            final HashSet<ComponentName> set = new HashSet<ComponentName>();
            for (final ResolveInfo resolveInfo : queryIntentServices) {
                if (enabledListenerPackages.contains(resolveInfo.serviceInfo.packageName)) {
                    final ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
                    if (resolveInfo.serviceInfo.permission != null) {
                        Log.w("NotifManCompat", "Permission present on component " + componentName + ", not adding listener record.");
                    }
                    else {
                        set.add(componentName);
                    }
                }
            }
            for (final ComponentName componentName2 : set) {
                if (!this.mRecordMap.containsKey(componentName2)) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Adding listener record for " + componentName2);
                    }
                    this.mRecordMap.put(componentName2, new NotificationManagerCompat$SideChannelManager$ListenerRecord(componentName2));
                }
            }
            final Iterator<Map.Entry<ComponentName, NotificationManagerCompat$SideChannelManager$ListenerRecord>> iterator3 = this.mRecordMap.entrySet().iterator();
            while (iterator3.hasNext()) {
                final Map.Entry<ComponentName, NotificationManagerCompat$SideChannelManager$ListenerRecord> entry = iterator3.next();
                if (!set.contains(entry.getKey())) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Removing listener record for " + entry.getKey());
                    }
                    this.ensureServiceUnbound(entry.getValue());
                    iterator3.remove();
                }
            }
        }
    }
    
    public boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                this.handleQueueTask((NotificationManagerCompat$Task)message.obj);
                return true;
            }
            case 1: {
                final NotificationManagerCompat$ServiceConnectedEvent notificationManagerCompat$ServiceConnectedEvent = (NotificationManagerCompat$ServiceConnectedEvent)message.obj;
                this.handleServiceConnected(notificationManagerCompat$ServiceConnectedEvent.componentName, notificationManagerCompat$ServiceConnectedEvent.iBinder);
                return true;
            }
            case 2: {
                this.handleServiceDisconnected((ComponentName)message.obj);
                return true;
            }
            case 3: {
                this.handleRetryListenerQueue((ComponentName)message.obj);
                return true;
            }
        }
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Connected to service " + componentName);
        }
        this.mHandler.obtainMessage(1, (Object)new NotificationManagerCompat$ServiceConnectedEvent(componentName, binder)).sendToTarget();
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Disconnected from service " + componentName);
        }
        this.mHandler.obtainMessage(2, (Object)componentName).sendToTarget();
    }
    
    public void queueTask(final NotificationManagerCompat$Task notificationManagerCompat$Task) {
        this.mHandler.obtainMessage(0, (Object)notificationManagerCompat$Task).sendToTarget();
    }
}
