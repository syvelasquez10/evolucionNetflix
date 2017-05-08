// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.res.Resources$NotFoundException;

final class AppCompatDelegateImplBase$1 implements UncaughtExceptionHandler
{
    final /* synthetic */ UncaughtExceptionHandler val$defHandler;
    
    AppCompatDelegateImplBase$1(final UncaughtExceptionHandler val$defHandler) {
        this.val$defHandler = val$defHandler;
    }
    
    private boolean shouldWrapException(final Throwable t) {
        boolean b2;
        final boolean b = b2 = false;
        if (t instanceof Resources$NotFoundException) {
            final String message = t.getMessage();
            b2 = b;
            if (message != null) {
                if (!message.contains("drawable")) {
                    b2 = b;
                    if (!message.contains("Drawable")) {
                        return b2;
                    }
                }
                b2 = true;
            }
        }
        return b2;
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        if (this.shouldWrapException(t)) {
            final Resources$NotFoundException ex = new Resources$NotFoundException(t.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            ((Throwable)ex).initCause(t.getCause());
            ((Throwable)ex).setStackTrace(t.getStackTrace());
            this.val$defHandler.uncaughtException(thread, (Throwable)ex);
            return;
        }
        this.val$defHandler.uncaughtException(thread, t);
    }
}
