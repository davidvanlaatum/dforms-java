package org.dforms.fields;

import org.dforms.AbstractFieldContainer;
import org.dforms.data.DataFieldContainer;

public class MultipleFields extends AbstractFieldContainer<MultipleFields> implements Field {

  @Override
  public void buildNewDataStructure ( DataFieldContainer container ) {
    for ( Field field : getFields ().values () ) {
      field.buildNewDataStructure ( container );
    }
  }
}
