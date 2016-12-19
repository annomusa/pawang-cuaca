#!/bin/bash

for i in {1..10} 
do
./gradlew clean assembleDebug --daemon | {
	while IFS= read -r line
	do
		lastline="$line"
	done
	echo $lastline | cut -d " " -f 3
	echo $lastline >> shellscript/cleanBuildWithDaemon.txt
}
done
