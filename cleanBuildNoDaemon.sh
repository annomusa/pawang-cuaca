#!/bin/bash

for i in {1..10} 
do
./gradlew clean assembleDebug --no-daemon | {
	while IFS= read -r line
	do
		lastline="$line"
	done
	echo $lastline | cut -d " " -f 3
	echo $lastline >> shellscript/cleanBuildNoDaemon.txt
}
done
