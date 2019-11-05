FROM openjdk:8

# 设定时区
ENV TZ=Asia/Shanghai
RUN set -eux; \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime; \
    echo $TZ > /etc/timezone

WORKDIR /litemall
COPY ../litemall/litemall.jar  ./litemall.jar
COPY ../litemall/application.yml ./application.yml
COPY ./docker-entrypoint.sh .

VOLUME /var/logs

EXPOSE 8080

ENV JAVA_OPTS="-Xmx2g -Xms2g"
ENV ENV="dev"

ENTRYPOINT ["bash","docker-entrypoint.sh"]