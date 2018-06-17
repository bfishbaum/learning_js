#!bin/bash

export proxy15440="127.0.0.1"
export CLASSPATH=$HOME"/Documents/coding_projects/learning_js/java_server/src"

function run {
	cd $CLASSPATH
	java Server
	cd ..
}

