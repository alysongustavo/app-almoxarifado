package br.gov.prefeitura.almoxarifado.rest;

/**
 * Jersey/jax-rs shim to get it use gson as the default json serializer
 *
 * @author James Kojo
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Provider
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
public final class GsonMessageBodyHandler implements MessageBodyWriter<Object>, MessageBodyReader<Object> {

    private Gson gson;

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @SuppressWarnings("deprecation")
	@Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) {
        InputStreamReader streamReader = new InputStreamReader(entityStream, Charsets.UTF_8);
        try {
            Type jsonType;
            if (type.equals(genericType)) {
                jsonType = type;
            } else {
                jsonType = genericType;
            }
            return getGson().fromJson(streamReader, jsonType);
        } finally {
            IOUtils.closeQuietly(streamReader);
        }
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        OutputStreamWriter writer = new OutputStreamWriter(entityStream, Charsets.UTF_8);
        try {
            Type jsonType;
            if (type.equals(genericType)) {
                jsonType = type;
            } else {
                jsonType = genericType;
            }
            getGson().toJson(object, jsonType, writer);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    /*
    protected Gson getGson() {
        if (gson == null) {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();
        }
        return gson;
    }
    **/
    
    private Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }
        return gson;
    }
}