package com.takorojo;

import com.takorojo.DOM.DOMTree;
import com.takorojo.DOM.nodes.HtmlTable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;

public class Main {

    public static void main(String[] args) {
        String input_file;
        String output_file;
        StringBuilder sb = new StringBuilder();

        if (args.length == 0) {
            // No arguments given.  Print usage statement
            usage_statement(sb);
        } else if (args.length == 1) {
            // Input file given.  Print error message and usage statement.
            sb.append("Please provide an output file.\n\n");

            usage_statement(sb);
        } else {
            // Both given.  Proceed with program.
            input_file = args[0];
            output_file = args[1];

            WordCounter counter = new WordCounter(input_file);

            DOMTree HtmlDocument = new DOMTree();
            HtmlTable table = new HtmlTable();
            HtmlDocument.addToBody(table);

            table.setHeaderData("Word", "Count");

            counter.count().forEach((k, v) -> {
                table.addDataRow(k, v.toString());
            });

            sb.append(MessageFormat.format("HTML generated and may be found here: {0}", output_file));

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(output_file))) {
                writer.write(HtmlDocument.getDOMTree().toString().replaceFirst("\n", ""));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(sb);
    }

    /**
     * Add a usage statement.
     *
     * @param sb StringBuilder to append the usage statement to
     */
    private static void usage_statement(StringBuilder sb) {
        sb.append("Usage: word_counter input_file output_file\n    ");
        sb.append("input_file: Text file to process\n    ");
        sb.append("output_file: HTML file to write to.  Created if it doesn't exist.");
    }
}
