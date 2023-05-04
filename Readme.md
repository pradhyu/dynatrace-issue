# pre-requisite 
```note
jdk 17 
docker 
helm
oc/kubectl
```

# considering the source code dynatrace-issue is in ~/git folder in the linux , if it's not please change the location of that where ever ~/git/dynatrace-issue is used 

# how to build and push docker image to repository of your choice?
```bash
cd ~/git/dynatrace-issue
./mvnw clean install

# Note change DOCKER_REPO TO THE REPO that you use
export DOCKER_REPO=artifactory.cotiviti.com/crs-docker/
docker build --no-cache -t ${DOCKER_REPO}dynatrace-issue:v1 .
docker push ${DOCKER_REPO}dynatrace-issue:v1 
# quick jar and docker test
./mvnw spring-boot:run
docker run -p 8080:8080 ${DOCKER_REPO}dynatrace-issue:v1 

 # Openshift deployment where you want to deploy
oc login -n rd-crs-qa -u pradhyumna.shrestha https://api.nonprodapps.cotiviti.com:6443

# deploying local chart with helm
cd ~/git/dynatrace-issue/chart
export DOCKER_REPO=artifactory.cotiviti.com/crs-docker/
helm uninstall dynatrace-issue
helm install dynatrace-issue dynatrace-issue --set image.repository=${DOCKER_REPO}dynatrace-issue --set image.tag=v1 --set "ingress.pathType=ImplementationSpecific"  --set "ingress.enabled=true" --set "ingress.host=dynatrace-issue.rd.crs.qa.apps.nonprodapps.cotiviti.com" --set "image.pullPolicy=Always"


# test ingress url
kubectl get ingress
curl dynatrace-issue.rd.crs.qa.apps.nonprodapps.cotiviti.com 

```