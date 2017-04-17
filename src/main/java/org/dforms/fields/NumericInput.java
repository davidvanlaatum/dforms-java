package org.dforms.fields;

import org.dforms.data.DataFieldContainer;
import org.dforms.data.NumericField;

public class NumericInput extends AbstractNumericInput {

  @Override
  public void buildNewDataStructure ( DataFieldContainer container ) {
    container.addField ( getName (), new NumericField ( null, this, container ) );
  }
}
