package org.dforms;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.dforms.fields.Field;

import java.util.LinkedHashMap;

public interface FieldContainer extends NamedObject {
  @JsonInclude ( JsonInclude.Include.ALWAYS )
  String getLabel ();

  LinkedHashMap<String, Field> getFields ();

  Field getField ( String fieldName );

  Expression getExpression ( String name );
}
