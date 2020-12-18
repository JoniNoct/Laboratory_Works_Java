public class LineThread implements Runnable {
    public Parser line_parser;
    private int line;

    public LineThread(Parser a, int b) {
        this.line_parser = a;
        this.line = b;
    }

    public void run() {
        line_parser.content.set(line, line_parser.blockSizes(line_parser.lightParseStrings(line_parser.content.get(line))));
        line_parser.current_threads--;
    }
}