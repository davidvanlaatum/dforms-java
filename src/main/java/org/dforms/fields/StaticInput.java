package org.dforms.fields;

import org.dforms.data.DataFieldContainer;
import org.dforms.data.StaticField;

public class StaticInput extends Input {

  @Override
  public void buildNewDataStructure ( DataFieldContainer container ) {
    container.addField ( getName (), new StaticField ( null, this, container ) );
  }
}
