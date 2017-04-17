package org.dforms;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.LinkedHashMap;

public class NamedFieldDeserializer<T> extends StdDeserializer<LinkedHashMap<String, T>> {
  protected NamedFieldDeserializer ( Class<? extends T> vc ) {
    super ( vc );
  }

  @Override
  public LinkedHashMap<String, T> deserialize ( JsonParser p, DeserializationContext ctxt ) throws IOException {
    LinkedHashMap<String, T> map = new LinkedHashMap<> ();
    while ( p.nextToken () != JsonToken.END_OBJECT ) {
      p.nextValue ();
      @SuppressWarnings ( "unchecked" ) T value = (T) p.readValueAs ( this.handledType () );
      if ( value instanceof NamedObject ) {
        ( (NamedObject) value ).setName ( p.getCurrentName () );
      }
      map.put ( p.getCurrentName (), value );
    }
    return map;
  }
}
