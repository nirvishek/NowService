package utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import dto.LegalEntityDto;
import java.io.IOException;
import java.util.ArrayList;


public class ResponseParser {

    public static ArrayList<LegalEntityDto> getResult(String sapResponse) throws IOException {

        // Using jackson library for json parsing
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        // Parse the string builder into a JSON object
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(sapResponse);
        JsonNode jsonNode = mapper.readTree(parser);
        ArrayNode resultList = (ArrayNode) jsonNode.get("d").get("results");
        System.out.println("Total items returned = " + resultList.size());

        int count = 0;
        String legalEntity;

        ArrayList<LegalEntityDto> legalEntityDtos = new ArrayList<>();

        for (JsonNode node : resultList) {
            count += 1;
            System.out.println("Iterating item no. " + count + " ............................................");
            legalEntity = node.toString();
            System.out.println("Item = " + legalEntity);

            // deserializing json object to pojo
            LegalEntityDto legalEntitydto = mapper.readValue(legalEntity, LegalEntityDto.class);

            System.out.println("Adding " + legalEntitydto + " to the DTO list ..............................");
            legalEntityDtos.add(legalEntitydto);
        }

        return legalEntityDtos;



    }
}
