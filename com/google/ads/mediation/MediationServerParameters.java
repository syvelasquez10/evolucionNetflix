// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import com.google.android.gms.internal.ct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public abstract class MediationServerParameters
{
    protected void a() {
    }
    
    public void load(Map<String, String> iterator) throws MappingException {
        final HashMap<Object, Field> hashMap = new HashMap<Object, Field>();
        final Field[] fields = this.getClass().getFields();
        for (int length = fields.length, i = 0; i < length; ++i) {
            final Field field = fields[i];
            final Parameter parameter = field.getAnnotation(Parameter.class);
            if (parameter != null) {
                hashMap.put(parameter.name(), field);
            }
        }
        if (hashMap.isEmpty()) {
            ct.v("No server options fields detected. To suppress this message either add a field with the @Parameter annotation, or override the load() method.");
        }
        iterator = ((Map<Object, Object>)iterator).entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<Object, Object> entry = iterator.next();
            final Field field2 = hashMap.remove(entry.getKey());
            if (field2 != null) {
                try {
                    field2.set(this, entry.getValue());
                }
                catch (IllegalAccessException ex) {
                    ct.v("Server option \"" + entry.getKey() + "\" could not be set: Illegal Access");
                }
                catch (IllegalArgumentException ex2) {
                    ct.v("Server option \"" + entry.getKey() + "\" could not be set: Bad Type");
                }
            }
            else {
                ct.r("Unexpected server option: " + entry.getKey() + " = \"" + entry.getValue() + "\"");
            }
        }
        final StringBuilder sb = new StringBuilder();
        for (final Field field3 : hashMap.values()) {
            if (field3.getAnnotation(Parameter.class).required()) {
                ct.v("Required server option missing: " + field3.getAnnotation(Parameter.class).name());
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(field3.getAnnotation(Parameter.class).name());
            }
        }
        if (sb.length() > 0) {
            throw new MappingException("Required server option(s) missing: " + sb.toString());
        }
        this.a();
    }
    
    public static final class MappingException extends Exception
    {
        public MappingException(final String s) {
            super(s);
        }
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.FIELD })
    protected @interface Parameter {
        String name();
        
        boolean required() default true;
    }
}
