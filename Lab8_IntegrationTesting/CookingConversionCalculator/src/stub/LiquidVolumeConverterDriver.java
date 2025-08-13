package stub;

import sqa.main.LiquidVolumeConverter;

public class LiquidVolumeConverterDriver {
    public double testConvertCupToMl(double value) {
        LiquidVolumeConverter converter = new LiquidVolumeConverter();
        return converter.convert(value, "cup", "ml");
    }
}