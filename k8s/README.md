# CSYE 7374 - Spring-2020

## Team Information

| Name | NEU ID | Email Address |
| --- | --- | --- |
| Sonia Mahankali| 001476237 | mahankali.s@husky.neu.edu |
| Akash Katakam| 001400025 | katakam.a@husky.neu.edu |
| Junjie He| 001893762 | he.jun@husky.neu.edu |

## Create cluster
### run command: ansible-playbook -v setup-k8s-cluster.yml --extra-vars "@extra_vars.json" 

## Delete cluster
### run command: ansible-playbook -v delete-k8s-cluster.yml --extra-vars "@extra_vars.json"