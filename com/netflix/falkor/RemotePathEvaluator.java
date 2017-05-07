// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.ArrayList;
import java.net.URLEncoder;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import com.google.gson.JsonObject;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.lang.reflect.Type;
import com.google.gson.GsonBuilder;
import java.net.URI;
import com.google.gson.Gson;

public class RemotePathEvaluator extends BasePathEvaluator
{
    private static final String TAG = "RemotePathEvaluator";
    final Gson gson;
    Class<?> rootType;
    URI uri;
    
    public RemotePathEvaluator(final Class<?> clazz, final URI uri) {
        this(clazz, uri, new GsonBuilder());
    }
    
    public RemotePathEvaluator(final Class<?> rootType, final URI uri, final GsonBuilder gsonBuilder) {
        this.rootType = rootType;
        this.uri = uri;
        this.gson = gsonBuilder.registerTypeAdapter(PQL.class, new PQLAdapter()).create();
    }
    
    private Type getPathClass(final PQL pql) {
        final List<Object> keySegments = pql.getKeySegments();
        Class<?> rootType = this.rootType;
        final Iterator<Object> iterator = keySegments.iterator();
        Type type = null;
        Type genericReturnType = null;
        Class<?> clazz;
        String string;
        Type type2;
        Class<?> returnType = null;
        Method method;
        Type genericReturnType2;
        Type type3 = null;
        Class<?> clazz2;
        Class<?> clazz3;
        Type type4;
        Label_0219_Outer:Label_0069_Outer:
        while (true) {
            clazz = rootType;
            if (!iterator.hasNext()) {
                return clazz;
            }
            string = iterator.next().toString();
            while (true) {
                while (true) {
                    Label_0230: {
                        if (genericReturnType == null) {
                            break Label_0230;
                        }
                        Label_0241: {
                            try {
                                if (this.parseInt(string) != null) {
                                    type2 = null;
                                    type = genericReturnType;
                                    genericReturnType = null;
                                    break Label_0219;
                                }
                                break Label_0230;
                            Label_0154:
                                while (true) {
                                    Block_8: {
                                        break Block_8;
                                        method = rootType.getMethod("get" + string.substring(0, 1).toUpperCase() + string.substring(1), (Class<?>[])new Class[0]);
                                        returnType = method.getReturnType();
                                        while (true) {
                                            Block_6: {
                                                break Block_6;
                                                type3 = ((ParameterizedType)genericReturnType2).getActualTypeArguments()[1];
                                                break Label_0241;
                                                Label_0193:
                                                clazz = JsonObject.class;
                                                return clazz;
                                                genericReturnType = method.getGenericReturnType();
                                                type = returnType;
                                                type2 = null;
                                                break Label_0219;
                                            }
                                            continue;
                                        }
                                    }
                                    genericReturnType2 = method.getGenericReturnType();
                                    continue;
                                }
                            }
                            // iftrue(Label_0209:, !Map.class.isAssignableFrom((Class<?>)returnType))
                            // iftrue(Label_0193:, !returnType instanceof Class)
                            // iftrue(Label_0154:, !List.class.isAssignableFrom((Class<?>)returnType))
                            // iftrue(Label_0204:, !genericReturnType2 instanceof ParameterizedType)
                            catch (Exception ex) {
                                return JsonObject.class;
                            }
                            Label_0204: {
                                type3 = null;
                            }
                            break Label_0241;
                            Label_0209:
                            clazz2 = null;
                            genericReturnType = null;
                            type = returnType;
                            type2 = clazz2;
                            break Label_0219;
                        }
                        type = returnType;
                        clazz3 = null;
                        type2 = type3;
                        genericReturnType = clazz3;
                        type4 = type;
                        type = type2;
                        rootType = (Class<?>)type4;
                        continue Label_0219_Outer;
                    }
                    if (type != null) {
                        type2 = null;
                        genericReturnType = null;
                        continue Label_0069_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    private Integer parseInt(final String s) {
        if (s == null) {
            return null;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public Iterable<PathBoundValue> deleteAbsolute(final Iterable<PQL> iterable) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Iterable<PathBoundValue> getAbsolute(final Iterable<PQL> iterable) {
        final String string = this.uri.toString();
        final StringBuilder sb = new StringBuilder(string);
        while (true) {
            Label_0113: {
                if (string.indexOf("?") != -1) {
                    break Label_0113;
                }
                sb.append("?");
                sb.append("method=get&responseFormat=json&pathFormat=canonical&progressive=false");
                final Iterator<PQL> iterator = iterable.iterator();
                int n = 0;
                while (iterator.hasNext()) {
                    final PQL pql = iterator.next();
                    ++n;
                    try {
                        sb.append("&path=").append(URLEncoder.encode(this.gson.toJson(pql), "UTF-8"));
                        continue;
                    }
                    catch (Exception ex) {
                        return new ArrayList<PathBoundValue>();
                    }
                    break Label_0113;
                }
                if (n == 0) {
                    return new ArrayList<PathBoundValue>();
                }
                return new RemotePathEvaluator$1(this, sb);
            }
            sb.append("&");
            continue;
        }
    }
    
    @Override
    public AbstractPathEvaluator getRoot() {
        return this;
    }
    
    @Override
    public Iterable<PathBoundValue> setAbsolute(final Iterable<PathBoundValue> iterable) {
        throw new UnsupportedOperationException();
    }
}
