// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.OutputStream;
import org.json.JSONArray;

public abstract class ci extends bp
{
    public abstract JSONArray a();
    
    @Override
    public final void a(final OutputStream outputStream) {
        final String string = this.a().toString();
        new StringBuilder("BREADCRUMB WRITING ").append(string);
        dy.b();
        outputStream.write(string.getBytes());
    }
}
