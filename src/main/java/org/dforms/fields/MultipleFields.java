package org.dforms.fields;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.AbstractFieldContainer;
import org.dforms.data.DataField;
import org.dforms.data.DataFieldContainer;

public class MultipleFields extends AbstractFieldContainer<MultipleFields> implements Field {
  @Override
  public DataField parseJSON ( JsonNode node, DataFieldContainer parent ) {
    return null;
  }
}
