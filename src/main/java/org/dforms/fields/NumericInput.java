package org.dforms.fields;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.data.DataField;
import org.dforms.data.DataFieldContainer;
import org.dforms.data.NumericField;

public class NumericInput extends AbstractNumericInput {
  @Override
  public DataField parseJSON ( JsonNode node, DataFieldContainer parent ) {
    return new NumericField ( node.isNull () ? null : node.asInt (), this, parent );
  }
}
