package org.dforms.data;

import org.dforms.fields.CurrencyInput;

public class CurrencyField extends DecimalField {
  public CurrencyField ( Double value, CurrencyInput currencyInput, DataFieldContainer parent ) {
    super ( value, currencyInput, parent );
  }
}
