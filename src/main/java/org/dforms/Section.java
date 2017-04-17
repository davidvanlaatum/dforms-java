package org.dforms;

import org.dforms.data.DataFieldContainer;
import org.dforms.fields.Field;

public class Section extends AbstractFieldContainer<Section> {

  @Override
  public void buildNewDataStructure ( DataFieldContainer container ) {
    for ( Field field : getFields ().values () ) {
      field.buildNewDataStructure ( container );
    }
  }
}
