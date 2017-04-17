package org.dforms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties ( { "name" } )
public interface NamedObject {
  String getName ();

  void setName ( String name );

  @JsonIgnore
  String getPath ();

  @JsonIgnore
  String getLabelPath ();
}
