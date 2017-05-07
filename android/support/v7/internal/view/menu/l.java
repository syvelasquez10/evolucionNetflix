// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.KeyEvent$DispatcherState;
import android.view.Window;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.content.DialogInterface;
import android.view.WindowManager$LayoutParams;
import android.view.View;
import android.support.v7.appcompat.R$style;
import android.support.v7.appcompat.R$layout;
import android.app.AlertDialog$Builder;
import android.os.IBinder;
import android.app.AlertDialog;
import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnClickListener;

public class l implements DialogInterface$OnClickListener, DialogInterface$OnDismissListener, DialogInterface$OnKeyListener, z
{
    g a;
    private i b;
    private AlertDialog c;
    private z d;
    
    public l(final i b) {
        this.b = b;
    }
    
    public void a() {
        if (this.c != null) {
            this.c.dismiss();
        }
    }
    
    public void a(final IBinder token) {
        final i b = this.b;
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(b.f());
        (this.a = new g(R$layout.abc_list_menu_item_layout, R$style.Theme_AppCompat_CompactMenu)).setCallback(this);
        this.b.a(this.a);
        alertDialog$Builder.setAdapter(this.a.a(), (DialogInterface$OnClickListener)this);
        final View p = b.p();
        if (p != null) {
            alertDialog$Builder.setCustomTitle(p);
        }
        else {
            alertDialog$Builder.setIcon(b.o()).setTitle(b.n());
        }
        alertDialog$Builder.setOnKeyListener((DialogInterface$OnKeyListener)this);
        (this.c = alertDialog$Builder.create()).setOnDismissListener((DialogInterface$OnDismissListener)this);
        final WindowManager$LayoutParams attributes = this.c.getWindow().getAttributes();
        attributes.type = 1003;
        if (token != null) {
            attributes.token = token;
        }
        attributes.flags |= 0x20000;
        this.c.show();
    }
    
    public void a(final z d) {
        this.d = d;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.b.a((MenuItem)this.a.a().getItem(n), 0);
    }
    
    public void onCloseMenu(final i i, final boolean b) {
        if (b || i == this.b) {
            this.a();
        }
        if (this.d != null) {
            this.d.onCloseMenu(i, b);
        }
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        this.a.onCloseMenu(this.b, true);
    }
    
    public boolean onKey(final DialogInterface dialogInterface, final int n, final KeyEvent keyEvent) {
        if (n == 82 || n == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                final Window window = this.c.getWindow();
                if (window != null) {
                    final View decorView = window.getDecorView();
                    if (decorView != null) {
                        final KeyEvent$DispatcherState keyDispatcherState = decorView.getKeyDispatcherState();
                        if (keyDispatcherState != null) {
                            keyDispatcherState.startTracking(keyEvent, (Object)this);
                            return true;
                        }
                    }
                }
            }
            else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled()) {
                final Window window2 = this.c.getWindow();
                if (window2 != null) {
                    final View decorView2 = window2.getDecorView();
                    if (decorView2 != null) {
                        final KeyEvent$DispatcherState keyDispatcherState2 = decorView2.getKeyDispatcherState();
                        if (keyDispatcherState2 != null && keyDispatcherState2.isTracking(keyEvent)) {
                            this.b.b(true);
                            dialogInterface.dismiss();
                            return true;
                        }
                    }
                }
            }
        }
        return this.b.performShortcut(n, keyEvent, 0);
    }
    
    public boolean onOpenSubMenu(final i i) {
        return this.d != null && this.d.onOpenSubMenu(i);
    }
}
