package org.dforms.conditions;

import org.apache.commons.lang3.BooleanUtils;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractRelativeCondition extends AbstractMultipleSubCondition {
  private final static List<Class<? extends Number>> numberHirac = Arrays.asList ( Integer.class, Long.class, Float.class, Double.class );


  protected boolean isGreater ( Object first, Object second ) {
    Boolean rt = areNumbers ( first, second, ( f, s, common ) -> {
      switch ( common.getSimpleName () ) {
        case "Integer":
          return f.intValue () > s.intValue ();
        case "Long":
          return f.longValue () > s.longValue ();
        case "Float":
          return f.floatValue () > s.floatValue ();
        case "Double":
          return f.doubleValue () > s.doubleValue ();
        default:
          throw new IllegalStateException ( "Unsupported number type! " + common.getSimpleName () );
      }
    } ); // todo exception if not numbers?
    return BooleanUtils.isTrue ( rt );
  }

  @SuppressWarnings ( "unchecked" )
  protected Boolean areNumbers ( Object first, Object second, NumbersCallback callback ) {
    if ( first instanceof Number && second instanceof Number ) {
      Class<? extends Number> common;
      if ( numberHirac.indexOf ( first.getClass () ) > numberHirac.indexOf ( second.getClass () ) ) {
        common = (Class<? extends Number>) first.getClass ();
      } else {
        common = (Class<? extends Number>) second.getClass ();
      }
      return callback.callback ( (Number) first, (Number) second, common );
    }
    return null;
  }

  interface NumbersCallback {
    Boolean callback ( Number first, Number second, Class<? extends Number> common );
  }
}
