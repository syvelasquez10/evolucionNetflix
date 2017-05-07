// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.text.TextUtils;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import java.io.IOException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api;

public final class Cast
{
    public static final Api<CastOptions> API;
    static final Api.c<ij> CU;
    private static final Api.b<ij, CastOptions> CV;
    public static final CastApi CastApi;
    public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
    public static final int MAX_MESSAGE_LENGTH = 65536;
    public static final int MAX_NAMESPACE_LENGTH = 128;
    
    static {
        CU = new Api.c();
        CV = new Api.b<ij, CastOptions>() {
            public ij a(final Context context, final Looper looper, final ClientSettings clientSettings, final CastOptions castOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                n.b(castOptions, "Setting the API options is required.");
                return new ij(context, looper, castOptions.EK, castOptions.EM, castOptions.EL, connectionCallbacks, onConnectionFailedListener);
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api<CastOptions>((Api.b<C, CastOptions>)Cast.CV, (Api.c<C>)Cast.CU, new Scope[0]);
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
        
        PendingResult<ApplicationConnectionResult> launchApplication(final GoogleApiClient p0, final String p1, final LaunchOptions p2);
        
        @Deprecated
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
                return googleApiClient.a(Cast.CU).getApplicationMetadata();
            }
            
            @Override
            public String getApplicationStatus(final GoogleApiClient googleApiClient) throws IllegalStateException {
                return googleApiClient.a(Cast.CU).getApplicationStatus();
            }
            
            @Override
            public double getVolume(final GoogleApiClient googleApiClient) throws IllegalStateException {
                return googleApiClient.a(Cast.CU).fF();
            }
            
            @Override
            public boolean isMute(final GoogleApiClient googleApiClient) throws IllegalStateException {
                return googleApiClient.a(Cast.CU).isMute();
            }
            
            @Override
            public PendingResult<ApplicationConnectionResult> joinApplication(final GoogleApiClient googleApiClient) {
                return googleApiClient.b((PendingResult<ApplicationConnectionResult>)new c() {
                    protected void a(final ij ij) throws RemoteException {
                        try {
                            ij.b(null, null, (BaseImplementation.b<ApplicationConnectionResult>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).V(2001);
                        }
                    }
                });
            }
            
            @Override
            public PendingResult<ApplicationConnectionResult> joinApplication(final GoogleApiClient googleApiClient, final String s) {
                return googleApiClient.b((PendingResult<ApplicationConnectionResult>)new c() {
                    protected void a(final ij ij) throws RemoteException {
                        try {
                            ij.b(s, null, (BaseImplementation.b<ApplicationConnectionResult>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).V(2001);
                        }
                    }
                });
            }
            
            @Override
            public PendingResult<ApplicationConnectionResult> joinApplication(final GoogleApiClient googleApiClient, final String s, final String s2) {
                return googleApiClient.b((PendingResult<ApplicationConnectionResult>)new c() {
                    protected void a(final ij ij) throws RemoteException {
                        try {
                            ij.b(s, s2, (BaseImplementation.b<ApplicationConnectionResult>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).V(2001);
                        }
                    }
                });
            }
            
            @Override
            public PendingResult<ApplicationConnectionResult> launchApplication(final GoogleApiClient googleApiClient, final String s) {
                return googleApiClient.b((PendingResult<ApplicationConnectionResult>)new c() {
                    protected void a(final ij ij) throws RemoteException {
                        try {
                            ij.a(s, false, (BaseImplementation.b<ApplicationConnectionResult>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).V(2001);
                        }
                    }
                });
            }
            
            @Override
            public PendingResult<ApplicationConnectionResult> launchApplication(final GoogleApiClient googleApiClient, final String s, final LaunchOptions launchOptions) {
                return googleApiClient.b((PendingResult<ApplicationConnectionResult>)new c() {
                    protected void a(final ij ij) throws RemoteException {
                        try {
                            ij.a(s, launchOptions, (BaseImplementation.b<ApplicationConnectionResult>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).V(2001);
                        }
                    }
                });
            }
            
            @Deprecated
            @Override
            public PendingResult<ApplicationConnectionResult> launchApplication(final GoogleApiClient googleApiClient, final String s, final boolean relaunchIfRunning) {
                return this.launchApplication(googleApiClient, s, new LaunchOptions.Builder().setRelaunchIfRunning(relaunchIfRunning).build());
            }
            
            @Override
            public PendingResult<Status> leaveApplication(final GoogleApiClient googleApiClient) {
                return googleApiClient.b((PendingResult<Status>)new b() {
                    protected void a(final ij ij) throws RemoteException {
                        try {
                            ij.d((BaseImplementation.b<Status>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).V(2001);
                        }
                    }
                });
            }
            
            @Override
            public void removeMessageReceivedCallbacks(final GoogleApiClient googleApiClient, final String s) throws IOException, IllegalArgumentException {
                try {
                    googleApiClient.a(Cast.CU).aE(s);
                }
                catch (RemoteException ex) {
                    throw new IOException("service error");
                }
            }
            
            @Override
            public void requestStatus(final GoogleApiClient googleApiClient) throws IOException, IllegalStateException {
                try {
                    googleApiClient.a(Cast.CU).fE();
                }
                catch (RemoteException ex) {
                    throw new IOException("service error");
                }
            }
            
            @Override
            public PendingResult<Status> sendMessage(final GoogleApiClient googleApiClient, final String s, final String s2) {
                return googleApiClient.b((PendingResult<Status>)new b() {
                    protected void a(final ij ij) throws RemoteException {
                        try {
                            ij.a(s, s2, (BaseImplementation.b<Status>)this);
                        }
                        catch (IllegalArgumentException ex) {
                            ((Cast.a)this).V(2001);
                        }
                        catch (IllegalStateException ex2) {
                            ((Cast.a)this).V(2001);
                        }
                    }
                });
            }
            
            @Override
            public void setMessageReceivedCallbacks(final GoogleApiClient googleApiClient, final String s, final MessageReceivedCallback messageReceivedCallback) throws IOException, IllegalStateException {
                try {
                    googleApiClient.a(Cast.CU).a(s, messageReceivedCallback);
                }
                catch (RemoteException ex) {
                    throw new IOException("service error");
                }
            }
            
            @Override
            public void setMute(final GoogleApiClient googleApiClient, final boolean b) throws IOException, IllegalStateException {
                try {
                    googleApiClient.a(Cast.CU).G(b);
                }
                catch (RemoteException ex) {
                    throw new IOException("service error");
                }
            }
            
            @Override
            public void setVolume(final GoogleApiClient googleApiClient, final double n) throws IOException, IllegalArgumentException, IllegalStateException {
                try {
                    googleApiClient.a(Cast.CU).a(n);
                }
                catch (RemoteException ex) {
                    throw new IOException("service error");
                }
            }
            
            @Override
            public PendingResult<Status> stopApplication(final GoogleApiClient googleApiClient) {
                return googleApiClient.b((PendingResult<Status>)new b() {
                    protected void a(final ij ij) throws RemoteException {
                        try {
                            ij.a("", (BaseImplementation.b<Status>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).V(2001);
                        }
                    }
                });
            }
            
            @Override
            public PendingResult<Status> stopApplication(final GoogleApiClient googleApiClient, final String s) {
                return googleApiClient.b((PendingResult<Status>)new b() {
                    protected void a(final ij ij) throws RemoteException {
                        if (TextUtils.isEmpty((CharSequence)s)) {
                            ((Cast.a)this).e(2001, "IllegalArgument: sessionId cannot be null or empty");
                            return;
                        }
                        try {
                            ij.a(s, (BaseImplementation.b<Status>)this);
                        }
                        catch (IllegalStateException ex) {
                            ((Cast.a)this).V(2001);
                        }
                    }
                });
            }
        }
    }
    
    public static final class CastOptions implements HasOptions
    {
        final CastDevice EK;
        final Listener EL;
        private final int EM;
        
        private CastOptions(final Builder builder) {
            this.EK = builder.EN;
            this.EL = builder.EO;
            this.EM = builder.EP;
        }
        
        public static Builder builder(final CastDevice castDevice, final Listener listener) {
            return new Builder(castDevice, listener);
        }
        
        public static final class Builder
        {
            CastDevice EN;
            Listener EO;
            private int EP;
            
            private Builder(final CastDevice en, final Listener eo) {
                n.b(en, "CastDevice parameter cannot be null");
                n.b(eo, "CastListener parameter cannot be null");
                this.EN = en;
                this.EO = eo;
                this.EP = 0;
            }
            
            public CastOptions build() {
                return new CastOptions(this);
            }
            
            public Builder setVerboseLoggingEnabled(final boolean b) {
                if (b) {
                    this.EP |= 0x1;
                    return this;
                }
                this.EP &= 0xFFFFFFFE;
                return this;
            }
        }
    }
    
    public static class Listener
    {
        public void W(final int n) {
        }
        
        public void X(final int n) {
        }
        
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
    
    protected abstract static class a<R extends Result> extends BaseImplementation.a<R, ij>
    {
        public a() {
            super(Cast.CU);
        }
        
        public void V(final int n) {
            this.b(this.c(new Status(n)));
        }
        
        public void e(final int n, final String s) {
            this.b(this.c(new Status(n, s, null)));
        }
    }
    
    private abstract static class b extends Cast.a<Status>
    {
        public Status d(final Status status) {
            return status;
        }
    }
    
    private abstract static class c extends Cast.a<ApplicationConnectionResult>
    {
        public ApplicationConnectionResult j(final Status status) {
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
