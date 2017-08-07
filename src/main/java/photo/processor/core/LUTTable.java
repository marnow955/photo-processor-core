package photo.processor.core;

class LUTTable {
    private int[] lutTable;

    LUTTable(LUTEquation equationValue) {
        lutTable = new int[256];
        for(int i=0; i<256; i++) {
            double value = equationValue.getLUTEqationValue(i);
            if (value> 255){
                lutTable[i] = 255;
            } else if (value < 0) {
                lutTable[i] = 0;
            } else {
                lutTable[i] = (int) value;
            }
        }
    }

    int getValue(int index) {
        return lutTable[index];
    }

    void setValue(int index, int value) {
        lutTable[index] = value;
    }
}
