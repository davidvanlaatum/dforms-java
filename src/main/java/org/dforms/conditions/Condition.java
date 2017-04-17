package org.dforms.conditions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@JsonDeserialize ( using = Condition.ConditionDeserializer.class )
public interface Condition {
  Object evaluate ( EvaluationContext context );

  class ConditionDeserializer extends StdDeserializer<Condition> {

    private Map<String, Class<? extends Condition>> registry;

    @SuppressWarnings ( "unchecked" )
    public ConditionDeserializer () {
      super ( Condition.class );
      registry = new HashMap<> ();
      for ( JsonSubTypes.Type type : AbstractCondition.class.getAnnotation ( JsonSubTypes.class ).value () ) {
        if ( Condition.class.isAssignableFrom ( type.value () ) ) {
          registry.put ( type.name (), (Class<Condition>) type.value () );
        }
      }
    }

    @Override
    public Condition deserialize ( JsonParser jp, DeserializationContext ctxt ) throws IOException {
      Class<? extends Condition> clazz = null;

      ObjectMapper mapper = (ObjectMapper) jp.getCodec ();
      ObjectNode obj = mapper.readTree ( jp );
      Iterator<Map.Entry<String, JsonNode>> elementsIterator = obj.fields ();

      while ( elementsIterator.hasNext () ) {
        Map.Entry<String, JsonNode> element = elementsIterator.next ();
        String name = element.getKey ();
        if ( registry.containsKey ( name ) ) {
          clazz = registry.get ( name );
          break;
        }
      }

      if ( clazz == null ) {
        List<String> list = new ArrayList<> ();
        obj.fieldNames ().forEachRemaining ( list::add );
        throw ctxt.mappingException ( "No registered unique properties found for polymorphic deserialization " + list );
      }

      return mapper.treeToValue ( obj, clazz );
    }

  }
}
