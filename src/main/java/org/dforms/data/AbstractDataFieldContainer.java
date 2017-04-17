package org.dforms.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.FieldContainer;

import java.util.LinkedHashMap;

public abstract class AbstractDataFieldContainer implements DataFieldContainer {
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

  @JsonIgnore
  public Object getValue () {
    return getFields ();
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

  @Override
  public void parseJSON ( JsonNode node ) {
    node.fields ().forEachRemaining ( entry -> {
      final DataField field = getField ( entry.getKey () );
      field.parseJSON ( entry.getValue () );
    } );
  }
}
