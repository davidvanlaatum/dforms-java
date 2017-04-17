package org.dforms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.dforms.conditions.Condition;

public class Expression implements NamedObject {
  private final Condition expression;
  private String name;

  @JsonCreator
  public Expression ( Condition expression ) {
    this.expression = expression;
  }

  @JsonValue
  public Condition getExpression () {
    return expression;
  }

  @Override
  public String getName () {
    return name;
  }

  @Override
  public void setName ( String name ) {
    this.name = name;
  }

  @Override
  public String getPath () {
    return getName ();
  }

  @Override
  public String getLabelPath () {
    return getPath ();
  }
}
