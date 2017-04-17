package org.dforms.fields;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.data.CurrencyField;
import org.dforms.data.DataField;
import org.dforms.data.DataFieldContainer;

public class CurrencyInput extends DecimalInput {
  @Override
  public DataField parseJSON ( JsonNode node, DataFieldContainer parent ) {
    return new CurrencyField ( node.isNull () ? null : node.asDouble (), this, parent );
  }
}
