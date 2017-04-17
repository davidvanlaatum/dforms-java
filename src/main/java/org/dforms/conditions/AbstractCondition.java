package org.dforms.conditions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonSubTypes ( {
    @JsonSubTypes.Type ( name = "and", value = AndCondition.class ),
    @JsonSubTypes.Type ( name = "or", value = OrCondition.class ),
    @JsonSubTypes.Type ( name = "not", value = NotCondition.class ),
    @JsonSubTypes.Type ( name = "empty", value = EmptyCondition.class ),
    @JsonSubTypes.Type ( name = "field", value = FieldCondition.class ),
    @JsonSubTypes.Type ( name = "equals", value = EqualsCondition.class ),
    @JsonSubTypes.Type ( name = "value", value = ValueCondition.class ),
    @JsonSubTypes.Type ( name = "greaterthan", value = GreaterThanCondition.class ),
} )
@JsonDeserialize
public abstract class AbstractCondition implements Condition {

  protected static boolean isTrue ( Object value ) {
    if ( value instanceof Boolean ) {
      return (boolean) value;
    }
    return false;
  }
}
