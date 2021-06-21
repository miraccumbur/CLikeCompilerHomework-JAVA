public enum BooleanOperationType {
    EQUAL_AND_EQUAL("=="),NOT_EQUAL("!="),LESS_AND_EQUAL("<="),GRATER_AND_EQUAL(">="),
    LESS("<"),GRATER(">");

    public String getText() {
        return text;
    }

    private final String text;

    BooleanOperationType(String text) {
        this.text = text;
    }

    BooleanOperationType() {
        this.text = this.toString();
    }
}
