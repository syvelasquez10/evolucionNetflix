// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.os.Build$VERSION;
import android.annotation.SuppressLint;
import android.app.Application$ActivityLifecycleCallbacks;

@SuppressLint({ "NewApi" })
public final class as implements Application$ActivityLifecycleCallbacks
{
    private int a;
    private boolean b;
    private boolean c;
    private boolean d;
    private at e;
    private ax f;
    private ap g;
    private bb h;
    private boolean i;
    
    public as(final at e, final ax f, final ap g) {
        this.a = 0;
        this.b = false;
        this.c = false;
        this.d = false;
        this.i = false;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = new bb(e.getBaseContext(), f);
    }
    
    @SuppressLint({ "NewApi" })
    public static void a(final at at, final as as) {
        if (Build$VERSION.SDK_INT >= 14) {
            try {
                if (at.a == null) {
                    throw new at$b();
                }
            }
            catch (at$b at$b) {
                dw.b("Application context not provided. Automatic breadcrumbs and transaction foreground times will not be recorded.");
                return;
            }
            final Application a = at.a;
            dw.d("registering lifecycle callbacks");
            a.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)as);
            return;
        }
        dw.c("API Level is less than 14. Automatic breadcrumbs are not supported.");
    }
    
    public final void onActivityCreated(final Activity activity, final Bundle bundle) {
    }
    
    public final void onActivityDestroyed(final Activity activity) {
    }
    
    public final void onActivityPaused(final Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            if (!this.i) {
                this.a = 1;
                this.i = true;
                dw.d("about to send app load from onPause");
                this.g.a();
            }
            if (this.c) {
                activity.unregisterReceiver((BroadcastReceiver)this.h);
                this.c = false;
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public final void onActivityResumed(final Activity activity) {
        if (activity == null) {
            return;
        }
        while (true) {
            while (true) {
                Label_0207: {
                    try {
                        if (!this.i) {
                            this.i = true;
                            dw.d("about to send app load from onResume");
                            this.g.a();
                        }
                        if (this.b) {
                            dw.d("not a foreground. rotation event.");
                            this.b = false;
                        }
                        else {
                            if (this.a != 0) {
                                break Label_0207;
                            }
                            this.f.a(new bj(bj$a.a));
                            be.g();
                            if (!this.d) {
                                this.d = true;
                                final b a = new d(this.e.getBaseContext()).a();
                                if (a != com.crittercism.internal.b.c) {
                                    if (a != com.crittercism.internal.b.d) {
                                        goto Label_0181;
                                    }
                                    this.f.a(new cc(cc$a.b));
                                }
                            }
                        }
                        ++this.a;
                        final IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
                        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                        activity.registerReceiver((BroadcastReceiver)this.h, intentFilter);
                        this.c = true;
                        return;
                    }
                    catch (ThreadDeath threadDeath) {
                        throw threadDeath;
                    }
                    catch (Throwable t) {
                        dw.b(t);
                        return;
                    }
                }
                this.f.a(new bh(bh$a.a, activity.getClass().getName()));
                continue;
            }
        }
    }
    
    public final void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public final void onActivityStarted(final Activity activity) {
    }
    
    public final void onActivityStopped(final Activity activity) {
        if (activity != null) {
            try {
                if (!this.i) {
                    this.a = 1;
                    this.i = true;
                }
                --this.a;
                if (activity.isChangingConfigurations()) {
                    dw.d("not a background. rotation event.");
                    this.b = true;
                    return;
                }
                if (this.a == 0) {
                    this.f.a(new bj(bj$a.b));
                    be.a(this.f);
                    return;
                }
                goto Label_0084;
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dw.b(t);
            }
        }
    }
}
