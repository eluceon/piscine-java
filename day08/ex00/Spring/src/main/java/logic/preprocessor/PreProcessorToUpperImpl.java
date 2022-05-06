package logic.preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor{
    @Override
    public String translate(String msg) {
        return msg.toUpperCase();
    }
}
