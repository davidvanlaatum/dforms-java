package org.dforms.data;


import org.dforms.fields.NumericInput;

public class NumericField extends AbstractDataField<Integer, NumericInput> {
  public NumericField ( Integer value, NumericInput input, DataFieldContainer parent ) {
    super ( value, input, parent );
  }
}
