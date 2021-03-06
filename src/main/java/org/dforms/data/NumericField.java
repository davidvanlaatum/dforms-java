package org.dforms.data;


import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.fields.NumericInput;

public class NumericField extends AbstractDataField<Integer, NumericInput> {
  public NumericField ( Integer value, NumericInput input, DataFieldContainer parent ) {
    super ( input, parent );
  }

  @Override
  public void parseJSON ( JsonNode node ) {
    if ( node.canConvertToInt () ) {
      setValue ( node.asInt () );
    } else if ( !node.isNull () ) {
      throw new IllegalArgumentException ( "Invalid value for field " + getPath () + " " + node.getNodeType ().toString () );
    }
  }
}
