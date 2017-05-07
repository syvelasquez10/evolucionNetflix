// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import android.net.Uri;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import java.util.List;
import android.app.Activity;
import android.content.Intent;

public class NetflixComHandlerFactory
{
    protected static final String ADD_TO_MY_LIST_SUFFIX = "add";
    protected static final String BROWSE_SUFFIX = "browse";
    protected static final String DETAILS_PAGE_SUFFIX = "title";
    public static final String FUTURE_HANDLER_SCHEME = "https";
    public static final String HANDLER_PREFIX = "www.netflix.com";
    public static final String HANDLER_SCHEME = "http";
    private static final String HOME_SUFFIX = "";
    protected static final String SEARCH_SUFFIX = "search";
    protected static final String SYNC_SUFFIX = "sync";
    private static final String TAG = "NetflixComHandlerFactory";
    protected static final String WATCH_SUFFIX = "watch";
    
    public static boolean canHandle(final Intent intent) {
        return intent != null && intent.getData() != null && ("http".equalsIgnoreCase(intent.getData().getScheme()) || "https".equalsIgnoreCase(intent.getData().getScheme())) && "www.netflix.com".equalsIgnoreCase(intent.getData().getAuthority());
    }
    
    public static boolean finishMeAndLaunchBrowserIfNeeded(final Activity activity, final Intent intent) {
        final boolean b = true;
        final List pathSegments = intent.getData().getPathSegments();
        final NetflixComHandler handler = getHandler(pathSegments);
        boolean b2 = b;
        if (handler != null) {
            b2 = (!handler.canHandle(pathSegments) && b);
        }
        if (b2) {
            NetflixComUtils.launchBrowser(activity, intent.getData());
            activity.finish();
        }
        return b2;
    }
    
    private static NetflixComHandler getHandler(final List<String> list) {
        int n = 0;
        String s;
        if (list.size() > 0) {
            s = list.get(0);
        }
        else {
            s = "";
        }
        Label_0094: {
            switch (s.hashCode()) {
                case 0: {
                    if (s.equals("")) {
                        break Label_0094;
                    }
                    break;
                }
                case 110371416: {
                    if (s.equals("title")) {
                        n = 1;
                        break Label_0094;
                    }
                    break;
                }
                case 112903375: {
                    if (s.equals("watch")) {
                        n = 2;
                        break Label_0094;
                    }
                    break;
                }
                case -1380604278: {
                    if (s.equals("browse")) {
                        n = 3;
                        break Label_0094;
                    }
                    break;
                }
                case 96417: {
                    if (s.equals("add")) {
                        n = 4;
                        break Label_0094;
                    }
                    break;
                }
                case -906336856: {
                    if (s.equals("search")) {
                        n = 5;
                        break Label_0094;
                    }
                    break;
                }
                case 3545755: {
                    if (s.equals("sync")) {
                        n = 6;
                        break Label_0094;
                    }
                    break;
                }
            }
            n = -1;
        }
        switch (n) {
            default: {
                final String string = "SPY-7518 - got unsupported suffix: " + s;
                Log.e("NetflixComHandlerFactory", string);
                ErrorLoggingManager.logHandledException(string);
                return null;
            }
            case 0: {
                return new NetflixComHomeHandler();
            }
            case 1: {
                return new NetflixComVideoDetailsHandler();
            }
            case 2: {
                return new NetflixComWatchHandler();
            }
            case 3: {
                return new NetflixComBrowseHandler();
            }
            case 4: {
                return new NetflixComAddToListHandler();
            }
            case 5: {
                return new NetflixComSearchHandler();
            }
            case 6: {
                return new NetflixComSyncHandler();
            }
        }
    }
    
    public static NflxHandler$Response handle(final NetflixActivity netflixActivity, final Uri uri) {
        final List pathSegments = uri.getPathSegments();
        final NetflixComHandler handler = getHandler(pathSegments);
        if (handler == null) {
            Log.w("NetflixComHandlerFactory", "Got null creator for data: " + uri.toString() + ". Redirecting user to browser.");
        }
        else {
            final NflxHandler$Response tryHandle = handler.tryHandle(netflixActivity, pathSegments, NetflixComUtils.getTrackId(uri));
            if (tryHandle != NflxHandler$Response.NOT_HANDLING) {
                UIViewLogUtils.reportUIViewCommand((Context)netflixActivity, UIViewLogging$UIViewCommandName.deepLink, IClientLogging$ModalView.homeScreen, null, uri.toString());
                return tryHandle;
            }
            ErrorLoggingManager.logHandledException("SPY-7518 - couldn't handle the following data: " + uri.toString());
        }
        NetflixComUtils.launchBrowser(netflixActivity, uri);
        return NflxHandler$Response.HANDLING;
    }
}
