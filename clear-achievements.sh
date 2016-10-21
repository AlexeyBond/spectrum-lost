#!/bin/bash

SL_DIR=$HOME/spectrum-lost
BUP_DIR=$SL_DIR/achievements-backup

mkdir $BUP_DIR

mv "$SL_DIR/achievements.json" "$BUP_DIR/$(date).json"
mv "$SL_DIR/achievements.json~" "$BUP_DIR/$(date).json~"
