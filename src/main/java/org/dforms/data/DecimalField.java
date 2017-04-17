package org.dforms.data;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.fields.DecimalInput;

public class DecimalField extends AbstractDataField<Double, DecimalInput> {
  public DecimalField ( Double value, DecimalInput input, DataFieldContainer parent ) {
    super ( input, parent );
  }

  @Override
  public void parseJSON ( JsonNode node ) {
    if ( node.isDouble () ) {
      setValue ( node.asDouble () );
    } else if ( !node.isNull () ) {
      throw new IllegalArgumentException ( "Invalid value for field " + getPath () + " " + node.getNodeType ().toString () );
    }
  }
}
