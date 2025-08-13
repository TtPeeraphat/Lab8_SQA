package stub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import sqa.main.CookingConversionCalculator;


public class BottomUpIntegrationTest {

    // TC1: ทดสอบ LiquidVolumeConverter แยกเดี่ยวผ่าน driver
    @Test
    void testConvertCupToMl() {
        LiquidVolumeConverterDriver driver = new LiquidVolumeConverterDriver();
        double result = driver.testConvertCupToMl(2.0);
        assertEquals(500.0, result, 0.001); // 2 cups -> 500 ml
    }

    // TC2: ทดสอบ MassConverter แยกเดี่ยวผ่าน driver
    @Test
    void testConvertCupToGram() {
        MassConverterDriver driver = new MassConverterDriver();
        double result = driver.testConvertCupToGram(3.0);
        assertEquals(375.0, result, 0.001); // 3 cups -> 375 grams
    }

    // TC3: ทดสอบ TemperatureConverter แยกเดี่ยวผ่าน driver
    @Test
    void testConvertFToC() {
        TemperatureConverterDriver driver = new TemperatureConverterDriver();
        double result = driver.testConvertFToC(212.0);
        assertEquals(100.0, result, 0.001); // 212 F -> 100 C
    }

    // TC4: integration ทดสอบผ่าน main module
    @Test
    void testCookingConversionCalculatorIntegration() {
        CookingConversionCalculator calc = new CookingConversionCalculator();

        double liquidResult = calc.convert(2.0, "liquid", "cup", "ml");
        assertEquals(500.0, liquidResult, 0.001);

        double massResult = calc.convert(3.0, "mass", "cup", "gram");
        assertEquals(375.0, massResult, 0.001);

        double tempResult = calc.convert(212.0, "temperature", "fahrenheit", "celsius");
        assertEquals(100.0, tempResult, 0.001);
    }
}