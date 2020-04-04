#!/bin/bash


GREEN_COLOR='\033[0;32m'
RED_COLOR='\033[0;31m'
NO_COLOR='\033[0m'

runAutoUnitTest() {
  mvn test
}

printSuccessMessage() {
  echo -e "${GREEN_COLOR}"
  echo " _   _           _   _       _                  _       ____       _      ____    ____    _____   ____"
  echo "| | | |  _ __   (_) | |_    | |_    ___   ___  | |_    |  _ \     / \    / ___|  / ___|  | ____| |  _ \\"
  echo "| | | | | '_ \  | | | __|   | __|  / _ \ / __| | __|   | |_) |   / _ \   \___ \  \___ \  |  _|   | | | |"
  echo "| |_| | | | | | | | | |_    | |_  |  __/ \__ \ | |_    |  __/   / ___ \   ___) |  ___) | | |___  | |_| |"
  echo " \___/  |_| |_| |_|  \__|    \__|  \___| |___/  \__|   |_|     /_/   \_\ |____/  |____/  |_____| |____/"
  echo -e "${NO_COLOR}"
}

printFailedMessage() {
  echo -e "${RED_COLOR}"
  echo " _   _           _   _       _                  _       _____      _      ___   _       _____   ____"
  echo "| | | |  _ __   (_) | |_    | |_    ___   ___  | |_    |  ___|    / \    |_ _| | |     | ____| |  _ \\"
  echo "| | | | | '_ \  | | | __|   | __|  / _ \ / __| | __|   | |_      / _ \    | |  | |     |  _|   | | | |"
  echo "| |_| | | | | | | | | |_    | |_  |  __/ \__ \ | |_    |  _|    / ___ \   | |  | |___  | |___  | |_| |"
  echo " \___/  |_| |_| |_|  \__|    \__|  \___| |___/  \__|   |_|     /_/   \_\ |___| |_____| |_____| |____/ "
  echo -e "${NO_COLOR}"
}

run() {
  runAutoUnitTest
  mavenUnitTestResult=$?
  if [ "$mavenUnitTestResult" = 0 ]; then
    printSuccessMessage
    exit 0
  else
    printFailedMessage
    exit 1
  fi
}

run
