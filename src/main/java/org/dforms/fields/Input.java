package org.dforms.fields;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.dforms.FieldContainer;

public abstract class Input implements Field {
  private String name;
  private String label;
  private String help;
  private String placeholder;
  private Integer size;
  private String visible;
  private String valid;
  private Boolean required;
  private FieldContainer parent;

  public Input ( String name ) {
    this.name = name;
  }

  public Input () {
  }

  @JsonIgnore
  public FieldContainer getParent () {
    return parent;
  }

  @JsonIgnore
  public void setParent ( FieldContainer parent ) {
    this.parent = parent;
  }

  @Override
  public String getName () {
    return this.name;
  }

  @Override
  public void setName ( String name ) {
    this.name = name;
  }

  @Override
  public String getPath () {
    return ( parent != null ? parent.getPath () : "" ) + getName ();
  }

  @Override
  public String getLabelPath () {
    return ( parent != null ? parent.getLabelPath () : "" ) + getLabel ();
  }

  public String getValid () {
    return valid;
  }

  public Input setValid ( String valid ) {
    this.valid = valid;
    return this;
  }

  public String getVisible () {
    return visible;
  }

  public Input setVisible ( String visible ) {
    this.visible = visible;
    return this;
  }

  public Boolean getRequired () {
    return required;
  }

  public Input setRequired ( Boolean required ) {
    this.required = required;
    return this;
  }

  @JsonProperty ( required = true )
  @JsonInclude ( JsonInclude.Include.ALWAYS )
  public String getLabel () {
    return label;
  }

  public Input setLabel ( String label ) {
    this.label = label;
    return this;
  }

  public String getHelp () {
    return help;
  }

  public Input setHelp ( String help ) {
    this.help = help;
    return this;
  }

  public String getPlaceholder () {
    return placeholder;
  }

  public Input setPlaceholder ( String placeholder ) {
    this.placeholder = placeholder;
    return this;
  }

  public Integer getSize () {
    return size;
  }

  public Input setSize ( Integer size ) {
    this.size = size;
    return this;
  }
}
