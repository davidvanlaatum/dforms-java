package org.dforms.data;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.fields.TextInput;

public class TextField extends AbstractDataField<String, TextInput> {
  public TextField ( String value, TextInput field, DataFieldContainer parent ) {
    super ( field, parent );
  }

  @Override
  public void parseJSON ( JsonNode node ) {
    if ( node.isTextual () ) {
      setValue ( node.asText () );
    } else if ( !node.isNull () ) {
      throw new IllegalArgumentException ( "Invalid value for field " + getPath () + " " + node.getNodeType ().toString () );
    }
  }
}
