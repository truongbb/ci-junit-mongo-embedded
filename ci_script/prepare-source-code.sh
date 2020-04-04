#!/bin/bash

branchName=$1

GIT_CREDENTIAL=$(cat ~/git_credential)
REPO_PATH="/home/gitlab-runner/ci-cd-demo"
GIT_URL="https://$GIT_CREDENTIAL@gitlab.com/truongbb/ci-cd-demo.git"

cloneSourceCode() {
  echo "Cloning $GIT_URL ..."
  git clone "$GIT_URL" "$REPO_PATH"
}

prepareSourceCode() {
  if [ ! -d "$REPO_PATH" ]; then
    cloneSourceCode
  fi

  cd "$REPO_PATH"
  currentBranch=$(git rev-parse --abbrev-ref HEAD)
  if [ "$currentBranch" = "$branchName" ]; then
    git fetch --all
    git reset --hard HEAD
    git clean -f -d
    git pull
  else
    git fetch --all
    git checkout -f "$branchName"
    git pull
  fi
}

prepareSourceCode
