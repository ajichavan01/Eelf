#!/bin/bash
# Script to set Java 17 environment and compile/run the project

# Set Java 17 environment
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH=$JAVA_HOME/bin:$PATH

echo "Using Java version:"
java -version
echo

echo "Using javac version:"
javac -version
echo

# Navigate to project directory
cd /Users/a786515/Documents/projects/aji-Eelf

echo "Compiling project..."
javac -cp ".:lib/*" -d build src/main/*.java src/main/Creature/*.java src/main/Creature/BodySegments/*.java src/main/Genetics/*.java src/main/Nourishments/*.java

if [ $? -eq 0 ]; then
    echo "✅ Project compiled successfully!"
    
    echo "Compiling test..."
    javac -cp ".:lib/*:build" ConditionalGeneTest.java
    
    if [ $? -eq 0 ]; then
        echo "✅ Test compiled successfully!"
        echo
        echo "Running conditional gene test..."
        java -cp ".:lib/*:build" ConditionalGeneTest
    else
        echo "❌ Test compilation failed!"
        exit 1
    fi
else
    echo "❌ Project compilation failed!"
    exit 1
fi
