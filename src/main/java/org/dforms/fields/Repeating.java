package org.dforms.fields;

import org.dforms.AbstractFieldContainer;
import org.dforms.data.DataFieldContainer;
import org.dforms.data.RepeatingRow;
import org.dforms.data.RepeatingSection;

public class Repeating extends AbstractFieldContainer<Repeating> implements Field {
  private Boolean inline;
  private Integer minRows;
  private Integer maxRows;

  public Integer getMinRows () {
    return minRows;
  }

  public Repeating setMinRows ( Integer minRows ) {
    this.minRows = minRows;
    return this;
  }

  public Integer getMaxRows () {
    return maxRows;
  }

  public Repeating setMaxRows ( Integer maxRows ) {
    this.maxRows = maxRows;
    return this;
  }

  public Boolean getInline () {
    return inline;
  }

  public Repeating setInline ( Boolean inline ) {
    this.inline = inline;
    return this;
  }

  @Override
  public void buildNewDataStructure ( DataFieldContainer container ) {
    if ( container instanceof RepeatingRow ) {
      for ( Field field : getFields ().values () ) {
        field.buildNewDataStructure ( container );
      }
    } else {
      container.addField ( getName (), new RepeatingSection ( this, container ) );
    }
  }
}
