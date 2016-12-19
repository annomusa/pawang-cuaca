#!/bin/bash

for i in {1..10} 
do
./gradlew assembleDebug | {
	while IFS= read -r line
	do
		lastline="$line"
	done
	echo $lastline | cut -d " " -f 3
	echo $lastline | cut -d " " -f 3 >> shellscript/assembleDebug.txt
}
done
