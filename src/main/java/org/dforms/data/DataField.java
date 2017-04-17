package org.dforms.data;

import org.dforms.NamedObject;

public interface DataField<T> extends NamedObject {
  void validate ( ValidationContext context );

  T getValue ();
}
