package org.dforms.fields;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.data.DataField;
import org.dforms.data.DataFieldContainer;

public class StaticInput extends Input {
  @Override
  public DataField parseJSON ( JsonNode node, DataFieldContainer parent ) {
    return null;
  }
}
