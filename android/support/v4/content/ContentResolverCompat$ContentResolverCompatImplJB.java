// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.support.v4.os.OperationCanceledException;
import android.database.Cursor;
import android.support.v4.os.CancellationSignal;
import android.net.Uri;
import android.content.ContentResolver;

class ContentResolverCompat$ContentResolverCompatImplJB extends ContentResolverCompat$ContentResolverCompatImplBase
{
    @Override
    public Cursor query(final ContentResolver contentResolver, final Uri uri, final String[] array, final String s, final String[] array2, final String s2, final CancellationSignal cancellationSignal) {
        Label_0029: {
            if (cancellationSignal == null) {
                break Label_0029;
            }
            try {
                Object cancellationSignalObject = cancellationSignal.getCancellationSignalObject();
                return ContentResolverCompatJellybean.query(contentResolver, uri, array, s, array2, s2, cancellationSignalObject);
                cancellationSignalObject = null;
                return ContentResolverCompatJellybean.query(contentResolver, uri, array, s, array2, s2, cancellationSignalObject);
            }
            catch (Exception ex) {
                if (ContentResolverCompatJellybean.isFrameworkOperationCanceledException(ex)) {
                    throw new OperationCanceledException();
                }
                throw ex;
            }
        }
    }
}
