FROM eclipse-temurin:24-jdk

WORKDIR /app

COPY out/production /app/out
COPY lib/app/lib

CMD ["java", "--module-path", "out:lib", "--module", "gateway/gateway.Main"]