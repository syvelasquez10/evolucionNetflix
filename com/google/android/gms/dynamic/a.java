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
    private T RP;
    private Bundle RQ;
    private LinkedList<a> RR;
    private final f<T> RS;
    
    public a() {
        this.RS = new f<T>() {
            @Override
            public void a(final T t) {
                a.this.RP = t;
                final Iterator iterator = a.this.RR.iterator();
                while (iterator.hasNext()) {
                    iterator.next().b(a.this.RP);
                }
                a.this.RR.clear();
                a.this.RQ = null;
            }
        };
    }
    
    private void a(final Bundle bundle, final a a) {
        if (this.RP != null) {
            a.b(this.RP);
            return;
        }
        if (this.RR == null) {
            this.RR = new LinkedList<a>();
        }
        this.RR.add(a);
        if (bundle != null) {
            if (this.RQ == null) {
                this.RQ = (Bundle)bundle.clone();
            }
            else {
                this.RQ.putAll(bundle);
            }
        }
        this.a(this.RS);
    }
    
    public static void b(final FrameLayout frameLayout) {
        final Context context = frameLayout.getContext();
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        final String d = GooglePlayServicesUtil.d(context, googlePlayServicesAvailable);
        final String e = GooglePlayServicesUtil.e(context, googlePlayServicesAvailable);
        final LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        frameLayout.addView((View)linearLayout);
        final TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        textView.setText((CharSequence)d);
        linearLayout.addView((View)textView);
        if (e != null) {
            final Button button = new Button(context);
            button.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
            button.setText((CharSequence)e);
            linearLayout.addView((View)button);
            button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    context.startActivity(GooglePlayServicesUtil.c(context, googlePlayServicesAvailable));
                }
            });
        }
    }
    
    private void cv(final int n) {
        while (!this.RR.isEmpty() && this.RR.getLast().getState() >= n) {
            this.RR.removeLast();
        }
    }
    
    protected void a(final FrameLayout frameLayout) {
        b(frameLayout);
    }
    
    protected abstract void a(final f<T> p0);
    
    public T it() {
        return this.RP;
    }
    
    public void onCreate(final Bundle bundle) {
        this.a(bundle, (a)new a() {
            @Override
            public void b(final LifecycleDelegate lifecycleDelegate) {
                a.this.RP.onCreate(bundle);
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
                frameLayout.addView(a.this.RP.onCreateView(layoutInflater, viewGroup, bundle));
            }
            
            @Override
            public int getState() {
                return 2;
            }
        });
        if (this.RP == null) {
            this.a(frameLayout);
        }
        return (View)frameLayout;
    }
    
    public void onDestroy() {
        if (this.RP != null) {
            this.RP.onDestroy();
            return;
        }
        this.cv(1);
    }
    
    public void onDestroyView() {
        if (this.RP != null) {
            this.RP.onDestroyView();
            return;
        }
        this.cv(2);
    }
    
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        this.a(bundle2, (a)new a() {
            @Override
            public void b(final LifecycleDelegate lifecycleDelegate) {
                a.this.RP.onInflate(activity, bundle, bundle2);
            }
            
            @Override
            public int getState() {
                return 0;
            }
        });
    }
    
    public void onLowMemory() {
        if (this.RP != null) {
            this.RP.onLowMemory();
        }
    }
    
    public void onPause() {
        if (this.RP != null) {
            this.RP.onPause();
            return;
        }
        this.cv(5);
    }
    
    public void onResume() {
        this.a(null, (a)new a() {
            @Override
            public void b(final LifecycleDelegate lifecycleDelegate) {
                a.this.RP.onResume();
            }
            
            @Override
            public int getState() {
                return 5;
            }
        });
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        if (this.RP != null) {
            this.RP.onSaveInstanceState(bundle);
        }
        else if (this.RQ != null) {
            bundle.putAll(this.RQ);
        }
    }
    
    public void onStart() {
        this.a(null, (a)new a() {
            @Override
            public void b(final LifecycleDelegate lifecycleDelegate) {
                a.this.RP.onStart();
            }
            
            @Override
            public int getState() {
                return 4;
            }
        });
    }
    
    public void onStop() {
        if (this.RP != null) {
            this.RP.onStop();
            return;
        }
        this.cv(4);
    }
    
    private interface a
    {
        void b(final LifecycleDelegate p0);
        
        int getState();
    }
}
