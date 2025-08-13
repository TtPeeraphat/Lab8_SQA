package stub;

import sqa.main.MassConverter;

public class MassConverterDriver {
    public double testConvertCupToGram(double value) {
        MassConverter converter = new MassConverter();
        return converter.convert(value, "cup", "gram");
    }

	
}