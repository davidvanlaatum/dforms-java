package org.dforms.fields;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.AbstractFieldContainer;
import org.dforms.data.DataField;
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
  public DataField parseJSON ( JsonNode node, DataFieldContainer parent ) {
    RepeatingSection section = new RepeatingSection ( this );

    if ( node.isArray () ) {
      int i = 0;
      for ( JsonNode jsonNode : node ) {
        final RepeatingRow row = new RepeatingRow ( parent, this, i++ );
        section.add ( row );
        jsonToFields ( jsonNode, row );
      }
    }

    return section;
  }

}
