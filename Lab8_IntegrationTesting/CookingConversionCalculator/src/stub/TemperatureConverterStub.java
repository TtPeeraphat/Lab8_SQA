package stub;

import sqa.main.TemperatureConverter;

public class TemperatureConverterStub extends TemperatureConverter {
    @Override
    public double convert(double tempValue, String fromUnit, String toUnit) {
        // return ค่าจำลองเสมอ
        return -999.0;
    }
}