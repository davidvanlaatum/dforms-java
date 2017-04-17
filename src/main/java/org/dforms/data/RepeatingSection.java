package org.dforms.data;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.fields.Repeating;

import java.util.ArrayList;

public class RepeatingSection extends ArrayList<RepeatingRow> implements DataField<Object> {
  private final Repeating repeating;
  private final DataFieldContainer container;

  public RepeatingSection ( Repeating repeating, DataFieldContainer container ) {
    this.repeating = repeating;
    this.container = container;
  }

  @Override
  public void validate ( ValidationContext context ) {
    for ( RepeatingRow row : this ) {
      row.validate ( context.contextForContainer ( row ) );
    }
  }

  @Override
  public Object getValue () {
    return null;
  }

  @Override
  public void setValue ( Object value ) {

  }

  @Override
  public void parseJSON ( JsonNode node ) {
    clear ();
    for ( JsonNode jsonNode : node ) {
      addRow ().parseJSON ( jsonNode );
    }
  }

  public RepeatingRow addRow () {
    RepeatingRow rt = new RepeatingRow ( container, repeating, size () );
    repeating.buildNewDataStructure ( rt );
    add ( rt );
    return rt;
  }

  @Override
  public String getName () {
    return repeating.getName ();
  }

  @Override
  public void setName ( String name ) {

  }

  @Override
  public String getPath () {
    return repeating.getPath ();
  }

  public String getLabelPath () {
    return repeating.getLabelPath (); // todo
  }
}
