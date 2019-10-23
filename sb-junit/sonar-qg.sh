sonar_host_name=$1
project_key=$2

# sudo apt-get install jq

apt-get update -y
apt-get install curl -y
curl --version
apt-get install -y jq
jq --version

ce_status=$(curl -s ${sonar_host_name}/api/qualitygates/project_status?projectKey=${project_key} | jq --raw-output '.projectStatus.status')

if [ ${ce_status} != "OK" ]; then
  echo "QG Script --> Quality gate is "${ce_status}
  exit 1
else
  echo "QG Script --> Quality gate is "${ce_status}
  exit 0
fi