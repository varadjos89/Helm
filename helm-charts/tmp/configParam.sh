#!/bin/zsh

kubectl apply -f elasticsearch-output.yml -n logging
kubectl apply -f apache-log-parser.yml -n logging