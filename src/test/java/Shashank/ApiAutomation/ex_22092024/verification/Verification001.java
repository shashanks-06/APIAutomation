package Shashank.ApiAutomation.ex_22092024.verification;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class Verification001 {

    @Description("Verify that AssertJ is working Fine")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Shashank")
    @Link(name = "Website", url = "http://stage.example.com")
    @Issue("Auth-123")
    @Test
    public void test_verify_assertJ(){
        String name = "Shashank";
        assertThat(name).isEqualTo("Shashank").isNotEmpty().isNotBlank();

        List<String> names = Arrays.asList("John", "Jane", "Jenny");
        assertThat(names).hasSize(3).isNotNull();

        LocalDate date =  LocalDate.now();
        System.out.println(date);
        assertThat(date)
                .isAfterOrEqualTo(LocalDate.of(2021, 1, 10))
                .isBeforeOrEqualTo(LocalDate.of(2024, 12, 25))
                .isBetween(
                        LocalDate.of(2022, 4, 30),
                        LocalDate.of(2024, 10, 21)
                );

        File file = new File("TestData.json");
        assertThat(file).exists().isFile().canRead();

        Map<String, Integer> ages = new HashMap<>();
        ages.put("John", 30);
        ages.put("Kate", 26);
        assertThat(ages).hasSize(2).containsEntry("John", 30).doesNotContainValue(50);
    }
}
