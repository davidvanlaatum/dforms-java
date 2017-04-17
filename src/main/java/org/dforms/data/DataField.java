package org.dforms.data;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.NamedObject;

public interface DataField<T> extends NamedObject {
  void validate ( ValidationContext context );

  T getValue ();

  void setValue ( T value );

  void parseJSON ( JsonNode node );
}
