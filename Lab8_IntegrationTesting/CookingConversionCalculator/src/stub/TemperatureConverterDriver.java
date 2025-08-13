package stub;

import sqa.main.TemperatureConverter;

public class TemperatureConverterDriver {
    public double testConvertFToC(double value) {
        TemperatureConverter converter = new TemperatureConverter();
        return converter.convert(value, "fahrenheit", "celsius");
    }
}