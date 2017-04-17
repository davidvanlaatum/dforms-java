package org.dforms.data;

import org.dforms.fields.Repeating;

import java.util.ArrayList;

public class RepeatingSection extends ArrayList<RepeatingRow> implements DataField<RepeatingSection> {
  private final Repeating repeating;

  public RepeatingSection ( Repeating repeating ) {
    this.repeating = repeating;
  }

  @Override
  public void validate ( ValidationContext context ) {
    for ( RepeatingRow row : this ) {
      row.validate ( context.contextForContainer ( row ) );
    }
  }

  @Override
  public RepeatingSection getValue () {
    return this;
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
