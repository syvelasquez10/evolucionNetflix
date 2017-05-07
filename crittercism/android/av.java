// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.annotation.SuppressLint;
import android.app.Application$ActivityLifecycleCallbacks;

@SuppressLint({ "NewApi" })
public final class av implements Application$ActivityLifecycleCallbacks
{
    private int a;
    private boolean b;
    private boolean c;
    private boolean d;
    private Context e;
    private az f;
    private bd g;
    
    public av(final Context e, final az f) {
        this.a = 0;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = e;
        this.f = f;
        this.g = new bd(e, f);
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
            if (this.c) {
                activity.unregisterReceiver((BroadcastReceiver)this.g);
                this.c = false;
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
    
    public final void onActivityResumed(final Activity activity) {
        if (activity == null) {
            return;
        }
        while (true) {
            while (true) {
                Label_0178: {
                    try {
                        if (this.b) {
                            dy.b();
                            this.b = false;
                        }
                        else {
                            if (this.a != 0) {
                                break Label_0178;
                            }
                            this.f.a(new bl(bl$a.a));
                            bg.f();
                            if (!this.d) {
                                this.d = true;
                                final b a = new d(this.e).a();
                                if (a != crittercism.android.b.c) {
                                    if (a != crittercism.android.b.d) {
                                        goto Label_0152;
                                    }
                                    this.f.a(new ce(ce$a.b));
                                }
                            }
                        }
                        ++this.a;
                        final IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
                        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                        activity.registerReceiver((BroadcastReceiver)this.g, intentFilter);
                        this.c = true;
                        return;
                    }
                    catch (ThreadDeath threadDeath) {
                        throw threadDeath;
                    }
                    catch (Throwable t) {
                        dy.a(t);
                        return;
                    }
                }
                this.f.a(new bj(bj$a.a, activity.getClass().getName()));
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
                --this.a;
                if (activity.isChangingConfigurations()) {
                    dy.b();
                    this.b = true;
                    return;
                }
                if (this.a == 0) {
                    this.f.a(new bl(bl$a.b));
                    bg.a(this.f);
                    return;
                }
                goto Label_0065;
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dy.a(t);
            }
        }
    }
}
