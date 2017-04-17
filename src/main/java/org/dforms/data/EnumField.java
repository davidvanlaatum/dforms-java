package org.dforms.data;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.fields.EnumInput;

public class EnumField extends AbstractDataField<String, EnumInput> {
  public EnumField ( String value, EnumInput input, DataFieldContainer parent ) {
    super ( input, parent );
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
