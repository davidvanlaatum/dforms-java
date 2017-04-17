package org.dforms;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.dforms.fields.Field;
import org.dforms.fields.TextInput;

import java.util.LinkedHashMap;

@JsonInclude ( JsonInclude.Include.NON_EMPTY )
public abstract class AbstractFieldContainer<T extends AbstractFieldContainer> implements NamedObject, ModifiableFieldContainer {
  private String name;
  private String label;
  private LinkedHashMap<String, Field> fields = new LinkedHashMap<> ();
  private FieldContainer parent;

  public FieldContainer getParent () {
    return parent;
  }

  @JsonBackReference
  public void setParent ( FieldContainer parent ) {
    this.parent = parent;
  }

  @Override
  public String getName () {
    return this.name;
  }

  public void setName ( String name ) {
    this.name = name;
  }

  @Override
  public String getPath () {
    return ( parent != null ? parent.getPath () : "" ) + getName () + "/";
  }

  @Override
  public String getLabelPath () {
    return ( parent != null ? parent.getLabelPath () : "" ) + getLabel () + "/";
  }

  @Override
  @JsonInclude ( JsonInclude.Include.ALWAYS )
  public String getLabel () {
    return label;
  }

  public T setLabel ( String label ) {
    this.label = label;
    return (T) this;
  }

  @Override
  public LinkedHashMap<String, Field> getFields () {
    return fields;
  }

  @JsonDeserialize ( using = FieldDeserializer.class )
  public T setFields ( LinkedHashMap<String, Field> fields ) {
    this.fields = fields;
    if ( fields != null ) {
      fields.values ().forEach ( field -> {
        field.setParent ( this );
      } );
    }
    return (T) this;
  }

  @Override
  public TextInput addTextField ( String name ) {
    TextInput rt = new TextInput ( name );
    fields.put ( name, rt );
    return rt;
  }

  public Field getField ( String fieldName ) {
    Field field = fields.get ( fieldName );
    if ( field == null ) {
      for ( Field sub : fields.values () ) {
        if ( sub instanceof FieldContainer ) {
          field = ( (FieldContainer) sub ).getField ( fieldName );
          if ( field != null ) {
            break;
          }
        }
      }
    }
    return field;
  }

  @Override
  public Expression getExpression ( String name ) {
    return parent.getExpression ( name );
  }

  public static class FieldDeserializer extends NamedFieldDeserializer<Field> {
    protected FieldDeserializer () {
      super ( Field.class );
    }
  }
}
