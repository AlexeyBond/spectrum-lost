#!/bin/sh

ls android/assets/levels/c*/l*.json | grep -v -e '.*\$\.*' | wc -l
