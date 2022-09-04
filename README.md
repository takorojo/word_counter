# Word Counter

A very simple word counter that takes a text file, counts the number of words,
and generates an HTML document with a table of the word frequencies.

NOTE: The table is NOT sorted alphabetically. I did this rather quickly, so that
could be something I do later.

## Usage

Using the file with no arguments, like so, will result in a helpful message
popping up:

```shell
$ java -jar word_counter.jar

Usage: word_counter input_file output_file
    input_file: Text file to process
    output_file: HTML file to write to.  Created if it doesn't exist.
```

Using it with the correct number of arguments, on the other hand, will result in
the HTML file being generated if it doesn't already exist and a success message
printed to the command line:

```shell
$ java -jar word_counter.jar gettysburg.txt gettysburg.html

HTML generated and may be found here: gettysburg.html
```