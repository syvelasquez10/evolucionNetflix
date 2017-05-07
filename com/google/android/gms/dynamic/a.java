// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.widget.FrameLayout;
import java.util.Iterator;
import java.util.LinkedList;
import android.os.Bundle;

public abstract class a<T extends LifecycleDelegate>
{
    private T ss;
    private Bundle st;
    private LinkedList<a> su;
    private final d<T> sv;
    
    public a() {
        this.sv = new d<T>() {
            @Override
            public void a(final T t) {
                a.this.ss = t;
                final Iterator iterator = a.this.su.iterator();
                while (iterator.hasNext()) {
                    iterator.next().b(a.this.ss);
                }
                a.this.su.clear();
                a.this.st = null;
            }
        };
    }
    
    private void a(final Bundle bundle, final a a) {
        if (this.ss != null) {
            a.b(this.ss);
            return;
        }
        if (this.su == null) {
            this.su = new LinkedList<a>();
        }
        this.su.add(a);
        if (bundle != null) {
            if (this.st == null) {
                this.st = (Bundle)bundle.clone();
            }
            else {
                this.st.putAll(bundle);
            }
        }
        this.a(this.sv);
    }
    
    private void ay(final int n) {
        while (!this.su.isEmpty() && this.su.getLast().getState() >= n) {
            this.su.removeLast();
        }
    }
    
    public void a(final FrameLayout frameLayout) {
        final Context context = frameLayout.getContext();
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        final String b = GooglePlayServicesUtil.b(context, googlePlayServicesAvailable, -1);
        final String b2 = GooglePlayServicesUtil.b(context, googlePlayServicesAvailable);
        final LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        frameLayout.addView((View)linearLayout);
        final TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        textView.setText((CharSequence)b);
        linearLayout.addView((View)textView);
        if (b2 != null) {
            final Button button = new Button(context);
            button.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
            button.setText((CharSequence)b2);
            linearLayout.addView((View)button);
            button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    context.startActivity(GooglePlayServicesUtil.a(context, googlePlayServicesAvailable, -1));
                }
            });
        }
    }
    
    protected abstract void a(final d<T> p0);
    
    public T cZ() {
        return this.ss;
    }
    
    public void onCreate(final Bundle bundle) {
        this.a(bundle, (a)new a() {
            @Override
            public void b(final LifecycleDelegate lifecycleDelegate) {
                a.this.ss.onCreate(bundle);
            }
            
            @Override
            public int getState() {
                return 1;
            }
        });
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        this.a(bundle, (a)new a() {
            @Override
            public void b(final LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(a.this.ss.onCreateView(layoutInflater, viewGroup, bundle));
            }
            
            @Override
            public int getState() {
                return 2;
            }
        });
        if (this.ss == null) {
            this.a(frameLayout);
        }
        return (View)frameLayout;
    }
    
    public void onDestroy() {
        if (this.ss != null) {
            this.ss.onDestroy();
            return;
        }
        this.ay(1);
    }
    
    public void onDestroyView() {
        if (this.ss != null) {
            this.ss.onDestroyView();
            return;
        }
        this.ay(2);
    }
    
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        this.a(bundle2, (a)new a() {
            @Override
            public void b(final LifecycleDelegate lifecycleDelegate) {
                a.this.ss.onInflate(activity, bundle, bundle2);
            }
            
            @Override
            public int getState() {
                return 0;
            }
        });
    }
    
    public void onLowMemory() {
        if (this.ss != null) {
            this.ss.onLowMemory();
        }
    }
    
    public void onPause() {
        if (this.ss != null) {
            this.ss.onPause();
            return;
        }
        this.ay(3);
    }
    
    public void onResume() {
        this.a(null, (a)new a() {
            @Override
            public void b(final LifecycleDelegate lifecycleDelegate) {
                a.this.ss.onResume();
            }
            
            @Override
            public int getState() {
                return 3;
            }
        });
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        if (this.ss != null) {
            this.ss.onSaveInstanceState(bundle);
        }
        else if (this.st != null) {
            bundle.putAll(this.st);
        }
    }
    
    private interface a
    {
        void b(final LifecycleDelegate p0);
        
        int getState();
    }
}
