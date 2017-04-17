package org.dforms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.data.DataField;
import org.dforms.data.DataFieldContainer;
import org.dforms.fields.Field;

import java.util.LinkedHashMap;

public interface FieldContainer extends NamedObject {
  @JsonInclude ( JsonInclude.Include.ALWAYS )
  String getLabel ();

  LinkedHashMap<String, Field> getFields ();

  Field getField ( String fieldName );

  Expression getExpression ( String name );

  default void jsonToFields ( JsonNode jsonNode, DataFieldContainer container ) {
    jsonNode.fields ().forEachRemaining ( cur -> {
      Field f = getField ( cur.getKey () );
      if ( f != null ) {
        final DataField field = f.parseJSON ( cur.getValue (), container );
        if ( field != null ) {
          container.addField ( cur.getKey (), field );
        }
      }
    } );
  }
}
