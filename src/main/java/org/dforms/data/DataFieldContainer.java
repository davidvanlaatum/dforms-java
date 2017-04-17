package org.dforms.data;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.FieldContainer;
import org.dforms.NamedObject;

public interface DataFieldContainer extends NamedObject {
  void addField ( String key, DataField field );

  DataField getField ( String name );

  default <T extends DataField> T getField ( String name, Class<T> klass ) {
    DataField f = getField ( name );
    if ( klass.isInstance ( f ) ) {
      return klass.cast ( f );
    } else if ( f != null ) {
      throw new IllegalStateException ( "Expected field to be of type " + klass.getName () + " but got " + f.getClass ().getName () );
    } else {
      return null;
    }
  }

  void validate ( ValidationContext context );

  FieldContainer getFieldContainer ();

  void parseJSON ( JsonNode node );
}
