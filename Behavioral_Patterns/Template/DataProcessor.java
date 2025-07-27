public abstract class DataProcessor {
    public final void processData() {
        readData();
        processDataInternally();
        writeData();
    }

    protected abstract void readData();
    protected abstract void processDataInternally();
    protected abstract void writeData();
}
