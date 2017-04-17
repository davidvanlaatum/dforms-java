package org.dforms.fields;

import org.dforms.data.DataFieldContainer;
import org.dforms.data.DecimalField;

public class DecimalInput extends AbstractNumericInput {

  @Override
  public void buildNewDataStructure ( DataFieldContainer container ) {
    container.addField ( getName (), new DecimalField ( null, this, container ) );
  }
}
