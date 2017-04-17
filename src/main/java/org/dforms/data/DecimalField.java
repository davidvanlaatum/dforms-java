package org.dforms.data;

import org.dforms.fields.DecimalInput;

public class DecimalField extends AbstractDataField<Double, DecimalInput> {
  public DecimalField ( Double value, DecimalInput input, DataFieldContainer parent ) {
    super ( value, input, parent );
  }
}
