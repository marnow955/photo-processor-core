package photo.processor.core;

class ImageProbabilityDistribution {
    private double[] table;

    ImageProbabilityDistribution(LUTTable histogram, int dataSize) {
        table = new double[256];
        for (int i = 0; i < 256; i++) {
            double sum = 0.0;
            for (int j = 0; j <= i; j++) {
                sum += histogram.getValue(j);
            }
            table[i] = sum / dataSize;
        }
    }

    double getValue(int index) {
        return table[index];
    }
}
