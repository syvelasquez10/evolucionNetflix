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
    private T Hj;
    private Bundle Hk;
    private LinkedList<a> Hl;
    private final f<T> Hm;
    
    public a() {
        this.Hm = new f<T>() {
            @Override
            public void a(final T t) {
                a.this.Hj = t;
                final Iterator iterator = a.this.Hl.iterator();
                while (iterator.hasNext()) {
                    iterator.next().b(a.this.Hj);
                }
                a.this.Hl.clear();
                a.this.Hk = null;
            }
        };
    }
    
    private void a(final Bundle bundle, final a a) {
        if (this.Hj != null) {
            a.b(this.Hj);
            return;
        }
        if (this.Hl == null) {
            this.Hl = new LinkedList<a>();
        }
        this.Hl.add(a);
        if (bundle != null) {
            if (this.Hk == null) {
                this.Hk = (Bundle)bundle.clone();
            }
            else {
                this.Hk.putAll(bundle);
            }
        }
        this.a(this.Hm);
    }
    
    private void aR(final int n) {
        while (!this.Hl.isEmpty() && this.Hl.getLast().getState() >= n) {
            this.Hl.removeLast();
        }
    }
    
    public static void b(final FrameLayout frameLayout) {
        final Context context = frameLayout.getContext();
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        final String c = GooglePlayServicesUtil.c(context, googlePlayServicesAvailable);
        final String d = GooglePlayServicesUtil.d(context, googlePlayServicesAvailable);
        final LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        frameLayout.addView((View)linearLayout);
        final TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        textView.setText((CharSequence)c);
        linearLayout.addView((View)textView);
        if (d != null) {
            final Button button = new Button(context);
            button.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
            button.setText((CharSequence)d);
            linearLayout.addView((View)button);
            button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    context.startActivity(GooglePlayServicesUtil.b(context, googlePlayServicesAvailable));
                }
            });
        }
    }
    
    protected void a(final FrameLayout frameLayout) {
        b(frameLayout);
    }
    
    protected abstract void a(final f<T> p0);
    
    public T fW() {
        return this.Hj;
    }
    
    public void onCreate(final Bundle bundle) {
        this.a(bundle, (a)new a() {
            @Override
            public void b(final LifecycleDelegate lifecycleDelegate) {
                a.this.Hj.onCreate(bundle);
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
                frameLayout.addView(a.this.Hj.onCreateView(layoutInflater, viewGroup, bundle));
            }
            
            @Override
            public int getState() {
                return 2;
            }
        });
        if (this.Hj == null) {
            this.a(frameLayout);
        }
        return (View)frameLayout;
    }
    
    public void onDestroy() {
        if (this.Hj != null) {
            this.Hj.onDestroy();
            return;
        }
        this.aR(1);
    }
    
    public void onDestroyView() {
        if (this.Hj != null) {
            this.Hj.onDestroyView();
            return;
        }
        this.aR(2);
    }
    
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        this.a(bundle2, (a)new a() {
            @Override
            public void b(final LifecycleDelegate lifecycleDelegate) {
                a.this.Hj.onInflate(activity, bundle, bundle2);
            }
            
            @Override
            public int getState() {
                return 0;
            }
        });
    }
    
    public void onLowMemory() {
        if (this.Hj != null) {
            this.Hj.onLowMemory();
        }
    }
    
    public void onPause() {
        if (this.Hj != null) {
            this.Hj.onPause();
            return;
        }
        this.aR(5);
    }
    
    public void onResume() {
        this.a(null, (a)new a() {
            @Override
            public void b(final LifecycleDelegate lifecycleDelegate) {
                a.this.Hj.onResume();
            }
            
            @Override
            public int getState() {
                return 5;
            }
        });
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        if (this.Hj != null) {
            this.Hj.onSaveInstanceState(bundle);
        }
        else if (this.Hk != null) {
            bundle.putAll(this.Hk);
        }
    }
    
    public void onStart() {
        this.a(null, (a)new a() {
            @Override
            public void b(final LifecycleDelegate lifecycleDelegate) {
                a.this.Hj.onStart();
            }
            
            @Override
            public int getState() {
                return 4;
            }
        });
    }
    
    public void onStop() {
        if (this.Hj != null) {
            this.Hj.onStop();
            return;
        }
        this.aR(4);
    }
    
    private interface a
    {
        void b(final LifecycleDelegate p0);
        
        int getState();
    }
}
