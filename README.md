# Harmonic Analysis Tool

This Java program performs harmonic analysis on a given musical scale and generates chords. The results are printed out to the screen via the command line.

## Features

- Harmonic analysis on a given scale.
- Generation of chords based on the harmonic analysis.
- Prints the results to the command line.

\# How to Use

The program accepts a 12-character string as an argument, where each character is either a 0 or a 1. Each character represents a note in the 12-tone musical system, with a 1 indicating the presence of the note in the scale and a 0 indicating its absence.

For example, to analyze a major scale (which in this system is represented as 101011010101), you would run the program like this:

```bash
java HarmonicAnalysis 101011010101
```

If no argument is provided, the program defaults to analyzing a major scale.

## Code Overview

The `HarmonicAnalysis` class contains the main logic of the program. It includes:

- Dictionaries for musical notation and chord intervals.
- Methods for validating user input, translating input to a scale, and starting the analysis.
- Methods for finding the locations of notes in the scale, finding available chords, and printing the results.

## Future Improvements

This is a basic version of the tool, and there's room for enhancements. Future versions could include more complex harmonic analysis features, a graphical user interface, or integration with music composition software.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the terms of the MIT license.
