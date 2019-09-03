#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL1.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! find ../src/main/java -name "*.java" -print | xargs javac -cp ../src -Xlint:none -d ../bin
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi


#
# run the program, feed commands from input1.txt file and redirect the output to the ACTUAL1.TXT
java -classpath ../bin Duke ../data/test1.txt < input1.txt > ACTUAL1.TXT

# compare the output to the expected output
diff ACTUAL1.TXT EXPECTED1.TXT
if [ $? -eq 0 ]
then
    echo ""
    echo "TEST list // Test result: PASSED"
    exit 0
else
    echo "TEST list // Test result: FAILED"
    exit 1
fi