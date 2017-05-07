// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import java.util.Iterator;
import java.util.List;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.query.internal.Operator;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.drive.query.internal.f;

public class c implements f<String>
{
    public <T> String a(final b<T> b, final T t) {
        return String.format("contains(%s,%s)", b.getName(), t);
    }
    
    public <T> String a(final Operator operator, final MetadataField<T> metadataField, final T t) {
        return String.format("cmp(%s,%s,%s)", operator.getTag(), metadataField.getName(), t);
    }
    
    public String a(final Operator operator, final List<String> list) {
        final StringBuilder sb = new StringBuilder(operator.getTag() + "(");
        final Iterator<String> iterator = list.iterator();
        String s = "";
        while (iterator.hasNext()) {
            final String s2 = iterator.next();
            sb.append(s);
            sb.append(s2);
            s = ",";
        }
        return sb.append(")").toString();
    }
    
    public String bn(final String s) {
        return String.format("not(%s)", s);
    }
    
    public String c(final MetadataField<?> metadataField) {
        return String.format("fieldOnly(%s)", metadataField.getName());
    }
    
    public <T> String c(final MetadataField<T> metadataField, final T t) {
        return String.format("has(%s,%s)", metadataField.getName(), t);
    }
    
    public String ir() {
        return "all()";
    }
}
