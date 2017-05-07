// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.text.TextUtils;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import java.io.IOException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.fq;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.fc;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.en;
import com.google.android.gms.common.api.Api;

public final class Cast
{
    public static final Api<CastOptions> API;
    public static final CastApi CastApi;
    public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
    public static final int MAX_MESSAGE_LENGTH = 65536;
    public static final int MAX_NAMESPACE_LENGTH = 128;
    static final Api.c<en> wx;
    private static final Api.b<en, CastOptions> wy;
    
    static {
        wx = new Api.c();
        wy = new Api.b<en, CastOptions>() {
            public en a(final Context context, final Looper looper, final fc fc, final CastOptions castOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                fq.b(castOptions, "Setting the API options is required.");
                return new en(context, looper, castOptions.xT, castOptions.xV, castOptions.xU, connectionCallbacks, onConnectionFailedListener);
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api<CastOptions>((Api.b<C, CastOptions>)Cast.wy, (Api.c<C>)Cast.wx, new Scope[0]);
        CastApi = new CastApi.a();
    }
    
    public interface ApplicationConnectionResult extends Result
    {
        ApplicationMetadata getApplicationMetadata();
        
        String getApplicationStatus();
        
        String getSessionId();
        
        boolean getWasLaunched();
    }
    
    public interface CastApi
    {
        ApplicationMetadata getApplicationMetadata(final GoogleApiClient p0) throws IllegalStateException;
        
        String getApplicationStatus(final GoogleApiClient p0) throws IllegalStateException;
        
        double getVolume(final GoogleApiClient p0) throws IllegalStateException;
        
        boolean isMute(final GoogleApiClient p0) throws IllegalStateException;
        
        PendingResult<ApplicationConnectionResult> joinApplication(final GoogleApiClient p0);
        
        PendingResult<ApplicationConnectionResult> joinApplication(final GoogleApiClient p0, final String p1);
        
        PendingResult<ApplicationConnectionResult> joinApplication(final GoogleApiClient p0, final String p1, final String p2);
        
        PendingResult<ApplicationConnectionResult> launchApplication(final GoogleApiClient p0, final String p1);
        
        PendingResult<ApplicationConnectionResult> launchApplication(final GoogleApiClient p0, final String p1, final boolean p2);
        
        PendingResult<Status> leaveApplication(final GoogleApiClient p0);
        
        void removeMessageReceivedCallbacks(final GoogleApiClient p0, final String p1) throws IOException, IllegalArgumentException;
        
        void requestStatus(final GoogleApiClient p0) throws IOException, IllegalStateException;
        
        PendingResult<Status> sendMessage(final GoogleApiClient p0, final String p1, final String p2);
        
        void setMessageReceivedCallbacks(final GoogleApiClient p0, final String p1, final MessageReceivedCallback p2) throws IOException, IllegalStateException;
        
        void setMute(final GoogleApiClient p0, final boolean p1) throws IOException, IllegalStateException;
        
        void setVolume(final GoogleApiClient p0, final double p1) throws IOException, IllegalArgumentException, IllegalStateException;
        
        PendingResult<Status> stopApplication(final GoogleApiClient p0);
        
        PendingResult<Status> stopApplication(final GoogleApiClient p0, final String p1);
        
        public static final class a implements CastApi
        {
            @Override
            public ApplicationMetadata getApplicationMetadata(final GoogleApiClient googleApiClient) throws IllegalStateException {
                return googleApiClient.a(Cast.wx).getApplicationMetadata();
            }
            
            @Override
            public String getApplicationStatus(final GoogleApiClient googleApiClient) throws IllegalStateException {
                return googleApiClient.a(Cast.wx).getApplicationStatus();
            }
            
            @Override
            public double getVolume(final GoogleApiClient googleApiClient) throws IllegalStateException {
                return googleApiClient.a(Cast.wx).dI();
            }
            
            @Override
            public boolean isMute(final GoogleApiClient googleApiClient) throws IllegalStateException {
                return googleApiClient.a(Cast.wx).isMute();
            }
            
            @Override
            public PendingResult<ApplicationConnectionResult> joinApplication(final GoogleApiClient googleApiClient) {
                return googleApiClient.b((PendingResult<ApplicationConnectionResult>)new c() {
                    protected void a(final en en) throws RemoteException {
                        try {
                            en.b(null, null, (d<ApplicationConnectionResult>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).x(2001);
                        }
                    }
                });
            }
            
            @Override
            public PendingResult<ApplicationConnectionResult> joinApplication(final GoogleApiClient googleApiClient, final String s) {
                return googleApiClient.b((PendingResult<ApplicationConnectionResult>)new c() {
                    protected void a(final en en) throws RemoteException {
                        try {
                            en.b(s, null, (d<ApplicationConnectionResult>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).x(2001);
                        }
                    }
                });
            }
            
            @Override
            public PendingResult<ApplicationConnectionResult> joinApplication(final GoogleApiClient googleApiClient, final String s, final String s2) {
                return googleApiClient.b((PendingResult<ApplicationConnectionResult>)new c() {
                    protected void a(final en en) throws RemoteException {
                        try {
                            en.b(s, s2, (d<ApplicationConnectionResult>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).x(2001);
                        }
                    }
                });
            }
            
            @Override
            public PendingResult<ApplicationConnectionResult> launchApplication(final GoogleApiClient googleApiClient, final String s) {
                return googleApiClient.b((PendingResult<ApplicationConnectionResult>)new c() {
                    protected void a(final en en) throws RemoteException {
                        try {
                            en.a(s, false, (d<ApplicationConnectionResult>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).x(2001);
                        }
                    }
                });
            }
            
            @Override
            public PendingResult<ApplicationConnectionResult> launchApplication(final GoogleApiClient googleApiClient, final String s, final boolean b) {
                return googleApiClient.b((PendingResult<ApplicationConnectionResult>)new c() {
                    protected void a(final en en) throws RemoteException {
                        try {
                            en.a(s, b, (d<ApplicationConnectionResult>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).x(2001);
                        }
                    }
                });
            }
            
            @Override
            public PendingResult<Status> leaveApplication(final GoogleApiClient googleApiClient) {
                return googleApiClient.b((PendingResult<Status>)new b() {
                    protected void a(final en en) throws RemoteException {
                        try {
                            en.e((d<Status>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).x(2001);
                        }
                    }
                });
            }
            
            @Override
            public void removeMessageReceivedCallbacks(final GoogleApiClient googleApiClient, final String s) throws IOException, IllegalArgumentException {
                try {
                    googleApiClient.a(Cast.wx).V(s);
                }
                catch (RemoteException ex) {
                    throw new IOException("service error");
                }
            }
            
            @Override
            public void requestStatus(final GoogleApiClient googleApiClient) throws IOException, IllegalStateException {
                try {
                    googleApiClient.a(Cast.wx).dH();
                }
                catch (RemoteException ex) {
                    throw new IOException("service error");
                }
            }
            
            @Override
            public PendingResult<Status> sendMessage(final GoogleApiClient googleApiClient, final String s, final String s2) {
                return googleApiClient.b((PendingResult<Status>)new b() {
                    protected void a(final en en) throws RemoteException {
                        try {
                            en.a(s, s2, (d<Status>)this);
                        }
                        catch (IllegalArgumentException ex) {
                            ((Cast.a)this).x(2001);
                        }
                        catch (IllegalStateException ex2) {
                            ((Cast.a)this).x(2001);
                        }
                    }
                });
            }
            
            @Override
            public void setMessageReceivedCallbacks(final GoogleApiClient googleApiClient, final String s, final MessageReceivedCallback messageReceivedCallback) throws IOException, IllegalStateException {
                try {
                    googleApiClient.a(Cast.wx).a(s, messageReceivedCallback);
                }
                catch (RemoteException ex) {
                    throw new IOException("service error");
                }
            }
            
            @Override
            public void setMute(final GoogleApiClient googleApiClient, final boolean b) throws IOException, IllegalStateException {
                try {
                    googleApiClient.a(Cast.wx).v(b);
                }
                catch (RemoteException ex) {
                    throw new IOException("service error");
                }
            }
            
            @Override
            public void setVolume(final GoogleApiClient googleApiClient, final double n) throws IOException, IllegalArgumentException, IllegalStateException {
                try {
                    googleApiClient.a(Cast.wx).a(n);
                }
                catch (RemoteException ex) {
                    throw new IOException("service error");
                }
            }
            
            @Override
            public PendingResult<Status> stopApplication(final GoogleApiClient googleApiClient) {
                return googleApiClient.b((PendingResult<Status>)new b() {
                    protected void a(final en en) throws RemoteException {
                        try {
                            en.a("", (d<Status>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).x(2001);
                        }
                    }
                });
            }
            
            @Override
            public PendingResult<Status> stopApplication(final GoogleApiClient googleApiClient, final String s) {
                return googleApiClient.b((PendingResult<Status>)new b() {
                    protected void a(final en en) throws RemoteException {
                        if (TextUtils.isEmpty((CharSequence)s)) {
                            ((Cast.a)this).c(2001, "IllegalArgument: sessionId cannot be null or empty");
                            return;
                        }
                        try {
                            en.a(s, (d<Status>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).x(2001);
                        }
                    }
                });
            }
        }
    }
    
    public static final class CastOptions implements HasOptions
    {
        final CastDevice xT;
        final Listener xU;
        private final int xV;
        
        private CastOptions(final Builder builder) {
            this.xT = builder.xW;
            this.xU = builder.xX;
            this.xV = builder.xY;
        }
        
        public static Builder builder(final CastDevice castDevice, final Listener listener) {
            return new Builder(castDevice, listener);
        }
        
        public static final class Builder
        {
            CastDevice xW;
            Listener xX;
            private int xY;
            
            private Builder(final CastDevice xw, final Listener xx) {
                fq.b(xw, "CastDevice parameter cannot be null");
                fq.b(xx, "CastListener parameter cannot be null");
                this.xW = xw;
                this.xX = xx;
                this.xY = 0;
            }
            
            public CastOptions build() {
                return new CastOptions(this);
            }
            
            public Builder setVerboseLoggingEnabled(final boolean b) {
                if (b) {
                    this.xY |= 0x1;
                    return this;
                }
                this.xY &= 0xFFFFFFFE;
                return this;
            }
        }
    }
    
    public abstract static class Listener
    {
        public void onApplicationDisconnected(final int n) {
        }
        
        public void onApplicationStatusChanged() {
        }
        
        public void onVolumeChanged() {
        }
    }
    
    public interface MessageReceivedCallback
    {
        void onMessageReceived(final CastDevice p0, final String p1, final String p2);
    }
    
    protected abstract static class a<R extends Result> extends com.google.android.gms.common.api.a.b<R, en>
    {
        public a() {
            super(Cast.wx);
        }
        
        public void c(final int n, final String s) {
            this.a(this.d(new Status(n, s, null)));
        }
        
        public void x(final int n) {
            this.a(this.d(new Status(n)));
        }
    }
    
    private abstract static class b extends Cast.a<Status>
    {
        public Status f(final Status status) {
            return status;
        }
    }
    
    private abstract static class c extends Cast.a<ApplicationConnectionResult>
    {
        public ApplicationConnectionResult h(final Status status) {
            return new ApplicationConnectionResult() {
                @Override
                public ApplicationMetadata getApplicationMetadata() {
                    return null;
                }
                
                @Override
                public String getApplicationStatus() {
                    return null;
                }
                
                @Override
                public String getSessionId() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public boolean getWasLaunched() {
                    return false;
                }
            };
        }
    }
}
