package Shashank.ApiAutomation.ex_22092024.jsonPath;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static org.assertj.core.api.Assertions.assertThat;


public class JsonPath002 {
    public static void main(String[] args) {
        String response = "{\n" +
                "  \"squadName\": \"Super hero squad\",\n" +
                "  \"homeTown\": \"Metro City\",\n" +
                "  \"formed\": 2016,\n" +
                "  \"secretBase\": \"Super tower\",\n" +
                "  \"active\": true,\n" +
                "  \"members\": [\n" +
                "    {\n" +
                "      \"name\": \"Molecule Man\",\n" +
                "      \"age\": 29,\n" +
                "      \"secretIdentity\": \"Dan Jukes\",\n" +
                "      \"powers\": [\n" +
                "        \"Radiation resistance\",\n" +
                "        \"Turning tiny\",\n" +
                "        \"Radiation blast\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Madame Uppercut\",\n" +
                "      \"age\": 39,\n" +
                "      \"secretIdentity\": \"Jane Wilson\",\n" +
                "      \"powers\": [\n" +
                "        \"Million tonne punch\",\n" +
                "        \"Damage resistance\",\n" +
                "        \"Superhuman reflexes\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Eternal Flame\",\n" +
                "      \"age\": 1000000,\n" +
                "      \"secretIdentity\": \"Unknown\",\n" +
                "      \"powers\": [\n" +
                "        \"Immortality\",\n" +
                "        \"Heat Immunity\",\n" +
                "        \"Inferno\",\n" +
                "        \"Teleportation\",\n" +
                "        \"Interdimensional travel\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        JsonPath jsonPath = JsonPath.from(response);

        String sup1Name = jsonPath.getString("members[0].name");
        String sup1Power = jsonPath.getString("members[0].powers[2]");
        System.out.println(sup1Name + "-" + sup1Power);

        String sup2SecretId = jsonPath.getString("members[1].secretIdentity");
        System.out.println(sup2SecretId);

        Assert.assertEquals(jsonPath.getString("squadName"), "Super hero squad");

        assertThat(jsonPath.getString("secretBase")).isNotBlank().isNotEmpty()
                .isEqualTo("Super tower");
    }
}
