package logic.preprocessor;

public class PreProcessorToLower implements PreProcessor{
    @Override
    public String translate(String msg) {
        return msg.toLowerCase();
    }
}
