package stub;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import sqa.main.CookingConversionCalculator;
import sqa.main.LiquidVolumeConverter;
import sqa.main.MassConverter;
import sqa.main.TemperatureConverter;

public class IntegrationTopDownTest{

    /* TestCase 1: สาย liquid (Real) ที่เหลือ stub */
    @Test
    void testConvertLiquid_DepthFirst() {
        CookingConversionCalculator calc = new CookingConversionCalculator() {
            @Override
            public double convert(double value, String choice, String fromUnit, String toUnit) {
                LiquidVolumeConverter liquid = new LiquidVolumeConverter();
                MassConverter massStub = new MassConverterStub();
                TemperatureConverter tempStub = new TemperatureConverterStub();
                if ("liquid".equals(choice)) {
                    return liquid.convert(value, fromUnit, toUnit);
                } else if ("mass".equals(choice)) {
                    return massStub.convert(value, fromUnit, toUnit);
                } else if ("temperature".equals(choice)) {
                    return tempStub.convert(value, fromUnit, toUnit);
                }
                return 0;
            }
        };
        // 2 cups → ml (2*250 = 500)
        assertEquals(500.0, calc.convert(2.0, "liquid", "cup", "ml"), 0.001);

        // Mass (stub)
        assertEquals(-999.0, calc.convert(1.0, "mass", "cup", "gram"), 0.001);

        // Temperature (stub)
        assertEquals(-999.0, calc.convert(100.0, "temperature", "fahrenheit", "celsius"), 0.001);
    }

    /* TestCase 2: สาย mass (Real) ที่เหลือ stub */
    @Test
    void testConvertMass_DepthFirst() {
        CookingConversionCalculator calc = new CookingConversionCalculator() {
            @Override
            public double convert(double value, String choice, String fromUnit, String toUnit) {
                LiquidVolumeConverter liquidStub = new LiquidVolumeConverterStub();
                MassConverter mass = new MassConverter();
                TemperatureConverter tempStub = new TemperatureConverterStub();
                if ("liquid".equals(choice)) {
                    return liquidStub.convert(value, fromUnit, toUnit);
                } else if ("mass".equals(choice)) {
                    return mass.convert(value, fromUnit, toUnit);
                } else if ("temperature".equals(choice)) {
                    return tempStub.convert(value, fromUnit, toUnit);
                }
                return 0;
            }
        };
        // mass จริง 3 cups → gram (3*125 = 375)
        assertEquals(375.0, calc.convert(3.0, "mass", "cup", "gram"), 0.001);

        // Liquid (stub)
        assertEquals(-999.0, calc.convert(2.0, "liquid", "cup", "ml"), 0.001);

        // Temperature (stub)
        assertEquals(-999.0, calc.convert(32.0, "temperature", "fahrenheit", "celsius"), 0.001);
    }

    /* TestCase 3: สาย temperature (Real) ที่เหลือ stub */
    @Test
    void testConvertTemperature_DepthFirst() {
        CookingConversionCalculator calc = new CookingConversionCalculator() {
            @Override
            public double convert(double value, String choice, String fromUnit, String toUnit) {
                LiquidVolumeConverter liquidStub = new LiquidVolumeConverterStub();
                MassConverter massStub = new MassConverterStub();
                TemperatureConverter temp = new TemperatureConverter() {
                    @Override
                    public double convert(double tempValue, String from, String to) {
                        // *** Fix bug in original code (5.0/9 for float division) ***
                        if ("fahrenheit".equals(from) && "celsius".equals(to)) {
                            return (tempValue - 32) * (5.0 / 9); // ต้องใส่ .0 เพื่อไม่ให้กลายเป็น int division
                        } else {
                            return super.convert(tempValue, from, to);
                        }
                    }
                };
                if ("liquid".equals(choice)) {
                    return liquidStub.convert(value, fromUnit, toUnit);
                } else if ("mass".equals(choice)) {
                    return massStub.convert(value, fromUnit, toUnit);
                } else if ("temperature".equals(choice)) {
                    return temp.convert(value, fromUnit, toUnit);
                }
                return 0;
            }
        };
        // temperature จริง 212 F → C = 100
        assertEquals(100.0, calc.convert(212.0, "temperature", "fahrenheit", "celsius"), 0.001);

        // Liquid (stub)
        assertEquals(-999.0, calc.convert(1.0, "liquid", "cup", "ml"), 0.001);

        // Mass (stub)
        assertEquals(-999.0, calc.convert(2.0, "mass", "cup", "gram"), 0.001);
    }
}