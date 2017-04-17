package org.dforms.conditions;

import org.dforms.data.DataFieldContainer;

public interface EvaluationContext {
  ExpressionState getExpression ( String name );

  DataFieldContainer getDataContext ();
}
