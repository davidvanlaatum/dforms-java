package org.dforms;

import org.dforms.fields.TextInput;

public interface ModifiableFieldContainer extends FieldContainer {
  TextInput addTextField ( String name );
}
