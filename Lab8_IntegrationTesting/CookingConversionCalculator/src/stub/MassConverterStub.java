package stub;

import sqa.main.MassConverter;

public class MassConverterStub extends MassConverter {
    @Override
    public double convert(double massValue, String fromUnit, String toUnit) {
        // return ค่าจำลองเสมอ
        return -999.0;
    }
}