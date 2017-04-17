package org.dforms.fields;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.data.DataField;
import org.dforms.data.DataFieldContainer;
import org.dforms.data.DecimalField;

public class DecimalInput extends AbstractNumericInput {
  @Override
  public DataField parseJSON ( JsonNode node, DataFieldContainer parent ) {
    return new DecimalField ( node.isNull () ? null : node.asDouble (), this, parent );
  }
}
