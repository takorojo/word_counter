package com.takorojo;

import java.text.MessageFormat;

public class Main {

    public static void main(String[] args) {
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
            String input_file = args[0];
            String output_file = args[1];

            sb.append(MessageFormat.format("Input file: {0}\nOutput file: {1}", input_file, output_file));
        }

        System.out.println(sb);
    }

    private static void usage_statement(StringBuilder sb) {
        sb.append("Usage: word_counter input_file output_file\n    ");
        sb.append("input_file: Text file to process\n    ");
        sb.append("output_file: HTML file to write to.  Created if it doesn't exist.");
    }
}
