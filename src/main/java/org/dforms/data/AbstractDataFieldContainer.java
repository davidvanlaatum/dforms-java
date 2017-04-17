package org.dforms.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import org.dforms.FieldContainer;

import java.util.LinkedHashMap;

public class AbstractDataFieldContainer implements DataFieldContainer, DataField {
  private final FieldContainer fieldContainer;
  private LinkedHashMap<String, DataField> fields = new LinkedHashMap<> ();
  private DataFieldContainer parent;

  public AbstractDataFieldContainer ( DataFieldContainer parent, FieldContainer fieldContainer ) {
    this.parent = parent;
    this.fieldContainer = fieldContainer;
  }

  @JsonValue
  public LinkedHashMap<String, DataField> getFields () {
    return fields;
  }

  public AbstractDataFieldContainer setFields ( LinkedHashMap<String, DataField> fields ) {
    this.fields = fields;
    return this;
  }

  @Override
  public void addField ( String key, DataField field ) {
    if ( field == null ) {
      throw new IllegalArgumentException ( "Attempted to add null field for " + key );
    }
    fields.put ( key, field );
  }

  @Override
  public DataField getField ( String name ) {
    return fields.get ( name );
  }

  @Override
  public void validate ( ValidationContext context ) {
    for ( DataField field : fields.values () ) {
      field.validate ( context );
    }
  }

  @Override
  @JsonIgnore
  public Object getValue () {
    return getFields ();
  }

  @Override
  public String getName () {
    return fieldContainer.getName ();
  }

  @Override
  public void setName ( String name ) {

  }

  @Override
  public String getPath () {
    return parent.getPath () + getName () + "/";
  }

  public String getLabelPath () {
    return parent.getLabelPath () + fieldContainer.getLabel () + "/";
  }

  @Override
  public FieldContainer getFieldContainer () {
    return fieldContainer;
  }
}
