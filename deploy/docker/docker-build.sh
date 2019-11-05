mvn clean package -Dmaven.test.skip=true

version=$(date "+%Y%m%d")
docker build -t app:${version} .