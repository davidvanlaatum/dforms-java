package org.dforms.data;

import org.dforms.FieldContainer;
import org.dforms.NamedObject;

public interface DataFieldContainer extends NamedObject {
  void addField ( String key, DataField field );

  DataField getField ( String name );

  void validate ( ValidationContext context );

  FieldContainer getFieldContainer ();
}
