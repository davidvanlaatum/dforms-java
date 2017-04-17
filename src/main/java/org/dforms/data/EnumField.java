package org.dforms.data;

import org.dforms.fields.EnumInput;

public class EnumField extends AbstractDataField<String, EnumInput> {
  public EnumField ( String value, EnumInput input, DataFieldContainer parent ) {
    super ( value, input, parent );
  }
}
