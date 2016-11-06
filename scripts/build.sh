#!/bin/bash
set -e

# You can run it from any directory.
CURRENT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
PROJECT_DIR="$CURRENT_DIR/.."

# This will compile the project, run all the tests, run the code analysis tools and assemble the jar archives.
"$PROJECT_DIR"/gradlew clean build
