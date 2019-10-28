#!/usr/bin/env bash

echo "fk"
export SOURCE_NAME=test2
export DEST_NAME=test1

java -cp "./hive-tools-current.jar" com.netease.hivetools.apps.MetaDataMerge --s=$SOURCE_NAME --d=$DEST_NAME
