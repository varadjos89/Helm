# helm-chartsTo get the application running, use these commands:
1. Bring up your Kubernetes cluster.(May take 5-10 minutes)
2. Spin up your RDS instance on AWS.(May take 5-10 minutes)
3. Set up your Kubernetes dashboard by using the following commands:

kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-beta4/aio/deploy/recommended.yaml

To create a user, run the below command where user.yml must be a file in your directory with user details.

kubectl apply -f user.yml

To get the login token, run

kubectl -n kubernetes-dashboard describe secret $(kubectl -n kubernetes-dashboard get secret | grep admin-user | awk '{print $1}')

kubectl proxy

Finally, to access the Kubernetes dashboard, run

http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/

To deploy your helm charts,
install helm and run the below command:
Helm install test1 ./charts 
Where 'test1' is the any desired name of the release and './charts' is the charts folder in your helm charts directory.

At this point, your dashboard should have your container ready.
Hitting the load balancer URL will let you access your application successfully.

test