package com.ags.httpserver.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Json {

    private static ObjectMapper myObjectMapper = defaultObjectMapper();

    /**
     * Creates and configures a default instance of the ObjectMapper class.
     *
     * @return A configured instance of the ObjectMapper class.
     */
    private static ObjectMapper defaultObjectMapper(){
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    /**
     * Parses a JSON string and returns a JSON node tree.
     *
     * @param jsonSrc The JSON string to parse.
     * @return A JSON node tree representing the structure and values of the JSON.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     */
    public static JsonNode parse(String jsonSrc) throws JsonProcessingException { //throws IOException
        return myObjectMapper.readTree(jsonSrc);
    }

    /**
     * Converts a JSON node into an object of the specified class.
     *
     * @param node  The JSON node to convert into a Java object.
     * @param clazz The Java class to which the JSON node will be converted.
     * @param <A>   The type of the Java object.
     * @return An instance of the specified class with the values from the JSON node.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     */
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return myObjectMapper.treeToValue(node,clazz);
    }

    /**
     * Converts a JSON node into a JSON string.
     *
     * @param node The JSON node to convert into a string.
     * @return A JSON string representing the given JSON node.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     */
    public static String stringify(Json node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    /**
     * Converts a JSON node into a pretty-printed JSON string.
     *
     * @param node The JSON node to convert into a pretty-printed string.
     * @return A pretty-printed JSON string representing the given JSON node.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     */
    public static String stringifyPretty(Json node) throws JsonProcessingException {
        return generateJson(node, true);
    }

    /**
     * Generates a JSON string from an object.
     *
     * @param o     The object to generate a JSON string from.
     * @param pretty Whether to generate a pretty-printed JSON string.
     * @return A JSON string representing the given object.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     */
    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = myObjectMapper.writer();
        if (pretty){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(o);
    }



}
