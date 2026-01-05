# Eelf

Ever Evolving Life Forms

A Java-based creature evolution simulation using genetic algorithms and the Processing framework for visualization.

## Features

### Conditional Gene Processing

- **Robust Feature Management**: Creatures can evolve with or without specific body parts (flippers, tail, eyes, mouth)
- **Crash-Free Operation**: System handles disabled features gracefully without null pointer exceptions
- **Performance Optimized**: Skips unnecessary calculations for disabled features
- **Configurable Thresholds**: Features are enabled when their presence gene value >= 0.5

### Genetics System

- **Chromosome-Based Architecture**: Genes organized into logical chromosomes (Body, Vision, Mouth, Physics, etc.)
- **Conditional Gene Processing**: Related genes are only processed when their parent feature is enabled
- **Safe Gene Access**: Built-in null checks and default value handling

## Project Structure

- `main/Creature/BodySegments` - Contains classes representing different body parts (Head, Flippers, Tail, Eyes, Mouth, etc.)
- `main/Creature` - Core creature classes including Body, Decision Engine, Metabolism, Olfactory, Pheromones, Physics, Reproduction, Vision, Vitals
- `main/Genetics` - Genetic system classes: Genes, Chromosomes, and Genome management
- `main/Nourishments` - Food system classes for Plant and Meat nourishment
- `main` - Main application class, World simulation, Population management, and core utilities

## Requirements

- **Java 17+** (Processing library compatibility)
- **Processing Core Library** (included in `lib/`)

## Setup

1. Ensure Java 17 is installed and available
2. Use the provided setup script: `./setup_java17.sh`
3. The script will automatically:
   - Set correct Java version
   - Compile the project
   - Run tests to verify functionality

## Building

```bash
# Set Java 17 environment
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH=$JAVA_HOME/bin:$PATH

# Compile
javac -cp ".:lib/*" -d build src/main/*.java src/main/Creature/*.java src/main/Creature/BodySegments/*.java src/main/Genetics/*.java src/main/Nourishments/*.java

# Run
java -cp ".:lib/*:build" main.Main
```

## Recent Improvements

- ✅ **Fixed IndexOutOfBoundsException**: Resolved gene definition and chromosome sizing issues
- ✅ **Conditional Feature Processing**: Implemented robust system for optional creature features
- ✅ **Null Pointer Prevention**: Added comprehensive null checks for disabled body segments
- ✅ **Java 17 Compatibility**: Upgraded to work with modern Java and Processing library
- ✅ **Enhanced Gene Descriptions**: Improved gene documentation and corrected copy-paste errors
