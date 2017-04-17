package org.dforms.fields;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.dforms.DataStructureBuilder;
import org.dforms.FieldContainer;
import org.dforms.NamedObject;

@JsonTypeInfo ( property = "type", use = JsonTypeInfo.Id.NAME )
@JsonSubTypes ( {
    @JsonSubTypes.Type ( value = TextInput.class, name = "text" ),
    @JsonSubTypes.Type ( value = StaticInput.class, name = "static" ),
    @JsonSubTypes.Type ( value = EnumInput.class, name = "enum" ),
    @JsonSubTypes.Type ( value = MultipleFields.class, name = "multiple" ),
    @JsonSubTypes.Type ( value = DecimalInput.class, name = "decimal" ),
    @JsonSubTypes.Type ( value = NumericInput.class, name = "number" ),
    @JsonSubTypes.Type ( value = CurrencyInput.class, name = "currency" ),
    @JsonSubTypes.Type ( value = Repeating.class, name = "repeating" ),
} )
@JsonInclude ( JsonInclude.Include.NON_EMPTY )
public interface Field extends NamedObject, DataStructureBuilder {
  @JsonIgnore
  FieldContainer getParent ();
  void setParent ( FieldContainer parent );
}
