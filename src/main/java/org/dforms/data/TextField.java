package org.dforms.data;

import org.dforms.fields.TextInput;

public class TextField extends AbstractDataField<String, TextInput> {
  public TextField ( String value, TextInput field, DataFieldContainer parent ) {
    super ( value, field, parent );
  }
}
