package org.dforms.data;

import org.dforms.FieldContainer;

public class RepeatingRow extends AbstractDataFieldContainer {
  private final int rowNum;

  public RepeatingRow ( DataFieldContainer parent, FieldContainer container, int rowNum ) {
    super ( parent, container );
    this.rowNum = rowNum;
  }

  @Override
  public String getPath () {
    return String.format ( "%s%d/", super.getPath (), rowNum );
  }
}
