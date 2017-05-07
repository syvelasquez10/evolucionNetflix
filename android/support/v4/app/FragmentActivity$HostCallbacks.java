// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.content.Intent;
import android.view.Window;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import java.io.PrintWriter;
import java.io.FileDescriptor;

class FragmentActivity$HostCallbacks extends FragmentHostCallback<FragmentActivity>
{
    final /* synthetic */ FragmentActivity this$0;
    
    public FragmentActivity$HostCallbacks(final FragmentActivity this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    public void onAttachFragment(final Fragment fragment) {
        this.this$0.onAttachFragment(fragment);
    }
    
    @Override
    public void onDump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        this.this$0.dump(s, fileDescriptor, printWriter, array);
    }
    
    @Override
    public View onFindViewById(final int n) {
        return this.this$0.findViewById(n);
    }
    
    @Override
    public LayoutInflater onGetLayoutInflater() {
        return this.this$0.getLayoutInflater().cloneInContext((Context)this.this$0);
    }
    
    @Override
    public int onGetWindowAnimations() {
        final Window window = this.this$0.getWindow();
        if (window == null) {
            return 0;
        }
        return window.getAttributes().windowAnimations;
    }
    
    @Override
    public boolean onHasView() {
        final Window window = this.this$0.getWindow();
        return window != null && window.peekDecorView() != null;
    }
    
    @Override
    public boolean onHasWindowAnimations() {
        return this.this$0.getWindow() != null;
    }
    
    @Override
    public boolean onShouldSaveFragmentState(final Fragment fragment) {
        return !this.this$0.isFinishing();
    }
    
    @Override
    public void onStartActivityFromFragment(final Fragment fragment, final Intent intent, final int n) {
        this.this$0.startActivityFromFragment(fragment, intent, n);
    }
    
    @Override
    public void onSupportInvalidateOptionsMenu() {
        this.this$0.supportInvalidateOptionsMenu();
    }
}
