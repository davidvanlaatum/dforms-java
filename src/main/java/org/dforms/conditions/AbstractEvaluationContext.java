package org.dforms.conditions;

import org.dforms.Expression;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public abstract class AbstractEvaluationContext implements EvaluationContext {
  private final EvaluationContext parent;
  private Map<String, ExpressionState> expressions = new HashMap<> ();

  protected AbstractEvaluationContext ( EvaluationContext parent ) {
    this.parent = parent;
  }

  @Override
  public ExpressionState getExpression ( String name ) {
    ExpressionState expression = expressions.get ( name );

    if ( expression == null ) {
      Expression parentExpr = requireNonNull ( requireNonNull ( getDataContext (), "No data context" )
          .getFieldContainer (), "no field container" ).getExpression ( name );
      if ( parentExpr == null ) {
        if ( parent != null ) {
          expression = parent.getExpression ( name );
        }
        if ( expression == null ) {
          throw new IllegalArgumentException ( "Expression " + name + " not found" );
        }
      } else {
        expression = new ExpressionState ( parentExpr, this );
        expressions.put ( name, expression );
      }
    }

    return expression;
  }
}
